package tv.zencoder.flix.muxer;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.StringUtil;
import tv.zencoder.flix.util.VideoMuxerConfig;

import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.Muxer;

public class VideoMuxerBuilder extends MuxerBuilderBase {

    /*
     * Short names for the various muxers.  The keys are also what
     * we would expect to see on the command line after the -vmux
     * switch.
     */
    protected static Map<String, VideoMuxerConfig> muxerConfigs;
    static {
        Map<String, VideoMuxerConfig> map = new TreeMap<String, VideoMuxerConfig>();
        map.put("flv",  VideoMuxerConfig.FLV);
        map.put("mov",  VideoMuxerConfig.MOV);
        map.put("mp4",  VideoMuxerConfig.MP4);
        map.put("3g2",  VideoMuxerConfig._3G2);
        map.put("3gp",  VideoMuxerConfig._3GP);
        muxerConfigs = Collections.unmodifiableMap(map);
    }
    
    
    public VideoMuxerBuilder() {
	super();
	addChild(new FaststartVideoMuxerModifier());
    }

    public void apply(FlixEngine2 flix, String options) {
	try {
	    VideoMuxerConfig videoMuxerConfig = muxerConfigs.get(options);
	    
	    // Stash the chosen muxer for later use by any modifiers.
	    BuilderCache.getInstance().setChosenVideoMuxer(videoMuxerConfig);
	    
	    // Grab the String constant that Flix uses for this codec type.
	    String flixMuxerName = videoMuxerConfig.getFlixMuxerName();
	    log.debug("VideoMuxerBuilder.applyMuxer(): User asked for muxer: " + options);
	    log.debug("VideoMuxerBuilder.applyMuxer(): Using Flix muxer name: " + flixMuxerName);
	    
	    // Create the muxer.
	    muxer = new Muxer(flix, flixMuxerName);
	    muxer.add();
    	    
    	    // Trigger any child modifiers of this builder.
    	    applyChildBuilders(muxer);
	} catch (FlixException e) {
	    //
	}
    }

    public String getFriendlyName() {
	return "Video Muxer Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("muxer")
	                    .hasArg()
                            .withDescription("Sets the video muxer. Valid values include: " + StringUtil.mapKeysToString(muxerConfigs, ","))
                            .create(getSwitch());
    }

    public String getSwitch() {
	return "vmux";
    }

    public boolean isPrimaryOption() {
	return true;
    }

}
