package tv.zencoder.flix.filter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tv.zencoder.flix.cli.CommandLineHelper;
import tv.zencoder.flix.util.LogWrapper;

import com.on2.flix.Filter;
import com.on2.flix.FlixException;

/**
 * Base class for filter builders that handles common methods, like children.
 * @author jdl
 *
 */
public abstract class FilterBuilderBase implements FilterBuilder {
    protected LogWrapper log = LogWrapper.getInstance();
    protected List<FilterModifier> children = new ArrayList<FilterModifier>();
  
    public List<FilterModifier> children() {
	return children;
    }

    public void addChild(FilterModifier child) {
	children.add(child);
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
        	    child.modifyFilter(filter, optionArgument);
        	}
            }
	}
    }
}
