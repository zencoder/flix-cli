package tv.zencoder.flix.filter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.on2.flix.Filter;
import com.on2.flix.flixengine2_internalConstants;

public class ScaleFilterBuilderTest {

	private FilterBuilderTestHelper fbtHelper;
	
	@Before
	public void setUp() throws Exception {
		fbtHelper = new FilterBuilderTestHelper();
		fbtHelper.setUp(new ScaleFilterBuilder());
	}
	
	public void tearDown() throws Exception {
		fbtHelper.tearDown();
		fbtHelper = null;
	}

	@Test
	public void testApplyFilter() {
		Filter filter = fbtHelper.getFilterBuilder().applyFilter(fbtHelper.getFlix(), "480x320");
		try {
			assertEquals(new Double(480), new Double(filter.getParam(flixengine2_internalConstants.FE2_SCALE_WIDTH)));
			assertEquals(new Double(320), new Double(filter.getParam(flixengine2_internalConstants.FE2_SCALE_HEIGHT)));
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

}
