package tv.zencoder.flix.filter.blur;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

import com.on2.flix.FlixEngine2;
import com.on2.flix.blurfilter_t;
import com.on2.flix.flixengine2_internalConstants;

/**
 * Sets the type of blur filter to use.
 * @author jdl
 *
 */
public class BlurTypeFilterBuilder extends FilterBuilderBase {

    public BlurTypeFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	modifyFilter(BuilderCache.getInstance().getBlurFilterBuilder(flix).getFilter(), getBlurFilterTypeFromOptions(options), flixengine2_internalConstants.FE2_BLUR_FILTER);
    }

    public String getFriendlyName() {
	return "Blur Type Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("gauss|lowpass")
	    		    .hasArg()
	    		    .withDescription("Sets the type of blur filter to use.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "blurtype";
    }

    public boolean isPrimaryOption() {
	return true;
    }

    
    private String getBlurFilterTypeFromOptions(String options) {
	int value = 0;
	if (options != null) {
	    if (options.equals("lowpass")) {
		value = blurfilter_t.BLUR_LOWPASS.swigValue();
		log.debug("BlurTypeFilterBuilder.getBlurFilterTypeFromOptions(): Setting blur filter type to lowpass.");
	    }
	    if (options.equals("gauss")) {
		value = blurfilter_t.BLUR_GAUSS.swigValue();
		log.debug("BlurTypeFilterBuilder.getBlurFilterTypeFromOptions(): Setting blur filter type to gauss.");
	    }

	    log.debug("BlurTypeFilterBuilder.getBlurFilterTypeFromOptions(): value=" + value);
	}
	return Integer.toString(value);
    }
    
}
