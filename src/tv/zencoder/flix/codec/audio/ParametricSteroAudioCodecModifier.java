package tv.zencoder.flix.codec.audio;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.Codec;
import com.on2.flix.FlixException;

import tv.zencoder.flix.codec.CodecModifier;
import tv.zencoder.flix.util.AudioCodecConfig;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.LogWrapper;


/**
 * Turns on parametric stereo in the audio codec.  Currently works for AAC+.
 * @author jdl
 *
 */
public class ParametricSteroAudioCodecModifier implements CodecModifier {

    LogWrapper log = LogWrapper.getInstance();
    
    public ParametricSteroAudioCodecModifier() {
	super();
    }

    public void modifyCodec(Codec codec, String options) throws FlixException {
	AudioCodecConfig audioCodecConfig = BuilderCache.getInstance().getChosenAudioCodec();
	if (audioCodecConfig.getFlixParametricStereoParamName() != null) {
	    log.debug("ParametricSteroAudioCodecModifier.modifyCodec(): Turning on parametric stero.");
	    codec.setParam(audioCodecConfig.getFlixParametricStereoParamName(), new Double("1.0"));
	} else {
	    log.debug("ParametricSteroAudioCodecModifier.modifyCodec(): Selected audio codec does not support parametric stereo.  Ignoring.");
	}
    }

    public String getFriendlyName() {
	return "Parametric Stero";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withDescription("Turns on parametric stereo.  Currently works with AAC+.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "a_parametric_stereo";
    }

    public boolean isPrimaryOption() {
	return false;
    }

}
