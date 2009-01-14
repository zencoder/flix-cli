package tv.zencoder.flix.muxer;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.LogWrapper;
import tv.zencoder.flix.util.VideoMuxerConfig;

import com.on2.flix.FlixException;
import com.on2.flix.Muxer;
import com.on2.flix._on2bool;

/**
 * Sets the FATSTART option to true.  Available for MOV, MP4, 3GP, and 3G2 muxers.
 * 
 * @author jdl
 *
 */
public class FaststartVideoMuxerModifier implements MuxerModifier {
    LogWrapper log = LogWrapper.getInstance();
    
    public FaststartVideoMuxerModifier() {
	super();
    }

    public void modifyMuxer(Muxer muxer, String options) throws FlixException {
	VideoMuxerConfig videoMuxerConfig = BuilderCache.getInstance().getChosenVideoMuxer();
	if (videoMuxerConfig.getFlixFaststartParamName() != null) {
	    muxer.setParam(videoMuxerConfig.getFlixFaststartParamName(), new Double(_on2bool.on2true.swigValue()));
	} else {
	    log.debug("FaststartVideoMuxerModifier.modifyMuxer(): faststart is not supported by this muxer.  Ignoring.");
	}
    }

    public String getFriendlyName() {
	return "Faststart muxer modifier";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withDescription("Enables faststart muxers that support it.")
                            .create(getSwitch());
    }

    public String getSwitch() {
	return "faststart";
    }

    public boolean isPrimaryOption() {
	return false;
    }

}
