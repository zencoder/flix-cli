package tv.zencoder.flix.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.on2.flix.Filter;
import com.on2.flix.flixengine2_internalConstants;

public class FramerateFilterBuilderTest {

    private FilterBuilderTestHelper fbtHelper;

    @Before
    public void setUp() throws Exception {
	fbtHelper = new FilterBuilderTestHelper();
	fbtHelper.setUp(new FramerateFilterBuilder());
    }

    public void tearDown() throws Exception {
	fbtHelper.tearDown();
	fbtHelper = null;
    }


    @Test
    public void testApplyFilter() {
	Filter filter = fbtHelper.getFilterBuilder().applyFilter(fbtHelper.getFlix(), "25");
	try {
	    double fps = filter.getParam(flixengine2_internalConstants.FE2_FRAMERATE_FPS);
	    assertEquals(new Double(25.0), new Double(fps));
	} catch (Exception e) {
	    fail(e.getMessage());
	    e.printStackTrace();
	}
    }

}
