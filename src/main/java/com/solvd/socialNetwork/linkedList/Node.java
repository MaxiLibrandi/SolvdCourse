package com.solvd.socialNetwork.linkedList;

public class Node {
	private Object value;
	private Node previous;
	private Node next;
	
	public Node() {
		
	}
	
	public Node(Object value) {
		this.value = value;
		previous = null;
		next = null;
	}
	
	public Object getValue() {
		return this.value;
	}
	
	public Node getNext() {
		return next;
	}
	
	public Node getPrevious() {
		return previous;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public void setPrevious(Node previous) {
		this.previous = previous;
	}
	
	public void setNext(Node next) {
		this.next = next;
	}
}
