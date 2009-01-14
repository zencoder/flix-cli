package tv.zencoder.flix.codec.video;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tv.zencoder.flix.BuilderTestHelper;
import tv.zencoder.flix.codec.CodecBuilderBase;
import tv.zencoder.flix.util.CommandLineHelper;
import tv.zencoder.flix.util.VideoCodecConfig;

import com.on2.flix.Codec;
import com.on2.flix.FE2_CompressMode;
import com.on2.flix.FE2_VideoKeyframeTypes;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;
import com.on2.flix.h264profile_t;
import com.on2.flix.vp6profile_t;

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
	CommandLineHelper.getInstance().setArgs(new String[] {"-b", "400", "-vcompress", "best", "-vprofile", "vp6s", "-vkftype", "fixed"});
	Codec codec = checkCodecParams("vp6", VideoCodecConfig.VP6);
	
	try {
	    assertEquals(new Double(vp6profile_t.VP6_S.swigValue()), new Double(codec.getParam(VideoCodecConfig.VP6.getFlixProfileParamName())));
	} catch (FlixException e) {
	    fail(e.getMessage());
	    e.printStackTrace();
	}
    }
    
    @Test
    public void testVp6a() {
	CommandLineHelper.getInstance().setArgs(new String[] {"-b", "400", "-vcompress", "best", "-vkftype", "fixed"});
	checkCodecParams("vp6a", VideoCodecConfig.VP6A);
    }
    
    @Test
    public void testH263() {
	CommandLineHelper.getInstance().setArgs(new String[] {"-b", "400", "-vcompress", "best", "-vkftype", "fixed"});
	checkCodecParams("h263", VideoCodecConfig.H263);
    }
    
    @Test
    public void testH264() {
	CommandLineHelper.getInstance().setArgs(new String[] {"-b", "400", "-vprofile", "h264high", "-r_h264b", "5", "-vkftype", "fixed"});
	Codec codec = checkCodecParams("h264", VideoCodecConfig.H264);
	    
	try {
	    assertEquals(new Double(h264profile_t.HIGH_H264PROFILE.swigValue()), new Double(codec.getParam(VideoCodecConfig.H264.getFlixProfileParamName())));
	    assertEquals(new Double(5), new Double(codec.getParam(flixengine2_internalConstants.FE2_H264_B_FRAME_RATE)));
	} catch (FlixException e) {
	    fail(e.getMessage());
	    e.printStackTrace();
	}
    }

    private Codec checkCodecParams(String options, VideoCodecConfig videoCodecConfig) {
	builderTestHelper.apply(options);
	Codec codec = ((CodecBuilderBase) builderTestHelper.getFlixBuilder()).getCodec();
	try {
	    // Check bitrate
	    assertEquals(new Double(400), new Double(codec.getParam(videoCodecConfig.getFlixBitrateParamName())));
	    
	    // Check compress mode
	    if (videoCodecConfig.getFlixCompressModeParamName() != null) {
		assertEquals(new Double(FE2_CompressMode.COMPRESSMODE_BEST.swigValue()), new Double(codec.getParam(videoCodecConfig.getFlixCompressModeParamName())));
	    }
	    
	    // Check keyframe type
	    assertEquals(new Double(FE2_VideoKeyframeTypes.FIXED_KEYFRAMES.swigValue()), new Double(codec.getParam(videoCodecConfig.getFlixKeyframeTypeParamName())));
	    
	} catch (Exception e) {
	    fail(e.getMessage());
	    e.printStackTrace();
	}
	return codec;
    }

}
