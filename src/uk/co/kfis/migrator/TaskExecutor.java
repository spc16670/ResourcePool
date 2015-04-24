package uk.co.kfis.migrator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TaskExecutor extends ThreadPoolExecutor {

	private boolean isPaused;
	private ReentrantLock pauseLock = new ReentrantLock();
	private Condition unpaused = pauseLock.newCondition();

	DataExtractor dataExtractor;
	ResultPersister resultPersister;
	
	/**
	 * 
	 * @param coreSize
	 * @param maxSize
	 * @param keepAlive
	 * @param qIN
	 */
	public TaskExecutor(int coreSize, int maxSize, int keepAlive, BlockingQueue<Runnable> qIN) {
		super(coreSize, maxSize, keepAlive, TimeUnit.SECONDS, qIN);
	}
	
	public void start() {
		allowCoreThreadTimeOut(true);
		prestartAllCoreThreads();
		/*
		 *  The queue shared by the executor with a thread that saves the
		 *  results to a database.
		 *  
		 *  The thread needs to be able to keep up with saving otherwise
		 *  it is going to slow down the executor.
		 */
		int queueCapacity = this.getQueue().remainingCapacity();
		BlockingQueue<Result> qOUT = new ArrayBlockingQueue<Result>(queueCapacity);
		
		// Thread that extracts the data from a database
		dataExtractor = new DataExtractor(this, qOUT);
		new Thread(dataExtractor).start();

		// Thread that persists the results to a database
		resultPersister = new ResultPersister(this,dataExtractor,qOUT);
		new Thread(resultPersister).start();
	}

	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		pauseLock.lock();
		try {
			while (isPaused) {
				unpaused.await();
			}
		} catch (InterruptedException ie) {
			t.interrupt();
		} finally {
			pauseLock.unlock();
		}
	}

	public void pause() {
		pauseLock.lock();
		try {
			isPaused = true;
		} finally {
			pauseLock.unlock();
		}
	}

	public void resume() {
		pauseLock.lock();
		try {
			isPaused = false;
			unpaused.signalAll();
		} finally {
			pauseLock.unlock();
		}
	}

	@Override
	public void shutdown() {
		dataExtractor.shutdown();
		super.shutdown();
		resultPersister.shutdown();
	}
	
	
}
