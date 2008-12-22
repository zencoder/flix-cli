package tv.zencoder.flix.filter.bchs;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.filter.FilterBuilderBase;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

/**
 * This is a parent builder that will check for the existance of any of the four 
 * particular types of its children: Brightness, Contrast, Hue, and/or Saturation 
 * filter builders.
 * 
 * @author jdl
 *
 */
public class BchsFilterBuilder extends FilterBuilderBase {

    public BchsFilterBuilder() {
	super();
	addChild(new BrightnessFilterModifier());
	addChild(new ContrastFilterModifier());
	addChild(new HueFilterModifier());
	addChild(new SaturationFilterModifier());
    }
    
    public Filter apply(FlixEngine2 flix, String options) {
	Filter filter = null;
	try {
    	    filter = new Filter(flix, flixengine2_internalConstants.FE2_FILTER_BCHS);
    	    filter.add();
    	    applyChildBuilders(filter);
	} catch (FlixException e) {
	    //
	}
	return filter;
    }

    public String getFriendlyName() {
	return "Brightness, contrast, hue, saturation parent filter builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withDescription("Turns on the BCHS filter. Configure with -brightness, -contrast, -hue, and/or -saturation options.")
                            .create(getSwitch());
    }

    public String getSwitch() {
	return "bchs";
    }

    public boolean isPrimaryOption() {
	return true;
    }


}
