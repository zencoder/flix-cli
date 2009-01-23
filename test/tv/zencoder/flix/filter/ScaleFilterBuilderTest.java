package tv.zencoder.flix.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tv.zencoder.flix.BuilderTestHelper;

import com.on2.flix.Filter;
import com.on2.flix.flixengine2_internalConstants;

public class ScaleFilterBuilderTest {

    private BuilderTestHelper btHelper;

    @Before
    public void setUp() throws Exception {
	btHelper = new BuilderTestHelper();
	btHelper.setUp(new ScaleFilterBuilder());
    }

    @After
    public void tearDown() throws Exception {
	btHelper.tearDown();
	btHelper = null;
    }

    @Test
    public void testWidthAndHeight() {
	btHelper.apply("480x320");
	Filter filter = ((FilterBuilderBase) btHelper.getFlixBuilder()).getFilter();
	try {
	    assertEquals(new Double(480), new Double(filter.getParam(flixengine2_internalConstants.FE2_SCALE_WIDTH)));
	    assertEquals(new Double(320), new Double(filter.getParam(flixengine2_internalConstants.FE2_SCALE_HEIGHT)));
	} catch (Exception e) {
	    fail(e.getMessage());
	    e.printStackTrace();
	}
    }

    /*
     * Flix will accept 0, -1, -2, or -3 for a width or height, each of which has a special meaning.
     * Make sure that negative numbers get passed through OK.
     */
    
    @Test
    public void testWidthIsSpecial() {
	btHelper.apply("-2x320");
	Filter filter = ((FilterBuilderBase) btHelper.getFlixBuilder()).getFilter();
	try {
	    assertEquals(new Double(-2), new Double(filter.getParam(flixengine2_internalConstants.FE2_SCALE_WIDTH)));
	    assertEquals(new Double(320), new Double(filter.getParam(flixengine2_internalConstants.FE2_SCALE_HEIGHT)));
	} catch (Exception e) {
	    fail(e.getMessage());
	    e.printStackTrace();
	}
    }

    @Test
    public void testHeightIsSpecial() {
	btHelper.apply("480x-2");
	Filter filter = ((FilterBuilderBase) btHelper.getFlixBuilder()).getFilter();
	try {
	    assertEquals(new Double(480), new Double(filter.getParam(flixengine2_internalConstants.FE2_SCALE_WIDTH)));
	    assertEquals(new Double(-2), new Double(filter.getParam(flixengine2_internalConstants.FE2_SCALE_HEIGHT)));
	} catch (Exception e) {
	    fail(e.getMessage());
	    e.printStackTrace();
	}
    }

    
}
