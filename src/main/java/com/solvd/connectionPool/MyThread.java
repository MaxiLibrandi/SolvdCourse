package com.solvd.connectionPool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyThread implements Runnable{
	private static final Logger LOGGER = LogManager.getLogger(MyThread.class);
	private String name;
	private ConnectionPool cp;
	
	public MyThread(String threadName, ConnectionPool cp) {
		this.name = threadName;
		this.cp = cp;
	}
	
	@Override
	public void run() {
		String myConnection = "";
		
		try {
			myConnection = cp.getConnection();
		} catch (InterruptedException e) {
			LOGGER.error(e);
		}
		
		LOGGER.info("Thread " + name + " got connection " + myConnection);
		
		/*
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			LOGGER.error(e);
		}
		
		cp.releaseConnection(myConnection);
		*/
	}
}
