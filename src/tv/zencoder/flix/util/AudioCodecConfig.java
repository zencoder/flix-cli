package tv.zencoder.flix.util;

import com.on2.flix.flixengine2_internalConstants;

public enum AudioCodecConfig {
    AAC(flixengine2_internalConstants.FE2_CODEC_AAC, flixengine2_internalConstants.FE2_AAC_BITRATE),
    AACPLUS(flixengine2_internalConstants.FE2_CODEC_AACPLUS, flixengine2_internalConstants.FE2_AACPLUS_BITRATE),
    MP3(flixengine2_internalConstants.FE2_CODEC_LAME, flixengine2_internalConstants.FE2_LAME_BITRATE);
    
    private final String flixCodecName;
    private final String flixBitrateParamName;

    private AudioCodecConfig(String flixCodecName, String flixBitrateParamName) {
	this.flixCodecName = flixCodecName;
	this.flixBitrateParamName = flixBitrateParamName;
    }
    
    public String getFlixCodecName() {
        return flixCodecName;
    }

    public String getFlixBitrateParamName() {
        return flixBitrateParamName;
    }
}
