package tv.zencoder.flix.muxer.cuepoint;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.muxer.MuxerModifier;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.LogWrapper;
import tv.zencoder.flix.util.VideoMuxerConfig;

import com.on2.flix.FlixException;
import com.on2.flix.Muxer;

public class CuepointNavVideoMuxerModifier implements MuxerModifier {
    LogWrapper log = LogWrapper.getInstance();
    
    public CuepointNavVideoMuxerModifier() {
	super();
    }
    
    public void modifyMuxer(Muxer muxer, String options) throws FlixException {
	VideoMuxerConfig videoMuxerConfig = BuilderCache.getInstance().getChosenVideoMuxer();
	if (videoMuxerConfig.getFlixCuepointNavParamName() != null) {
	    // The user is allowed to set a list of cuepoints, separated by commas.
	    String[] cuePointArray = options.split(",");
	    for (int i=0; i < cuePointArray.length; i++) {
		muxer.setParamAsStr(videoMuxerConfig.getFlixCuepointNavParamName(), cuePointArray[i]);
	    }
	} else {
	    log.debug("CuepointNavVideoMuxerModifier.modifyMuxer(): cue point nav is not supported by this muxer.  Ignoring.");
	}
    }

    public String getFriendlyName() {
	return "Cue Point Nav Muxer Modifier";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("cpName0=cpTime0,cpName1=cpTime1,...")
	    		    .hasArg()
	    		    .withDescription("Sets a nav cue point(s) of name cpName at time cpTime in seconds. Example: nav0=10.0,nav1=12.5 to set two cue points, one at 10s and 12.5s.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "cp_nav";
    }

    public boolean isPrimaryOption() {
	return false;
    }

}
