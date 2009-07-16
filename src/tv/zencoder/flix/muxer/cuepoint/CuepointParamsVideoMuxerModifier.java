package tv.zencoder.flix.muxer.cuepoint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.FlixException;
import com.on2.flix.Muxer;

import tv.zencoder.flix.muxer.MuxerModifier;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.LogWrapper;
import tv.zencoder.flix.util.VideoMuxerConfig;

public class CuepointParamsVideoMuxerModifier implements MuxerModifier {
    LogWrapper log = LogWrapper.getInstance();
    
    /**
     * Pattern to recognize colons and commas when we want to change them to ampersands.
     */
    protected static final Pattern paramSeparatorPattern = Pattern.compile(":|,");

    public CuepointParamsVideoMuxerModifier() {
	super();
    }

    public void modifyMuxer(Muxer muxer, String options) throws FlixException {
	VideoMuxerConfig videoMuxerConfig = BuilderCache.getInstance().getChosenVideoMuxer();
	if (videoMuxerConfig.getFlixCuepointParamParamName() != null) {
	    // The user is allowed to set a list of cue point params.
	    // -cp_params cp0:n00=v00,n01=v01::cp1:n01=v01,n02=v02
	    //   :: separates the cue point names
	    //    : separates the cue point name from its list of name-value pairs
	    //    , separates the name-value pairs
	    String[] cuePointParamBlocks = options.split("::");
	    
	    
	    for (int i=0; i < cuePointParamBlocks.length; i++) {
		// At this point we have something that looks like "cp0:n00=v00,n01=v01".
		// Flix Engine expects us to use an ampersand as the separator, rather 
		// than colons and commas.
		Matcher m = paramSeparatorPattern.matcher(cuePointParamBlocks[i]);
		String flixEngineFriendlyParams = m.replaceAll("&");
		muxer.setParamAsStr(videoMuxerConfig.getFlixCuepointParamParamName(), flixEngineFriendlyParams);
	    }
	} else {
	    log.debug("CuepointParamsVideoMuxerModifier.modifyMuxer(): cue point event is not supported by this muxer.  Ignoring.");
	}
    }

    public String getFriendlyName() {
	return "Cue Point Params Muxer Modifier";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("cp0:n0=v0,[n1=v1...][::cp1:n0=v0,[n1=v1...]]")
	    		    .hasArg()
	    		    .withDescription("Sets param(s) on named cue points. '::' separates the cue point names.  ':' separates the cue point name from its list of name-value pairs.  ',' separates the name-value pairs")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "cp_params";
    }

    public boolean isPrimaryOption() {
	return false;
    }


}
