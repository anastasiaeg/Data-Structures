package Graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Graphs {
	
	public static void main(String[] args) {
		Node s = new Node('s'), c = new Node('c'), g = new Node('g'),
				b = new Node('b'), a = new Node('a'),
				d = new Node('d'), h = new Node('h'),
				f = new Node('f'), l = new Node('l'),
				i = new Node('i'), j = new Node('j'),
				k = new Node('k'), e = new Node('e');
		s.add(c, 3); s.add(a, 7); s.add(b, 2);
		b.add(a, 3); a.add(d, 4); d.add(b, 4);
		b.add(h, 1); d.add(f, 5); f.add(h, 3);
		h.add(g, 2); g.add(e, 2); e.add(k, 5);
		k.add(i, 4); k.add(j, 4); i.add(j, 6);
		i.add(l, 4); l.add(j, 4); c.add(l, 2);
		
		ArrayList<Edge> output = dijkstra(s, e);
	}
	
	public static ArrayList<Edge> dijkstra(Node a, Node b) {
		
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		for (Edge e: a.al) {
			pq.add(e);
		}
		pq.poll();
		while (pq != null) {
			
		}
		
		
		return null;
	}
	
	public static class Node {
		char data;
		ArrayList<Edge> al;
		
		public Node(char data) {
			this.data = data;
			al = new ArrayList<Edge>();
		}
		
		public void add(Node n, int length) {
			
			al.add(new Edge(this, n, length));
			n.add(al.get(al.size() -1));
		}
		
		public void add(Edge e) {
			al.add(e);
		}
	}
	
	public static class Edge implements Comparator<Edge> {
		private Node a, b;
		private int length;
		
		public Edge(Node one, Node two, int size) {
			a = one; b = two; length = size;
		}

		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.length - o2.length;
		}
	}
}
