package tv.zencoder.flix.filter.bchs;

import java.util.List;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.filter.FilterModifier;

import com.on2.flix.Filter;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

/**
 * Builds a brightness filter.  This is a child of the BchsFilterBuilder
 * which must be triggered if this filter builder is to run.
 * 
 * @author jdl
 *
 */
public class BrightnessFilterBuilder implements FilterModifier {

    public BrightnessFilterBuilder() {
	super();
    }

    /** 
     * BchsFilterBuilder will call this when needed.
     */
    public void modifyFilter(Filter filter, String options) throws FlixException {
	filter.setParam(flixengine2_internalConstants.FE2_BCHS_BRIGHTNESS, Double.parseDouble(options));
    }

    public String getFriendlyName() {
	return "Brightness Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("value")
                            .hasArg()
                            .withDescription("Sets brightness in range of [-255,255].  You must also specify -bcsh.")
                            .create(getSwitch());
    }

    public String getSwitch() {
	return "brightness";
    }

    public boolean isPrimaryOption() {
	return false;
    }

    public List children() {
	return null;
    }
}
