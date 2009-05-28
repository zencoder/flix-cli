package tv.zencoder.flix.filter.thumbnail;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.FlixEngine2;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

public class ThumbnailCompressionFilterBuilder extends FilterBuilderBase {

    public ThumbnailCompressionFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	modifyFilter(BuilderCache.getInstance().getThumbnailFilterBuilder(flix).getFilter(), options, flixengine2_internalConstants.FE2_PNGEX_COMPRESSION_LEVEL, "double");
    }

    public String getFriendlyName() {
	return "Thumbnail Compression Level Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("value")
	    		    .hasArg()
	    		    .withDescription("Sets the thumbnail compression level used by libpng. [-1,9]")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "pngex_compression_level";
    }

    public boolean isPrimaryOption() {
	return true;
    }
}
