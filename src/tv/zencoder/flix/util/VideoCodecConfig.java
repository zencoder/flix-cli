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
	flixengine2_internalConstants.FE2_VP6_CXMODE,
	flixengine2_internalConstants.FE2_VP6_PROFILE,
	flixengine2_internalConstants.FE2_VP6_KFINTTYPE,
	flixengine2_internalConstants.FE2_VP6_KFFREQ),
	
    VP6A(flixengine2_internalConstants.FE2_CODEC_VP6ALPHA,
	 flixengine2_internalConstants.FE2_VP6A_ALPHA_BITRATE,
	 flixengine2_internalConstants.FE2_VP6A_CXMODE,
	 null,
	 flixengine2_internalConstants.FE2_VP6A_KFINTTYPE,
	 flixengine2_internalConstants.FE2_VP6A_KFFREQ),
    
    H263(flixengine2_internalConstants.FE2_CODEC_H263,
	 flixengine2_internalConstants.FE2_H263_BITRATE,
	 null,
	 null,
	 flixengine2_internalConstants.FE2_H263_KFINTTYPE,
	 flixengine2_internalConstants.FE2_H263_KFFREQ),
    
    H264(flixengine2_internalConstants.FE2_CODEC_H264,
	 flixengine2_internalConstants.FE2_H264_BITRATE,
	 null,
	 flixengine2_internalConstants.FE2_H264_PROFILE,
	 flixengine2_internalConstants.FE2_H264_KFINTTYPE,
	 flixengine2_internalConstants.FE2_H264_KFFREQ);
    
    
    private final String flixCodecName;
    private final String flixBitrateParamName;
    private final String flixCompressModeParamName;
    private final String flixProfileParamName;
    private final String flixKeyframeTypeParamName;
    private final String flixKeyframeFreqParamName;
    
    
    private VideoCodecConfig(String flixCodecName, 
	    		     String flixBitmapParamName, 
	    		     String flixCompressModeParamName, 
	    		     String flixProfileParamName, 
	    		     String flixKeyframeTypeParamName,
	    		     String flixKeyframeFreqParamName) {
	
	this.flixCodecName = flixCodecName;
	this.flixBitrateParamName = flixBitmapParamName;
	this.flixCompressModeParamName = flixCompressModeParamName;
	this.flixProfileParamName = flixProfileParamName;
	this.flixKeyframeTypeParamName = flixKeyframeTypeParamName;
	this.flixKeyframeFreqParamName = flixKeyframeFreqParamName;
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

    public String getFlixProfileParamName() {
        return flixProfileParamName;
    }

    public String getFlixKeyframeTypeParamName() {
        return flixKeyframeTypeParamName;
    }

    public String getFlixKeyframeFreqParamName() {
        return flixKeyframeFreqParamName;
    }
    
}
