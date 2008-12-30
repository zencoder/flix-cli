package tv.zencoder.flix.filter;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

/**
 * Creates a low pass filter.
 * <p>
 * From the Flix docs:<br>
 * The lowpass audio filter is a filter to attenuate sounds in the audio 
 * track that are higher than the cutoff frequency. In other words, low 
 * frequencies are passed by the filter, and high frequencies are stopped.
 *
 * @author jdl
 *
 */
public class LowpassFilterBuilder extends FilterBuilderBase {

    public LowpassFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	try {
	    filter = new Filter(flix, flixengine2_internalConstants.FE2_FILTER_LOWPASS);
	    filter.add();
            filter.setParam(flixengine2_internalConstants.FE2_LOWPASS_CUTOFF, Double.parseDouble(options));
	} catch (FlixException e) {}
    }

    public String getFriendlyName() {
	return "Lowpass Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("cutoff_frequency")
                            .hasArg()
                            .withDescription("Sets an upper bound (in hz) for audio.")
                            .create(getSwitch());
    }

    public String getSwitch() {
	return "lowpass";
    }

    public boolean isPrimaryOption() {
	return true;
    }

}
