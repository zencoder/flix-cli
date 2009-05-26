package tv.zencoder.flix.filter.mirror;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tv.zencoder.flix.BuilderTestHelper;
import tv.zencoder.flix.cli.FlixBuilder;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.CommandLineHelper;

import com.on2.flix.Filter;
import com.on2.flix.FlixException;
import com.on2.flix._on2bool;
import com.on2.flix.flixengine2_internalConstants;

public class MirrorFilterBuilderTest {
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
	// Set up a command line that should trigger children of the MirrorFilterBuilder to also be executed.
	CommandLineHelper clHelper = CommandLineHelper.getInstance();
	clHelper.setArgs(new String[] {"-mirror_h", "-mirror_v"});
	
	FlixBuilder[] builders = {new MirrorHorizontalFilterBuilder(), new MirrorVerticalFilterBuilder()};
	
	for (int i = 0; i < builders.length; i++) {
	    if (clHelper.isOptionInUse(builders[i])) {
    	    	String optionArgument = clHelper.getLine().getOptionValue(builders[i].getSwitch());
    	    	builders[i].apply(btHelper.getFlix(), optionArgument);
    	    }
	}
	
	Filter filter = BuilderCache.getInstance().getMirrorFilterBuilder(btHelper.getFlix()).getFilter();
	
	try {
	    // Check the values that should have come from the child builders 
	    assertEquals(new Double(_on2bool.on2true.swigValue()), new Double(filter.getParam(flixengine2_internalConstants.FE2_MIRROR_HORIZONTAL)));
	    assertEquals(new Double(_on2bool.on2true.swigValue()), new Double(filter.getParam(flixengine2_internalConstants.FE2_MIRROR_VERTICAL)));
	} catch (FlixException e) {
	    fail();
	    e.printStackTrace();
	}
    }

}
