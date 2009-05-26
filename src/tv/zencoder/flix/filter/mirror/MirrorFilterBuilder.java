package tv.zencoder.flix.filter.mirror;


import org.apache.commons.cli.Option;

import tv.zencoder.flix.filter.FilterBuilderBase;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

/**
 * Parent for MirrorHorizontal and MirrorVertical filter builders.  This does have its
 * own command line switch.
 * 
 * @author jdl
 *
 */
public class MirrorFilterBuilder extends FilterBuilderBase {

    public MirrorFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	try {
    	    filter = new Filter(flix, flixengine2_internalConstants.FE2_FILTER_MIRROR);
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
