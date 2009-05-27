package tv.zencoder.flix.filter.overlay;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix._on2bool;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

/**
 * Sets the G value for the overlay filter mask.
 * @author jdl
 *
 */
public class OverlayMaskGFilterBuilder extends FilterBuilderBase {

    public OverlayMaskGFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	Filter f = BuilderCache.getInstance().getOverlayFilterBuilder(flix).getFilter();
	modifyFilter(f, options, flixengine2_internalConstants.FE2_OVERLAY_MASK_G, "double");
	try {
	    f.setParam(flixengine2_internalConstants.FE2_OVERLAY_MASK_RGB, new Double(_on2bool.on2true.swigValue()));
	} catch (FlixException e) {
	    log.error("OverlayMaskGFilterBuilder.apply(): e=" + e.getLocalizedMessage());
	}
    }

    public String getFriendlyName() {
	return "Overlay Mask G filter builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("value")
                            .hasArg()
                            .withDescription("Sets the overlay mask G value.")
                            .create(getSwitch());
    }

    public String getSwitch() {
	return "fo_mask_g";
    }

    public boolean isPrimaryOption() {
	return true;
    }
}
