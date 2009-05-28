package tv.zencoder.flix.filter.thumbnail;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.FlixEngine2;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

public class ThumbnailFilenameSuffixFilterBuilder extends FilterBuilderBase {

    public ThumbnailFilenameSuffixFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	modifyFilter(BuilderCache.getInstance().getThumbnailFilterBuilder(flix).getFilter(), options, flixengine2_internalConstants.FE2_PNGEX_FILENAME_SUFFIX, "string");
    }

    public String getFriendlyName() {
	return "Thumbnail Filename Suffix Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("path")
	    		    .hasArg()
	    		    .withDescription("Sets the filename suffix for the thumbnail files.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "pngex_filename_suffix";
    }

    public boolean isPrimaryOption() {
	return true;
    }
}
