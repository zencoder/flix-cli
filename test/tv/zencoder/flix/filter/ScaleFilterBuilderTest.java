package tv.zencoder.flix.filter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.on2.flix.Filter;
import com.on2.flix.flixengine2_internalConstants;

public class ScaleFilterBuilderTest extends FilterBuilderTest {

	@Before
	public void setUp() throws Exception {
		super.setUp();
		filterBuilder = new ScaleFilterBuilder();
	}

	@Test
	public void testApplyFilter() {
		Filter filter = filterBuilder.applyFilter(flix, "480x320");
		try {
			assertEquals(new Double(480), new Double(filter.getParam(flixengine2_internalConstants.FE2_SCALE_WIDTH)));
			assertEquals(new Double(320), new Double(filter.getParam(flixengine2_internalConstants.FE2_SCALE_HEIGHT)));
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

}
