package tv.zencoder.flix.muxer;

import tv.zencoder.flix.cli.OptionHandler;

import com.on2.flix.FlixException;
import com.on2.flix.Muxer;

/**
 * Typically a child of a MuxerBuilder.  This supplies extra parameters to an already created muxer.
 * 
 * @author jdl
 *
 */
public interface MuxerModifier extends OptionHandler {

    /**
     * If we already have the Muxer built, and just want to add a param to it, this
     * allows us to do so.  This is mainly used by child builders, where the parent would
     * have already created the Muxer.
     * @param	muxer
     * @param	options	A string representing the command line options for this switch.
     */
    public void modifyMuxer(Muxer muxer, String options) throws FlixException;
}
