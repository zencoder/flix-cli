package tv.zencoder.flix.filter.cut;

import org.apache.commons.cli.Option;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.filter.FilterBuilderBase;

/**
 * Created if either the CutStart or CutStop filter builders are needed.
 * This builder doesn't have its own command line switch.
 * 
 * @author jdl
 *
 */
public class CutFilterBuilder extends FilterBuilderBase {

    public CutFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	try {
    	    filter = new Filter(flix, flixengine2_internalConstants.FE2_FILTER_CUT);
    	    filter.add();
	} catch (FlixException e) {
	}
    }

    public String getFriendlyName() {
	return null;
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
