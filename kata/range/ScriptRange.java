package kata.range;

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


public class ScriptRange implements Range {

	private ScriptEngineManager manager;
	protected ScriptEngine engine;
	protected Invocable inv;

	public ScriptRange(String language, String[] scripts) throws ScriptException, FileNotFoundException {
		manager = new ScriptEngineManager();
		engine = manager.getEngineByName(language);
		assertNotNull(engine);
		inv = (Invocable) engine;
		for(String script : scripts) {
			engine.eval(new java.io.FileReader(scriptName(language, script)));
		}
	}

	public static String scriptName(String language, String script) {
		return "src/kata/range/" + language.toLowerCase() + "/" + script;
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
