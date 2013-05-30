package kata.range;

import javax.script.ScriptException;

public interface Range {

	public abstract boolean contains(String rangeString, int point) throws ScriptException, NoSuchMethodException;

	public abstract int[] allPoints(String rangeString) throws ScriptException, NoSuchMethodException;

	public abstract boolean containsRange(String rangeString1, String rangeString2) throws ScriptException, NoSuchMethodException;

	public abstract boolean overlaps(String rangeString1, String rangeString2) throws ScriptException, NoSuchMethodException;

}