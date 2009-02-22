package tv.zencoder.flix.filter.overlay;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.StringUtil;

import com.on2.flix.FE2_OverlayPositionMode;
import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

/**
 * Sets the position mode for the overlay filter.  You don't have to use XY mode if you're
 * setting an X/Y coordinate.  That mode it set automatically.
 * 
 * @author jdl
 *
 */
public class OverlayPositionModeFilterBuilder extends FilterBuilderBase {

    /**
     * Position Mode types.  This maps the command line option to a Flix param.
     */
    protected static Map<String, FE2_OverlayPositionMode> posModeTypes;
    static {
        Map<String, FE2_OverlayPositionMode> map = new TreeMap<String, FE2_OverlayPositionMode>();
        map.put("botleft", FE2_OverlayPositionMode.FE2_OVERLAY_POS_MODE_BOTLEFT);
        map.put("botright", FE2_OverlayPositionMode.FE2_OVERLAY_POS_MODE_BOTRIGHT);
        map.put("center", FE2_OverlayPositionMode.FE2_OVERLAY_POS_MODE_CENTER);
        map.put("topleft", FE2_OverlayPositionMode.FE2_OVERLAY_POS_MODE_TOPLEFT);      
        map.put("topright", FE2_OverlayPositionMode.FE2_OVERLAY_POS_MODE_TOPRIGHT);
        posModeTypes = Collections.unmodifiableMap(map);
    }
    
    
    public OverlayPositionModeFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	Filter f = BuilderCache.getInstance().getOverlayFilterBuilder(flix).getFilter();
	try {
	    if (options != null && posModeTypes.containsKey(options)) {
	      f.setParam(flixengine2_internalConstants.FE2_OVERLAY_POS, new Double(posModeTypes.get(options).swigValue()));
	    }
	} catch (FlixException e) {
	    log.error("OverlayPositionModeFilterBuilder.apply(): e=" + e.getLocalizedMessage());
	}
    }

    public String getFriendlyName() {
	return "Overlay Position Mode filter builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("mode")
                            .hasArg()
                            .withDescription("Valid values: " + StringUtil.mapKeysToString(posModeTypes, ", ") + " -- Sets the overlay position mode.  Don't use this if you are setting an X/Y coordinate. That mode is enabled automatically.")
                            .create(getSwitch());
    }

    public String getSwitch() {
	return "fo_pos_mode";
    }

    public boolean isPrimaryOption() {
	return true;
    }
    

}
