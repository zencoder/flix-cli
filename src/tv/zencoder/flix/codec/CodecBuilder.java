package tv.zencoder.flix.codec;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;

import tv.zencoder.flix.cli.OptionHandler;

/**
 * Interface for codec builders.  There are several of these, but we should 
 * only be dealing with one at a time.
 * 
 * @author jdl
 *
 */
public interface CodecBuilder extends OptionHandler {
    /** 
     * Given a FlixEngine2 object and an option String, which would have 
     * most likely come directly from the command line, add a new codec
     * to the FlixEngine2. 
     * 
     * @param	flix	The FlixEngine2 object being configured.
     * @param	options	A string representing the command line options for this codec
     */		
    public Filter applyCodec(FlixEngine2 flix, String options);
}
