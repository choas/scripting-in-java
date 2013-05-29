import static org.junit.Assert.*;

import org.junit.Test;

public class JavaRangeKataTest {

	public class Range {

		private int start;
		private int end;

		public Range(String rangeString) {
			int p = rangeString.indexOf(',');
			start = Integer.valueOf(rangeString.substring(1, p));
			boolean isStartIncluded = rangeString.substring(0, 1).equals("[");
			if (!isStartIncluded) {
				start++;
			}
			end = Integer.valueOf(rangeString.substring(p + 1, rangeString.length() - 1));
			boolean isEndIncluded = rangeString.substring(rangeString.length() - 1).equals("]");
			if (isEndIncluded) {
				end++;
			}
		}

		public int getStart() {
			return start;
		}

		public int getEnd() {
			return end;
		}
	}

	@Test
	public void testIntegerRangeContains() {
		assertTrue(contains("[2,6)", 2));
		assertTrue(contains("[2,6)", 4));
		assertFalse(contains("[2,6)", -1));
		assertFalse(contains("[2,6)", 1));
		assertFalse(contains("[2,6)", 6));
		assertFalse(contains("[2,6)", 10));
	}

	@Test
	public void testAllPoints() {
		assertArrayEquals(new int[] { 2, 3, 4, 5 }, allPoints("[2,6)"));
	}

	@Test
	public void testContainsRange() {
		assertFalse(contains("[2,5)", "[7,10)"));
		assertFalse(contains("[2,5)", "[3,10)"));
		assertFalse(contains("[3,5)", "[2,10)"));
		assertTrue(contains("[2,10)", "[3,5]"));
		assertTrue(contains("[3,5]", "[3,5)"));
	}
	
	@Test
	public void testEndPoints() {
		assertArrayEquals(new int[] { 2,3,4,5 }, allPoints("[2,6)"));
		assertArrayEquals(new int[] { 2,3,4,5,6 }, allPoints("[2,6]"));
		assertArrayEquals(new int[] { 3,4,5 }, allPoints("(2,6)"));
		assertArrayEquals(new int[] { 3,4,5,6 }, allPoints("(2,6]"));
	}

	
	
	public boolean contains(String rangeString1, String rangeString2) {
		for (int point : allPoints(rangeString2)) {
			if (!contains(rangeString1, point)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean contains(String rangeString, int point) {
		Range range = new Range(rangeString);

		return (point >= range.getStart() && point < range.getEnd());
	}

	public int[] allPoints(String rangeString) {
		Range range = new Range(rangeString);

		int[] points = new int[range.getEnd() - range.getStart()];
		for (int i = range.getStart(); i < range.getEnd(); i++) {
			points[i - range.getStart()] = i;
		}

		return points;
	}

}
