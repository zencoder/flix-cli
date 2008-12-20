package tv.zencoder.flix.filter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.on2.flix.Filter;
import com.on2.flix.FlixException;

import tv.zencoder.flix.util.CommandLineHelper;
import tv.zencoder.flix.util.LogWrapper;

/**
 * Base class for filter builders that handles common methods, like children.
 * @author jdl
 *
 */
public abstract class FilterBuilderBase implements FilterBuilder {
    protected LogWrapper log = LogWrapper.getInstance();
    protected List<FilterBuilder> children = new ArrayList<FilterBuilder>();
 
    public void modifyFilter(Filter filter, String options) throws FlixException {}
    
    public List<FilterBuilder> children() {
	return children;
    }

    public void addChild(FilterBuilder child) {
	children.add(child);
    }
    
    /**
     * Helper that parent filter builders can use to trigger their children when its
     * time to use them to modify the existing filter.
     */
    protected void applyChildBuilders(Filter filter) throws FlixException {
	if (children().size() > 0) {
	    CommandLineHelper clHelper = CommandLineHelper.getInstance();
            Iterator<FilterBuilder> childIter = children().iterator();
            while (childIter.hasNext()) {
        	FilterBuilder child = childIter.next();
        
        	// Was this particular child called for on the command line?
        	if (clHelper.isOptionInUse(child)) {
        	    String optionArgument = clHelper.getLine().getOptionValue(child.getSwitch());
        	    log.debug("BchsFilterBuilder.applyFilter(): Modifying filter with " + child.getFriendlyName() + " and option: " + optionArgument);
        	    child.modifyFilter(filter, optionArgument);
        	}
            }
	}
    }
}
