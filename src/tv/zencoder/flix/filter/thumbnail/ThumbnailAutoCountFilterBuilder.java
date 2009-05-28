package tv.zencoder.flix.filter.thumbnail;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.FlixEngine2;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

/**
 * Sets the number of thumbnails to generate.  They are evenly spaced chronologically across
 * the output file.
 * @author jdl
 *
 */
public class ThumbnailAutoCountFilterBuilder extends FilterBuilderBase {

    public ThumbnailAutoCountFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	modifyFilter(BuilderCache.getInstance().getThumbnailFilterBuilder(flix).getFilter(), options, flixengine2_internalConstants.FE2_PNGEX_AUTO_EXPORT_COUNT, "double");
    }

    public String getFriendlyName() {
	return "Thumbnail Export Count Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("value")
	    		    .hasArg()
	    		    .withDescription("Sets the number of thumbnails to generate, evenly spaced chronologically across the output file. Max 10000.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "pngex_auto_count";
    }

    public boolean isPrimaryOption() {
	return true;
    }

}
