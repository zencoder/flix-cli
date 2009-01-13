package tv.zencoder.flix.codec.video;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tv.zencoder.flix.BuilderTestHelper;
import tv.zencoder.flix.codec.CodecBuilderBase;
import tv.zencoder.flix.codec.video.VideoCodecBuilder;
import tv.zencoder.flix.util.CommandLineHelper;
import tv.zencoder.flix.util.VideoCodecConfig;

import com.on2.flix.Codec;
import com.on2.flix.FE2_CompressMode;

public class VideoCodecBuilderTest {

    private BuilderTestHelper builderTestHelper;

    @Before
    public void setUp() throws Exception {
	builderTestHelper = new BuilderTestHelper();
	builderTestHelper.setUp(new VideoCodecBuilder());
    }

    @After
    public void tearDown() throws Exception {
	builderTestHelper.tearDown();
	builderTestHelper = null;
    }

    @Test
    public void testVp6() {
	// Set up a command line so that a bitrate is also set.
	CommandLineHelper.getInstance().setArgs(new String[] {"-b", "400", "-vcompress", "best"});
	checkCodecParams("vp6", VideoCodecConfig.VP6);
	
	
	Codec codec = ((CodecBuilderBase) builderTestHelper.getFlixBuilder()).getCodec();
	try {
	    double val = codec.getParam(VideoCodecConfig.VP6.getFlixCompressModeParamName());
	    assertEquals(new Double(FE2_CompressMode.COMPRESSMODE_BEST.swigValue()), new Double(val));
	} catch (Exception e) {
	    fail(e.getMessage());
	    e.printStackTrace();
	}
    }
    
    @Test
    public void testH264() {
	// Set up a command line so that a bitrate is also set.
	CommandLineHelper.getInstance().setArgs(new String[] {"-b", "400"});
	checkCodecParams("h264", VideoCodecConfig.H264);
    }

    private void checkCodecParams(String options, VideoCodecConfig videoCodecConfig) {
	builderTestHelper.apply(options);
	Codec codec = ((CodecBuilderBase) builderTestHelper.getFlixBuilder()).getCodec();
	try {
	    double val = codec.getParam(videoCodecConfig.getFlixBitrateParamName());
	    assertEquals(new Double(400), new Double(val));
	} catch (Exception e) {
	    fail(e.getMessage());
	    e.printStackTrace();
	}
    }

}
