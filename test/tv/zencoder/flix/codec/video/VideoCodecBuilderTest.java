package tv.zencoder.flix.codec.video;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tv.zencoder.flix.BuilderTestHelper;
import tv.zencoder.flix.codec.CodecBuilderBase;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.CommandLineHelper;
import tv.zencoder.flix.util.VideoCodecConfig;

import com.on2.flix.Codec;
import com.on2.flix.FE2_CompressMode;
import com.on2.flix.FE2_VideoBitrateControls;
import com.on2.flix.FE2_VideoKeyframeTypes;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;
import com.on2.flix.h264profile_t;
import com.on2.flix.vp6profile_t;

public class VideoCodecBuilderTest {

    private BuilderTestHelper builderTestHelper;
    private List<String> commandLineArgs;
    
    
    @Before
    public void setUp() throws Exception {
	builderTestHelper = new BuilderTestHelper();
	builderTestHelper.setUp(new VideoCodecBuilder());
	
	commandLineArgs = new ArrayList<String>();
	commandLineArgs.add("-b");
	commandLineArgs.add("400");
	commandLineArgs.add("-vcompress");
	commandLineArgs.add("best");
	commandLineArgs.add("-vkftype");
	commandLineArgs.add("fixed");
	commandLineArgs.add("-vkffreq");
	commandLineArgs.add("1000");
	commandLineArgs.add("-vrc");
	commandLineArgs.add("vbr1");
	commandLineArgs.add("-vminq");
	commandLineArgs.add("4");
	commandLineArgs.add("-vmaxq");
	commandLineArgs.add("5");
	commandLineArgs.add("-vnoisereduce");
	commandLineArgs.add("0");
	commandLineArgs.add("-vtemporalresample");
	commandLineArgs.add("1");
	commandLineArgs.add("-vstream_pre_buffer");
	commandLineArgs.add("5");
	commandLineArgs.add("-vstream_optimal_buffer");
	commandLineArgs.add("6");
    }

    @After
    public void tearDown() throws Exception {
	builderTestHelper.tearDown();
	builderTestHelper = null;
    }

    @Test
    public void testVp6() {
	commandLineArgs.add("-vprofile");
	commandLineArgs.add("vp6s");
	commandLineArgs.add("-undershoot_pct");
	commandLineArgs.add("50");
	CommandLineHelper.getInstance().setArgs(commandLineArgs);
	Codec codec = checkCodecParams("vp6", VideoCodecConfig.VP6);
	
	try {
	    assertEquals(new Double(vp6profile_t.VP6_S.swigValue()), new Double(codec.getParam(VideoCodecConfig.VP6.getFlixProfileParamName())));
	    assertEquals(new Double(50), new Double(codec.getParam(VideoCodecConfig.VP6.getFlixUndershootPctParamName())));
	} catch (FlixException e) {
	    fail(e.getMessage());
	    e.printStackTrace();
	}
    }
    
    @Test
    public void testVp6a() {
	commandLineArgs.add("-undershoot_pct");
	commandLineArgs.add("50");
	CommandLineHelper.getInstance().setArgs(commandLineArgs);
	Codec codec = checkCodecParams("vp6a", VideoCodecConfig.VP6A);
	try {
	    assertEquals(new Double(50), new Double(codec.getParam(VideoCodecConfig.VP6.getFlixUndershootPctParamName())));
	} catch (FlixException e) {
	    fail(e.getMessage());
	    e.printStackTrace();
	}
    }
    
    @Test
    public void testH263() {
	CommandLineHelper.getInstance().setArgs(commandLineArgs);
	checkCodecParams("h263", VideoCodecConfig.H263);
    }
    
    @Test
    public void testH263Base() {
	CommandLineHelper.getInstance().setArgs(commandLineArgs);
	checkCodecParams("h263_base", VideoCodecConfig.H263_BASELINE);
    }
    
    @Test
    public void testH264() {
	commandLineArgs.add("-vprofile");
	commandLineArgs.add("h264high");
	commandLineArgs.add("-r_h264b");
	commandLineArgs.add("5");

	CommandLineHelper.getInstance().setArgs(commandLineArgs);
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
	    assertEquals(videoCodecConfig, BuilderCache.getInstance().getChosenVideoCodec());
	    
	    // Check bitrate
	    if (videoCodecConfig.getFlixBitrateParamName() != null) {
	        assertEquals(new Double(400), new Double(codec.getParam(videoCodecConfig.getFlixBitrateParamName())));
	    }
	    
	    // Check compress mode
	    if (videoCodecConfig.getFlixCompressModeParamName() != null) {
		assertEquals(new Double(FE2_CompressMode.COMPRESSMODE_BEST.swigValue()), new Double(codec.getParam(videoCodecConfig.getFlixCompressModeParamName())));
	    }
	    
	    // Check keyframe type
	    if (videoCodecConfig.getFlixKeyframeTypeParamName() != null) {
		assertEquals(new Double(FE2_VideoKeyframeTypes.FIXED_KEYFRAMES.swigValue()), new Double(codec.getParam(videoCodecConfig.getFlixKeyframeTypeParamName())));
	    }
	    
	    // Check keyframe frequency
	    if (videoCodecConfig.getFlixKeyframeFreqParamName() != null) {
		assertEquals(new Double(1000), new Double(codec.getParam(videoCodecConfig.getFlixKeyframeFreqParamName())));
	    }
	    
	    // Check rate control
	    if (videoCodecConfig.getFlixRateControlParamName() != null) {
		assertEquals(new Double(FE2_VideoBitrateControls.VBR_1PASSControl.swigValue()), new Double(codec.getParam(videoCodecConfig.getFlixRateControlParamName())));
	    }
	    
	    // Check Min Q
	    if (videoCodecConfig.getFlixMinQParamName() != null) {
		assertEquals(new Double(4), new Double(codec.getParam(videoCodecConfig.getFlixMinQParamName())));
	    }

	    // Check Max Q
	    if (videoCodecConfig.getFlixMaxQParamName() != null) {
		assertEquals(new Double(5), new Double(codec.getParam(videoCodecConfig.getFlixMaxQParamName())));
	    }
	    
	    // Check Noise Reduction
	    if (videoCodecConfig.getFlixNoiseReductionParamName() != null) {
		assertEquals(new Double(0), new Double(codec.getParam(videoCodecConfig.getFlixNoiseReductionParamName())));
	    }
	    
	    // Check Temporal Resampling
	    if (videoCodecConfig.getFlixTemporalResamplingParamName() != null) {
		assertEquals(new Double(1), new Double(codec.getParam(videoCodecConfig.getFlixTemporalResamplingParamName())));
	    }
	    
	    // Check Stream Pre Buffer
	    if (videoCodecConfig.getFlixStreamPrebufferParamName() != null) {
		assertEquals(new Double(5), new Double(codec.getParam(videoCodecConfig.getFlixStreamPrebufferParamName())));
	    }
	   
	    // Check Stream Optimal Buffer
	    if (videoCodecConfig.getFlixStreamOptimalBufferParamName() != null) {
		assertEquals(new Double(6), new Double(codec.getParam(videoCodecConfig.getFlixStreamOptimalBufferParamName())));
	    }

	    
	} catch (Exception e) {
	    fail(e.getMessage());
	    e.printStackTrace();
	}
	return codec;
    }

}
