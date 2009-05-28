package tv.zencoder.flix.filter.thumbnail;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.FlixEngine2;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

public class ThumbnailAutoEndTimeFilterBuilder extends FilterBuilderBase {

    public ThumbnailAutoEndTimeFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	modifyFilter(BuilderCache.getInstance().getThumbnailFilterBuilder(flix).getFilter(), options, flixengine2_internalConstants.FE2_PNGEX_AUTO_EXPORT_END_TIME, "double");
    }

    public String getFriendlyName() {
	return "Thumbnail Export End Time Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("value")
	    		    .hasArg()
	    		    .withDescription("Sets the PNG auto generation end time, in milliseconds.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "pngex_auto_end_time";
    }

    public boolean isPrimaryOption() {
	return true;
    }
}
