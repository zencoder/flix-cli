package tv.zencoder.flix.muxer.cuepoint;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.muxer.MuxerModifier;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.LogWrapper;
import tv.zencoder.flix.util.VideoMuxerConfig;

import com.on2.flix.FlixException;
import com.on2.flix.Muxer;

public class CuepointEventVideoMuxerModifier implements MuxerModifier {
    LogWrapper log = LogWrapper.getInstance();

    public CuepointEventVideoMuxerModifier() {
	super();
    }

    public void modifyMuxer(Muxer muxer, String options) throws FlixException {
	VideoMuxerConfig videoMuxerConfig = BuilderCache.getInstance().getChosenVideoMuxer();
	if (videoMuxerConfig.getFlixCuepointEventParamName() != null) {
	    // The user is allowed to set a list of cuepoints, separated by commas.
	    String[] cuePointArray = options.split(",");
	    for (int i=0; i < cuePointArray.length; i++) {
		muxer.setParamAsStr(videoMuxerConfig.getFlixCuepointEventParamName(), cuePointArray[i]);
	    }
	} else {
	    log.debug("CuepointEventVideoMuxerModifier.modifyMuxer(): cue point event is not supported by this muxer.  Ignoring.");
	}
    }

    public String getFriendlyName() {
	return "Cue Point Event Muxer Modifier";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("cpName0=cpTime0,cpName1=cpTime1,...")
	    		    .hasArg()
	    		    .withDescription("Sets an event cue point(s) of name cpName at time cpTime in seconds. Example: event0=10.0,event1=12.5 to set two cue points, one at 10s and 12.5s.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "cp_event";
    }

    public boolean isPrimaryOption() {
	return false;
    }

}
