package tv.zencoder.flix.filter.thumbnail;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tv.zencoder.flix.BuilderTestHelper;
import tv.zencoder.flix.cli.FlixBuilder;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.CommandLineHelper;

import com.on2.flix.FE2_PNGExCuePtMode;
import com.on2.flix.Filter;
import com.on2.flix._on2bool;
import com.on2.flix.flixengine2_internalConstants;

public class ThumbnailFilterBuilderTest {
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
	// Set up a command line that should trigger children of the ThumbnailFilterBuilder to also be executed.
	CommandLineHelper clHelper = CommandLineHelper.getInstance();
	clHelper.setArgs(new String[] {"-pngex_auto_count",         "5000",
		                       "-pngex_auto_end_time",      "6000",
		                       "-pngex_auto_random_period", "2",
		                       "-pngex_auto_start_time",    "100",
		                       "-pngex_compression_level",  "5",
		                       "-pngex_enable_alpha",
		                       "-pngex_export_cue_points",  "event",
		                       "-pngex_export_first_frame",
		                       "-pngex_export_interval",    "500",
		                       "-pngex_export_time_string", "30,40,50",
		                       "-pngex_filename_prefix",    "PRE",
		                       "-pngex_filename_suffix",    "SUF",
		                       "-pngex_height",             "360",
		                       "-pngex_width",              "480",
		                       "-pngex_output_dir",         "/tmp/foo/thumbs"
	});
	
	FlixBuilder[] builders = {
		new ThumbnailEnableAlphaFilterBuilder(),
		new ThumbnailAutoCountFilterBuilder(),
		new ThumbnailAutoEndTimeFilterBuilder(),
		new ThumbnailAutoRandomPeriodFilterBuilder(),
		new ThumbnailAutoStartTimeFilterBuilder(),
		new ThumbnailCompressionFilterBuilder(),
		new ThumbnailExportCuePointsFilterBuilder(),
		new ThumbnailExportFirstFrameFilterBuilder(),
		new ThumbnailExportIntervalFilterBuilder(),
		new ThumbnailExportTimeStringFilterBuilder(),
		new ThumbnailFilenamePrefixFilterBuilder(),
		new ThumbnailFilenameSuffixFilterBuilder(),
		new ThumbnailHeightFilterBuilder(),
		new ThumbnailOutputDirectoryFilterBuilder(),
		new ThumbnailWidthFilterBuilder()	
	};
	
	for (int i = 0; i < builders.length; i++) {
	    if (clHelper.isOptionInUse(builders[i])) {
    	    	String optionArgument = clHelper.getLine().getOptionValue(builders[i].getSwitch());
    	    	builders[i].apply(btHelper.getFlix(), optionArgument);
    	    }
	}
	
	Filter filter = BuilderCache.getInstance().getThumbnailFilterBuilder(btHelper.getFlix()).getFilter();
	
	try {
	    // Check the values that should have come from the child builders 
	    assertEquals(new Double(5000), new Double(filter.getParam(flixengine2_internalConstants.FE2_PNGEX_AUTO_EXPORT_COUNT)));
	    assertEquals(new Double(6000), new Double(filter.getParam(flixengine2_internalConstants.FE2_PNGEX_AUTO_EXPORT_END_TIME)));
	    assertEquals(new Double(2), new Double(filter.getParam(flixengine2_internalConstants.FE2_PNGEX_AUTO_EXPORT_RANDOM_PERIOD)));
	    assertEquals(new Double(100), new Double(filter.getParam(flixengine2_internalConstants.FE2_PNGEX_AUTO_EXPORT_START_TIME)));
	    assertEquals(new Double(5), new Double(filter.getParam(flixengine2_internalConstants.FE2_PNGEX_COMPRESSION_LEVEL)));
	    assertEquals(new Double(_on2bool.on2true.swigValue()), new Double(filter.getParam(flixengine2_internalConstants.FE2_PNGEX_ENABLE_ALPHA)));
	    assertEquals(new Double(FE2_PNGExCuePtMode.FE2_PNGEX_CP_EVENT.swigValue()), new Double(filter.getParam(flixengine2_internalConstants.FE2_PNGEX_EXPORT_CUE_POINTS)));
	    assertEquals(new Double(_on2bool.on2true.swigValue()), new Double(filter.getParam(flixengine2_internalConstants.FE2_PNGEX_EXPORT_FIRST_FRAME_PNG)));
	    assertEquals(new Double(500), new Double(filter.getParam(flixengine2_internalConstants.FE2_PNGEX_EXPORT_INTERVAL)));
	    
	    // TODO: This is currently bugged in flix engine 8.0.16.  Uncomment after the bug is fixed.
	    // assertEquals(new Double(360), new Double(filter.getParam(flixengine2_internalConstants.FE2_PNGEX_HEIGHT)));
	    // assertEquals(new Double(480), new Double(filter.getParam(flixengine2_internalConstants.FE2_PNGEX_WIDTH)));
	} catch (Exception e) {
	    fail();
	    e.printStackTrace();
	}
    }
}
