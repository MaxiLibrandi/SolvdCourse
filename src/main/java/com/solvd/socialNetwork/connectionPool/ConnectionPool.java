package com.solvd.socialNetwork.connectionPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;;

public class ConnectionPool {
	private static ConnectionPool cp;
	private BlockingQueue<String> connections;
	private Integer connectionsCount;
	public static final Integer POOL_SIZE = 5;
	
	private ConnectionPool(){
		connections = new LinkedBlockingQueue<String>(POOL_SIZE);
		connectionsCount = 0;
	};
	
	public static ConnectionPool getInstance(){
		if(cp == null){
			synchronized (ConnectionPool.class){ 
				if(cp == null)
				{
					cp = new ConnectionPool();
				}
			}
		}
		return cp;
	}
	
	private void initConnection() {
		connections.add("Connection " + (++connectionsCount));
	}

	public String getConnection() throws InterruptedException {
		if(connections.size() == 0 && connectionsCount < POOL_SIZE ) {
			synchronized (ConnectionPool.class){
				if(connections.size() == 0 && connectionsCount < POOL_SIZE ) {
					initConnection();
				}
			}
		}
		return connections.take();
	}
	
	public void releaseConnection(String connectionReleased) {
		connections.offer(connectionReleased);
	}
}
