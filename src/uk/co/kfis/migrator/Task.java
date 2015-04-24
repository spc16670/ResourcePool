package uk.co.kfis.migrator;
import java.util.concurrent.BlockingQueue;


public class Task implements Runnable {
	
	Object data;
	BlockingQueue<Result> qOUT;
	
	public Task(Object data, BlockingQueue<Result> qOUT) {
		this.data = data;
		this.qOUT = qOUT;
	}

	@Override
	public void run() {
		boolean beenRun = false;
		while (!beenRun) {
			try {
				System.out.println("Running data object: " + data);
				Thread.sleep(5000);
				try {
					qOUT.add(new Result(data));
					beenRun = true;
				} catch (IllegalStateException queuefullE) {
					System.out.println("Queue is full, blocking task: " + data.toString());
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}

	}

}
