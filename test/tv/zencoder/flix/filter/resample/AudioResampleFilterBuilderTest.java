package tv.zencoder.flix.filter.resample;


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
import com.on2.flix.flixengine2_internalConstants;

public class AudioResampleFilterBuilderTest {
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
	// Set up a command line that should trigger children of this builder.
	CommandLineHelper clHelper = CommandLineHelper.getInstance();
	clHelper.setArgs(new String[] {"-ar", "11025", "-ac", "2"});
	
	
	FlixBuilder[] builders = {new AudioResampleRateFilterBuilder(), new AudioResampleChannelsFilterBuilder()};
	
	for (int i = 0; i < builders.length; i++) {
	    if (clHelper.isOptionInUse(builders[i])) {
    	    	String optionArgument = clHelper.getLine().getOptionValue(builders[i].getSwitch());
    	    	builders[i].apply(btHelper.getFlix(), optionArgument);
    	    }
	}
	
	Filter filter = BuilderCache.getInstance().getAudioResampleFilterBuilder(btHelper.getFlix()).getFilter();
	
	try {
	    assertEquals(new Double(11025), new Double(filter.getParam(flixengine2_internalConstants.FE2_RESAMPLE_RATE)));
	    assertEquals(new Double(2), new Double(filter.getParam(flixengine2_internalConstants.FE2_RESAMPLE_CHANNELS)));
	} catch (FlixException e) {
	    fail();
	    e.printStackTrace();
	}
    }
    
}
