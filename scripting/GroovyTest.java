package scripting;

import static org.junit.Assert.*;

import groovy.lang.GroovyShell;

import java.util.List;

import javax.script.ScriptEngineManager;

import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;
import org.junit.Test;

public class GroovyTest {

	String myScript = "println('Hello World')\n  return [1, 2, 3]";

	@Test
	public void testBSF() throws BSFException {
		// groovy-bsf-2.1.3.jar groovy-2.1.3.jar asm-4.0.jar antlr-2.7.7.jar
		
		BSFManager manager = new BSFManager();
		List<?> answer = (List<?>) manager.eval("groovy", "myScript.groovy", 0, 0, myScript);
		assertEquals(3, answer.size());
	}
	
	@Test
	public void testJSR223() {
		
		ScriptEngineManager manager = new ScriptEngineManager();
		Object engine = manager.getEngineByName("groovy");		
		assertNotNull(engine);
	}
	
	@Test
	public void testGroovyScripting() {
		// http://www.javaworld.com/javaworld/jw-11-2007/jw-11-jsr223.html?page=2
		GroovyShell gs = new GroovyShell();
		List<?> answer = (List<?>) gs.evaluate(myScript);
		assertEquals(3, answer.size());
	}
	
	/*
	 
	 http://www.javaworld.com/javaworld/jw-11-2007/jw-11-jsr223.html?page=2
	 
// run a Ruby scripting  (JRuby V1.1b1)
Ruby runtime = Ruby.getDefaultInstance();
runtime.executeScript(scripting, filename);

// run a Groovy scripting (Groovy V1.1)
GroovyShell gs = new GroovyShell();
gs.evaluate(scripting);

// run a Python scripting (jython V2.2)
PythonInterpreter interp = new PythonInterpreter();
interp.exec(scripting)	 
	 
	 
	 */
	
	
}
