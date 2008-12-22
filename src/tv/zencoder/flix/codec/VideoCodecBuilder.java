package tv.zencoder.flix.codec;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.cli.CommandLineHelper;
import tv.zencoder.flix.codec.config.VideoCodecConfig;

import com.on2.flix.Codec;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;

/**
 * Builds the video codec object that we want to use.  Flix Engine will use VP6 by
 * default, so any codec-related options that are passed in without specifying a 
 * particular codec will be assumed to be VP6.
 * 
 * @author jdl
 *
 */
public class VideoCodecBuilder extends CodecBuilderBase {

    /*
     * Short names for the various codecs.  The keys are also what
     * we would expect to see on the command line after the -vcodec
     * switch.
     */
    protected static Map<String, VideoCodecConfig> codecConfigs;
    static {
        Map<String, VideoCodecConfig> map = new TreeMap<String, VideoCodecConfig>();
        map.put("vp6",  VideoCodecConfig.VP6);
        map.put("vp6a", VideoCodecConfig.VP6A);
        map.put("h263", VideoCodecConfig.H263);
        map.put("h264", VideoCodecConfig.H264);
        codecConfigs = Collections.unmodifiableMap(map);
    }
    
    public VideoCodecBuilder() {
	super();
	addChild(new VideoBitrateCodecModifier());
    }

    public Codec apply(FlixEngine2 flix, String options) {
	Codec codec = null;
	try {
	    VideoCodecConfig videoCodecConfig = codecConfigs.get(options);
	    
	    // Stash the chosen codec so that any modifiers can look up which code we're using.
	    CommandLineHelper.getInstance().setChosenVideoCodec(videoCodecConfig);
	    
	    // Grab the String constant that Flix uses for this codec type.
	    String flixCodecName = videoCodecConfig.getFlixCodecName();
	    log.debug("VideoCodecBuilder.applyCodec(): User asked for codec: " + options);
	    log.debug("VideoCodecBuilder.applyCodec(): Using Flix codec name: " + flixCodecName);
	    
	    // Create the codec.
    	    codec = new Codec(flix, flixCodecName);
    	    codec.add();
    	    
    	    // Trigger any child modifiers of this builder.
    	    applyChildBuilders(codec);
	} catch (FlixException e) {
	    //
	}
	return codec;
    }

    public String getFriendlyName() {
	return "Video Codec Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	
	// Use the codec names Map from above to generate a list of codecs that the user 
	// can specify.
	StringBuffer codecNames = new StringBuffer();
	Iterator<String> codecNameIter = codecConfigs.keySet().iterator();
	while (codecNameIter.hasNext()) {
	    codecNames.append(codecNameIter.next());
	    if (codecNameIter.hasNext()) {
		codecNames.append(", ");
	    }
	}
	
	return OptionBuilder.withArgName("codec_name")
        		    .hasArg()
                            .withDescription("Sets the video codec. Valid values include: " + codecNames.toString())
                            .create(getSwitch());
    }

    public String getSwitch() {
	return "vcodec";
    }

    public boolean isPrimaryOption() {
	return true;
    }

}
