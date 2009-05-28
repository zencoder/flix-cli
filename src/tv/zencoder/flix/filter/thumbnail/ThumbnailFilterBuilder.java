package tv.zencoder.flix.filter.thumbnail;

import org.apache.commons.cli.Option;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.filter.FilterBuilderBase;

/**
 * Parent builder for the various thumbnail builders.  This has no 
 * command line option of its own.
 *  
 * @author jdl
 *
 */
public class ThumbnailFilterBuilder extends FilterBuilderBase {

    public ThumbnailFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	try {
    	    filter = new Filter(flix, flixengine2_internalConstants.FE2_FILTER_PNGEX);
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
