package tv.zencoder.flix.filter.thumbnail;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.FlixEngine2;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

public class ThumbnailFilenamePrefixFilterBuilder extends FilterBuilderBase {

    public ThumbnailFilenamePrefixFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	modifyFilter(BuilderCache.getInstance().getThumbnailFilterBuilder(flix).getFilter(), options, flixengine2_internalConstants.FE2_PNGEX_FILENAME_PREFIX, "string");
    }

    public String getFriendlyName() {
	return "Thumbnail Filename Prefix Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("path")
	    		    .hasArg()
	    		    .withDescription("Sets the filename prefix for the thumbnail files.")
	    		    .create(getSwitch());
    }
    

    public String getSwitch() {
	return "pngex_filename_prefix";
    }

    public boolean isPrimaryOption() {
	return true;
    }
}
