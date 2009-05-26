package tv.zencoder.flix.codec.video;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.Codec;
import com.on2.flix.FlixException;

import tv.zencoder.flix.codec.CodecModifier;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.LogWrapper;

/**
 * Sets the keyframe frequency for the current codec.
 */
public class KeyframeFrequencyVideoCodecModifier implements CodecModifier {
    LogWrapper log = LogWrapper.getInstance();

    public KeyframeFrequencyVideoCodecModifier() {
	super();
    }

    public void modifyCodec(Codec codec, String options) throws FlixException {
	codec.setParam(BuilderCache.getInstance().getChosenVideoCodec().getFlixKeyframeFreqParamName(), Double.parseDouble(options));
    }

    public String getFriendlyName() {
	return "Keyframe Frequency Video Codec Modifier";
    }
   
    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("freq")
	    		    .hasArg()
	    		    .withDescription("Sets the video keyframe in frames. The interpretation depends on the setting of -vkftype.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "vkffreq";
    }

    public boolean isPrimaryOption() {
	return false;
    }

}
