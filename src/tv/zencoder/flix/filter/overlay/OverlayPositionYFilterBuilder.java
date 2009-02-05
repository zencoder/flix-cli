package tv.zencoder.flix.filter.overlay;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.FE2_OverlayPositionMode;
import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

/**
 * Sets the Y value for the overlay filter position.
 * @author jdl
 *
 */
public class OverlayPositionYFilterBuilder extends FilterBuilderBase {

    public OverlayPositionYFilterBuilder() {
    }

    public void apply(FlixEngine2 flix, String options) {
	Filter f = BuilderCache.getInstance().getOverlayFilterBuilder(flix).getFilter();
	modifyFilter(f, options, flixengine2_internalConstants.FE2_OVERLAY_POS_Y);
	try {
	    f.setParam(flixengine2_internalConstants.FE2_OVERLAY_POS, new Double(FE2_OverlayPositionMode.FE2_OVERLAY_POS_MODE_XY.swigValue()));
	} catch (FlixException e) {
	    log.error("OverlayPositionYFilterBuilder.apply(): e=" + e.getLocalizedMessage());
	}
    }

    public String getFriendlyName() {
	return "Overlay Position Y filter builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("value")
                            .hasArg()
                            .withDescription("Sets the overlay position Y value.")
                            .create(getSwitch());
    }

    public String getSwitch() {
	return "fo_pos_y";
    }

    public boolean isPrimaryOption() {
	return true;
    }
}
