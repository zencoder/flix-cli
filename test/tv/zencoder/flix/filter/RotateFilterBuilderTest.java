package tv.zencoder.flix.filter;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tv.zencoder.flix.BuilderTestHelper;

import com.on2.flix.Filter;
import com.on2.flix.flixengine2_internalConstants;

public class RotateFilterBuilderTest {
    private BuilderTestHelper btHelper;

    @Before
    public void setUp() throws Exception {
	btHelper = new BuilderTestHelper();
	btHelper.setUp(new RotateFilterBuilder());
    }

    @After
    public void tearDown() throws Exception {
	btHelper.tearDown();
	btHelper = null;
    }


    @Test
    public void testApply() {
	btHelper.apply("90");
	Filter filter = ((FilterBuilderBase) btHelper.getFlixBuilder()).getFilter();
	try {
	    double angle = filter.getParam(flixengine2_internalConstants.FE2_ROTATE_ANGLE);
	    assertEquals(new Double(90.0), new Double(angle));
	} catch (Exception e) {
	    fail(e.getMessage());
	    e.printStackTrace();
	}
    }

}
