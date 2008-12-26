package tv.zencoder.flix.filter.bchs;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.filter.FilterBuilderBase;

import com.on2.flix.FlixEngine2;
import com.on2.flix.flixengine2_internalConstants;

/**
 * Builds a hue filter.  This is a child of the BchsFilterBuilder
 * which must be triggered if this filter builder is to run.
 * 
 * @author jdl
 *
 */
public class HueFilterBuilder extends FilterBuilderBase {

    public HueFilterBuilder() {
	super();
    }

    /**
     * Modifies the BCHS Filter.
     */
    public void apply(FlixEngine2 flix, String options) {
	(new BchsFilterHelper()).apply(flix, options, flixengine2_internalConstants.FE2_BCHS_HUE);
    }
    
    public String getFriendlyName() {
	return "Hue Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("value")
                            .hasArg()
                            .withDescription("Sets hue in range of [-180,180].")
                            .create(getSwitch());
    }

    public String getSwitch() {
	return "hue";
    }

    public boolean isPrimaryOption() {
	return true;
    }
}
