package tv.zencoder.flix.muxer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tv.zencoder.flix.BuilderTestHelper;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.CommandLineHelper;
import tv.zencoder.flix.util.LogWrapper;
import tv.zencoder.flix.util.VideoMuxerConfig;

import com.on2.flix.Muxer;
import com.on2.flix._on2bool;

public class VideoMuxerBuilderTest {
    LogWrapper log = LogWrapper.getInstance();
    private BuilderTestHelper builderTestHelper;

    @Before
    public void setUp() throws Exception {
	builderTestHelper = new BuilderTestHelper();
	builderTestHelper.setUp(new VideoMuxerBuilder());
    }

    @After
    public void tearDown() throws Exception {
	builderTestHelper.tearDown();
	builderTestHelper = null;
    }

    @Test
    public void testFlv() {
	// TODO: The -cp_foo switches are here, but we don't have a good way to test them
	// yet.  muxer.getParam() blows up on these; I suspect because there might be a list
	// rather than a single param. [JDL]
	CommandLineHelper.getInstance().setArgs(new String[] {"-cp_nav foo=5.1,bar=10.0 -cp_event fred=2.0,barney=3.3"});
	checkMuxerParams("flv", VideoMuxerConfig.FLV);
    }

    @Test
    public void testMov() {
	CommandLineHelper.getInstance().setArgs(new String[] {"-faststart"});
	checkMuxerParams("mov", VideoMuxerConfig.MOV);
    }
    
    @Test
    public void testMp4() {
	CommandLineHelper.getInstance().setArgs(new String[] {"-faststart"});
	checkMuxerParams("mp4", VideoMuxerConfig.MP4);
    }

    @Test
    public void test3gp() {
	CommandLineHelper.getInstance().setArgs(new String[] {"-faststart"});
	checkMuxerParams("3gp", VideoMuxerConfig._3GP);
    }

    @Test
    public void test3g2() {
	CommandLineHelper.getInstance().setArgs(new String[] {"-faststart"});
	checkMuxerParams("3g2", VideoMuxerConfig._3G2);
    }

    @Test
    public void testFxm() {
	CommandLineHelper.getInstance().setArgs(new String[] {"-faststart"});
	checkMuxerParams("fxm", VideoMuxerConfig.FXM);
    }
    
    private Muxer checkMuxerParams(String options, VideoMuxerConfig videoMuxerConfig) {
	builderTestHelper.apply(options);
	Muxer muxer = ((MuxerBuilderBase) builderTestHelper.getFlixBuilder()).getMuxer();
	try {
	    VideoMuxerConfig chosenVideoMuxer = BuilderCache.getInstance().getChosenVideoMuxer();
	    assertEquals(videoMuxerConfig, chosenVideoMuxer);
	    
	    // Check faststart
	    if (chosenVideoMuxer.getFlixFaststartParamName() != null) {
		assertEquals(new Double(_on2bool.on2true.swigValue()), new Double(muxer.getParam(chosenVideoMuxer.getFlixFaststartParamName())));
	    }
	    
	} catch (Exception e) {
	    fail(e.getMessage());
	    e.printStackTrace();
	}
	return muxer;
    }

}
