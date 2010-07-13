package tv.zencoder.flix.codec.video;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.Codec;
import com.on2.flix.FlixException;

import tv.zencoder.flix.codec.CodecModifier;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.LogWrapper;

/**
 * Codec parameter for temporal down watermark percentage.
 * 
 * Specifies the percentage of the datarate buffer remaining below which the encoder
 * is allowed to start dropping frames. 
 * Only used if FE2_VP6_TEMPORAL_RESAMPLING is enabled.

 * @author jdl
 *
 */
public class TemporalDownWatermarkVideoCodecModifier implements CodecModifier {
    LogWrapper log = LogWrapper.getInstance();
    
    public TemporalDownWatermarkVideoCodecModifier() {
	super();
    }

    public void modifyCodec(Codec codec, String options) throws FlixException {
	String paramName = BuilderCache.getInstance().getChosenVideoCodec().getFlixTemporalDownWatermarkParamName();
	if (paramName != null) {
	    codec.setParam(paramName, new Double(options));
	} else {
	    log.debug("TemporalDownWatermarkVideoCodecModifier.modifyCodec(): This codec does not support Temporal Down Watermark.  Ignoring.");
	}
    }    

    public String getFriendlyName() {
	return "Temporal Down Watermark codec modifier.";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("value")
	    		    .hasArg()
	    		    .withDescription("Sets the Temporal Down Watermark option for codecs which support it -- currently VP6 and VP6A.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "vtemporal_down_watermark";
    }

    public boolean isPrimaryOption() {
	return false;
    }


}
