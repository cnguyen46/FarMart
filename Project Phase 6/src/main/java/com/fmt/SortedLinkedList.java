package com.fmt;

import java.util.Comparator;
import java.util.Iterator;

/**
 * A linked list ADT to portray the functions of one linked list.
 * <p>
 * Also, its implementation is to support each of the following three orders:
 * <li>Last name/first name of the customer in alphabetic ordering
 * <li>Total value of the invoice highest-to-lowest
 * <li>An ordering that groups all invoices by their store and then by the sales
 * person by last name/first name.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 *
 * @param <T>
 */
public class SortedLinkedList<T> implements Iterable<T> {

	private Node<T> head;
	private int size;
	private Comparator<T> cmp;

	public SortedLinkedList(Comparator<T> cmp) {
		this.cmp = cmp;
		this.head = null;
		this.size = 0;
	}

	/**
	 * This is a private helper method that returns a {@link Node} corresponding to
	 * the given index.
	 * 
	 * @param index
	 * @return
	 */
	private Node<T> getNode(int index) {
		if (index < 0 || index >= this.size) {
			throw new IllegalArgumentException("Index: " + index + " is out of bounds");
		}
		Node<T> current = this.head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current;
	}

	/**
	 * This method returns the element stored at the given index.
	 * 
	 * @param index
	 * @return
	 */
	public T getElement(int index) {
		if (index < 0 || index >= this.size) {
			throw new IllegalArgumentException("Invalid index: " + index);
		}
		return this.getNode(index).getElement();
	}

	/**
	 * This private method adds the given elements to the a linked list at a given
	 * index. It is a helper method for {@link SortedLinkedList#addElement}
	 * <p>
	 * It indicates to increase the size of linked list.
	 * 
	 * @param element
	 * @param index
	 */
	private void addElementAtIndex(T element, int index) {
		if (index < 0 || index > this.size) {
			throw new IllegalArgumentException("Index: " + index + " is out of bounds");
		}
		Node<T> newNode = new Node<T>(element);
		
		// Add element at the begin of a linked list
		if (index == 0) {
			newNode.setNext(this.head);
			this.head = newNode;
			this.size++;
			return;
		}
		
		// Add element, where new node points to the current node,
		// and the previous node points to the new node
		Node<T> previous = this.getNode(index - 1);
		newNode.setNext(previous.getNext());
		previous.setNext(newNode);
		this.size++;
	}

	/**
	 * This method adds the given elements instance to a linked list with sorting
	 * order.
	 * 
	 * @param element
	 */
	public void addElement(T element) {

		Node<T> newNode = new Node<T>(element);
		Node<T> current = this.head;

		int count = 0;
		
		// If the linked list is empty, add element at the beginning of the linked list
		// If not, check the element of current node and new node, then add element
		if (this.head == null) {
			addElementAtIndex(element, 0);
		} else {
			while (current != null && this.cmp.compare(current.getElement(), newNode.getElement()) < 0) {
				current = current.getNext();
				count++;
			}
			addElementAtIndex(element, count);
		}
	}

	/**
	 * This method removes the element from the given index indices start at 0.
	 * Implicitly, the remaining elements' indices are reduced.
	 * 
	 * @param index
	 */
	public void removeElement(int index) {
		if (index < 0 || index >= this.size) {
			throw new IllegalArgumentException("Index: " + index + " is out of bounds");
		} else if (index == 0) {
			this.head = this.head.getNext();
			this.size--;
		} else {
			Node<T> previous = this.getNode(index - 1);
			Node<T> current = previous.getNext();
			previous.setNext(current.getNext());
			this.size--;
		}
	}
	
	/**
	 * This function returns the size of the list, the number of elements currently
	 * stored in it.
	 * 
	 * @return
	 */
	public int getSize() {
		return this.size;
	}
	
	@Override
	public Iterator<T> iterator() {

		Iterator<T> iterator = new Iterator<>() {
			Node<T> current = head;

			@Override
			public boolean hasNext() {
				if (current != null) {
					return true;
				} else {
					return false;
				}
			}

			@Override
			public T next() {
				T element = current.getElement();
				current = current.getNext();
				return element;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("Not yet implemented.");
			}
		};
		return iterator;
	}
}
