package tv.zencoder.flix.filter.resample;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.FlixEngine2;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

/**
 * Sets the audio resample rate.
 * <p>
 * From the Flix docs:<br>
 * "FLV/SWF files are limited to sample rates of 11025 Hz, 22050 Hz and 44100 Hz."
 * 
 * @author jdl
 *
 */
public class AudioResampleRateFilterBuilder extends FilterBuilderBase {

    public AudioResampleRateFilterBuilder() {
    }

    public void apply(FlixEngine2 flix, String options) {
	modifyFilter(BuilderCache.getInstance().getAudioResampleFilterBuilder(flix).getFilter(), options, flixengine2_internalConstants.FE2_RESAMPLE_RATE);
    }

    public String getFriendlyName() {
	return "Audio Resample Rate Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("rate")
                            .hasArg()
                            .withDescription("Sets the audio resample rate in Hz.  If you are generating FLV/SWF files you are limited to specifying 11025, 22050, or 44100 for this value.")
                            .create(getSwitch());
    }

    public String getSwitch() {
	return "ar";
    }

    public boolean isPrimaryOption() {
	return true;
    }

}
