package tv.zencoder.flix.filter;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

/**
 * Creates a high pass filter.
 * <p>
 * From the Flix docs:<br>
 * The highpass audio filter is a filter to attenuate sounds in the audio track 
 * that are lower than the cutoff frequency. In other words, high frequencies 
 * are passed by the filter, and low frequencies are stopped.
 *
 * @author jdl
 *
 */
public class HighpassFilterBuilder extends FilterBuilderBase {

    public HighpassFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	try {
	    filter = new Filter(flix, flixengine2_internalConstants.FE2_FILTER_HIGHPASS);
	    filter.add();
            filter.setParam(flixengine2_internalConstants.FE2_HIGHPASS_CUTOFF, Double.parseDouble(options));
	} catch (FlixException e) {}
    }

    public String getFriendlyName() {
	return "Highpass Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("cutoff_frequency")
                            .hasArg()
                            .withDescription("Sets a lower bound (in hz) for audio.")
                            .create(getSwitch());
    }

    public String getSwitch() {
	return "highpass";
    }

    public boolean isPrimaryOption() {
	return true;
    }

}
