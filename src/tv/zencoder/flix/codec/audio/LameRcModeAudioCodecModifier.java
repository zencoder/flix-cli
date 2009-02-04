package tv.zencoder.flix.codec.audio;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.codec.CodecModifier;
import tv.zencoder.flix.util.AudioCodecConfig;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.StringUtil;

import com.on2.flix.Codec;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;
import com.on2.flix.lame_rcmode_t;

/**
 * Sets the RC mode for the LAME audio codec which support it.
 * @author jdl
 *
 */
public class LameRcModeAudioCodecModifier implements CodecModifier {

    /**
     * RC Mode types.  This maps the command line option to a Flix param.
     */
    protected static Map<String, lame_rcmode_t> rcModeTypes;
    static {
        Map<String, lame_rcmode_t> map = new TreeMap<String, lame_rcmode_t>();
        map.put("abr",      lame_rcmode_t.LAME_ABR);
        map.put("cbr",      lame_rcmode_t.LAME_CBR);
        map.put("vbr_mtrh", lame_rcmode_t.LAME_VBR_mtrh);
        map.put("vbr_rh",   lame_rcmode_t.LAME_VBR_rh);
        rcModeTypes = Collections.unmodifiableMap(map);
    }
    
    
    public LameRcModeAudioCodecModifier() {
	super();
    }

    public void modifyCodec(Codec codec, String options) throws FlixException {
	if(BuilderCache.getInstance().getChosenAudioCodec().equals(AudioCodecConfig.MP3)) {
	    if(rcModeTypes.containsKey(options)) {
		codec.setParam(flixengine2_internalConstants.FE2_LAME_RC_MODE, rcModeTypes.get(options).swigValue());
	    }
	}
    }

    public String getFriendlyName() {
	return "LAME RC Mode audio codec modifer";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("rc_mode")
        		    .hasArg()
                            .withDescription("Sets the LAME RC Mode. Valid values include: " + StringUtil.mapKeysToString(rcModeTypes, ", "))
                            .create(getSwitch());
    }

    public String getSwitch() {
	return "lame_rc_mode";
    }

    public boolean isPrimaryOption() {
	return false;
    }

}
