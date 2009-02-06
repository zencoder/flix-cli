package tv.zencoder.flix.codec.video;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.Codec;
import com.on2.flix.FE2_CompressMode;
import com.on2.flix.FlixException;

import tv.zencoder.flix.codec.CodecModifier;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.LogWrapper;
import tv.zencoder.flix.util.VideoCodecConfig;

/**
 * Sets the compress mode codecs that support this option.  Currently, these 
 * are VP6 and VP6A.
 * 
 * @author jdl
 *
 */
public class CompressModeVideoCodecModifier extends Object implements CodecModifier {

    LogWrapper log = LogWrapper.getInstance();
    
    public CompressModeVideoCodecModifier() {
	super();
    }

    public void modifyCodec(Codec codec, String options) throws FlixException {
	VideoCodecConfig videoCodecConfig = BuilderCache.getInstance().getChosenVideoCodec();
	if (videoCodecConfig.getFlixCompressModeParamName() != null) {
	    codec.setParam(videoCodecConfig.getFlixCompressModeParamName(), optionsToConstant(options).swigValue());
	} else {
	    log.debug("CompressModeVideoCodecModifier.modifyCodec(): The chosen codec does not support this option.  Ignoring.");
	}
    }

    public String getFriendlyName() {
	return "Video Compress Mode";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("good|best")
	    		    .hasArg()
	    		    .withDescription("Sets the video compress mode.  This currently only works with the VP6 and VP6A codecs.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "vcompress";
    }

    public boolean isPrimaryOption() {
	return false;
    }
    
    /**
     * Converts the command line option into a FE2_CompressMode constant.  
     * This defaults to "good" and will return "best" if that is found instead.
     * 
     * @param options
     * @return FE2_CompressMode
     */
    protected FE2_CompressMode optionsToConstant(String options) {
	FE2_CompressMode mode = FE2_CompressMode.COMPRESSMODE_GOOD;
	
	Pattern pattern = Pattern.compile("^best$");
	Matcher m = pattern.matcher(options);
	if (m.matches()) {
	    log.debug("CompressModeVideoCodecModifier.optionsToConstant(): Setting compress mode to 'best'.");
	    mode = FE2_CompressMode.COMPRESSMODE_BEST;
	} else {
	    log.debug("CompressModeVideoCodecModifier.optionsToConstant(): Setting compress mode to 'good'.");
	}
	return mode;
    }

}
