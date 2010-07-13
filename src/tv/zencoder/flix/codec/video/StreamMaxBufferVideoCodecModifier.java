package tv.zencoder.flix.codec.video;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.Codec;
import com.on2.flix.FlixException;

import tv.zencoder.flix.codec.CodecModifier;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.LogWrapper;

/**
 * Max stream buffer in seconds. (Only works with CBR)
 * @author jdl
 *
 */
public class StreamMaxBufferVideoCodecModifier implements CodecModifier {
    LogWrapper log = LogWrapper.getInstance();
    
    public StreamMaxBufferVideoCodecModifier() {
	super();
    }

    public void modifyCodec(Codec codec, String options) throws FlixException {
	String paramName = BuilderCache.getInstance().getChosenVideoCodec().getFlixStreamMaxBufferParamName();
	if (paramName != null) {
	    codec.setParam(paramName, new Double(options));
	} else {
	    log.debug("StreamMaxBufferVideoCodecModifier.modifyCodec(): This codec does not support Stream Max Buffer.  Ignoring.");
	}}

    public String getFriendlyName() {
	return "Stream Max Buffer video codec modifier.";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("value")
	    		    .hasArg()
	    		    .withDescription("Sets the Stream Max Buffer option in seconds for codecs which support it -- currently VP6 and VP6A. Only valid with CBR.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "vstream_max_buffer";
    }

    public boolean isPrimaryOption() {
	return false;
    }

}
