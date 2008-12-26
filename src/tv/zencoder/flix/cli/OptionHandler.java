package tv.zencoder.flix.cli;

import org.apache.commons.cli.Option;


/**
 * This is the main interface that the builders use.  It defines the basic methods
 * that are needed when we're converting command line switches into flix engine
 * configurations.
 * 
 * @author jdl
 *
 */
public interface OptionHandler {
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
     * When we're cycling through the command line options, should we consider this
     * option on its own, or will another option look at this one if needed?
     */
    public boolean isPrimaryOption();
 
}
