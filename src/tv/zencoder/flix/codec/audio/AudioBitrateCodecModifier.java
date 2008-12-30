package tv.zencoder.flix.codec.audio;

import java.util.List;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.codec.CodecModifier;
import tv.zencoder.flix.util.BuilderCache;

import com.on2.flix.Codec;
import com.on2.flix.FlixException;

/**
 * Sets the audio bitrate for whatever audio codec was selected.
 * 
 * @author jdl
 *
 */
public class AudioBitrateCodecModifier implements CodecModifier {

    public AudioBitrateCodecModifier() {
	super();
    }

    public void modifyCodec(Codec codec, String options) throws FlixException {
	codec.setParam(BuilderCache.getInstance().getChosenAudioCodec().getFlixBitrateParamName(), Double.parseDouble(options));
    }

    public String getFriendlyName() {
	return "Audio bitrate";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("bitrate")
	    		    .hasArg()
	    		    .withDescription("Sets the audio bitrate. You also need to specify a value for -acodec.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "ab";
    }

    public boolean isPrimaryOption() {
	return false;
    }

    public List children() {
	return null;
    }
}
