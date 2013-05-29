import static org.junit.Assert.*;

import org.junit.Test;


public class JavaRangeKataTest {

	public class Range {

		public Range(String string) {
			// TODO Auto-generated constructor stub
		}

		public boolean contains(int i) {
			// TODO Auto-generated method stub
			return true;
		}

	}
	@Test
	public void testContains() {
		assertTrue(contains("[2,6)", 2));
		assertTrue(contains("[2,6)", 4));
	}
	@Test
	public void testContainsNot() {
		assertFalse(contains("[2,6)", -1));
		assertFalse(contains("[2,6)", 1));
		assertFalse(contains("[2,6)", 6));
		assertFalse(contains("[2,6)", 10));
	}
	
	@Test
	public void testAllPoint() {
		assertArrayEquals(new int[] {2,3,4,5}, allPoints("[2,6)"));
	}
	
	@Test
	public void testNotContainsRange() {
		assertFalse(contains("[2,5)", "[7,10)"));
		assertFalse(contains("[2,5)", "[3,10)"));
		assertFalse(contains("[3,5)", "[2,10)"));
	}
	@Test
	public void testContainsRange() {
		assertTrue(contains("[2,10)", "[3,5]"));
		assertTrue(contains("[3,5]", "[3,5)"));
	}
	
	
	public boolean contains(String string, String string2) {
		for(int p : allPoints(string2)) {
			if (!contains(string, p)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean contains(String string, int i) {
		int p = string.indexOf(',');
		int start = Integer.valueOf(string.substring(1,p));
		int end = Integer.valueOf(string.substring(p+1,string.length()-1));
		
		return (i >= start && i < end);
	}
	public int[] allPoints(String string) {
		int p = string.indexOf(',');
		int start = Integer.valueOf(string.substring(1,p));
		int end = Integer.valueOf(string.substring(p+1,string.length()-1));
		
		int[] ps = new int[end-start];
		for(int i = start; i < end; i++) {
			ps[i-start] = i;
		}
		
		return ps;
	}

}
