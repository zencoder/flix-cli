package tv.zencoder.flix.filter.thumbnail;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.FlixEngine2;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

/**
 * Thumbnail Output Directory
 * 
 * @author jdl
 *
 */
public class ThumbnailOutputDirectoryFilterBuilder extends FilterBuilderBase {

    public ThumbnailOutputDirectoryFilterBuilder() {
        super();
    }

    public void apply(FlixEngine2 flix, String options) {
	modifyFilter(BuilderCache.getInstance().getThumbnailFilterBuilder(flix).getFilter(), options, flixengine2_internalConstants.FE2_PNGEX_OUTPUT_DIRECTORY, "string");
    }

    public String getFriendlyName() {
	return "Thumbnail Output Directory Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("path")
	    		    .hasArg()
	    		    .withDescription("Sets the thumbnail output directory.")
	    		    .create(getSwitch());
    }
    

    public String getSwitch() {
	return "pngex_output_dir";
    }

    public boolean isPrimaryOption() {
	return true;
    }

}
