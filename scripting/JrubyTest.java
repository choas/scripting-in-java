package scripting;

import static org.junit.Assert.*;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;

public class JrubyTest {

	@Test
	public void test() throws ScriptException, NoSuchMethodException {
		
		ScriptEngineManager manager = new ScriptEngineManager();
	    ScriptEngine engine = manager.getEngineByName("jruby");		
	    assertNotNull(engine);
	    
	    engine.eval("def sayhello(name) puts 'hello '+name \n return 42 \n end");
	    System.out.println(engine.eval("sayhello 'ruby'"));
	    Invocable inv = (Invocable) engine;
	    System.out.println(inv.invokeFunction("sayhello", "jsr223"));
	}
}
