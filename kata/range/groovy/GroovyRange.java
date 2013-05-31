package kata.range.groovy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import kata.range.Range;

public class GroovyRange implements Range {

	private static final String SCRIPT_GROOVY = "src/kata/range/groovy/range.groovy";
	private ScriptEngineManager manager;
	private ScriptEngine engine;
	private Invocable inv;

	public GroovyRange() throws ScriptException, FileNotFoundException {
		manager = new ScriptEngineManager();
		engine = manager.getEngineByName("groovy");		
		assertNotNull(engine);
		inv = (Invocable) engine;
		engine.eval(new java.io.FileReader(SCRIPT_GROOVY));
	}
	
	@Override
	public boolean contains(String rangeString, int point) throws ScriptException, NoSuchMethodException {
		return (Boolean)inv.invokeFunction("contains", rangeString, point);
	}

	@Override
	public boolean containsRange(String rangeString1, String rangeString2) throws ScriptException, NoSuchMethodException {
		return (Boolean)inv.invokeFunction("containsRange", rangeString1, rangeString2);
	}

	@Override
	public int[] allPoints(String rangeString) throws ScriptException, NoSuchMethodException  {
		return (int[]) inv.invokeFunction("allPoints", rangeString);
	}

	@Override
	public boolean overlaps(String rangeString1, String rangeString2) throws ScriptException, NoSuchMethodException {
		return (Boolean)inv.invokeFunction("overlaps", rangeString1, rangeString2);
	}
}
