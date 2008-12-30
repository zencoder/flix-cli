package tv.zencoder.flix.filter.resample;

import org.apache.commons.cli.Option;

import tv.zencoder.flix.filter.FilterBuilderBase;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

/**
 * Creates an Audio Resample Filter.  This is used by the resample rate and 
 * resample channels filters.
 * 
 * @author jdl
 *
 */
public class AudioResampleFilterBuilder extends FilterBuilderBase {

    public AudioResampleFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	try {
    	    filter = new Filter(flix, flixengine2_internalConstants.FE2_FILTER_RESAMPLE);
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
