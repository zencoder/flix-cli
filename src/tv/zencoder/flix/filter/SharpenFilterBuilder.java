package tv.zencoder.flix.filter;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

/**
 * Turns on the sharpen filter.
 * 
 * @author jdl
 *
 */
public class SharpenFilterBuilder extends FilterBuilderBase {

    public SharpenFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	try {
	    filter = new Filter(flix, flixengine2_internalConstants.FE2_FILTER_SHARPEN);
	    filter.add();
	} catch (FlixException e) {}
    }

    public String getFriendlyName() {
	return "Sharpen Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withDescription("Enables a sharpen filter.")
                            .create(getSwitch());
    }


    public String getSwitch() {
	return "sharpen";
    }

    public boolean isPrimaryOption() {
	return true;
    }

}
