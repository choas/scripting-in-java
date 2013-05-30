package kata.wordwrapper;
import static org.junit.Assert.*;

import org.junit.Test;


public class WordWrapperKataTest {

	@Test
	public void testEmptyString() {
		assertEquals("", wrap("", 5));
	}

	@Test
	public void testOneWord() {
		assertEquals("word", wrap("word", 5));
	}

	@Test
	public void testTwoWords() {
		assertEquals("word\nword", wrap("word word", 5));
	}

	@Test
	public void testTwoWordsColumnGreaterThanLength() {
		assertEquals("word word", wrap("word word", 50));
	}

	@Test
	public void testColumnBetweenSecondAndThirdWord() {
		assertEquals("1234 6789\n1234", wrap("1234 6789 1234", 10));
	}

	@Test
	public void testColumnBeforeEndSecondWord() {
		assertEquals("1234\n6789\n1234", wrap("1234 6789 1234", 9));
	}

	@Test
	public void testColumnBeforeEndSecondWordAndThirdWordIsSmall() {
		assertEquals("1234\n1234 56", wrap("1234 1234 56", 9));
	}

	@Test
	public void testMoreLines() {
		assertEquals("1234\n1234 56\n7890", wrap("1234 1234 56 7890", 9));
	}

	public String wrap(String word, int c) {

		String ret = "";
		String rest = word;
		
		while (rest.length() >= c) {
			String str1 = rest.substring(0, c);
			rest = rest.substring(c);
			
			int p = str1.lastIndexOf(" ");
			if (p >= 0 && p < c) {
				String s1 = str1.substring(0, p) + "\n";
				String s2 = str1.substring(p + 1);
				str1 = s1;
				rest = s2 + rest;
			}
			ret += str1;
		}
		
		return ret + rest;
	}
}
