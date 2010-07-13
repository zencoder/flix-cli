package tv.zencoder.flix.codec.video;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.Codec;
import com.on2.flix.FlixException;

import tv.zencoder.flix.codec.CodecModifier;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.LogWrapper;

/**
 * Stream Peak Bitrate
 * @author jdl
 *
 */
public class StreamPeakBitrateVideoCodecModifier implements CodecModifier {
    LogWrapper log = LogWrapper.getInstance();

    public StreamPeakBitrateVideoCodecModifier() {
	super();
    }

    public void modifyCodec(Codec codec, String options) throws FlixException {
	String paramName = BuilderCache.getInstance().getChosenVideoCodec().getFlixStreamPeakBitrateParamName();
	if (paramName != null) {
	    codec.setParam(paramName, new Double(options));
	} else {
	    log.debug("StreamPeakBitrateVideoCodecModifier.modifyCodec(): This codec does not support Stream Peak Bitrate.  Ignoring.");
	}}

    public String getFriendlyName() {
	return "Stream Peak Bitrate video codec modifier.";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("value")
	    		    .hasArg()
	    		    .withDescription("Sets the Stream Peak Bitrate for codecs which support it -- currently VP6 and VP6A.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "vstream_peak_bitrate";
    }

    public boolean isPrimaryOption() {
	return false;
    }


}
