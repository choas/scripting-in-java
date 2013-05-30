package kata.range.javascript;

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

public class JavascriptRange implements Range {

	private static final String UNDERSCORE_JS = "src/kata/range/javascript/underscore.js"; //"https://raw.github.com/documentcloud/underscore/master/underscore.js";
	private static final String SCRIPT_JS = "src/kata/range/javascript/range.js";
	private ScriptEngineManager manager;
	private ScriptEngine engine;
	private Invocable inv;

	public JavascriptRange() throws ScriptException, FileNotFoundException {
		manager = new ScriptEngineManager();
		engine = manager.getEngineByName("JavaScript");		
		assertNotNull(engine);
		inv = (Invocable) engine;
//		try {
//			URL url = new URL(UNDERSCORE_JS);
//			BufferedReader in;
//			in = new BufferedReader(new InputStreamReader(url.openStream()));
//			engine.eval(in);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		engine.eval(new java.io.FileReader(UNDERSCORE_JS));
		engine.eval(new java.io.FileReader(SCRIPT_JS));
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
