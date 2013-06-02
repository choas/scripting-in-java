package kata.range.sleep;

import java.io.IOException;

import javax.script.ScriptException;

import kata.range.Range;
import sleep.error.YourCodeSucksException;
import sleep.runtime.Scalar;
import sleep.runtime.ScriptInstance;
import sleep.runtime.ScriptLoader;
import sleep.runtime.ScriptVariables;
import sleep.runtime.SleepUtils;

public class SleepRange implements Range {

	private static final String SCRIPT = "src/kata/range/sleep/range.pl";
	private ScriptVariables variables;
	private ScriptInstance script;
	
	public SleepRange() throws ScriptException, YourCodeSucksException, IOException {
		ScriptLoader loader = new ScriptLoader();
		script = loader.loadScript(SCRIPT);
		variables = script.getScriptVariables();
	}
	
	@Override
	public boolean contains(String rangeString, int point) throws ScriptException, NoSuchMethodException {
		variables.putScalar("$fun", SleepUtils.getScalar("contains"));
		variables.putScalar("$range", SleepUtils.getScalar(rangeString));
		variables.putScalar("$point", SleepUtils.getScalar(point));
		Scalar r = script.runScript();
		return r.intValue() == 1;
	}

	@Override
	public boolean containsRange(String rangeString1, String rangeString2) throws ScriptException, NoSuchMethodException {
		variables.putScalar("$fun", SleepUtils.getScalar("containsRange"));
		variables.putScalar("$range1", SleepUtils.getScalar(rangeString1));
		variables.putScalar("$range2", SleepUtils.getScalar(rangeString2));
		Scalar r = script.runScript();
		return r.intValue() == 1;
	}

	@Override
	public int[] allPoints(String rangeString) throws ScriptException, NoSuchMethodException  {
		variables.putScalar("$fun", SleepUtils.getScalar("allPoints"));
		variables.putScalar("$range", SleepUtils.getScalar(rangeString));
		Scalar r = script.runScript();
		int[] ps = new int[r.getArray().size()];
		int i = 0;

		while(i < r.getArray().size()) {
			ps[i] = ((Scalar)r.getArray().getAt(i)).intValue();
			i++;
		}
		return ps;
	}

	@Override
	public boolean overlaps(String rangeString1, String rangeString2) throws ScriptException, NoSuchMethodException {
		variables.putScalar("$fun", SleepUtils.getScalar("overlaps"));
		variables.putScalar("$range1", SleepUtils.getScalar(rangeString1));
		variables.putScalar("$range2", SleepUtils.getScalar(rangeString2));
		Scalar r = script.runScript();
		return r.intValue() == 1;
	}
}
