package tv.zencoder.flix.codec.video;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.Codec;
import com.on2.flix.FlixException;

import tv.zencoder.flix.codec.CodecModifier;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.LogWrapper;

/**
 * For VP6 or VP6A this will set the undershoot percentage. Default within Flix Engine is 90.
 */
public class UndershootPctVideoCodecModifier implements CodecModifier {
    LogWrapper log = LogWrapper.getInstance();
    
    public UndershootPctVideoCodecModifier() {
	super();
    }

    public void modifyCodec(Codec codec, String options) throws FlixException {
	String paramName = BuilderCache.getInstance().getChosenVideoCodec().getFlixUndershootPctParamName();
	if (paramName != null) {
	    codec.setParam(paramName, new Double(options));
	} else {
	    log.debug("UndershootPctVideoCodecModifer.modifyCodec(): This codec does not support Undershoot Pct.  Ignoring.");
	}
    }

    public String getFriendlyName() {
	return "Undershoot Percentage codec modifier.";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("value")
	    		    .hasArg()
	    		    .withDescription("Sets the undershoot pct for codecs which support it -- currently VP6 and VP6A. Default is 90.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "undershoot_pct";
    }

    public boolean isPrimaryOption() {
	return false;
    }

}
