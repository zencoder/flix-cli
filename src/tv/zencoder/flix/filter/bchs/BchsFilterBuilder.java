package tv.zencoder.flix.filter.bchs;

import org.apache.commons.cli.Option;

import tv.zencoder.flix.filter.FilterBuilderBase;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

/**
 * A BCHS Filter in Flix can handle params for one or more of Brightness, Contrast, Hue, and Saturation.  This builder
 * creates the mail Filter object, but by itself doesn't really do anything.  This shouldn't be created from the command
 * line, which is why it returns a null Option.  The Brightness, etc. builders will create one of these when needed.
 * 
 * @author jdl
 *
 */
public class BchsFilterBuilder extends FilterBuilderBase {
    public BchsFilterBuilder() {
	super();
    }
    
    public void apply(FlixEngine2 flix, String options) {
	try {
    	    filter = new Filter(flix, flixengine2_internalConstants.FE2_FILTER_BCHS);
    	    filter.add();
	} catch (FlixException e) {
	}
    }

    public String getFriendlyName() {
	return "";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return null;
    }

    public String getSwitch() {
	return "";
    }

    public boolean isPrimaryOption() {
	return false;
    }
}
