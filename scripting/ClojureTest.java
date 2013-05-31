package scripting;

import static org.junit.Assert.*;

import javax.script.ScriptEngineManager;

import org.junit.Test;

public class ClojureTest {

	@Test
	public void test() {
		ScriptEngineManager manager = new ScriptEngineManager();
		Object engine = manager.getEngineByName("Clojure");		
		assertNotNull(engine);
	}

}
