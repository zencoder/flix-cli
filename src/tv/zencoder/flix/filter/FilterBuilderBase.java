package tv.zencoder.flix.filter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tv.zencoder.flix.cli.FlixBuilder;
import tv.zencoder.flix.cli.FlixModifier;
import tv.zencoder.flix.cli.OptionHandler;
import tv.zencoder.flix.util.CommandLineHelper;
import tv.zencoder.flix.util.LogWrapper;

import com.on2.flix.Filter;
import com.on2.flix.FlixException;
import com.on2.flix._on2bool;

/**
 * Base class for filter builders that handles common methods, like children.
 * @author jdl
 *
 */
public abstract class FilterBuilderBase implements FlixBuilder, OptionHandler {
    protected LogWrapper log = LogWrapper.getInstance();
    protected List<FilterModifier> children = new ArrayList<FilterModifier>();
    protected Filter filter;
    
    public List<FilterModifier> children() {
	return children;
    }

    public void addChild(FlixModifier child) {
	children.add((FilterModifier) child);
    }
    
    /**
     * Helper that parent builders can use to trigger their children when its
     * time to use them to modify the existing filter.
     */
    protected void applyChildBuilders(Filter filter) throws FlixException {
	if (children().size() > 0) {
	    CommandLineHelper clHelper = CommandLineHelper.getInstance();
            Iterator<FilterModifier> childIter = children().iterator();
            while (childIter.hasNext()) {
        	FilterModifier child = childIter.next();
        
        	// Was this particular child called for on the command line?
        	if (clHelper.isOptionInUse(child)) {
        	    String optionArgument = clHelper.getLine().getOptionValue(child.getSwitch());
        	    log.debug("FilterBuilderBase.applyChildBuilders(): Modifying filter with " + child.getFriendlyName() + " and option: " + optionArgument);
        	    child.apply(filter, optionArgument);
        	}
            }
	}
    }

    
    /**
     * Sets a given param-option (name-value) for a Filter.
     * 
     * @param filter
     * @param options
     * @param paramName
     * @param paramValueType TODO
     */
    protected void modifyFilter(Filter filter, String options, String paramName, String paramValueType) {
	try {
	    if (paramValueType.equals("double")) {
		// The most common case, where some text from the command line needs to be
		// converted into a double in the filter.
		filter.setParam(paramName, Double.parseDouble(options));
		
	    } else if (paramValueType.equals("string")) {
		// Passes whatever is on the command line as an option directly into the filter.
		filter.setParamAsStr(paramName, options);
		
	    } else if (paramValueType.equals("true")) {
		// Sets the double value for an "On2 True".
		filter.setParam(paramName, new Double(_on2bool.on2true.swigValue()));
		
	    } else if (paramValueType.equals("false")) {
		// Sets the double value for an "On2 False".
		filter.setParam(paramName, new Double(_on2bool.on2false.swigValue()));
	    }
	    
	} catch (NumberFormatException e) {
	    log.debug("FilterBuilderBase.modifyFilter(): Failed to parse options into a Double. e=" + e.getLocalizedMessage());
	} catch (FlixException e) {
	    log.debug("FilterBuilderBase.modifyFilter(): Failed to modify the filter. e=" + e.getLocalizedMessage());
	}
    }
    
    public Filter getFilter() {
        return filter;
    }

    protected void setFilter(Filter filter) {
        this.filter = filter;
    }
}
