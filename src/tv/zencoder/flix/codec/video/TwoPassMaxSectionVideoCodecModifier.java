package tv.zencoder.flix.codec.video;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.Codec;
import com.on2.flix.FlixException;

import tv.zencoder.flix.codec.CodecModifier;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.LogWrapper;

/**
 * Set the 2-pass max section percentage. Default is 40.
 * @author jdl
 *
 */
public class TwoPassMaxSectionVideoCodecModifier implements CodecModifier {
    LogWrapper log = LogWrapper.getInstance();
    
    public TwoPassMaxSectionVideoCodecModifier() {
	super();
    }

    public void modifyCodec(Codec codec, String options) throws FlixException {
	String paramName = BuilderCache.getInstance().getChosenVideoCodec().getFlix2PassMaxSectionParamName();
	if (paramName != null) {
	    codec.setParam(paramName, new Double(options));
	} else {
	    log.debug("TwoPassMaxSectionVideoCodecModifier.modifyCodec(): This codec does not support 2-pass max section.  Ignoring.");
	}
    }

    public String getFriendlyName() {
	return "2 Pass Max Section codec modifier.";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("value")
	    		    .hasArg()
	    		    .withDescription("Sets the 2pass max section percentage option for codecs which support it -- currently VP6 and VP6A.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "2pass_max_section";
    }

    public boolean isPrimaryOption() {
	return false;
    }

}
