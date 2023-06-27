package com.fmt;

/**
 * A helper class to create a node that supports for {@link SortedLinkedList}
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 *
 * @param <T>
 */
public class Node<T> {

	private Node<T> next;
	private T element;

	public Node(T element) {
		this.element = element;
		this.next = null;
	}
	
	public T getElement() {
		return this.element;
	}

	public Node<T> getNext() {
		return this.next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}
}
