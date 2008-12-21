package tv.zencoder.flix.codec;

import tv.zencoder.flix.cli.OptionHandler;

import com.on2.flix.Codec;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;

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
    public Codec applyCodec(FlixEngine2 flix, String options);
    
    /**
     * If we already have the Codec built, and just want to add a param to it, this
     * allows us to do so.  This is mainly used by child builders, where the parent would
     * have already created the Codec.
     * 
     * @param	codec
     * @param	options	A string representing the command line options for this switch.
     */
    public void modifyCodec(Codec codec, String options) throws FlixException;
    
}
