package tv.zencoder.flix.util;

import com.on2.flix.flixengine2_internalConstants;

public enum AudioCodecConfig {
    AAC(flixengine2_internalConstants.FE2_CODEC_AAC),
    MP3(flixengine2_internalConstants.FE2_CODEC_LAME);
    
    private String flixCodecName;

    private AudioCodecConfig(String flixCodecName) {
	this.flixCodecName = flixCodecName;
    }
    
    public String getFlixCodecName() {
        return flixCodecName;
    }
}
