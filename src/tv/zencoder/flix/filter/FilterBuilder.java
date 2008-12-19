package tv.zencoder.flix.filter;

import org.apache.commons.cli.Option;

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
public interface FilterBuilder {

    /**
     * Defines the Apache Commons CLI Option that should be used for this builder.
     * @return	Option
     */
    public Option getOption();
    
    
    /**
     * Returns the command line switch that would trigger this particular 
     * filter builder.  For example, the framerate filter would return "r" 
     * for this, since its triggered by "-r fps" on the command line.
     * 
     * @return	String	command line switch
     */
    public String getSwitch();
    
    
    /**
     * Returns a name for this builder that we can use in log messages.
     */
    public String getFriendlyName();
    
    /** 
     * Given a FlixEngine2 object and an option String, which would have 
     * most likely come directly from the command line, add a new filter
     * to the FlixEngine2. 
     * 
     * @param	flix	The FlixEngine2 object being configured.
     * @param	options	A string representing the command line options supplied
     * 					with this particular switch.  For example if "-r 480x320" 
     * 					is passed in, the builder responsible for this filter
     * 					would see "480x360" as its options.  The builder
     *                  is responsible for generating the correct FlixEngine2
     *                  parameters based on this string.
     * @return TODO
     */		
    public Filter applyFilter(FlixEngine2 flix, String options);
}
