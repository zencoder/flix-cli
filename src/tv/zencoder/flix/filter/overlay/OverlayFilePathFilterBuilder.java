package tv.zencoder.flix.filter.overlay;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

/**
 * Sets the overlay file path.  This file path must be absolute, as opposed
 * to relative.
 * 
 * @author jdl
 *
 */
public class OverlayFilePathFilterBuilder extends FilterBuilderBase {

    public OverlayFilePathFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	Filter filter = BuilderCache.getInstance().getOverlayFilterBuilder(flix).getFilter();
	try {
	    // The modifyFilter() method from the base class won't work here, because that
	    // expects to be able to parse the options into a Double.  That obviously won't
	    // work with a file path.
	    filter.setParamAsStr(flixengine2_internalConstants.FE2_OVERLAY_FILE, options);
	} catch (FlixException e) {
	    log.debug("FilterBuilderBase.modifyFilter(): Failed to modify the filter. e=" + e.getLocalizedMessage());
	}
    }

    public String getFriendlyName() {
	return "Overlay file path filter builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("file path")
                            .hasArg()
                            .withDescription("Sets the overlay file path (must be absolute not relative).")
                            .create(getSwitch());
    }
    
    public String getSwitch() {
	return "fo_file";
    }

    public boolean isPrimaryOption() {
	return true;
    }

}
