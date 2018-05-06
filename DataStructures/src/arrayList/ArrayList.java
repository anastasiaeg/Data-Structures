package arrayList;

import java.util.AbstractList;

/**
 * Implementation of a generic ArrayBasedList
 * @author Anastasia Egorova
 *
 * @param <E> for generic element type
 */
public class ArrayList<E> extends AbstractList<E> {
	/* Array of generic elements*/
	private E[] data;
	/* Size of the current array*/
	private int size;
	/* Default capacity of a new arraybasedlist*/
	private static final int CAPACITY = 10;

	/* Default constructor of ArrayBasedList uses default size*/
	public ArrayList() {
		this(CAPACITY);
	}

	/**
	 * Constructor of an ArrayBasedList, uses cap as a size of a new array
	 * @param cap size of a new array
	 */
	@SuppressWarnings("unchecked")
	public ArrayList(int cap) {
		data = (E[]) new Object[cap];
	}
	
	/**
	 * Adds a new element to the end of the ArrayBasedList
	 * @param element to add to the array
	 */
	public boolean add(E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (size + 1 >= data.length) resizeArray();
		data[size++] = element;
		return true;
	}
	
	/**
	 * Adds a new element to a chosen index in the ArrayBasedList
	 * @param index place where you want to add a new element
	 * @param element new element to add
	 */
	public void add(int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (size + 1 >= data.length) resizeArray();
		for (int i = size; i > index; i--) {
			data[i] = data[i - 1];
		}
		data[index] = element;
		size++;
	}

	/**
	 * Sets a chosen element in the array to a chosen value
	 * @param index of an element to change
	 * @param element value to change to
	 */
	public E set(int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		E save = data[index];
		data[index] = element;
		return save;
	}
	
	/**
	 * Removes an element at chosen index
	 * @param index of the element to remove
	 */
	public E remove(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		E save = data[index];
		for (int i = index; i < size - 1; i++) {
			data[i] = data[i + 1];
		}
		size--;
		return save;
	}
	
	
	/**
	 * Resizes array
	 */
	@SuppressWarnings("unchecked")
	private void resizeArray() {
		int newCap = data.length * 2 + 1;
		E[] output = (E[]) new Object[newCap];
		for (int i = 0; i < size; i++) {
			output[i] = data[i];
		}
		data = output;
	}
	
	/**
	 * Get an element at a chosen index
	 * @param index to get
	 */
	@Override
	public E get(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		return data[index];
	}

	/**
	 * Gets the current size of the ArrayBasedlist
	 */
	@Override
	public int size() {
		return size;
	}
}
