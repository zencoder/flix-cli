package tv.zencoder.flix.filter.overlay;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tv.zencoder.flix.BuilderTestHelper;
import tv.zencoder.flix.cli.FlixBuilder;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.CommandLineHelper;

import com.on2.flix.FE2_OverlayPositionMode;
import com.on2.flix.Filter;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

public class OverlayPositionModeFilterBuilderTest {
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
    public void testBotleft() {
	apply("botleft", FE2_OverlayPositionMode.FE2_OVERLAY_POS_MODE_BOTLEFT);
    }
    
    private void apply(String mode, FE2_OverlayPositionMode expectedModeEnum) {
	String filePath = "/tmp/foo.png";
	
	// Set up a command line that should trigger children of the BchsFilterBuilder to also be executed.
	CommandLineHelper clHelper = CommandLineHelper.getInstance();
	clHelper.setArgs(new String[] {"-fo_file", filePath, "-fo_pos_mode", mode});
	
	FlixBuilder builder = new OverlayPositionModeFilterBuilder();
	String optionArgument = clHelper.getLine().getOptionValue(builder.getSwitch());
    	builder.apply(btHelper.getFlix(), optionArgument);
    	
	Filter filter = BuilderCache.getInstance().getOverlayFilterBuilder(btHelper.getFlix()).getFilter();
	
	try {
	    assertEquals(new Double(expectedModeEnum.swigValue()), new Double(filter.getParam(flixengine2_internalConstants.FE2_OVERLAY_POS)));
	} catch (FlixException e) {
	    fail();
	    e.printStackTrace();
	}
    }
}
