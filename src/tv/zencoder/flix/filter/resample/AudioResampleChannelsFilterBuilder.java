package tv.zencoder.flix.filter.resample;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.FlixEngine2;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

/**
 * Sets the audio resample channels (1 or 2).
 * 
 * @author jdl
 *
 */
public class AudioResampleChannelsFilterBuilder extends FilterBuilderBase {

    public AudioResampleChannelsFilterBuilder() {
    }

    public void apply(FlixEngine2 flix, String options) {
	modifyFilter(BuilderCache.getInstance().getAudioResampleFilterBuilder(flix).getFilter(), options, flixengine2_internalConstants.FE2_RESAMPLE_CHANNELS, "double");
    }

    public String getFriendlyName() {
	return "Audio Resample Channels Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("channels")
                            .hasArg()
                            .withDescription("Sets the audio resample channels. (1 or 2)")
                            .create(getSwitch());
    }

    public String getSwitch() {
	return "ac";
    }

    public boolean isPrimaryOption() {
	return true;
    }

}
