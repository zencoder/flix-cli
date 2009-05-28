package tv.zencoder.flix.filter.thumbnail;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

import com.on2.flix.FE2_PNGExCuePtMode;
import com.on2.flix.FlixEngine2;
import com.on2.flix.flixengine2_internalConstants;

public class ThumbnailExportCuePointsFilterBuilder extends FilterBuilderBase {

    public ThumbnailExportCuePointsFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	modifyFilter(BuilderCache.getInstance().getThumbnailFilterBuilder(flix).getFilter(), getCuePointsTypeFromOptions(options), flixengine2_internalConstants.FE2_PNGEX_EXPORT_CUE_POINTS, "double");
    }

    public String getFriendlyName() {
	return "Thumbnail Export Cue Points Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("nav|event|all")
	    		    .hasArg()
	    		    .withDescription("Sets the cue points to use when generating thumbnails.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "pngex_export_cue_points";
    }

    public boolean isPrimaryOption() {
	return true;
    }

    private String getCuePointsTypeFromOptions(String options) {
	int value = 0;
	if (options != null) {
	    if (options.equals("nav")) {
		value = FE2_PNGExCuePtMode.FE2_PNGEX_CP_NAV.swigValue();
	    }
	    if (options.equals("event")) {
		value = FE2_PNGExCuePtMode.FE2_PNGEX_CP_EVENT.swigValue();
	    }
	    if (options.equals("all")) {
		value = FE2_PNGExCuePtMode.FE2_PNGEX_CP_ALL.swigValue();
	    }
	    log.debug("ThumbnailExportCuePointsFilterBuilder.getCuePointsTypeFromOptions(): Setting value to " + value);
	}
	
	return Integer.toString(value);
    }

}
