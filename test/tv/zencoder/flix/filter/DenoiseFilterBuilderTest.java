package tv.zencoder.flix.filter;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tv.zencoder.flix.BuilderTestHelper;

import com.on2.flix.Filter;
import com.on2.flix.flixengine2_internalConstants;

public class DenoiseFilterBuilderTest {
    private BuilderTestHelper btHelper;

    @Before
    public void setUp() throws Exception {
	btHelper = new BuilderTestHelper();
	btHelper.setUp(new DenoiseFilterBuilder());
    }

    @After
    public void tearDown() throws Exception {
	btHelper.tearDown();
	btHelper = null;
    }

    @Test
    public void testApply() {
	btHelper.apply("0.5");
	Filter filter = ((FilterBuilderBase) btHelper.getFlixBuilder()).getFilter();
	try {
	    assertEquals(new Double(0.5), new Double(filter.getParam(flixengine2_internalConstants.FE2_DENOISE_NOISE_LEVEL)));
	} catch (Exception e) {
	    fail(e.getMessage());
	    e.printStackTrace();
	}
    }

}
