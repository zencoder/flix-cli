package tv.zencoder.flix.codec.audio;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tv.zencoder.flix.BuilderTestHelper;
import tv.zencoder.flix.codec.CodecBuilderBase;
import tv.zencoder.flix.util.AudioCodecConfig;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.CommandLineHelper;

import com.on2.flix.Codec;

public class AudioCodecBuilderTest {

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
    public void testAac() {
	// Set up a command line so that a bitrate is also set.
	CommandLineHelper.getInstance().setArgs(new String[] {"-ab", "128"});
	checkCodecParams("aac", AudioCodecConfig.AAC);
    }
    
    @Test
    public void testAacPlus() {
	// Set up a command line so that a bitrate is also set.
	CommandLineHelper.getInstance().setArgs(new String[] {"-ab", "128", "-a_parametric_stereo"});
	checkCodecParams("aacplus", AudioCodecConfig.AACPLUS);
    }
    
    @Test
    public void testMp3() {
	// Set up a command line so that a bitrate is also set.
	CommandLineHelper.getInstance().setArgs(new String[] {"-ab", "128"});
	checkCodecParams("mp3", AudioCodecConfig.MP3);
    }

    private void checkCodecParams(String options, AudioCodecConfig audioCodecConfig) {
	builderTestHelper.apply(options);
	Codec codec = ((CodecBuilderBase) builderTestHelper.getFlixBuilder()).getCodec();
	try {
	    assertEquals(audioCodecConfig, BuilderCache.getInstance().getChosenAudioCodec());
	    
	    // Check bitrate
	    assertEquals(new Double(128), new Double(codec.getParam(audioCodecConfig.getFlixBitrateParamName())));
	    
	    // Check parametric stereo
	    if (audioCodecConfig.getFlixParametricStereoParamName() != null) {
		assertEquals(new Double(1.0), new Double(codec.getParam(audioCodecConfig.getFlixParametricStereoParamName())));
	    }
	} catch (Exception e) {
	    fail(e.getMessage());
	    e.printStackTrace();
	}
    }

}
