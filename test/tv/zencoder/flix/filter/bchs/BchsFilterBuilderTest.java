package tv.zencoder.flix.filter.bchs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tv.zencoder.flix.BuilderTestHelper;
import tv.zencoder.flix.cli.FlixBuilder;
import tv.zencoder.flix.util.CommandLineHelper;

import com.on2.flix.Filter;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

public class BchsFilterBuilderTest {
    private BuilderTestHelper btHelper;

    @Before
    public void setUp() throws Exception {
	btHelper = new BuilderTestHelper();
	btHelper.setUp();
    }

    @After
    public void tearDown() throws Exception {
	btHelper.tearDown();
	btHelper = null;
    }
    
    @Test
    public void testApply() {
	// Set up a command line that should trigger children of the BchsFilterBuilder to also be executed.
	CommandLineHelper clHelper = CommandLineHelper.getInstance();
	clHelper.setArgs(new String[] {"-bchs", "-brightness", "200", "-contrast", "-100", "-hue", "80", "-saturation", "50"});
	
	
	FlixBuilder[] builders = {new BrightnessFilterBuilder(), new ContrastFilterBuilder(), new HueFilterBuilder(), new SaturationFilterBuilder()};
	
	for (int i = 0; i < builders.length; i++) {
	    if (clHelper.isOptionInUse(builders[i])) {
    	    	String optionArgument = clHelper.getLine().getOptionValue(builders[i].getSwitch());
    	    	builders[i].apply(btHelper.getFlix(), optionArgument);
    	    }
	}
	
	Filter filter = clHelper.getBchsFilterBuilder(btHelper.getFlix()).getFilter();
	
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
