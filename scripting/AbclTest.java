package scripting;

import static org.junit.Assert.*;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;


public class AbclTest {

	@Test
	public void test() throws ScriptException, NoSuchMethodException {
		
		ScriptEngineManager manager = new ScriptEngineManager();
	    ScriptEngine engine = manager.getEngineByName("Lisp");		
	    assertNotNull(engine);
	    
	    // taken from https://github.com/rudi/abcl/blob/master/abcl/examples/jsr-223/JSR223Example.java
	    
	    System.out.println();
		System.out.println("*package* = " + engine.get("*package*"));
		Object someValue = new Object();
		engine.put("someVariable", someValue);
		System.out.println("someVariable = " + engine.get("someVariable"));
	    
	    engine.eval("(defun hello (arg) (print (list arg someVariable)) (terpri))");

	    Invocable inv = (Invocable)engine;
	    
		inv.invokeFunction("hello", "world");
		
		//Implementing a Java interface in Lisp
		engine.eval("(defun compare-to (&rest args) 42)");
		Comparable c = ((Invocable) engine).getInterface(java.lang.Comparable.class);
		System.out.println("compareTo: " + c.compareTo(null));

		//Compilation!
		engine.eval("(defmacro slow-compiling-macro (arg) (dotimes (i 1000000) (incf i)) `(print ,arg))");

		long millis = System.currentTimeMillis();
		engine.eval("(slow-compiling-macro 42)");
		millis = System.currentTimeMillis() - millis;
		System.out.println("interpretation took " + millis);

		millis = System.currentTimeMillis();
		CompiledScript cs = ((Compilable) engine).compile("(slow-compiling-macro 42)");
		millis = System.currentTimeMillis() - millis;
		System.out.println("compilation took " + millis);

		millis = System.currentTimeMillis();
		cs.eval();
		millis = System.currentTimeMillis() - millis;
		System.out.println("evaluation took " + millis);

		millis = System.currentTimeMillis();
		cs.eval();
		millis = System.currentTimeMillis() - millis;
		System.out.println("evaluation took " + millis);
	    
	}

}
