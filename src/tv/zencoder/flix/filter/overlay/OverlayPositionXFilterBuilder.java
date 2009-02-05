package tv.zencoder.flix.filter.overlay;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

import com.on2.flix.FE2_OverlayPositionMode;
import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

/**
 * Sets the X value for the overlay filter position.
 * @author jdl
 *
 */
public class OverlayPositionXFilterBuilder extends FilterBuilderBase {

    public OverlayPositionXFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	Filter f = BuilderCache.getInstance().getOverlayFilterBuilder(flix).getFilter();
	modifyFilter(f, options, flixengine2_internalConstants.FE2_OVERLAY_POS_X);
	try {
	    f.setParam(flixengine2_internalConstants.FE2_OVERLAY_POS, new Double(FE2_OverlayPositionMode.FE2_OVERLAY_POS_MODE_XY.swigValue()));
	} catch (FlixException e) {
	    log.error("OverlayPositionXFilterBuilder.apply(): e=" + e.getLocalizedMessage());
	}
    }

    public String getFriendlyName() {
	return "Overlay Position X filter builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("value")
                            .hasArg()
                            .withDescription("Sets the overlay position X value.")
                            .create(getSwitch());
    }

    public String getSwitch() {
	return "fo_pos_x";
    }

    public boolean isPrimaryOption() {
	return true;
    }
}
