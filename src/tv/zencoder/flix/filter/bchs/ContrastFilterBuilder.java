package tv.zencoder.flix.filter.bchs;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.filter.FilterBuilderBase;

public class ContrastFilterBuilder extends FilterBuilderBase {

    public ContrastFilterBuilder() {
	super();
    }

    public Filter applyFilter(FlixEngine2 flix, String options) {
	return null;
    }
    
    /** 
     * BchsFilterBuilder will call this when needed.
     */
    public void modifyFilter(Filter filter, String options) throws FlixException {
	filter.setParam(flixengine2_internalConstants.FE2_BCHS_CONTRAST, Double.parseDouble(options));
    }

    public String getFriendlyName() {
	return "Contrast Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("value")
                            .hasArg()
                            .withDescription("Sets contrast in range of [-255,255].  You must also specify -bcsh.")
                            .create(getSwitch());
    }
    
    public String getSwitch() {
	return "contrast";
    }

    public boolean isPrimaryOption() {
	return false;
    }

}
