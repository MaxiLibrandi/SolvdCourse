package com.solvd.socialNetwork.connectionPool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class RunnerConnectionPool {
	private static final Logger LOGGER = LogManager.getLogger(RunnerConnectionPool.class);
	public static final Integer POOL_SIZE = 7;
	
	public static void main(String[] args) {
		
		ConnectionPool cp = ConnectionPool.getInstance();
		
		ThreadPoolExecutor executorPool = new ThreadPoolExecutor(POOL_SIZE, POOL_SIZE, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		
		for (int i = 0; i < POOL_SIZE; i++) {
			executorPool.execute(new MyThread("TH" + (i+1), cp));
		}
		
		executorPool.shutdown();
		
		try {
			executorPool.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			LOGGER.error(e);
		}
		
		if(executorPool.isTerminated()) {
			LOGGER.info("All threads finished");
		}else {
			LOGGER.info("Not all threads finished");
		}
	}
}
