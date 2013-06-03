package kata.range;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.script.ScriptException;

import kata.range.abcl.AbclRange;
import kata.range.groovy.GroovyRange;
import kata.range.java.JavaRange;
import kata.range.javascript.JavascriptRange;
import kata.range.sleep.SleepRange;

import org.junit.Before;
import org.junit.Test;

import sleep.error.YourCodeSucksException;

public class KataRangeTest {
	
	private static Range range;

	@Before
	public void init() throws ScriptException, YourCodeSucksException, IOException {
		range = new JavaRange();
		range = new ScriptRange("javascript", new String[] {"underscore.js", "range.js"});
		range = new ScriptRange("groovy", new String[] {"range.groovy"});
		range = new ScriptRange("Clojure", new String[] {"range.clojure"});
		range = new ScriptRange("jaskell", new String[] {"range.hs"});
		range = new SleepRange();
		range = new AbclRange("ABCL", new String[] {"range.lisp"});
	}

	@Test
	public void testIntegerRangeContains() throws ScriptException, NoSuchMethodException {
		assertTrue(range.contains("[2,6)", 2));
		assertTrue(range.contains("[2,6)", 4));
		assertFalse(range.contains("[2,6)", -1));
		assertFalse(range.contains("[2,6)", 1));
		assertFalse(range.contains("[2,6)", 6));
		assertFalse(range.contains("[2,6)", 10));
	}

	@Test
	public void testAllPoints() throws ScriptException, NoSuchMethodException {
		assertArrayEquals(new int[] { 2, 3, 4, 5 }, range.allPoints("[2,6)"));
	}

	@Test
	public void testContainsRange() throws ScriptException, NoSuchMethodException {
		assertFalse(range.containsRange("[2,5)", "[7,10)"));
		assertFalse(range.containsRange("[2,5)", "[3,10)"));
		assertFalse(range.containsRange("[3,5)", "[2,10)"));
		assertTrue(range.containsRange("[2,10)", "[3,5]"));
		assertTrue(range.containsRange("[3,5]", "[3,5)"));
	}
	
	@Test
	public void testEndPoints() throws ScriptException, NoSuchMethodException {
		assertArrayEquals(new int[] { 2,3,4,5 }, range.allPoints("[2,6)"));
		assertArrayEquals(new int[] { 2,3,4,5,6 }, range.allPoints("[2,6]"));
		assertArrayEquals(new int[] { 3,4,5 }, range.allPoints("(2,6)"));
		assertArrayEquals(new int[] { 3,4,5,6 }, range.allPoints("(2,6]"));
	}

	@Test
	public void testOverlapsRange() throws ScriptException, NoSuchMethodException {
		assertFalse(range.overlaps("[2,5)", "[7,10)"));
		assertTrue(range.overlaps("[2,10)", "[3,5)"));
		assertTrue(range.overlaps("[3,5)", "[3,5)"));
		assertTrue(range.overlaps("[2,5)", "[3,10)"));
		assertTrue(range.overlaps("[3,5)", "[2,10)"));
	}
	
}
