package tv.zencoder.flix.codec;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tv.zencoder.flix.cli.CommandLineHelper;

import com.on2.flix.Codec;
import com.on2.flix.flixengine2_internalConstants;

public class VideoCodecBuilderTest {

    private CodecBuilderTestHelper cbtHelper;

    @Before
    public void setUp() throws Exception {
	cbtHelper = new CodecBuilderTestHelper();
	cbtHelper.setUp(new VideoCodecBuilder());
    }

    @After
    public void tearDown() throws Exception {
	cbtHelper.tearDown();
	cbtHelper = null;
    }

    @Test
    public void testApply() {
	// Set up a command line so that a bitrate is also set.
	CommandLineHelper.getInstance().setArgs(new String[] {"-b", "400"});
	
	Codec codec = cbtHelper.apply("vp6");
	try {
	    double val = codec.getParam(flixengine2_internalConstants.FE2_VP6_BITRATE);
	    assertEquals(new Double(400), new Double(val));
	} catch (Exception e) {
	    fail(e.getMessage());
	    e.printStackTrace();
	}
    }

}
