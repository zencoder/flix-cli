package tv.zencoder.flix.filter.bchs;

import tv.zencoder.flix.util.CommandLineHelper;
import tv.zencoder.flix.util.LogWrapper;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;

/**
 * Common methods for the brightness, contrast, hue, and saturation filter builder.
 * @author jdl
 *
 */
public class BchsFilterHelper {
    LogWrapper log = LogWrapper.getInstance();
    
    public BchsFilterHelper() {
	super();
    }
    
    /**
     * The only difference between the apply() methods for these builders is the param name that 
     * they are modifying.
     * 
     * @param flix
     * @param options
     * @param paramName
     */
    public void apply(FlixEngine2 flix, String options, String paramName) {
	Filter filter = CommandLineHelper.getInstance().getBchsFilterBuilder(flix).getFilter();
	try {
	    filter.setParam(paramName, Double.parseDouble(options));
	} catch (NumberFormatException e) {
	    log.debug("BchsFilterHelper.apply(): Failed to parse options into a Double. e=" + e.getLocalizedMessage());
	} catch (FlixException e) {
	    log.debug("BchsFilterHelper.apply(): Failed to modify the BCHS filter. e=" + e.getLocalizedMessage());
	}
    }
}
