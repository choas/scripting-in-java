import static org.junit.Assert.*;

import org.junit.Test;

public class JavaRangeKataTest {

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

	public boolean contains(String rangeString1, String rangeString2) {
		for (int point : allPoints(rangeString2)) {
			if (!contains(rangeString1, point)) {
				return false;
			}
		}
		return true;
	}

	public boolean contains(String rangeString, int point) {
		int p = rangeString.indexOf(',');
		int start = Integer.valueOf(rangeString.substring(1, p));
		int end = Integer.valueOf(rangeString.substring(p + 1, rangeString.length() - 1));

		return (point >= start && point < end);
	}

	public int[] allPoints(String rangeString) {
		int p = rangeString.indexOf(',');
		int start = Integer.valueOf(rangeString.substring(1, p));
		int end = Integer.valueOf(rangeString.substring(p + 1, rangeString.length() - 1));

		int[] ps = new int[end - start];
		for (int i = start; i < end; i++) {
			ps[i - start] = i;
		}

		return ps;
	}

}
