package tv.zencoder.flix.filter.thumbnail;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.FlixEngine2;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

public class ThumbnailWidthFilterBuilder extends FilterBuilderBase {

    public ThumbnailWidthFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	modifyFilter(BuilderCache.getInstance().getThumbnailFilterBuilder(flix).getFilter(), options, flixengine2_internalConstants.FE2_PNGEX_WIDTH, "double");
    }

    public String getFriendlyName() {
	return "Thumbnail Width Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("value")
	    		    .hasArg()
	    		    .withDescription("Sets the thumbnail width in pixels.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "pngex_width";
    }

    public boolean isPrimaryOption() {
	return true;
    }

}
