package tv.zencoder.flix.filter;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tv.zencoder.flix.BuilderTestHelper;

import com.on2.flix.Filter;
import com.on2.flix.deintmode_t;
import com.on2.flix.flixengine2_internalConstants;

/**
 * @author jdl
 *
 */
public class DeinterlaceFilterBuilderTest {

    private BuilderTestHelper btHelper;

    @Before
    public void setUp() throws Exception {
	btHelper = new BuilderTestHelper();
	btHelper.setUp(new DeinterlaceFilterBuilder());
    }

    @After
    public void tearDown() throws Exception {
	btHelper.tearDown();
	btHelper = null;
    }


    @Test
    public void testApply() {
	checkDeinterlaceType("a", new Double(deintmode_t.DEINTERLACE_ADAPTIVE.swigValue()));
	checkDeinterlaceType("b", new Double(deintmode_t.DEINTERLACE_1_2_1_BLUR.swigValue()));
	checkDeinterlaceType("d", new Double(deintmode_t.DEINTERLACE_DROP_FIELD.swigValue()));
    }

    private void checkDeinterlaceType(String filterOptions, Double expectedValue) {
	btHelper.apply(filterOptions);
	Filter filter = ((FilterBuilderBase) btHelper.getFlixBuilder()).getFilter();
	try {
	    double val = filter.getParam(flixengine2_internalConstants.FE2_ADAPTIVE_DEINTERLACE_MODE);
	    assertEquals(expectedValue, new Double(val));
	} catch (Exception e) {
	    fail(e.getMessage());
	    e.printStackTrace();
	}
    }

}
