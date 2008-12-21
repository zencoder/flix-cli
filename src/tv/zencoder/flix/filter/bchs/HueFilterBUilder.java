package tv.zencoder.flix.filter.bchs;

import java.util.List;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.filter.FilterModifier;

import com.on2.flix.Filter;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

/**
 * Builds a hue filter.  This is a child of the BchsFilterBuilder
 * which must be triggered if this filter builder is to run.
 * 
 * @author jdl
 *
 */
public class HueFilterBUilder implements FilterModifier {

    public HueFilterBUilder() {
	super();
    }

    /** 
     * BchsFilterBuilder will call this when needed.
     */
    public void modifyFilter(Filter filter, String options) throws FlixException {
	filter.setParam(flixengine2_internalConstants.FE2_BCHS_HUE, Double.parseDouble(options));
    }
    
    public String getFriendlyName() {
	return "Hue Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("value")
                            .hasArg()
                            .withDescription("Sets hue in range of [-180,180].  You must also specify -bcsh.")
                            .create(getSwitch());
    }

    public String getSwitch() {
	return "hue";
    }

    public boolean isPrimaryOption() {
	return false;
    }

    public List children() {
	return null;
    }
}
