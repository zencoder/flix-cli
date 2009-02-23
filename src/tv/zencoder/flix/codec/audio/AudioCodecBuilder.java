package tv.zencoder.flix.codec.audio;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.codec.CodecBuilderBase;
import tv.zencoder.flix.util.AudioCodecConfig;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.StringUtil;

import com.on2.flix.Codec;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;

/**
 * Sets the audio codec.
 * 
 * @author jdl
 *
 */
public class AudioCodecBuilder extends CodecBuilderBase {

    /*
     * Short names for the various codecs.  The keys are also what
     * we would expect to see on the command line after the -acodec
     * switch.
     */
    protected static Map<String, AudioCodecConfig> codecConfigs;
    static {
        Map<String, AudioCodecConfig> map = new TreeMap<String, AudioCodecConfig>();
        map.put("aac",     AudioCodecConfig.AAC);
        map.put("aacplus", AudioCodecConfig.AACPLUS);
        map.put("amr_nb",  AudioCodecConfig.AMR_NB);
        map.put("mp3",     AudioCodecConfig.MP3);
        codecConfigs = Collections.unmodifiableMap(map);
    }
    
    
    public AudioCodecBuilder() {
	super();
	addChild(new BitrateAudioCodecModifier());
	addChild(new ParametricSteroAudioCodecModifier());
	addChild(new LameRcModeAudioCodecModifier());
    }

    public void apply(FlixEngine2 flix, String options) {
	try {
	    AudioCodecConfig audioCodecConfig = codecConfigs.get(options);
	    
	    // Stash the chosen codec so that any modifiers can look up which code we're using.
	    BuilderCache.getInstance().setChosenAudioCodec(audioCodecConfig);
	    
	    // Grab the String constant that Flix uses for this codec type.
	    String flixCodecName = audioCodecConfig.getFlixCodecName();
	    log.debug("AudioCodecConfig.applyCodec(): User asked for codec: " + options);
	    log.debug("AudioCodecConfig.applyCodec(): Using Flix codec name: " + flixCodecName);
	    
	    // Create the codec.
    	    codec = new Codec(flix, flixCodecName);
    	    codec.add();
    	    
    	    // Trigger any child modifiers of this builder.
    	    applyChildBuilders(codec);
	} catch (FlixException e) {
	    //
	}
    }

    public String getFriendlyName() {
	return "Audio Codec Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("codec_name")
        		    .hasArg()
                            .withDescription("Sets the audio codec. Valid values include: " + StringUtil.mapKeysToString(codecConfigs, ", "))
                            .create(getSwitch());
    }

    public String getSwitch() {
	return "acodec";
    }

    public boolean isPrimaryOption() {
	return true;
    }

}
