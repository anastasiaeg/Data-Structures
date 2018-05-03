package tree;

public class Node<T extends Comparable<T>> {
	private T data;
	private Node<T> left;
	private Node<T> right;
	private Node<T> parent;
	
	public Node(T data) {
		this(data, null, null, null);
	}
	
	public Node(T data, Node<T> left, Node<T> right, Node<T> parent) {
		this.data = data;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @return the left
	 */
	public Node<T> getLeft() {
		return left;
	}

	/**
	 * @return the parent
	 */
	public Node<T> getParent() {
		return parent;
	}

	/**
	 * @return the right
	 */
	public Node<T> getRight() {
		return right;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(Node<T> left) {
		this.left = left;
		if (left != null) left.parent = this;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(Node<T> right) {
		this.right = right;
		if (right != null) right.parent = this;
	}

}
