package tv.zencoder.flix.codec.video;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.codec.CodecModifier;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.LogWrapper;
import tv.zencoder.flix.util.VideoCodecConfig;

import com.on2.flix.Codec;
import com.on2.flix.FlixException;
import com.on2.flix.h264profile_t;
import com.on2.flix.vp6profile_t;

/**
 * Sets the profile type for certain codecs.  Currently supported by VP6 and H264, although
 * they use different parameters entirely.
 * 
 * @author jdl
 *
 */
public class ProfileVideoCodecModifier implements CodecModifier {
    LogWrapper log = LogWrapper.getInstance();
    
    public ProfileVideoCodecModifier() {
	super();
    }

    public void modifyCodec(Codec codec, String options) throws FlixException {
	VideoCodecConfig videoCodecConfig = BuilderCache.getInstance().getChosenVideoCodec();
	if (videoCodecConfig.getFlixProfileParamName() != null) {
	    codec.setParam(videoCodecConfig.getFlixProfileParamName(), parseProfileName(options));
	} else {
	    log.debug("ProfileVideoCodecModifier.modifyCodec(): The chosen codec does not support this option.  Ignoring.");
	}
    }

    public String getFriendlyName() {
	return "Video Codec Profile";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("vp6e|vp6s|h264base|h264main|h264high")
	    		    .hasArg()
	    		    .withDescription("Sets the codec profile.  Note that the profile's prefix name should match your chosen codec.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "vprofile";
    }

    public boolean isPrimaryOption() {
	return false;
    }
    
    /**
     * Parses the options string to find the profile constant that we need.  Returns a Double, rather 
     * than the constant itself, since different Flix Codecs use different enums for these values.
     * 
     * @param options
     * @return Double
     */
    protected Double parseProfileName(String options) {
	int value = 0;
	if (options != null) {
	    if (options.equals("vp6e")) {
		value = vp6profile_t.VP6_E.swigValue();
		log.debug("ProfileVideoCodecModifier.parseProfileName(): Using codec profile VP6_E");
	    }
	    if (options.equals("vp6s")) {
		value = vp6profile_t.VP6_S.swigValue();
		log.debug("ProfileVideoCodecModifier.parseProfileName(): Using codec profile VP6_S");
	    }
	    if (options.equals("h264base")) {
		value = h264profile_t.BASE_H264PROFILE.swigValue();
		log.debug("ProfileVideoCodecModifier.parseProfileName(): Using codec profile BASE_H264");
	    }
	    if (options.equals("h264main")) {
		value = h264profile_t.MAIN_H264PROFILE.swigValue();
		log.debug("ProfileVideoCodecModifier.parseProfileName(): Using codec profile MAIN_H264");
	    }
	    if (options.equals("h264high")) {
		value = h264profile_t.HIGH_H264PROFILE.swigValue();
		log.debug("ProfileVideoCodecModifier.parseProfileName(): Using codec profile HIGH_H264");
	    }
	}
	return new Double(value);
    }

}
