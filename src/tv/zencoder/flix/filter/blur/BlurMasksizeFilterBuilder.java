package tv.zencoder.flix.filter.blur;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

import com.on2.flix.FlixEngine2;
import com.on2.flix.flixengine2_internalConstants;
import com.on2.flix.masksiz_t;

public class BlurMasksizeFilterBuilder extends FilterBuilderBase {

    public BlurMasksizeFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	modifyFilter(BuilderCache.getInstance().getBlurFilterBuilder(flix).getFilter(), getBlurFilterMasksizeFromOptions(options), flixengine2_internalConstants.FE2_BLUR_MASKSIZE);
    }

    public String getFriendlyName() {
	return "Blur Masksize Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("3x3|5x5")
	    		    .hasArg()
	    		    .withDescription("Sets the blur filter mask size to either 3x3 or 5x5.")
	    		    .create(getSwitch());
    }
    
    
    public String getSwitch() {
	return "blurmasksize";
    }

    public boolean isPrimaryOption() {
	return true;
    }

    
    private String getBlurFilterMasksizeFromOptions(String options) {
	int value = 0;
	if (options != null) {
	    if (options.equals("3x3")) {
		value = masksiz_t.MASK_3x3.swigValue();
		log.debug("BlurMasksizeFilterBuilder.getBlurFilterMasksizeFromOptions(): Setting blur mask size to 3x3.");
	    }
	    if (options.equals("5x5")) {
		value = masksiz_t.MASK_5x5.swigValue();
		log.debug("BlurMasksizeFilterBuilder.getBlurFilterMasksizeFromOptions(): Setting blur mask size to 5x5.");
	    }
	    log.debug("BlurMasksizeFilterBuilder.getBlurFilterMasksizeFromOptions(): value=" + value);
	}
	return Integer.toString(value);
    }
    
}
