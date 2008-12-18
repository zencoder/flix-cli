package tv.zencoder.flix.filter;

import org.junit.Before;

import com.on2.flix.FlixEngine2;

/**
 * Common setUp() and tearDown() code for the filter tests.
 * @author jdl
 *
 */
public abstract class FilterBuilderTest {
	  
	protected FlixEngine2 flix;
	protected FilterBuilder filterBuilder;
		
	  
	@Before
	public void setUp() throws Exception {
		flix = new FlixEngine2("localhost", 0);
		flix.Connect();
	}
	
	public void tearDown() throws Exception {
		flix.Destroy();
		flix = null;
		filterBuilder = null;
	}
}
