package tv.zencoder.flix.filter;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.on2.flix.Filter;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.BuilderTestHelper;

/**
 * @author jdl
 *
 */
public class HighpassFilterBuilderTest {

    private BuilderTestHelper btHelper;

    @Before
    public void setUp() throws Exception {
	btHelper = new BuilderTestHelper();
	btHelper.setUp(new HighpassFilterBuilder());
    }

    @After
    public void tearDown() throws Exception {
	btHelper.tearDown();
	btHelper = null;
    }

    @Test
    public void testApply() {
	btHelper.apply("2000");
	Filter filter = ((FilterBuilderBase) btHelper.getFlixBuilder()).getFilter();
	try {
	    assertEquals(new Double(2000), new Double(filter.getParam(flixengine2_internalConstants.FE2_HIGHPASS_CUTOFF)));
	} catch (Exception e) {
	    fail(e.getMessage());
	    e.printStackTrace();
	}
    }

}
