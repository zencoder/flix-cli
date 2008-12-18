package tv.zencoder.flix.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.on2.flix.Filter;
import com.on2.flix.flixengine2_internalConstants;

public class FramerateFilterBuilderTest extends FilterBuilderTest {

	@Before
	public void setUp() throws Exception {
		super.setUp();
	    filterBuilder = new FramerateFilterBuilder();
	}
	
	@Test
	public void testApplyFilter() {
		Filter filter = filterBuilder.applyFilter(flix, "25");
		try {
			double fps = filter.getParam(flixengine2_internalConstants.FE2_FRAMERATE_FPS);
			assertEquals(new Double(25.0), new Double(fps));
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

}
