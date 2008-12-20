package tv.zencoder.flix.filter.bchs;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.filter.FilterBuilderBase;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

/**
 * Brightness, Contrast, Hue, Saturation filter builder.
 * This is a parent builder that will check for the existance of any of the four 
 * particular types of specific filters mentioned above.
 * 
 * @author jdl
 *
 */
public class BchsFilterBuilder extends FilterBuilderBase {

    public BchsFilterBuilder() {
	super();
	addChild(new BrightnessFilterBuilder());
	addChild(new ContrastFilterBuilder());
	addChild(new HueFilterBUilder());
	addChild(new SaturationFilterBuilder());
    }
    
    public Filter applyFilter(FlixEngine2 flix, String options) {
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
	return OptionBuilder.withDescription("required if setting brightness, contrast, hue, and/or saturation")
                            .create(getSwitch());
    }

    public String getSwitch() {
	return "bchs";
    }

    public boolean isPrimaryOption() {
	return true;
    }


}
