package scripting;

import static org.junit.Assert.*;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;

import bsh.Primitive;
import bsh.UtilEvalError;

public class JavaTest {

	@Test
	public void test() throws ScriptException, NoSuchMethodException, UtilEvalError {
		ScriptEngineManager manager = new ScriptEngineManager();
	    ScriptEngine engine = manager.getEngineByName("java"); // using beanshell
	    assertNotNull(engine);
	    
	    engine.eval("int saySomething(String some) { System.out.println(some); int thing = 42; return thing;}");
	    Object r = engine.eval("saySomething(\"java\");");
	    System.out.println(r);
	    Invocable inv = (Invocable) engine;
	    r = inv.invokeFunction("saySomething", "JAVA");
	    Primitive p = (Primitive)r;
	    assertEquals(42, p.intValue());
	}

}
