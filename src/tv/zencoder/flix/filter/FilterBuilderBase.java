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
     */
    protected void modifyFilter(Filter filter, String options, String paramName) {
	try {
	    filter.setParam(paramName, Double.parseDouble(options));
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
