package tv.zencoder.flix.codec.video;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.Codec;
import com.on2.flix.FlixException;

import tv.zencoder.flix.codec.CodecModifier;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.LogWrapper;

/**
 * Sets the Noise Reduction option for codecs which support it.
 * @author jdl
 *
 */
public class NoiseReductionVideoCodecModifier implements CodecModifier {
    LogWrapper log = LogWrapper.getInstance();
    
    public NoiseReductionVideoCodecModifier() {
	super();
    }

    public void modifyCodec(Codec codec, String options) throws FlixException {
	String paramName = BuilderCache.getInstance().getChosenVideoCodec().getFlixNoiseReductionParamName();
	if (paramName != null) {
	    codec.setParam(paramName, new Double(options));
	} else {
	    log.debug("NoiseReductionVideoCodecModifier.modifyCodec(): This codec does not support Noise Reduction options.  Ignoring.");
	}
    }

    public String getFriendlyName() {
	return "Noise Reduction codec modifier.";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("value")
	    		    .hasArg()
	    		    .withDescription("Sets the Noise Reduction option for codecs which support it -- currently VP6.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "vnoisereduce";
    }


    public boolean isPrimaryOption() {
	return false;
    }

}
