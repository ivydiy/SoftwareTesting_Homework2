import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DataDrivenMinTest {
	private List list;
	private Object expectedResult;
	private static String NULL;
	public Class<? extends Exception> expectedException;
	public String expectedExceptionMsg;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	public DataDrivenMinTest(List data, Object result, Class ex, String msg) {
		this.list = data;
		this.expectedResult = result;
		this.expectedExceptionMsg = msg;
		this.expectedException = ex;
	}

	@Parameters public static Iterable<Object[]> parameters() {
	        return Arrays.asList(new Object[][] {     
	        	
	                 { null , null, NullPointerException.class, "NullPointerException" },
	                 {  Arrays.asList(null, "cat"), "cat", NullPointerException.class, "NullPointerException"  },
	                 {  Arrays.asList(NULL), "", NullPointerException.class, "NullPointerException"  },
	                 {  Arrays.asList("cat", "dog", 1), "cat", ClassCastException.class, "ClassCastException" },
	                 {  new ArrayList(), null, IllegalArgumentException.class, "IllegalArgumentException" },
	                 {  Arrays.asList("cat"), "cat", null, null },
	                 {  Arrays.asList("dog", "cat"), "cat", null, null },
	                 {  new ArrayList<String>() {{add("dog");add("cat");}}, "cat", null, null },
	                 {  new ArrayList<Integer>() {{add(1);add(2);}}, 1, null, null }
	           });
	        
	    }

	@Test
	public void minTest() {

		 if (expectedException != null) {

			 Assertions.assertThrows(expectedException, () -> Min.min(list));
	
			 System.out.println(MessageFormat.format("Min Test : {0} expected.",
			 expectedExceptionMsg));
		 }else {

			Object obj = Min.min(list);
			assertTrue(MessageFormat.format("{0} equals {1} is true", expectedResult, obj), expectedResult.equals(obj));
	
			System.out.println(MessageFormat.format("Min Test : {0} equals {1} is true", expectedResult, obj));
		 }

	}
}
