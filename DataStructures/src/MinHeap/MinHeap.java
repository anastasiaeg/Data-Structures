package MinHeap;

import java.util.LinkedList;
import java.util.Queue;

public class MinHeap {
	
	private int size;
	Node root;
	
	public MinHeap() {
		root = null;
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public void add(int data) {
		if (root == null) {
			root = new Node(null, data);
		} else {
			String s = "";
			int thing = size / 2 == 0 ? 1 : size / 2;
			while (thing != 1) {
				s += thing % 2 == 0 ? "l" : "r";
				thing = thing / 2;
			}
			Node current = root;
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(s.length() - 1 - i) == 'r') {
					current = current.right;
				} else {
					current = current.left;
				}
			}
			if (current.left != null && current.right != null) {
				System.out.println(current.data + " " + size + "  " + s);
				String s2 = "";
				for (int i = 0; i < s.length(); i++) {
					if (s.charAt(i) == 'r') {
						s2 += "l";
					} else {
						s2 += "r";
					}
				}
				if (s2.equals("")) s2 += "l";
				current = root;
				for (int i = 0; i < s2.length(); i++) {
					if (s2.charAt(i) == 'r') {
						current = current.right;
					} else {
						current = current.left;
					}
				}
			}
			if (current.left == null) {
				current.left = new Node(current, data);
				current = current.left;
			} else {
				current.right = new Node(current, data);
				current = current.right;
			}
//			while (current.parent != null && current.data < current.parent.data) {
//				int save = current.data;
//				current.data = current.parent.data;
//				current.parent.data = save;
//				current = current.parent;
//			}
		}
		
		size++;
	}
	
	public String toString() {
		return print(root);
	}
	public String print(Node n) {
		if (n == null) {
			return "";
		}
		return n.data + (n.left != null?"\nleft:" + print(n.left):"") + (n.right != null?"\nright:" + print(n.right):"");
	}
	
	public String levelOrder() {
		StringBuilder output = new StringBuilder(root.data + "\n");
		Queue<Node> q = new LinkedList<Node>();
		q.add(root);
		while (!q.isEmpty()) {
			Node current = q.poll();
			if (current.left != null) {
				output.append(current.left.data + "\n");
				q.add(current.left);
			}
			if (current.right != null) {
				output.append(current.right.data + "\n");
				q.add(current.right);
			}
		}
		return output.toString();
	}
	
	class Node {
		int data;
		Node right, left;
		Node parent;
		
		Node(Node parent, int data) {
			this.data = data;
			this.parent = parent;
		}
	}
}
