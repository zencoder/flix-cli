package tv.zencoder.flix.cli;

import java.util.List;

import com.on2.flix.FlixEngine2;

/**
 * A builder of one of the underlying Flix objects that control the encoding.  These
 * Flix objects include Filter, Codec, and Muxer.
 * 
 * @author jdl
 *
 */
public interface FlixBuilder extends OptionHandler {
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
     */		
    public void apply(FlixEngine2 flix, String options);
    
    /**
     * A list of other builders that are dependent on this one.  A child builder
     * is generally called by the parent builder.
     * 
     * @return	List
     */
    public List children();
    
    /**
     * Add a child to this builder object.  A child is an option that depends on this one.
     */
    public void addChild(FlixModifier child);
    
}
