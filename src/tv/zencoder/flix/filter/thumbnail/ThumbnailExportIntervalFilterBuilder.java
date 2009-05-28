package tv.zencoder.flix.filter.thumbnail;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.FlixEngine2;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

public class ThumbnailExportIntervalFilterBuilder extends FilterBuilderBase {

    public ThumbnailExportIntervalFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	modifyFilter(BuilderCache.getInstance().getThumbnailFilterBuilder(flix).getFilter(), options, flixengine2_internalConstants.FE2_PNGEX_EXPORT_INTERVAL, "double");
    }

    public String getFriendlyName() {
	return "Thumbnail Export Interval Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("value")
	    		    .hasArg()
	    		    .withDescription("Sets the thumbnail export interval in milliseconds.  Valid values are between 1 and the video length.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "pngex_export_interval";
    }

    public boolean isPrimaryOption() {
	return true;
    }
}
