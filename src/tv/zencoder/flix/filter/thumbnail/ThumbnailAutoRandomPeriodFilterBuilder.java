package tv.zencoder.flix.filter.thumbnail;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.FlixEngine2;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

public class ThumbnailAutoRandomPeriodFilterBuilder extends FilterBuilderBase {

    public ThumbnailAutoRandomPeriodFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	modifyFilter(BuilderCache.getInstance().getThumbnailFilterBuilder(flix).getFilter(), options, flixengine2_internalConstants.FE2_PNGEX_AUTO_EXPORT_RANDOM_PERIOD, "double");
    }

    public String getFriendlyName() {
	return "Thumbnail Export Random Period Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("value")
	    		    .hasArg()
	    		    .withDescription("Sets the random period to use when generating thumbnails.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "pngex_auto_random_period";
    }

    public boolean isPrimaryOption() {
	return true;
    }
}
