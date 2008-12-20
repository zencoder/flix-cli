package tv.zencoder.flix.filter.bchs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.on2.flix.Filter;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.filter.FilterBuilderTestHelper;
import tv.zencoder.flix.util.CommandLineHelper;

public class BchsFilterBuilderTest {
    private FilterBuilderTestHelper fbtHelper;

    @Before
    public void setUp() throws Exception {
	fbtHelper = new FilterBuilderTestHelper();
	fbtHelper.setUp(new BchsFilterBuilder());
    }

    @After
    public void tearDown() throws Exception {
	fbtHelper.tearDown();
	fbtHelper = null;
    }
    
    @Test
    public void testApplyFilter() {
	// Set up a command line that should trigger children of the BchsFilterBuilder to also be executed.
	CommandLineHelper.getInstance().setArgs(new String[] {"-bchs", "-brightness", "200", "-contrast", "-100", "-hue", "80", "-saturation", "50"});
	
	Filter filter = fbtHelper.applyFilter("");
	try {
	    // Check the values that should have come from the child builders (brightness, contrast, hue, and saturation)
	    assertEquals(new Double(200),  new Double(filter.getParam(flixengine2_internalConstants.FE2_BCHS_BRIGHTNESS)));
	    assertEquals(new Double(-100), new Double(filter.getParam(flixengine2_internalConstants.FE2_BCHS_CONTRAST)));
	    assertEquals(new Double(80),   new Double(filter.getParam(flixengine2_internalConstants.FE2_BCHS_HUE)));
	    assertEquals(new Double(50),   new Double(filter.getParam(flixengine2_internalConstants.FE2_BCHS_SATURATION)));
	} catch (FlixException e) {
	    fail();
	    e.printStackTrace();
	}
    }
}
