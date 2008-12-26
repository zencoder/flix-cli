package tv.zencoder.flix.codec;

import java.util.List;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.util.CommandLineHelper;

import com.on2.flix.Codec;
import com.on2.flix.FlixException;

public class VideoBitrateCodecModifier implements CodecModifier {

    public VideoBitrateCodecModifier() {
	super();
    }

    public void modifyCodec(Codec codec, String options) throws FlixException {
	codec.setParam(CommandLineHelper.getInstance().getChosenVideoCodec().getFlixBitmapParamName(), Double.parseDouble(options));
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
