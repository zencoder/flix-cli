package tv.zencoder.flix.filter.thumbnail;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.FlixEngine2;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

public class ThumbnailAutoStartTimeFilterBuilder extends FilterBuilderBase {

    public ThumbnailAutoStartTimeFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	modifyFilter(BuilderCache.getInstance().getThumbnailFilterBuilder(flix).getFilter(), options, flixengine2_internalConstants.FE2_PNGEX_AUTO_EXPORT_START_TIME, "double");
    }

    public String getFriendlyName() {
	return "Thumbnail Export Start Time Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("value")
	    		    .hasArg()
	    		    .withDescription("Sets the PNG auto generation start time, in milliseconds.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "pngex_auto_start_time";
    }

    public boolean isPrimaryOption() {
	return true;
    }
}
