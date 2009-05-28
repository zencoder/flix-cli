package tv.zencoder.flix.filter.thumbnail;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

import com.on2.flix.FlixEngine2;
import com.on2.flix.flixengine2_internalConstants;

/**
 * Turns on the "enable alpha" flag in the thumbnail filter.  Does not take any
 * param options.
 * 
 * @author jdl
 *
 */
public class ThumbnailEnableAlphaFilterBuilder extends FilterBuilderBase {

    public ThumbnailEnableAlphaFilterBuilder() {
	super();
    }
    
    public void apply(FlixEngine2 flix, String options) {
	modifyFilter(BuilderCache.getInstance().getThumbnailFilterBuilder(flix).getFilter(), options, flixengine2_internalConstants.FE2_PNGEX_ENABLE_ALPHA, "true");
    }

    public String getFriendlyName() {
	return "Thumbnail Enable Alpha Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withDescription("Turns on alpha for thumbnailing.")
	    		    .create(getSwitch());
    }
    

    public String getSwitch() {
	return "pngex_enable_alpha";
    }

    public boolean isPrimaryOption() {
	return true;
    }

}
