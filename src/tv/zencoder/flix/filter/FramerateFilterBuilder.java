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
 * Creates a framerate filter.  
 * <p>
 * This can either be a straight FPS or a (TODO: integer followed by "d"
 * meaning "decimate."  This is the amount that the original framerate will
 * be divided by.)
 * <p>
 * Example 1: "15.0"
 * This means "15.0 FPS"
 * <p>
 * Example 2: "2d"
 * This means "1/2 the framerate of the original"
 * 
 * @author	jdl
 *
 */
public class FramerateFilterBuilder extends FilterBuilderBase {
    // Looks for a number followed by "d" which would indicate "decimate".
    protected static final Pattern decimatePattern = Pattern.compile("^([0-9]+)d");
    
    public FramerateFilterBuilder() {
	super();
    }

    /* (non-Javadoc)
     * @see tv.zencoder.flix.filter.FilterBuilder#applyFilter(com.on2.flix.FlixEngine2, java.lang.String)
     */
    public Filter applyFilter(FlixEngine2 flix, String options) {
	Filter filter = null;
	try {
	    filter = new Filter(flix, flixengine2_internalConstants.FE2_FILTER_FRAMERATE);
	    filter.add();
	    
	    // Are we adding an fps number or a decimate value?
	    Matcher m = decimatePattern.matcher(options);
	    if (m.matches()) {
		// decimate
		filter.setParam(flixengine2_internalConstants.FE2_FRAMERATE_DECIMATE, Double.parseDouble(m.group(1)));
	    } else {
		// fps
		filter.setParam(flixengine2_internalConstants.FE2_FRAMERATE_FPS, Double.parseDouble(options));
	    }
	} catch (FlixException e) {}
	return filter;
    }

    public String getFriendlyName() {
	return "Framerate Filter Builder (-" + getSwitch() + ")";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("fps|xd")
                            .hasArg()
                            .withDescription("sets the framerate in fps(15.0) or xd(2d means 1/2 the original rate)")
                            .create(getSwitch());
    }

    public String getSwitch() {
	return "r";
    }
    
    public boolean isPrimaryOption() {
	return true;
    }

}
