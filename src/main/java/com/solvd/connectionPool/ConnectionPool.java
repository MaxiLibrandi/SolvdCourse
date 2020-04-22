package com.solvd.connectionPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;;

public class ConnectionPool {
	private static ConnectionPool cp;
	private BlockingQueue<String> connections;
	public static final Integer POOL_SIZE = 5;
	
	private ConnectionPool(){
		init();
	};
	
	public static synchronized ConnectionPool getInstance(){
		if(cp == null){
			cp = new ConnectionPool();
		}
		return cp;
	}
	
	private void init() {
		connections = new LinkedBlockingQueue<String>(POOL_SIZE);
		for(int i = 0; i < POOL_SIZE ; i++) {
			connections.add("Connection " + (i+1));
		}
	}

	public String getConnection() throws InterruptedException {
		return connections.take();
	}
	
	public void releaseConnection(String connectionReleased) {
		connections.offer(connectionReleased);
	}
}
