package tv.zencoder.flix.codec.video;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.codec.CodecModifier;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.LogWrapper;

import com.on2.flix.Codec;
import com.on2.flix.FE2_VideoBitrateControls;
import com.on2.flix.FlixException;

/**
 * Sets the rate control mode for the video codec.
 * 
 * CBR_1PASSControl single pass constant bitrate<br>
 * VBR_1PASSControl single pass variable bitrate<br>
 * CBR_2PASSControl two pass constant bitrate<br>
 * VBR_2PASSControl two pass variable bitrate<br>
 * 
 * @author jdl
 *
 */
public class RateControlVideoCodecModifier implements CodecModifier {
    LogWrapper log = LogWrapper.getInstance();
    
    public RateControlVideoCodecModifier() {
	super();
    }

    public void modifyCodec(Codec codec, String options) throws FlixException {
	String rateControlParamName = BuilderCache.getInstance().getChosenVideoCodec().getFlixRateControlParamName();
	if (rateControlParamName != null) {
	    codec.setParam(rateControlParamName, getRateControlConstantFromOptions(options));
	} else {
	    log.debug("RateControlVideoCodecModifier.getFriendlyName(): This codec does not support rate control.  Ignoring.");
	}
    }

    public String getFriendlyName() {
	return "Rate Control video codec modifier";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("cbr1|cbr2|vbr1|vbr2")
	    		    .hasArg()
	    		    .withDescription("Sets the rate control type.")
	    		    .create(getSwitch());
    }


    public String getSwitch() {
	return "vrc";
    }

    public boolean isPrimaryOption() {
	return false;
    }
    
    protected Double getRateControlConstantFromOptions(String options) {
	int value = 0;
	if (options != null) {
	    if (options.equals("cbr1")) {
		value = FE2_VideoBitrateControls.CBR_1PASSControl.swigValue();
		log.debug("RateControlVideoCodecModifier.getRateControlConstantFromOptions(): Setting rate control to CBR 1 pass");
	    }
	    if (options.equals("cbr2")) {
		value = FE2_VideoBitrateControls.CBR_2PASSControl.swigValue();
		log.debug("RateControlVideoCodecModifier.getRateControlConstantFromOptions(): Setting rate control to CBR 2 pass");
	    }
	    if (options.equals("vbr1")) {
		value = FE2_VideoBitrateControls.VBR_1PASSControl.swigValue();
		log.debug("RateControlVideoCodecModifier.getRateControlConstantFromOptions(): Setting rate control to VBR 1 pass");
	    }
	    if (options.equals("vbr2")) {
		value = FE2_VideoBitrateControls.VBR_2PASSControl.swigValue();
		log.debug("RateControlVideoCodecModifier.getRateControlConstantFromOptions(): Setting rate control to VBR 2 pass");
	    }
	}
	return new Double(value);
    }

}
