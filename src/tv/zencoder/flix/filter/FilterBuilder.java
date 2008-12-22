package tv.zencoder.flix.filter;

import java.util.List;

import tv.zencoder.flix.cli.OptionHandler;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;


/**
 * Interface for the filter builders.  The process of converting a set of
 * command line options into the proper Flix Engine configurations may involve
 * using many of these such builders.
 * 
 * @author jdl
 *
 */
public interface FilterBuilder extends OptionHandler {
    /** 
     * Given a FlixEngine2 object and an option String, which would have 
     * most likely come directly from the command line, add a new object
     * to the FlixEngine2.  Depending on the particular builder, this could
     * be a Filter, Codec, or Muxer object.
     * 
     * @param	flix	The FlixEngine2 object being configured.
     * @param	options	A string representing the command line options supplied
     * 					with this particular switch.  For example if "-r 480x320" 
     * 					is passed in, the builder responsible for this 
     * 					would see "480x360" as its options.  The builder
     *                  is responsible for generating the correct FlixEngine2
     *                  parameters based on this string.
     * @return TODO
     */		
    public Filter apply(FlixEngine2 flix, String options);
    
    /**
     * List of FilterModifier objects which depend on this one as a parent.
     */
    public List<FilterModifier> children();
    
    /**
     * Add a child to this filter builder object.  A child is an option that depends on this one.
     * <p>
     * Example: The BchsFilterBuilder is a parent option, upon which the BrightnessFilterModifier depends.
     */
    public void addChild(FilterModifier child);
}
