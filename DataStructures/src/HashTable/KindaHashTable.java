package HashTable;
import java.math.BigDecimal;

/**
 * Creates a hash table for the dictionary, adds and looks for objects in the table
 * @author Anastasia Egorova
 *
 */
public class KindaHashTable {
	//Size of dictionary
	private final int m = 25144;
	//Size of dictionary * perfect cellar(14%)
	private final int m1 = 28644;
	//Counter for testing
	private int added = 0;
	//Beginning of the cellar
	private int cellar;	
	//Data of the table
	private Word data[];
	
	/**
	 * Constructs a hash table with M size
	 */
	public KindaHashTable() {
		data = new Word[m1];
		cellar = 25144;
	}
	
	/**
	 * Creates a hash code for the given string and returns an index in the table
	 * @param word word to hash 
	 * @return index to add the word to
	 */
	public int hash(String word) {
		BigDecimal hashCode = BigDecimal.TEN;
		for (int i = 0; i < word.length(); i++) {
			hashCode = hashCode.add(new BigDecimal(word.charAt(i)).multiply(new BigDecimal(Math.pow(128, word.length() - i))));
		}
		return compress(hashCode);
	}
	
	/**
	 * Compresses the hash code to a visible index number
	 * @param hashCode hash code to compress
	 * @return index in the array
	 */
	private int compress(BigDecimal hashCode) {
		BigDecimal phi = new BigDecimal((1 + Math.sqrt(5)) / 2 - 1);
		hashCode = hashCode.multiply(phi);
		phi = hashCode.setScale(0, BigDecimal.ROUND_FLOOR);
		hashCode = hashCode.subtract(phi);
		phi = new BigDecimal(m);
		hashCode = hashCode.multiply(phi);
		hashCode = hashCode.setScale(0, BigDecimal.ROUND_FLOOR);
		return hashCode.intValue();
	}
	
	/**
	 * Looks up for the given word in the hash table
	 * @param word word to find
	 * @return true if the word was found
	 */
	public Counter lookUp(String word) {
		Counter counter = new Counter();
		int index = hash(word);
		if (data[index] == null) {
			counter.probes++;
			return counter;
		}
		while (!data[index].data.equals(word)) {
			counter.probes++;
			if (data[index].next == -1) {
				return counter;
			}
			index = data[index].next;
		}
		counter.probes++;
		counter.found = true;
		return counter;
	}
	
	/**
	 * Adds a word to the table
	 * @param word word to add
	 */
	public void add(String word) {
		int index = hash(word);
		while (data[index] != null) {
			if (data[index].next != -1) {
				index = data[index].next;
			} else {
				if (cellar == m1 - 1) {
					int newIndex = 0;
					while (data[newIndex] != null) {
						newIndex++;
					}
					data[index].next = newIndex;
					index = newIndex;
				} else {
					data[index].next = cellar;
					cellar++;
				}
			}
		}
		added++;
		data[index] = new Word(word);
	}
	
	/**
	 * Helper class for word constructor, has string data and index of the next element in the table
	 * @author Anastasia Egorova
	 *
	 */
	private class Word {
		//Data of the word
		String data;
		//Index of the next element
		int next;
		
		/**
		 * Creates a constructor of the word
		 * @param word takes a string to set the data to
		 */
		public Word(String word) {
			data = word;
			next = -1;
		}
	}
	
	/**
	 * Returns number of added elements to the table
	 * @return added
	 */
	public int getAdded() {
		return added;
	}
	
	/**
	 * Class for the counter for counting probes
	 * @author Anastasia Egorova
	 *
	 */
	private class Counter {
		/**Counter to save number of probes*/
		public int probes;
		/**Counter for lookups*/
		public int lookups;
		/**Boolean to store if the word was found*/
		public boolean found;
		
		/**
		 * Constructor for the counter to keep track of probes
		 */
		public Counter() {
			probes = 0;
			lookups = 0;
			found = false;
		}
	}
}