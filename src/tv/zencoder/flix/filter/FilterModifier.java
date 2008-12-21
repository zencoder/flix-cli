package tv.zencoder.flix.filter;

import com.on2.flix.Filter;
import com.on2.flix.FlixException;

import tv.zencoder.flix.cli.OptionHandler;

public interface FilterModifier extends OptionHandler {

    /**
     * If we already have the Filter built, and just want to add a param to it, this
     * allows us to do so.  This is mainly used by child builders, where the parent would
     * have already created the Filter.
     * 
     * @param	filter
     * @param	options	A string representing the command line options for this switch.
     */
    public void modifyFilter(Filter filter, String options) throws FlixException;
    
}
