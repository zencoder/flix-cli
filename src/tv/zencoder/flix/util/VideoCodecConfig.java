package tv.zencoder.flix.util;

import com.on2.flix.flixengine2_internalConstants;

/**
 * Based on the particular video codec that was chosen, the various codec params use
 * a different Flix constant.
 * 
 * @author jdl
 *
 */
public enum VideoCodecConfig {
    VP6(flixengine2_internalConstants.FE2_CODEC_VP6,
	flixengine2_internalConstants.FE2_VP6_BITRATE,
	flixengine2_internalConstants.FE2_VP6_CXMODE),
	
    VP6A(flixengine2_internalConstants.FE2_CODEC_VP6ALPHA,
	 flixengine2_internalConstants.FE2_VP6A_ALPHA_BITRATE,
	 flixengine2_internalConstants.FE2_VP6A_CXMODE),
    
    H263(flixengine2_internalConstants.FE2_CODEC_H263,
	 flixengine2_internalConstants.FE2_H263_BITRATE,
	 null),
    
    H264(flixengine2_internalConstants.FE2_CODEC_H264,
	 flixengine2_internalConstants.FE2_H264_BITRATE,
	 null);
    
    
    private final String flixCodecName;
    private final String flixBitrateParamName;
    private final String flixCompressModeParamName;
    
    private VideoCodecConfig(String flixCodecName, String flixBitmapParamName, String flixCompressModeParamName) {
	this.flixCodecName = flixCodecName;
	this.flixBitrateParamName = flixBitmapParamName;
	this.flixCompressModeParamName = flixCompressModeParamName;
    }
    
    public String getFlixBitrateParamName() {
        return flixBitrateParamName;
    }
    public String getFlixCodecName() {
        return flixCodecName;
    }
    public String getFlixCompressModeParamName() {
	return flixCompressModeParamName;
    }
    
}
