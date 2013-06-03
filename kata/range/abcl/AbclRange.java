package kata.range.abcl;


import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import kata.range.Range;

import org.armedbear.lisp.ComplexVector;
import org.armedbear.lisp.Symbol;


public class AbclRange implements Range {

	private ScriptEngineManager manager;
	private ScriptEngine engine;
	private Invocable inv;

	public AbclRange(String language, String[] scripts) throws ScriptException, FileNotFoundException {
		manager = new ScriptEngineManager();
		engine = manager.getEngineByName(language);
		assertNotNull(engine);
		inv = (Invocable) engine;
		for(String script : scripts) {
			engine.eval(new java.io.FileReader("src/kata/range/" + language.toLowerCase() + "/" + script));
		}
	}
	
	@Override
	public boolean contains(String rangeString, int point) throws ScriptException, NoSuchMethodException {
		Object r = inv.invokeFunction("contains", rangeString, point);
		return ((Symbol)r).getBooleanValue();
	}

	@Override
	public boolean containsRange(String rangeString1, String rangeString2) throws ScriptException, NoSuchMethodException {
		Object r = inv.invokeFunction("containsRange", rangeString1, rangeString2);
		return ((Symbol)r).getBooleanValue();
	}

	@Override
	public int[] allPoints(String rangeString) throws ScriptException, NoSuchMethodException  {
		Object r = inv.invokeFunction("allPoints", rangeString);
		ComplexVector v = (ComplexVector)r;
		int[] ps = new int[v.capacity()];
		for(int i = 0; i < v.capacity(); i++) {
			ps[i] = v.AREF(i).intValue();
		}
		return ps;
	}

	@Override
	public boolean overlaps(String rangeString1, String rangeString2) throws ScriptException, NoSuchMethodException {
		Object r = inv.invokeFunction("overlaps", rangeString1, rangeString2);
		return ((Symbol)r).getBooleanValue();
	}
}
