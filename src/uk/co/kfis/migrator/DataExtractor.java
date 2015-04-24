package uk.co.kfis.migrator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

public class DataExtractor implements Runnable {
	
	Executor executor;
	BlockingQueue<Result> qOUT;

	private volatile boolean finished = false;
	
	private volatile boolean running = true;
	
	/**
	 * 
	 * This class extracts data from a database and uses it to create Runnable
	 * tasks that are submitted to the Executor.
	 * 
	 * @param executor
	 * @param qOUT
	 */
	public DataExtractor(Executor executor, BlockingQueue<Result> qOUT) {
		this.executor = executor;
		this.qOUT = qOUT;
	}

	@Override
	public void run() {
		int i = 500;
		while (running) {
			while (i>0) {
				try {
					String data = "Some data from the DB " + i;
					Task task = new Task(data,qOUT);
					executor.execute(task);
					i--;
				} catch (RejectedExecutionException queueFullEx) {
					try {
						// internal queue is full therefore preventing the execution
						System.out.println("DataExtractor sleeps waiting the executor to catch up");
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			finished = true;	
		}
	}
	
	/**
	 * No need to synchronise the method as running is volatile.
	 */
	public void shutdown() {
		running = false;
		System.out.println("Shutting DataExtractor down");
	}
	
	/**
	 * No need to synchronise the method as finished is volatile.
	 */
	public boolean isFinished() {
		return this.finished;
	}

}
