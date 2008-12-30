package tv.zencoder.flix.filter;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

/**
 * Creates a Denoise Filter.  The input value is valid in the range [0.0,1.0)
 * 
 * @author jdl
 *
 */
public class DenoiseFilterBuilder extends FilterBuilderBase {

    public DenoiseFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	try {
	    filter = new Filter(flix, flixengine2_internalConstants.FE2_FILTER_DENOISE);
	    filter.add();
            filter.setParam(flixengine2_internalConstants.FE2_DENOISE_NOISE_LEVEL, Double.parseDouble(options));
	} catch (FlixException e) {}
    }

    public String getFriendlyName() {
	return "Denoise Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("level")
                            .hasArg()
                            .withDescription("Sets the denoise filter level in the range [0.0,1.0)")
                            .create(getSwitch());
    }

    
    public String getSwitch() {
	return "denoise";
    }

    public boolean isPrimaryOption() {
	return true;
    }

}
