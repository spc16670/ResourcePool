package uk.co.kfis.migrator;
import java.util.concurrent.BlockingQueue;


public class ResultPersister implements Runnable {
	
	DataExtractor dataExtractor;
	TaskExecutor taskExecutor;
	BlockingQueue<Result> queue;
	
	private volatile boolean running = true;
	
	public ResultPersister(TaskExecutor taskExecutor,DataExtractor dataExtractor, BlockingQueue<Result> queue) {
		this.dataExtractor = dataExtractor;
		this.taskExecutor = taskExecutor;
		this.queue = queue;
	}

	@Override
	public void run() {
		while (running) {
			try {
				try {
					if (!dataExtractor.isFinished()) {
						take();
					} else if (dataExtractor.isFinished() && !taskExecutor.getQueue().isEmpty()) {
						take();
					} else if (dataExtractor.isFinished() && taskExecutor.getQueue().isEmpty() && !queue.isEmpty()) {
						take();
					} else if (dataExtractor.isFinished() && taskExecutor.getQueue().isEmpty() && queue.isEmpty()) {
						System.out.println("FINISHED");
						taskExecutor.shutdown();
					}
				} finally {
				}
			} catch (InterruptedException e) {
				System.out.println("ResultPersister has been interrupted");
				//e.printStackTrace();
			}
		}
	}
	
	private void take() throws InterruptedException {
		Result result = queue.take();
		System.out.println("PERSISTED: " + result.getData());
	}
	
	/**
	 * No need to synchronise the method as running is volatile.
	 */
	public void shutdown() {
		running = false;
		Thread.currentThread().interrupt();
		System.out.println("Shutting ResultsPersister down");
	}

}
