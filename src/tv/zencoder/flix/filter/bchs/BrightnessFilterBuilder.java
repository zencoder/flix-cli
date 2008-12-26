package tv.zencoder.flix.filter.bchs;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.filter.FilterBuilderBase;

import com.on2.flix.FlixEngine2;
import com.on2.flix.flixengine2_internalConstants;

/**
 * Builds a brightness filter.  This is a child of the BchsFilterBuilder
 * which must be triggered if this filter builder is to run.
 * 
 * @author jdl
 *
 */
public class BrightnessFilterBuilder extends FilterBuilderBase {

    public BrightnessFilterBuilder() {
	super();
    }

    /**
     * Modifies the BCHS Filter.
     */
    public void apply(FlixEngine2 flix, String options) {
	(new BchsFilterHelper()).apply(flix, options, flixengine2_internalConstants.FE2_BCHS_BRIGHTNESS);
    }

    public String getFriendlyName() {
	return "Brightness Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("value")
                            .hasArg()
                            .withDescription("Sets brightness in range of [-255,255].")
                            .create(getSwitch());
    }

    public String getSwitch() {
	return "brightness";
    }

    public boolean isPrimaryOption() {
	return true;
    }
 
}
