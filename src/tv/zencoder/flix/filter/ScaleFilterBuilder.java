package tv.zencoder.flix.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

/**
 * Creates a scale filter.  
 * 
 * The options are of the form "wxh" such as "480x360".

 * @author	jdl
 *
 */
public class ScaleFilterBuilder implements FilterBuilder {

    protected static final Pattern widthHeightPattern = Pattern.compile("(\\d+)x(\\d+)");

    public ScaleFilterBuilder() {
	super();
    }

    /* (non-Javadoc)
     * @see tv.zencoder.flix.filter.FilterBuilder#applyFilter(com.on2.flix.FlixEngine2, java.lang.String)
     */
    public Filter applyFilter(FlixEngine2 flix, String options) {
	Filter filter = null;
	try {
	    filter = new Filter(flix, flixengine2_internalConstants.FE2_FILTER_SCALE);
	    filter.add();
	    filter.setParam(flixengine2_internalConstants.FE2_SCALE_WIDTH, getWidth(options));
	    filter.setParam(flixengine2_internalConstants.FE2_SCALE_HEIGHT, getHeight(options));
	} catch (FlixException e) {
	    //
	}
	return filter;
    }

    protected double getWidth(String options) {
	return getDimension(options, 1);
    }

    protected double getHeight(String options) {
	return getDimension(options, 2);
    }

    /**
     * 
     * @param options	
     * @param dimensionPosition	1 or 2 for before or after the "x" respectively.
     * @return	The value at the dimension position in the options.
     */
    protected double getDimension(String options, int dimensionPosition) {
	Matcher m = widthHeightPattern.matcher(options);
	m.matches();
	return Double.parseDouble(m.group(dimensionPosition));
    }

    public String getFriendlyName() {
	return "Scale Filter Builder (-" + getSwitch() + ")";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("wxh")
			    .hasArg()
            		    .withDescription("sets the output video size (480x360)")
            		    .create(getSwitch());
    }

    public String getSwitch() {
	return "s";
    }
}
