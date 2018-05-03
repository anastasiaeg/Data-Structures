package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/**
 * Implementation of Binary Search Tree with nodes that have parents and number of nodes
 * @author Anastasia Egorova
 *
 * @param <T> generic type that extends Comparable
 */
public class BinarySearchTree<T extends Comparable<T>> {
	
	private Node<T> root;
	private int nodes;
	
	public BinarySearchTree() {
		root = null;
		nodes = 0;
	}
	
	public Node<T> delete(T data) {
		return null;
	}
	public void insert(T data) {
		if (data == null) {
			throw new IllegalArgumentException("Inserted data cannot be null");
		}
		if (root == null) {
			nodes++;
			root = new Node<T>(data);
			return;
		}
		insert(root, data);
	}
	
	private void insert(Node<T> curr, T data) {
		if (curr.getData().compareTo(data) < 0) {
			if (curr.getRight() != null) {
				insert(curr.getRight(), data);
			} else {
				curr.setRight(new Node<T>(data));
				nodes++;
			}
		} else if (curr.getData().compareTo(data) > 0) {
			if (curr.getLeft() != null) {
				insert(curr.getLeft(), data);
			} else {
				curr.setLeft(new Node<T>(data));
				nodes++;
			}
		} else {
			return;
		}
	}
	
	public Node<T> lookUp(T data) {
		if (root == null || data == null) {
			throw new IllegalArgumentException("Tree is empty or can't lookup null.");
		}
		return lookUp(root, data);
	}
	
	private Node<T> lookUp(Node<T> curr, T data) {
		if (curr.getData().compareTo(data) < 0) {
			if (curr.getRight() != null) {
				return lookUp(curr.getRight(), data);
			} else {
				return null;
			}
		} else if (curr.getData().compareTo(data) > 0) {
			if (curr.getLeft() != null) {
				return lookUp(curr.getLeft(), data);
			} else {
				return null;
			}
		} else {
			return curr;
		}
	}
	
	public T remove(T data) {
		if (root == null || data == null) {
			throw new IllegalArgumentException("Tree can't be empty, argument can't be null");
		}
		return remove(lookUp(data), data);
	}
	
	/**
	 * Somewhat strugglesome because we need to keep track of parents
	 * @param curr Current Node
	 * @param data Data of the Element we want to remove
	 * @return returns data of the removed element
	 */
	private T remove(Node<T> curr, T data) {
		if (curr.getLeft() == null && curr.getRight() == null) {
			if (curr.equals(root)) {
				root = null;
				nodes = 0;
				return data;
			}
			if (curr.getParent().getLeft().equals(curr)) {
				nodes--;
				curr.getParent().setLeft(null);
			} else {
				nodes--;
				curr.getParent().setRight(null);
			}
		} else if (curr.getLeft() == null || curr.getRight() == null) {
			if (curr.equals(root)) {
				nodes--;
				root = curr.getLeft().equals(null) ? curr.getRight() : curr.getLeft();
				return data;
			}
			if (curr.getParent().getLeft().equals(curr)) {
				nodes--;
				curr.getParent().setLeft(curr.getLeft() == null ? curr.getRight() : curr.getLeft());
			} else {
				nodes--;
				curr.getParent().setRight(curr.getLeft() == null ? curr.getRight() : curr.getLeft());
			}
		} else {
			//Should probably use my remove algorithm to delete the smallest thing, but this is faster
			Node<T> leftMost = curr.getRight();
			while (leftMost.getLeft() != null) {
				leftMost = leftMost.getLeft();
			}
			curr.setData(leftMost.getData());
			leftMost.getParent().setLeft(null);
			nodes--;
		}
		return data;
	}
	
	public int getNodes() {
		return this.nodes;
	}
	
	/**
	 * Breadth first search for finding level
	 * @return
	 */
	public String levelOrderIterative() {
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		queue.add(root);
		StringBuilder s = new StringBuilder(root.getData().toString() + " ");
		while(!queue.isEmpty()) {
			Node<T> curr = queue.poll();
			if (curr.getLeft() != null) {
				queue.add(curr.getLeft());
				s.append(curr.getLeft().getData().toString() + " ");
			}
			if (curr.getRight() != null) {
				queue.add(curr.getRight());
				s.append(curr.getRight().getData().toString() + " ");
			}
		}
		return s.toString();
	}
	
	/**
	 * Recursive levelOrder
	 */
	public String levelOrderRecursive() {
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		queue.add(root);
		StringBuilder s = new StringBuilder(root.getData().toString() + " ");
		return levelOrderRecursive(s, queue);
	}
	
	private String levelOrderRecursive(StringBuilder s, Queue<Node<T>> queue) {
		if (queue.isEmpty()) return s.toString();
		Node<T> curr = queue.poll();
		if (curr.getLeft() != null) {
			queue.add(curr.getLeft());
			s.append(curr.getLeft().getData().toString() + " ");
		}
		if (curr.getRight() != null) {
			queue.add(curr.getRight());
			s.append(curr.getRight().getData().toString() + " ");
		}
		return levelOrderRecursive(s, queue);
	}
	
	/**
	 * In order recursive algorithm for binary tree
	 * In this case, it sorts. 
	 * @return
	 */
	public String inOrderRecursive() {
		if (root == null) return "";
		StringBuilder s = new StringBuilder();
		inOrderRecursive(s, root);
		return s.toString();
	}
	
	private void inOrderRecursive(StringBuilder s, Node<T> curr) {
		if (curr.getLeft() != null) {
			inOrderRecursive(s, curr.getLeft());
		}
		s.append(curr.getData().toString() + " ");
		if (curr.getRight() != null) {
			inOrderRecursive(s, curr.getRight());
		}
	}
	
	/**
	 * In order iterative
	 * @return
	 */
	public String inOrderIterative() {
		if (root == null) return "";
		Stack<Node<T>> stack = new Stack<Node<T>>();
		StringBuilder s = new StringBuilder();
		Node<T> curr = root;
		Boolean done = false;
		while (!done) {
			if (curr != null) {
				stack.push(curr);
				curr = curr.getLeft();
			} else {
				if (!stack.isEmpty()) {
					curr = stack.pop();
					s.append(curr.getData().toString() + " ");
					curr = curr.getRight();
				} else {
					done = true;
				}
			}
		}
		return s.toString();
	}
	
	/**
	 * Post order recursive algorithm for binary tree
	 * @return
	 */
	public String postOrderRecursive() {
		if (root == null) return "";
		StringBuilder s = new StringBuilder();
		postOrderRecursive(s, root);
		return s.toString();
	}
	
	private void postOrderRecursive(StringBuilder s, Node<T> curr) {
		if (curr.getLeft() != null) {
			postOrderRecursive(s, curr.getLeft());
		}
		if (curr.getRight() != null) {
			postOrderRecursive(s, curr.getRight());
		}
		s.append(curr.getData().toString() + " ");
	}
	
	/**
	 * Pre order recursive algorithm for binary tree
	 * @return
	 */
	public String preOrderRecursive() {
		if (root == null) return "";
		StringBuilder s = new StringBuilder();
		preOrderRecursive(s, root);
		return s.toString();
	}
	
	private void preOrderRecursive(StringBuilder s, Node<T> curr) {
		s.append(curr.getData().toString() + " ");
		if (curr.getLeft() != null) {
			preOrderRecursive(s, curr.getLeft());
		}
		if (curr.getRight() != null) {
			preOrderRecursive(s, curr.getRight());
		}
	}
	
	/**
	 * PreOrder iterative implementation
	 * @return
	 */
	public String preOrderIterative() {
		if (root == null) return "";
		Stack<Node<T>> stack = new Stack<Node<T>>();
		StringBuilder s = new StringBuilder();
		Node<T> curr = root;
		Boolean done = false;
		while (!done) {
			if (curr != null) {
				s.append(curr.getData().toString() + " ");
				stack.push(curr);
				curr = curr.getLeft();
			} else {
				if (!stack.isEmpty()) {
					curr = stack.pop();
					curr = curr.getRight();
				} else {
					done = true;
				}
			}
		}
		return s.toString();
	}
	
}
