package tv.zencoder.flix.filter.thumbnail;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.FlixEngine2;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

/**
 * Sets a string of export times in ms. The string MUST be in the format: t0,t1,t2,tn.
 * @author jdl
 *
 */
public class ThumbnailExportTimeStringFilterBuilder extends FilterBuilderBase {

    public ThumbnailExportTimeStringFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	modifyFilter(BuilderCache.getInstance().getThumbnailFilterBuilder(flix).getFilter(), options, flixengine2_internalConstants.FE2_PNGEX_EXPORT_TIME_STRING, "string");
    }

    public String getFriendlyName() {
	return "Thumbnail Export Time String Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("value")
	    		    .hasArg()
	    		    .withDescription("Sets a string of export times in ms. The string MUST be in the format: t0,t1,t2,tn")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "pngex_export_time_string";
    }

    public boolean isPrimaryOption() {
	return true;
    }

}
