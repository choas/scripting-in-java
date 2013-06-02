package scripting;

import static org.junit.Assert.*;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;

public class JaskellTest {

	@Test
	public void test() throws ScriptException, NoSuchMethodException {
		
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("jaskell");
		
		
		assertNotNull(engine);
		Invocable inv = (Invocable) engine;
//		engine.eval("factorial n = if n > 0 then n * factorial (n-1) else 1");
		engine.eval("contains range point = if point >= 2 then true else false;");
		Object factorial = engine.eval("contains \"[2,6)\" (-4)");
//				engine.eval("factorial 4");
		System.out.println(factorial);
//		inv.invokeMethod(engine, "factorial", 4);
		System.out.println(inv.invokeFunction("assertTrue", 4));
	}

}
