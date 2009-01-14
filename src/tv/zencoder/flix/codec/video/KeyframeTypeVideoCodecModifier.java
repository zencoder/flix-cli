package tv.zencoder.flix.codec.video;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.Codec;
import com.on2.flix.FE2_VideoKeyframeTypes;
import com.on2.flix.FlixException;

import tv.zencoder.flix.codec.CodecModifier;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.LogWrapper;

/**
 * Sets the keyframe type (max or fixed).
 * @author jdl
 *
 */
public class KeyframeTypeVideoCodecModifier implements CodecModifier {
    LogWrapper log = LogWrapper.getInstance();
    
    public KeyframeTypeVideoCodecModifier() {
	super();
    }

    public void modifyCodec(Codec codec, String options) throws FlixException {
	codec.setParam(BuilderCache.getInstance().getChosenVideoCodec().getFlixKeyframeTypeParamName(), getKeyframeTypeFromOptions(options));
    }

    public String getFriendlyName() {
	return "Keyframe Type Video Codec Modifier";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("max|fixed")
	    		    .hasArg()
	    		    .withDescription("Sets the video keyframe type.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "vkftype";
    }

    public boolean isPrimaryOption() {
	return false;
    }

    protected Double getKeyframeTypeFromOptions(String options) {
	int value = 0;
	if (options != null) {
	    if (options.equals("max")) {
		value = FE2_VideoKeyframeTypes.MAX_KEYFRAMES.swigValue();
		log.debug("KeyframeTypeVideoCodecModifier.getKeyframeTypeFromOptions(): Setting keyframe type to MAX.");
	    }
            if (options.equals("fixed")) {
		value = FE2_VideoKeyframeTypes.FIXED_KEYFRAMES.swigValue();
		log.debug("KeyframeTypeVideoCodecModifier.getKeyframeTypeFromOptions(): Setting keyframe type to FIXED.");
	    }
	}
	return new Double(value);
    }
    
}
