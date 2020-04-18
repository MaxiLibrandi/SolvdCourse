package com.solvd.linkedList;

public class LinkedList {
	private Node head;
	private Node tail;
	
	public LinkedList() {
		head = null;
		tail = null;
	}
	
	//ADD TO THE END OF THE LINKED LIST
	public void add(Object obj) {
		Node newNode = new Node(obj);
		if (head == null) {
			head = newNode;
		}else {
			tail.setNext(newNode);
			newNode.setPrevious(tail);
		}
		tail = newNode;
	}
	
	//ADD IN THE INDEX POSITION OF THE LINKED LIST
	public void add(Integer index, Object obj) throws IndexOutOfBoundsException{
		Node newNode = new Node(obj);
		if(index == 0) {
			if(head == null) { //THE NEW NODE IS THE ONLY ONE
				head = newNode;
				tail = newNode;
			}
			else { //THE NEW NODE GOES AT THE BEGINNING OF THE LIST
				newNode.setNext(head);
				head.setPrevious(newNode);
				head = newNode;
			}
		}
		else {
			Integer count = 0;
			Node it = head;
			while (count < index && it != null) {
				it = it.getNext();
				count++;
			}
			if (it == null) { 
				if(count == index) { // THE NEW NODE GOES AT THE END OF THE LIST
					tail.setNext(newNode);
					newNode.setPrevious(tail);
					tail = newNode;
				}
				else { //INDEX > LIST.SIZE()
					throw new IndexOutOfBoundsException("Trying to add object " + obj.toString() + " at index " + index);
				}
			}
			else{ 
				Node previousToNewNode = it.getPrevious();
				Node nextToNewNode = it;
				previousToNewNode.setNext(newNode);
				newNode.setPrevious(previousToNewNode);
				nextToNewNode.setPrevious(newNode);
				newNode.setNext(nextToNewNode);
			}
		}
	}
	
	public String iterateFirstToLast() {
		String out = "";
		Node it = head;
		while(it != null) {
			out += it.getValue().toString() + " | ";
			it = it.getNext();
		}
		return out;
	}
	
	public String iterateLastToFirst() {
		String out = "";
		Node it = tail;
		while(it != null) {
			out += it.getValue().toString() + " | ";
			it = it.getPrevious();
		}
		return out;
	}
}
