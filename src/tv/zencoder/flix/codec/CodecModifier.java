package tv.zencoder.flix.codec;

import tv.zencoder.flix.cli.OptionHandler;

import com.on2.flix.Codec;
import com.on2.flix.FlixException;

/**
 * Typically a child of a CodecBuilder.  This supplies extra parameters to an already created codec.
 * 
 * @author jdl
 *
 */
public interface CodecModifier extends OptionHandler {
    /**
     * If we already have the Codec built, and just want to add a param to it, this
     * allows us to do so.  This is mainly used by child builders, where the parent would
     * have already created the Codec.
     * @param	codec
     * @param	options	A string representing the command line options for this switch.
     */
    public void modifyCodec(Codec codec, String options) throws FlixException;
}
