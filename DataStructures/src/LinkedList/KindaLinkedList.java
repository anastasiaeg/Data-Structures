package LinkedList;

import java.util.HashMap;
import java.util.HashSet;

public class KindaLinkedList<E> {
	/** ListNode reference to the front of the list*/
	private ListNode front;
	/** Size of the list*/
	private int size;
	
	/**
	 * Constructor for the list
	 */
	public KindaLinkedList() {
		front = null;
		size = 0;
	}
	
	/**
	 * Returns true if the size is 0
	 * @return true if the size is 0
	 */
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Removes an item in the given index
	 * @param index index of the item to remove
	 * @return the item just removed
	 */
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		size--;
		if (index == 0) {
			E data = front.data;
			front = front.next;
			return data;
		} else {
			return front.remove(index);
		}
	}
	
	/**
	 * Prints out the list
	 */
	public String toString() {
		return "[" + front.toString() + "]";
	}

	/**
	 * Returns size of the list
	 * @return size of the list
	 */
	public int size() {
		return size;
	}

	/**
	 * Sets a new element in the chosen index
	 * @param index index to set
	 * @param data data to change the previous to
	 * @return an element that has been changed
	 */
	public E set(int index, E data) {
		if (front == null) {
			throw new IndexOutOfBoundsException();
		}
		if (data == null) throw new IllegalArgumentException();
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}		
		if (index == 0) {
			E data1 = front.data;
			front.data = data;
			return data1;
		} else {
			return front.set(index, data);
		}
	}

	/**
	 * Gets an element in the chosen index
	 * @param index chosen index
	 * @return the data element of the chosen index
	 */
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		} 
		if (isEmpty()) {
			throw new IllegalStateException();
		}
		if (index == 0) {
			return front.data;
		} else {
			return front.get(index);
		}
	}

	/**
	 * Adds a new value in the end of the list
	 * @param value value to add
	 * @return true if the value was added
	 */
	public boolean add(E value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		try {
			if (front == null) {
				front = new ListNode(value);
			} else {
				front.add(value);
			}
			size++;
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Adds an element to a chosen index
	 * @param index index to add the data to 
	 * @param data data to add to the index
	 */
	public void add(int index, E data) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (data == null) throw new IllegalArgumentException();
		if (index == 0) {
			front = new ListNode(data, front);
		} else {
			front.add(index, data);
		}
		size++;
	}
	
	public void removeDups() {
		ListNode current = front;
		HashSet<E> set = new HashSet<E>();
		ListNode prev = null;
		while (current != null) {
			if (set.contains(current.data)) {
				prev.next = current.next;
				size--;
			} else {
				set.add(current.data);
				prev = current;
			}
			current = current.next;
		}
	}
	/**
	 * Element of the linked list
	 * @author Anastasia Egorova and Brian Hogan
	 */
	private class ListNode {
		/** Data of the element*/
		public E data;
		/** Link to a next element*/
		public ListNode next;
		
		/**
		 * Constructor of the element
		 * @param data data of the element
		 */
		public ListNode(E data) {
			this(data, null);
		}
		
		/**
		 * Constructor of the element with link to next element
		 * @param data data of the element 
		 * @param next link to the next element
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
		
		/**
		 * Adds the element to the end of the array
		 * @param element element to add
		 */
		private void add(E element) {
			if (next == null) {
				next = new ListNode(element);
			} else {
				next.add(element);
			}
		}
		
		/**
		 * Adds the element to the chosen index
		 * @param index chosen index
		 * @param element element to add
		 */
		private void add(int index, E element) {
			if (index == 1) {
				next = new ListNode(element, next);
			} else {
				next.add(index - 1, element);
			}
		}
		
		/**
		 * Gets the element in chosen index
		 * @param index chosen index
		 * @return the element in the index
		 */
		private E get(int index) {
			if (index == 0) {
				return data;
			} else {
				return next.get(index - 1);
			}
		} 
		
		/**
		 * Removes the element in chosen index
		 * @param index chosen index
		 */
		private E remove(int index) {
			if (index == 1) {
				E data1 = next.data;
				next = next.next; 
				return data1;
			} else {
				return next.remove(index - 1); 
			}
		}
		
		/**
		 * Sets the element in chosen index to a new data
		 * @param index chosen index
		 * @param data new data
		 */
		private E set(int index, E data) {
			if (index == 1) {
				E data1 = next.data;
				next = new ListNode(data, next.next);
				return data1;
			} else {
				return next.set(index - 1, data);
			}
		}
		
		/**
		 * Prints out all the elements
		 */
		private String print() {
			String output = "" + data;
			if (next == null) {
				return output;
			} else {
				return next.print() + ", " + output;
			}
		}
	}

}