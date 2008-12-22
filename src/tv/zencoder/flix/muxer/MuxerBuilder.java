package tv.zencoder.flix.muxer;

import java.util.List;

import tv.zencoder.flix.cli.OptionHandler;

import com.on2.flix.FlixEngine2;
import com.on2.flix.Muxer;

/**
 * Interface for muxer builders.
 * 
 * @author jdl
 *
 */
public interface MuxerBuilder extends OptionHandler {
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
    public Muxer apply(FlixEngine2 flix, String options);
    
    /**
     * List of MuxerModifier objects which depend on this one as a parent.
     */
    public List<MuxerModifier> children();
    
    /**
     * Add a child modifier to this muxer builder object.
     */
    public void addChild(MuxerModifier child);
}
