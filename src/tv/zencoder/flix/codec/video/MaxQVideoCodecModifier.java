package tv.zencoder.flix.codec.video;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.Codec;
import com.on2.flix.FlixException;

import tv.zencoder.flix.codec.CodecModifier;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.LogWrapper;

/**
 * Sets the Max Q option for codecs which support it.
 * @author jdl
 *
 */
public class MaxQVideoCodecModifier implements CodecModifier {
    LogWrapper log = LogWrapper.getInstance();
    
    public MaxQVideoCodecModifier() {
	super();
    }

    public void modifyCodec(Codec codec, String options) throws FlixException {
	String paramName = BuilderCache.getInstance().getChosenVideoCodec().getFlixMaxQParamName();
	if (paramName != null) {
	    codec.setParam(paramName, new Double(options));
	} else {
	    log.debug("MaxQVideoCodecModifier.modifyCodec(): This codec does not support Max Q.  Ignoring.");
	}
    }

    public String getFriendlyName() {
	return "Max Q codec modifier.";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("value")
	    		    .hasArg()
	    		    .withDescription("Sets the Max Q option for codecs which support it -- currently VP6, VP6A, and H263.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "vmaxq";
    }

    public boolean isPrimaryOption() {
	return false;
    }

}
