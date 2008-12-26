package tv.zencoder.flix.filter.bchs;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.filter.FilterBuilderBase;

import com.on2.flix.FlixEngine2;
import com.on2.flix.flixengine2_internalConstants;

public class ContrastFilterBuilder extends FilterBuilderBase {

    public ContrastFilterBuilder() {
	super();
    }
    
    /**
     * Modifies the BCHS Filter.
     */
    public void apply(FlixEngine2 flix, String options) {
	(new BchsFilterHelper()).apply(flix, options, flixengine2_internalConstants.FE2_BCHS_CONTRAST);
    }


    public String getFriendlyName() {
	return "Contrast Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("value")
                            .hasArg()
                            .withDescription("Sets contrast in range of [-255,255].")
                            .create(getSwitch());
    }
    
    public String getSwitch() {
	return "contrast";
    }

    public boolean isPrimaryOption() {
	return true;
    }
}
