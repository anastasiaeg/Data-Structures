package LinkedList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class KindaLinkedListTest {	
	private KindaLinkedList<String> list;
  
	/**
	 * Set up before every test
	 * @throws Exception when can't set up
	 */
	@Before
	public void setUp() throws Exception {
		list = new KindaLinkedList<String>();
	}

	/**
	 * Tests that a CSC216ArrayList is constructed correctly.  A CSC216ArrayList of
	 * any generic type should be not null and empty, which implies a size of 0.
	 */
	@Test
	public void testLinkedListRecursive() {
		//Test that the list field is created correctly.
		list = new KindaLinkedList<String>();
		assertTrue(list.isEmpty());
		assertEquals(0, list.size());
		try {
			list.remove(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertTrue(list.isEmpty());
			assertEquals(0, list.size());			
		}
		try {
			list.get(0);
			fail("Should not be able to get an element that doesn't exist.");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(list.isEmpty());
			assertEquals(0, list.size());
		}
		try {
			list.set(0, "element");
			fail("Should not be able to set an element that doesn't exist yet");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(list.isEmpty());
			assertEquals(0, list.size());
		}
	}

	/**
	 * Tests removing duplicates from the list.
	 */
	@Test
	public void testRemoveDups() {
		list.add("Hello");
		list.add("Hello");
		list.add("Hello");
		list.add("Hello");
		assertEquals(4, list.size());
		list.removeDups();
		assertEquals(1, list.size());
		list.add("1");
		list.add("2");
		list.add("2");
		assertEquals(4, list.size());
		list.removeDups();
		assertEquals(3, list.size());
		list.add("1");
		list.add("1");
		assertEquals(5, list.size());
		list.removeDups();
		assertEquals(3, list.size());
		
	}
	/**
	 * Tests adding elements to an empty CSC216ArrayList.  Then tests adding elements to the 
	 * front, middle, and back of a CSC216ArrayList.  The size and contents should be checked
	 * after each insertion.  Additionally, the bounds of the list should be checked and null
	 * elements should not be added to the list.  Finally, test that the ArrayList with an
	 * initial capacity of 10 grows when an 11th element is added.
	 */
	@Test
	public void testAddIntE() {
		//Attempt to add an element to index 1 in an empty list.
		//Check that the element was not added.
		try {
			list.add(1, "apple");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
		//Add element to empty list
		list.add(0, "apple");
		assertEquals(1, list.size());
		assertEquals("apple", list.get(0));
		
		//Add element to the front of a list
		list.add(0, "pear");
		assertEquals(2, list.size());
		assertEquals("pear", list.get(0));
		assertEquals("apple", list.get(1));
		
		//Add element to the middle of a list
		list.add(1, "banana");
		assertEquals(3, list.size());
		assertEquals("pear", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("apple", list.get(2));
		
		//Add element to the back of a list
		list.add("orange");
		assertEquals(4, list.size());
		assertEquals("pear", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("apple", list.get(2));	
		assertEquals("orange", list.get(3));
		
		//Attempt to add a null element.  Check that the element
		//was not added.
		try {
			list.add(null);
			fail("Should not be allowed to add a null element");
		} catch (IllegalArgumentException e) {
			assertEquals(4, list.size());
			assertEquals("pear", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("apple", list.get(2));	
			assertEquals("orange", list.get(list.size() - 1));
		}
		
		//Attempt to add at index -1.  Check that the element was not
		//added.
		try {
			list.add(-1, "lime");
			fail("Should not be able to add an element at a negative index");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
			assertEquals("pear", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("apple", list.get(2));	
			assertEquals("orange", list.get(3));
		}
		
		//Attempt to add at index 5 (since there are 4 elements in the list).
		//Check that the element was not added.
		try {
			list.add(5, "lemon");
			fail("Should not be able to add an element at an index that doesn't exist");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
			assertEquals("pear", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("apple", list.get(2));	
			assertEquals("orange", list.get(3));
		}
		
		//Test adding an 11th element to an ArrayList with an initial 
		//capacity of 10.
		for (int i = list.size(); i < 10; i++) {
			list.add("e");
		}
		//Add an 11th element
		list.add("kiwi");
		assertEquals(11, list.size());
		assertEquals("kiwi", list.get(10));
		assertEquals("pear", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("apple", list.get(2));	
		assertEquals("orange", list.get(3));
				
	}

	/**
	 * Tests that elements are correctly removed from the front, middle, and back of an 
	 * ArrayList.  Removing the last element should leave an empty list.  The bounds are
	 * checked for the appropriate exceptions.
	 */
	@Test
	public void testRemoveInt() {
		//Attempt to remove an element from an empty list
		try {
			list.remove(0);
			fail("Should not be able to remove an element from an empty list");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(list.isEmpty());
		}
		
		//Add 4 elements to the list and test that the contents are correct.
		//Reuse code from your testAddIntE.
		list.add(0, "orange");
		list.add(1, "banana");
		list.add(2, "apple");
		list.add(3, "kiwi");
		assertEquals(4, list.size());
		assertEquals("orange", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("apple", list.get(2));
		assertEquals("kiwi", list.get(3));
		
		//Test that IndexOutOfBoundsException is thrown when remove() is passed
		//a negative index.  Make sure the list is unchanged.
		try {
			list.remove(-1);
			fail("Shoudl not be able to remove a negative index");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
			assertEquals("orange", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("apple", list.get(2));
			assertEquals("kiwi", list.get(3));			
		}
		
		//Test that IndexOutOfBoundsException is thrown when remove() is passed
		//an index > size() - 1.  Make sure the list is unchanged.
		try {
			list.remove(list.size());
			fail("Should not be able to remove when index > size - 1");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
			assertEquals("orange", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("apple", list.get(2));
			assertEquals("kiwi", list.get(3));			
		}
		
		//Remove middle element.  Test that the removed element is correct and
		//that the remaining list is correct after the removal.
		String s1 = list.remove(1);
		assertEquals(s1, "banana");
		assertEquals(3, list.size());
		assertEquals("orange", list.get(0));
		assertEquals("apple", list.get(1));
		assertEquals("kiwi", list.get(2));
		
		//Remove first element
		assertEquals("orange", list.remove(0));
		assertEquals(2, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("kiwi", list.get(1));
		
		//Remove last element
		assertEquals("kiwi", list.remove(list.size() - 1));
		assertEquals(1, list.size());
		assertEquals("apple", list.get(0));
		
		//Remove only element
		assertEquals("apple", list.remove(0));
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}

	/**
	 * Tests setting an element in an empty list, the bounds of the list when
	 * using the set() method, and setting an element at the front, middle, and back
	 * of the list.  The set() method is also passed null
	 */
	@Test
	public void testSetIntE() {
		//Attempt to set a value in an empty list
		try {
			list.set(0, "element");
			fail("Should not be able to set a value in an empty list");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(list.isEmpty());
		}
		
		//Add 4 elements to the list and test that the contents are correct.
		//Reuse code from your testAddIntE.
		list.add(0, "orange");
		list.add(1, "banana");
		list.add(2, "apple");
		list.add(3, "kiwi");
		assertEquals(4, list.size());
		assertEquals("orange", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("apple", list.get(2));
		assertEquals("kiwi", list.get(3));
		
		//Test that IndexOutOfBoundsException is thrown when set() is passed
		//a negative index.  Make sure the list is unchanged.
		try {
			list.set(-1, "element");
			fail("Should not be able to set a negative index");
		} catch(IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
			assertEquals("orange", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("apple", list.get(2));
			assertEquals("kiwi", list.get(3));
		}
		
		//Test that IndexOutOfBoundsException is thrown when set() is passed
		//an index > size() - 1.  Make sure the list is unchanged.
		try {
			list.set(list.size(), "element");
			fail("Shoulde not be able to add");
		} catch(IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
			assertEquals("orange", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("apple", list.get(2));
			assertEquals("kiwi", list.get(3));
		}
		
		//Set middle element.  Test that the element is modified correctly, set() returns the
		//right value, and that the rest of the list is correct.
		String s1 = list.set(1, "strawberry");
		assertEquals(s1, "banana"); 
		assertEquals(4, list.size());
		assertEquals("orange", list.get(0));
		assertEquals("strawberry", list.get(1));
		assertEquals("apple", list.get(2));
		assertEquals("kiwi", list.get(3));
		//Set first element
		String s2 = list.set(0, "mango");
		assertEquals(s2, "orange");
		assertEquals(4, list.size());
		assertEquals("mango", list.get(0));
		assertEquals("strawberry", list.get(1));
		assertEquals("apple", list.get(2));
		assertEquals("kiwi", list.get(3));
		
		//Set last element
		String s3 = list.set(list.size() - 1, "blueberry");
		assertEquals(s3, "kiwi");
		assertEquals(4, list.size());
		assertEquals("mango", list.get(0));
		assertEquals("strawberry", list.get(1));
		assertEquals("apple", list.get(2));
		assertEquals("blueberry", list.get(3));
		
		
		//Attempt to set an element to null.  Check that the element
		//was not modified.
		try {
			list.set(0, null);
			fail("Should not be able to set a null element");
		} catch (IllegalArgumentException e) {
			assertEquals(4, list.size());
			assertEquals("mango", list.get(0));
			assertEquals("strawberry", list.get(1));
			assertEquals("apple", list.get(2));
			assertEquals("blueberry", list.get(3));
		}
	}

	/**
	 * Main get() functionality is tested in the other test methods.  This method will
	 * focus on testing the exceptions associated with bounds.
	 */
	@Test
	public void testGetInt() {
		//Add 4 elements to the list and test that the contents are correct.
		//Reuse code from your testAddIntE.
		assertTrue(list.isEmpty());
		assertTrue(list.add("apple"));
		assertTrue(list.add("orange"));
		assertTrue(list.add("banana"));
		assertTrue(list.add("mango"));
		assertEquals(4, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("orange", list.get(1));
		assertEquals("banana", list.get(2));
		assertEquals("mango", list.get(3));
		
		//Test that IndexOutOfBoundsException is thrown when get() is passed
		//a negative index.  Make sure the list is unchanged.
		try {
			list.get(-1);
			fail("Should not be able to get() a value with negative index");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
			assertEquals("apple", list.get(0));
			assertEquals("orange", list.get(1));
			assertEquals("banana", list.get(2));
			assertEquals("mango", list.get(3));			
		}
		
		//Test that IndexOutOfBoundsException is thrown when get() is passed
		//an index > size() - 1.  Make sure the list is unchanged.
		try {
			list.get(list.size());
			fail("Should not be able to get a value when index > size -1");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
			assertEquals("apple", list.get(0));
			assertEquals("orange", list.get(1));
			assertEquals("banana", list.get(2));
			assertEquals("mango", list.get(3));	
		}
	}

}