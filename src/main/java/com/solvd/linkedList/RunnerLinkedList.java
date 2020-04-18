package com.solvd.linkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RunnerLinkedList {
	private final static Logger LOGGER = LogManager.getLogger(RunnerLinkedList.class);

	public static void main(String[] args) {
		
		LinkedList list = new LinkedList();
		
		for(int i = 0; i <50 ; i++) {
			list.add(i);
		}
		
		try {
			list.add(25, 0);
		} catch (IndexOutOfBoundsException e) {
			LOGGER.error(e);
		}
		
		try {
			list.add(50, 50);
		} catch (IndexOutOfBoundsException e) {
			LOGGER.error(e);
		}
		
		try {
			list.add(100, 100);
		} catch (IndexOutOfBoundsException e) {
			LOGGER.error(e);
		}
		
		LOGGER.info("ITERATE FROM FIRST TO LAST: " + list.iterateFirstToLast());
		
		LOGGER.info("ITERATE FROM LAST TO FIRST: " + list.iterateLastToFirst());
	}

}
