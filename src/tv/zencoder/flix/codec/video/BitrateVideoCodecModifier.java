package tv.zencoder.flix.codec.video;

import java.util.List;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.codec.CodecModifier;
import tv.zencoder.flix.util.BuilderCache;

import com.on2.flix.Codec;
import com.on2.flix.FlixException;

/**
 * Sets the video bitrate for whatever codec was selected.
 * 
 * @author jdl
 *
 */
public class BitrateVideoCodecModifier implements CodecModifier {

    public BitrateVideoCodecModifier() {
	super();
    }

    public void modifyCodec(Codec codec, String options) throws FlixException {
	codec.setParam(BuilderCache.getInstance().getChosenVideoCodec().getFlixBitrateParamName(), Double.parseDouble(options));
    }

    public String getFriendlyName() {
	return "Video bitrate";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("bitrate")
	    		    .hasArg()
	    		    .withDescription("Sets the video bitrate. You also need to specify a value for -vcodec.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "b";
    }

    public boolean isPrimaryOption() {
	return false;
    }

    public List children() {
	return null;
    }
}
