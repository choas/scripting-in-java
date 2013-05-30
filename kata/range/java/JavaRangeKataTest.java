package kata.range.java;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JavaRangeKataTest {
	
	private static Range range;

	@Before
	public void init() {
		range = new JavaRange();
	}

	@Test
	public void testIntegerRangeContains() {
		assertTrue(range.contains("[2,6)", 2));
		assertTrue(range.contains("[2,6)", 4));
		assertFalse(range.contains("[2,6)", -1));
		assertFalse(range.contains("[2,6)", 1));
		assertFalse(range.contains("[2,6)", 6));
		assertFalse(range.contains("[2,6)", 10));
	}

	@Test
	public void testAllPoints() {
		assertArrayEquals(new int[] { 2, 3, 4, 5 }, range.allPoints("[2,6)"));
	}

	@Test
	public void testContainsRange() {
		assertFalse(range.contains("[2,5)", "[7,10)"));
		assertFalse(range.contains("[2,5)", "[3,10)"));
		assertFalse(range.contains("[3,5)", "[2,10)"));
		assertTrue(range.contains("[2,10)", "[3,5]"));
		assertTrue(range.contains("[3,5]", "[3,5)"));
	}
	
	@Test
	public void testEndPoints() {
		assertArrayEquals(new int[] { 2,3,4,5 }, range.allPoints("[2,6)"));
		assertArrayEquals(new int[] { 2,3,4,5,6 }, range.allPoints("[2,6]"));
		assertArrayEquals(new int[] { 3,4,5 }, range.allPoints("(2,6)"));
		assertArrayEquals(new int[] { 3,4,5,6 }, range.allPoints("(2,6]"));
	}

	@Test
	public void testOverlapsRange() {
		assertFalse(range.overlaps("[2,5)", "[7,10)"));
		assertTrue(range.overlaps("[2,10)", "[3,5)"));
		assertTrue(range.overlaps("[3,5)", "[3,5)"));
		assertTrue(range.overlaps("[2,5)", "[3,10)"));
		assertTrue(range.overlaps("[3,5)", "[2,10)"));
	}
	
}
