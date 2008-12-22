package tv.zencoder.flix.codec;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tv.zencoder.flix.cli.CommandLineHelper;
import tv.zencoder.flix.codec.config.VideoCodecConfig;

import com.on2.flix.Codec;

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
    public void testVp6() {
	// Set up a command line so that a bitrate is also set.
	CommandLineHelper.getInstance().setArgs(new String[] {"-b", "400"});
	checkCodecParams("vp6", VideoCodecConfig.VP6);
    }
    
    @Test
    public void testH264() {
	// Set up a command line so that a bitrate is also set.
	CommandLineHelper.getInstance().setArgs(new String[] {"-b", "400"});
	checkCodecParams("h264", VideoCodecConfig.H264);
    }

    private void checkCodecParams(String options, VideoCodecConfig videoCodecConfig) {
	Codec codec = cbtHelper.apply(options);
	try {
	    double val = codec.getParam(videoCodecConfig.getFlixBitmapParamName());
	    assertEquals(new Double(400), new Double(val));
	} catch (Exception e) {
	    fail(e.getMessage());
	    e.printStackTrace();
	}
    }

}
