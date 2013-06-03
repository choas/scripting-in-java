package scripting;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;


import javax.script.*;

public class JavascriptTest {

	private static final String SRC_SCRIPT_JS = "src/scripting/script.js";
	
	private ScriptEngineManager manager;
	private ScriptEngine engine;
	private Invocable inv;

	@Before
	public void initEngine() {
		manager = new ScriptEngineManager();
		engine = manager.getEngineByName("JavaScript");		
		assertNotNull(engine);
		engine.put("z1", 6);
		engine.put("z2", 7);
		inv = (Invocable) engine;
	}
	
	@Test
	public void testJavaScriptSimple() throws ScriptException {
			assertEquals(42.0, engine.eval("6 * 7"));
	}

	@Test
	public void testVariables() throws ScriptException {
		assertEquals(42.0, engine.eval("z1 * z2"));
	}
	
	@Test
	public void testFunctionWithParams() throws ScriptException {
		assertEquals(42.0, engine.eval("function mul(p1, p2) { return p1*p2} mul(z1, z2)"));
	}

	@Test
	public void testJsFile() throws ScriptException, FileNotFoundException {
		engine.eval(new java.io.FileReader(SRC_SCRIPT_JS));
		assertEquals("JavaScript", engine.get("hello"));
	}
	@Test
	public void testInvoce() throws ScriptException, FileNotFoundException, NoSuchMethodException {
		engine.eval(new java.io.FileReader(SRC_SCRIPT_JS));
		assertEquals("JavaScript says 'hello' to Hugo.", sayHello("Hugo"));
	}

	private String sayHello(String name) throws ScriptException, NoSuchMethodException {
		return (String)inv.invokeFunction("sayHello", name);
	}
}
