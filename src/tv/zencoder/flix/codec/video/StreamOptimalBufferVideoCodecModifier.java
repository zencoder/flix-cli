package tv.zencoder.flix.codec.video;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.Codec;
import com.on2.flix.FlixException;

import tv.zencoder.flix.codec.CodecModifier;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.LogWrapper;

/**
 * Sets the Stream Pre Buffer option for codecs that support it.
 * @author jdl
 *
 */
public class StreamOptimalBufferVideoCodecModifier implements CodecModifier {
    LogWrapper log = LogWrapper.getInstance();
    
    public StreamOptimalBufferVideoCodecModifier() {
	super();
    }

    public void modifyCodec(Codec codec, String options) throws FlixException {
	String paramName = BuilderCache.getInstance().getChosenVideoCodec().getFlixStreamOptimalBufferParamName();
	if (paramName != null) {
	    codec.setParam(paramName, new Double(options));
	} else {
	    log.debug("StreamOptimalBufferVideoCodecModifier.modifyCodec(): This codec does not support Stream Optimal Buffer.  Ignoring.");
	}}

    public String getFriendlyName() {
	return "Stream Optimal Buffer video codec modifier.";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("value")
	    		    .hasArg()
	    		    .withDescription("Sets the Stream Optimal Buffer option for codecs which support it -- currently VP6.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "vstream_optimal_buffer";
    }

    public boolean isPrimaryOption() {
	return false;
    }

}
