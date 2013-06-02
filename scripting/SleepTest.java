package scripting;
import static org.junit.Assert.*;

import java.io.IOException;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;

import org.dashnine.sleep.SleepScriptEngine;
import org.junit.Test;

import sleep.error.YourCodeSucksException;
import sleep.runtime.Scalar;
import sleep.runtime.ScriptInstance;
import sleep.runtime.ScriptLoader;
import sleep.runtime.ScriptVariables;
import sleep.runtime.SleepUtils;


public class SleepTest {
	
	@Test
	public void test() throws YourCodeSucksException, IOException {
		
		ScriptLoader loader = new ScriptLoader();
		ScriptInstance script = loader.loadScript("src/scripting/script.sl");
		ScriptVariables variables = script.getScriptVariables();
		variables.putScalar("$range", SleepUtils.getScalar("[2,6)"));
		
		Scalar r = script.runScript();
		
		int[] ps = new int[r.getArray().size()];
		int i = 0;

		while(i < r.getArray().size()) {
			ps[i] = ((Scalar)r.getArray().getAt(i)).intValue();
			i++;
		}
		assertArrayEquals(new int[] { 2,3,4,5 }, ps);
		
		Scalar value = variables.getScalar("$answer");
		assertEquals(100, value.intValue());
		
		System.out.println(value.toString() + "  ... " + ps);
	}
	
//	@Test
	public void testJSR223() throws ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
	    ScriptEngine engine = manager.getEngineByName("sleep");		
	    assertNotNull(engine);
	    engine.eval("$range = '[2,6)'; $m = matches($range, '(.)(\\d+),(\\d+)(.)'); println($m[2] - 1);");

	    ScriptContext newContext = new SimpleScriptContext();
        Bindings engineScope = newContext.getBindings(ScriptContext.ENGINE_SCOPE);
        engineScope.put("range", "[2,7)");
        engineScope.put("end", "99");
        
	    engine.eval("$m = matches($range, '(.)(\\d+),(\\d+)(.)'); $end = $m[2] - 1; println($end);", newContext);
	    
	    SleepScriptEngine se = (SleepScriptEngine) engine;
	    se.get("end");
	    
	    System.out.println("end:" + engineScope.get("end"));
	}

}
