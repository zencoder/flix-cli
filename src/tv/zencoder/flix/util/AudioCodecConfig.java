package tv.zencoder.flix.util;

import com.on2.flix.flixengine2_internalConstants;

/**
 * Flix Engine 2 constants for various audio codec parameters.
 * @author jdl
 *
 */
public enum AudioCodecConfig {
    AAC(flixengine2_internalConstants.FE2_CODEC_AAC,
	flixengine2_internalConstants.FE2_AAC_BITRATE,
	null),
    
    AACPLUS(flixengine2_internalConstants.FE2_CODEC_AACPLUS,
	    flixengine2_internalConstants.FE2_AACPLUS_BITRATE,
	    flixengine2_internalConstants.FE2_AACPLUS_PARAMETRIC_STEREO),
    
    MP3(flixengine2_internalConstants.FE2_CODEC_LAME,
	flixengine2_internalConstants.FE2_LAME_BITRATE,
	null);
    
    private final String flixCodecName;
    private final String flixBitrateParamName;
    private final String flixParametricStereoParamName;
    
    private AudioCodecConfig(String flixCodecName, String flixBitrateParamName, String flixParametricStereoParamName) {
	this.flixCodecName = flixCodecName;
	this.flixBitrateParamName = flixBitrateParamName;
	this.flixParametricStereoParamName = flixParametricStereoParamName;
    }
    
    public String getFlixCodecName() {
        return flixCodecName;
    }

    public String getFlixBitrateParamName() {
        return flixBitrateParamName;
    }

    public String getFlixParametricStereoParamName() {
        return flixParametricStereoParamName;
    }

}
