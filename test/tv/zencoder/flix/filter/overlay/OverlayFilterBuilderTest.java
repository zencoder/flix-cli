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
import com.on2.flix._on2bool;
import com.on2.flix.flixengine2_internalConstants;

public class OverlayFilterBuilderTest {
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
	String filePath = "/tmp/foo.png";
	
	// Set up a command line that should trigger children of the BchsFilterBuilder to also be executed.
	CommandLineHelper clHelper = CommandLineHelper.getInstance();
	clHelper.setArgs(new String[] {"-fo_file", filePath, "-fo_mask_x", "20", "-fo_mask_y", "30", "-fo_mask_r", "100", "-fo_mask_g", "150", "-fo_mask_b", "200", "-fo_pos_x", "80", "-fo_pos_y", "90"});
	
	FlixBuilder[] builders = {new OverlayFilePathFilterBuilder(), 
				  new OverlayMaskXFilterBuilder(), 
				  new OverlayMaskYFilterBuilder(), 
				  new OverlayMaskRFilterBuilder(), 
				  new OverlayMaskGFilterBuilder(), 
				  new OverlayMaskBFilterBuilder(),
				  new OverlayPositionXFilterBuilder(),
				  new OverlayPositionYFilterBuilder()};
	
	for (int i = 0; i < builders.length; i++) {
	    if (clHelper.isOptionInUse(builders[i])) {
    	    	String optionArgument = clHelper.getLine().getOptionValue(builders[i].getSwitch());
    	    	builders[i].apply(btHelper.getFlix(), optionArgument);
    	    }
	}
	
	Filter filter = BuilderCache.getInstance().getOverlayFilterBuilder(btHelper.getFlix()).getFilter();
	
	try {
	    // Check the values that should have come from the child builders
	    assertEquals(new Double(_on2bool.on2true.swigValue()), new Double(filter.getParam(flixengine2_internalConstants.FE2_OVERLAY_MASK_XY)));
	    assertEquals(new Double(_on2bool.on2true.swigValue()), new Double(filter.getParam(flixengine2_internalConstants.FE2_OVERLAY_MASK_RGB)));
	    assertEquals(new Double(FE2_OverlayPositionMode.FE2_OVERLAY_POS_MODE_XY.swigValue()), new Double(filter.getParam(flixengine2_internalConstants.FE2_OVERLAY_POS)));
	    assertEquals(new Double(20),  new Double(filter.getParam(flixengine2_internalConstants.FE2_OVERLAY_MASK_X)));
	    assertEquals(new Double(30),  new Double(filter.getParam(flixengine2_internalConstants.FE2_OVERLAY_MASK_Y)));
	    assertEquals(new Double(100),  new Double(filter.getParam(flixengine2_internalConstants.FE2_OVERLAY_MASK_R)));
	    assertEquals(new Double(150),  new Double(filter.getParam(flixengine2_internalConstants.FE2_OVERLAY_MASK_G)));
	    assertEquals(new Double(200),  new Double(filter.getParam(flixengine2_internalConstants.FE2_OVERLAY_MASK_B)));
	    assertEquals(new Double(80),  new Double(filter.getParam(flixengine2_internalConstants.FE2_OVERLAY_POS_X)));
	    assertEquals(new Double(90),  new Double(filter.getParam(flixengine2_internalConstants.FE2_OVERLAY_POS_Y)));
	} catch (FlixException e) {
	    fail();
	    e.printStackTrace();
	}
    }

}
