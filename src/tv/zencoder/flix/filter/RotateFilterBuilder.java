package tv.zencoder.flix.filter;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

/**
 * Creates a rotate filter.  
 * <p>
 * Rotates the video clockwise.  Valid values are 0, 90, 180, and 270.
 * @author	jdl
 *
 */
public class RotateFilterBuilder extends FilterBuilderBase {

    public RotateFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	try {
	    filter = new Filter(flix, flixengine2_internalConstants.FE2_FILTER_ROTATE);
	    filter.add();
	    filter.setParam(flixengine2_internalConstants.FE2_ROTATE_ANGLE, Double.parseDouble(options));
	} catch (FlixException e) {}
    }

    public String getFriendlyName() {
	return "Rotate Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("angle")
                            .hasArg()
                            .withDescription("Rotates the video clockwise. Valid values are 0, 90, 180, and 270.")
                            .create(getSwitch());
    }


    public String getSwitch() {
	return "rotate";
    }

    public boolean isPrimaryOption() {
	return true;
    }

}
