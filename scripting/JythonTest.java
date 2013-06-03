package scripting;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;

public class JythonTest {

	// taken from http://groovy.codehaus.org/JSR-223+access+to+other+JVM+languages
	String script = "" +	
	"def factorial(n):\n" +
	"    i=fact=1\n" +
	"    while i <= n:\n" +
	"        fact=fact*i\n" +
	"        i=i+1\n" +
	"    return fact\n" +
	"result = factorial(4)\n";
	
	@Test
	public void test() throws ScriptException, NoSuchMethodException {
		ScriptEngineManager manager = new ScriptEngineManager();
	    ScriptEngine engine = manager.getEngineByName("jython");		
	    assertNotNull(engine);
	    
	    engine.eval(script);
	    Invocable inv = (Invocable) engine;
	    assertEquals(24, inv.invokeFunction("factorial", 4));
	}

}
