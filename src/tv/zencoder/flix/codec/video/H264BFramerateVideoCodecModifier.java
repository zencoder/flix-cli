package tv.zencoder.flix.codec.video;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.Codec;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.codec.CodecModifier;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.LogWrapper;
import tv.zencoder.flix.util.VideoCodecConfig;

/**
 * For the H264 codec only, this will set the B_FRAME_RATE value.
 * @author jdl
 *
 */
public class H264BFramerateVideoCodecModifier implements CodecModifier {
    LogWrapper log = LogWrapper.getInstance();

    public H264BFramerateVideoCodecModifier() {
	super();
    }

    public void modifyCodec(Codec codec, String options) throws FlixException {
	if (BuilderCache.getInstance().getChosenVideoCodec() == VideoCodecConfig.H264) {
	    codec.setParam(flixengine2_internalConstants.FE2_H264_B_FRAME_RATE, new Double(options));
	} else {
	    log.debug("H264BFramerateVideoCodecModifier.modifyCodec(): This option only works with the H264 codec.  Ignoring.");
	}
    }

    public String getFriendlyName() {
	return "H264 B Framerate";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("b_framerate")
	    		    .hasArg()
	    		    .withDescription("Codec parameter for B frame rate. Specifies number of B frames between I/P and next P frame.")
	    		    .create(getSwitch());
    }


    public String getSwitch() {
	return "r_h264b";
    }

    public boolean isPrimaryOption() {
	return false;
    }

}
