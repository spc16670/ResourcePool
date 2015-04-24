package uk.co.kfis.migrator;

import uk.co.kfis.migrator.test.Test;

public class Main {

	public static void main(String[] args) {
		Test t = new Test();
		//t.printMeta("85");
		//t.printUDLMeta();
		//t.quoteStore();
		//t.retrieveQuote();
		//t.clientSearch();
		//t.getPCSchema();
		t.recall();
		
		// The capacity of both the IN and OUT queues.  
		//int queueCapacity = 100;
		
		/*
		 * The  queue shared by the executor with the thread that extracts
		 * the data required by the tasks from the database;
		 */
		//BlockingQueue<Runnable> qIN = new ArrayBlockingQueue<Runnable>(queueCapacity);
		
		/*
		 * The executor runs the Runnable objects produced by the dataExtrctor.
		 * coreSize = 4, maxSize = 10, keepAliveThread = 20s, queue = qIN
		 */
		//TaskExecutor executor = new TaskExecutor(100, 100, 20, qIN);
		//executor.start();

	}

}
