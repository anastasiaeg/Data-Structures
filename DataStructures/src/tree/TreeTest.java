package tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class TreeTest {
	
	private BinarySearchTree<Integer> bst;
	
	/**
	 * Set up before every test
	 * @throws Exception when can't set up
	 */
	@Before
	public void setUp() throws Exception {
		bst = new BinarySearchTree<Integer>();
	}
	
	@Test
	public void testBinarySearchTreeInsert() {
		assertEquals(0, bst.getNodes());
		try {
			bst.insert(null);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(0, bst.getNodes());
		}
		try {
			bst.insert(8);
			bst.insert(3);
			bst.insert(10);
			bst.insert(14);
			bst.insert(6);
			bst.insert(1);
			bst.insert(4);
			bst.insert(13);
			assertEquals(8, bst.getNodes());
			bst.insert(4);
			bst.insert(7);
			assertEquals(9, bst.getNodes());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}
	
	@Test
	public void testBinarySearchTreeRemove() {
		bst.insert(50);
		bst.insert(30);
		bst.insert(70);
		bst.insert(20);
		bst.insert(40);
		bst.insert(60);
		bst.insert(80);
		assertEquals("50 30 70 20 40 60 80 ", bst.levelOrderRecursive());
		assertEquals("20 40 30 60 80 70 50 ", bst.postOrderRecursive());
		assertEquals("50 30 20 40 70 60 80 ", bst.preOrderRecursive());
		assertEquals("20 30 40 50 60 70 80 ", bst.inOrderIterative());
		assertEquals(3, bst.levels());
		bst.remove(20);
		assertEquals("50 30 70 40 60 80 ", bst.levelOrderIterative());
		assertEquals("40 30 60 80 70 50 ", bst.postOrderRecursive());
		assertEquals("50 30 40 70 60 80 ", bst.preOrderIterative());
		assertEquals("30 40 50 60 70 80 ", bst.inOrderRecursive());
		bst.remove(30);
		assertEquals("50 40 70 60 80 ", bst.levelOrderRecursive());
		assertEquals("40 60 80 70 50 ", bst.postOrderRecursive());
		assertEquals("50 40 70 60 80 ", bst.preOrderRecursive());
		assertEquals("40 50 60 70 80 ", bst.inOrderRecursive());
		bst.remove(50);
		assertEquals("60 40 70 80 ", bst.levelOrderIterative());
		assertEquals("40 80 70 60 ", bst.postOrderRecursive());
		assertEquals("60 40 70 80 ", bst.preOrderRecursive());
		assertEquals("40 60 70 80 ", bst.inOrderRecursive());
		bst.remove(80);
		assertEquals(2, bst.levels());
	}
}
