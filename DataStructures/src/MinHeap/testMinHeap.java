package MinHeap;

import static org.junit.Assert.*;

import org.junit.Test;

public class testMinHeap {
	
	//		1
	//	2		3
	// 4 5	   6 7
	//0
	@Test
	public void test() {
		MinHeap mh = new MinHeap();
		mh.add(1);
		mh.add(2);
		mh.add(3);
		mh.add(4);
		mh.add(5);
		mh.add(6);
		mh.add(7);
		assertEquals("1\nleft:2\nleft:4\nright:5\nright:3\nleft:6\nright:7", mh.toString());
		assertEquals("1\n2\n3\n4\n5\n6\n7\n", mh.levelOrder());
	}
}