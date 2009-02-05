package tv.zencoder.flix.filter.overlay;

import org.apache.commons.cli.Option;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.filter.FilterBuilderBase;

/**
 * The OverlayFilterBuilder does not have its own switch.  This filter builder is
 * used by the various overlay filter modifiers.
 * @author jdl
 *
 */
public class OverlayFilterBuilder extends FilterBuilderBase {

    public OverlayFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	try {
    	    filter = new Filter(flix, flixengine2_internalConstants.FE2_FILTER_OVERLAY);
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
	return null;
    }

    public boolean isPrimaryOption() {
	return false;
    }

}
