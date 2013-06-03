package kata.range.jaskell;

import java.io.FileNotFoundException;

import javax.script.ScriptException;

import kata.range.ScriptRange;


public class JaskellRange extends ScriptRange {

	public JaskellRange(String language, String[] scripts)
			throws ScriptException, FileNotFoundException {
		super(language, scripts);
	}

	@Override
	public boolean contains(String rangeString, int point) throws ScriptException, NoSuchMethodException {
		return (Boolean)engine.eval("contains \"" + rangeString + "\" (" + point + ")");
	}

	@Override
	public boolean containsRange(String rangeString1, String rangeString2) throws ScriptException, NoSuchMethodException {
		return (Boolean)engine.eval(" containsRange \"" + rangeString1 + "\" \"" + rangeString2 + "\"");
	}

	@Override
	public int[] allPoints(String rangeString) throws ScriptException, NoSuchMethodException  {
		return (int[])engine.eval(" allPoints \"" + rangeString + "\"");
	}

	@Override
	public boolean overlaps(String rangeString1, String rangeString2) throws ScriptException, NoSuchMethodException {
		return (Boolean)engine.eval(" overlaps \"" + rangeString1 + "\" \"" + rangeString2 + "\"");
	}
}
