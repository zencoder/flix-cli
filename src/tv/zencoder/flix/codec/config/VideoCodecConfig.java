package tv.zencoder.flix.codec.config;

import com.on2.flix.flixengine2_internalConstants;

/**
 * Based on the particular video codec that was chosen, the various codec params use
 * a different Flix constant.
 * 
 * @author jdl
 *
 */
public enum VideoCodecConfig {
    VP6(flixengine2_internalConstants.FE2_CODEC_VP6, flixengine2_internalConstants.FE2_VP6_BITRATE),
    VP6A(flixengine2_internalConstants.FE2_CODEC_VP6ALPHA, flixengine2_internalConstants.FE2_VP6A_ALPHA_BITRATE),
    H263(flixengine2_internalConstants.FE2_CODEC_H263, flixengine2_internalConstants.FE2_H263_BITRATE),
    H264(flixengine2_internalConstants.FE2_CODEC_H264, flixengine2_internalConstants.FE2_H264_BITRATE);
    
    private final String flixCodecName;
    private final String flixBitmapParamName;
    
    private VideoCodecConfig(String flixCodecName, String flixBitmapParamName) {
	this.flixCodecName = flixCodecName;
	this.flixBitmapParamName = flixBitmapParamName;
    }
    
    public String getFlixBitmapParamName() {
        return flixBitmapParamName;
    }
    public String getFlixCodecName() {
        return flixCodecName;
    }
    
}
