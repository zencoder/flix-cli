package tv.zencoder.flix.filter.crop;

import org.apache.commons.cli.Option;

import tv.zencoder.flix.filter.FilterBuilderBase;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

/**
 * Created if one or more of the CropTop, CropBottom, CropLeft, or CropRight
 * filter builders are needed.  This builder doesn't have its own command line
 * switch.
 * 
 * @author jdl
 *
 */
public class CropFilterBuilder extends FilterBuilderBase {

    public CropFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	try {
    	    filter = new Filter(flix, flixengine2_internalConstants.FE2_FILTER_CROP);
    	    filter.add();
	} catch (FlixException e) {
	}
    }

    public String getFriendlyName() {
	return "";
    }

    public Option getOption() {
	return null;
    }

    public String getSwitch() {
	return null;
    }

    public boolean isPrimaryOption() {
	return false;
    }

}
