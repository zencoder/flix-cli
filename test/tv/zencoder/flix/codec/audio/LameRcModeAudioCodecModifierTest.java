package tv.zencoder.flix.codec.audio;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.on2.flix.Codec;
import com.on2.flix.flixengine2_internalConstants;
import com.on2.flix.lame_rcmode_t;

import tv.zencoder.flix.BuilderTestHelper;
import tv.zencoder.flix.codec.CodecBuilderBase;
import tv.zencoder.flix.util.AudioCodecConfig;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.CommandLineHelper;

public class LameRcModeAudioCodecModifierTest {
    private BuilderTestHelper builderTestHelper;
    
    @Before
    public void setUp() throws Exception {
	builderTestHelper = new BuilderTestHelper();
	builderTestHelper.setUp(new AudioCodecBuilder());
    }

    @After
    public void tearDown() throws Exception {
	builderTestHelper.tearDown();
	builderTestHelper = null;
    }

    @Test
    public void testAbr() {
	CommandLineHelper.getInstance().setArgs(new String[] {"-lame_rc_mode", "abr"});
	checkCodecParams("mp3", AudioCodecConfig.MP3, lame_rcmode_t.LAME_ABR);
    }
    
    @Test
    public void testCbr() {
	CommandLineHelper.getInstance().setArgs(new String[] {"-lame_rc_mode", "cbr"});
	checkCodecParams("mp3", AudioCodecConfig.MP3, lame_rcmode_t.LAME_CBR);
    }
    
    @Test
    public void testVbrMtrh() {
	CommandLineHelper.getInstance().setArgs(new String[] {"-lame_rc_mode", "vbr_mtrh"});
	checkCodecParams("mp3", AudioCodecConfig.MP3, lame_rcmode_t.LAME_VBR_mtrh);
    }
    
    @Test
    public void testVbrRh() {
	CommandLineHelper.getInstance().setArgs(new String[] {"-lame_rc_mode", "vbr_rh"});
	checkCodecParams("mp3", AudioCodecConfig.MP3, lame_rcmode_t.LAME_VBR_rh);
    }
    
    
    private void checkCodecParams(String options, AudioCodecConfig audioCodecConfig, lame_rcmode_t expectedLameRcMode) {
	builderTestHelper.apply(options);
	Codec codec = ((CodecBuilderBase) builderTestHelper.getFlixBuilder()).getCodec();
	try {
	    // Check the audio codec
	    assertEquals(audioCodecConfig, BuilderCache.getInstance().getChosenAudioCodec());
	    
	    // Check the lame rc mode
	    
	    String rcParamName = flixengine2_internalConstants.FE2_LAME_RC_MODE;
	    
	    double rcModeDouble = codec.getParam(rcParamName);
	    
	    assertEquals(new Double(expectedLameRcMode.swigValue()), new Double(rcModeDouble));
	} catch (Exception e) {
	    fail(e.getMessage());
	    e.printStackTrace();
	}
    }
}
