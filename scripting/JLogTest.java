package scripting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Hashtable;

import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;
import org.apache.bsf.util.IOUtils;
import org.junit.Test;

import ubc.cs.JLog.Foundation.iPrologFileServices;
import ubc.cs.JLog.Foundation.jPrologAPI;
import ubc.cs.JLog.Foundation.jPrologFileServices;

public class JLogTest {

	private static final String SCRIPT = "src/scripting/bsf_pi.plog";
	String myScript = 
			"f2c(Start, End) :-\n" +
			"	Start =< End,\n" +
			"	bsf_lookup('out', OUT),\n" +
			"	bsf_static('Math', MATH),\n" +
			"	bsf_invoke(_, OUT, 'print', ['<tr><td>']),\n" +
			"	bsf_invoke(_, OUT, 'print', [Start]),\n" +
			"	bsf_invoke(_, OUT, 'print', ['</td><td>']),\n" +
			"	T is (Start-32) * 5/9,\n" +
			"	bsf_invoke(T1, MATH, 'round', [T]),\n" +
			"	bsf_invoke(_, OUT, 'print', [T1]),\n" +
			"	bsf_invoke(_, OUT, 'println', ['</td></tr>']),\n" +
			"	Start1 is Start + 10,\n" +
			"	f2c(Start1, End).\n" +
			"f2c(30, 100).\n";

	
	@Test
	public void testBSF() throws BSFException, IOException {
		BSFManager manager = new BSFManager();
		
	      FileReader in = new FileReader(SCRIPT);
	      String script = IOUtils.getStringFromReader(in);
     



	      manager.registerBean("B", 4);
	      
//	      manager.registerBean("ChildOne", "sally");
//	      manager.registerBean("ChildTwo", "trude");
	      
	      manager.registerBean("out", System.out);
	      
	      manager.exec("prolog", SCRIPT, 0, 0, script);
	      String query = "sibling(ChildOne, ChildTwo), parent_child(Z, ChildOne), parent_child(Z, ChildTwo).";

	      query = "member('berta', ['anton', 'berta', 'caesar']).";
	      
	      System.out.println(manager.eval("prolog", SCRIPT, 0, 0, query)); //"queens(7,P)."));

	      System.out.println("-------------------");
	      
	      
	      iPrologFileServices   fs = new jPrologFileServices();
	      PrintWriter			out = new PrintWriter(System.out);
	      
	      BufferedReader		inx = new BufferedReader(new InputStreamReader(System.in));
	      InputStream			is = new FileInputStream(new File(SCRIPT));
	      jPrologAPI			api = new jPrologAPI(script); //is,fs,out,inx,null);
	      
	      
	      Hashtable				bindings = new Hashtable();
	      Hashtable				result;
	      StringBuffer			qbuff = new StringBuffer();

//	      System.out.println(api.getRequiredCreditInfo());

//	      if (argv.length > 0)
	       bindings.put("B",new Integer(Integer.parseInt("8")));

//	      qbuff.append("(var(B), write('enter board size:'), read(B) ; true), ! , queens(B,X).");
	       
	      qbuff.append(query); //"queens(B,X).");
	      result = api.query(qbuff.toString(),bindings);
	      while (result != null)
	      {
	       System.out.println("The result is: " + result); //.get("X").toString());
	       
	       result = api.retry();
	      }
	      
	      
	      
	      
//		List<?> answer = (List<?>) manager.eval("prolog", "myScript.groovy", 0, 0, myScript);
//				new java.io.FileReader(SCRIPT));
//		assertEquals(3, answer.size());
	}
	
//	@Test
//	public void testJSR223() {
//		
//		ScriptEngineManager manager = new ScriptEngineManager();
//		Object engine = manager.getEngineByName("groovy");		
//		assertNotNull(engine);
//	}
//	
//	@Test
//	public void testGroovyScripting() {
//		// http://www.javaworld.com/javaworld/jw-11-2007/jw-11-jsr223.html?page=2
//		GroovyShell gs = new GroovyShell();
//		List<?> answer = (List<?>) gs.evaluate(myScript);
//		assertEquals(3, answer.size());
//	}
	
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
