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
	flixengine2_internalConstants.FE2_VP6_KFFREQ,
	flixengine2_internalConstants.FE2_VP6_RC_MODE,
	flixengine2_internalConstants.FE2_VP6_MIN_Q,
	flixengine2_internalConstants.FE2_VP6_MAX_Q,
	flixengine2_internalConstants.FE2_VP6_NOISE_REDUCTION,
	flixengine2_internalConstants.FE2_VP6_TEMPORAL_RESAMPLING,
	flixengine2_internalConstants.FE2_VP6_STREAM_PREBUFFER,
	flixengine2_internalConstants.FE2_VP6_STREAM_OPTIMAL_BUFFER),
	
    VP6A(flixengine2_internalConstants.FE2_CODEC_VP6ALPHA,
	 flixengine2_internalConstants.FE2_VP6A_ALPHA_BITRATE,
	 flixengine2_internalConstants.FE2_VP6A_CXMODE,
	 null,
	 flixengine2_internalConstants.FE2_VP6A_KFINTTYPE,
	 flixengine2_internalConstants.FE2_VP6A_KFFREQ,
	 flixengine2_internalConstants.FE2_VP6A_RC_MODE,
	 flixengine2_internalConstants.FE2_VP6A_MIN_Q,
         flixengine2_internalConstants.FE2_VP6A_MAX_Q,
         flixengine2_internalConstants.FE2_VP6A_NOISE_REDUCTION,
         flixengine2_internalConstants.FE2_VP6A_TEMPORAL_RESAMPLING,
         flixengine2_internalConstants.FE2_VP6A_STREAM_PREBUFFER,
         flixengine2_internalConstants.FE2_VP6A_STREAM_OPTIMAL_BUFFER),
         
    
    H263(flixengine2_internalConstants.FE2_CODEC_H263,
	 flixengine2_internalConstants.FE2_H263_BITRATE,
	 null,
	 null,
	 flixengine2_internalConstants.FE2_H263_KFINTTYPE,
	 flixengine2_internalConstants.FE2_H263_KFFREQ,
	 flixengine2_internalConstants.FE2_H263_RC_MODE,
	 flixengine2_internalConstants.FE2_H263_MIN_Q,
	 flixengine2_internalConstants.FE2_H263_MAX_Q,
	 null,
	 null,
	 null,
	 null),
    
    // Note that H263_BASELINE uses the same codec param names as H263.
    // On2 support confirms that this is correct.
    H263_BASELINE(flixengine2_internalConstants.FE2_CODEC_H263_BASELINE,
	          flixengine2_internalConstants.FE2_H263_BITRATE,
		  null,
		  null,
		  flixengine2_internalConstants.FE2_H263_KFINTTYPE,
		  flixengine2_internalConstants.FE2_H263_KFFREQ,
		  flixengine2_internalConstants.FE2_H263_RC_MODE,
		  flixengine2_internalConstants.FE2_H263_MIN_Q,
		  flixengine2_internalConstants.FE2_H263_MAX_Q,
	          null,
	          null,
	          null,
	          null),
    
    H264(flixengine2_internalConstants.FE2_CODEC_H264,
	 flixengine2_internalConstants.FE2_H264_BITRATE,
	 null,
	 flixengine2_internalConstants.FE2_H264_PROFILE,
	 flixengine2_internalConstants.FE2_H264_KFINTTYPE,
	 flixengine2_internalConstants.FE2_H264_KFFREQ,
	 flixengine2_internalConstants.FE2_H264_RC_MODE,
	 null,
	 null,
	 null,
	 null,
	 null,
	 null); 
    
    
    private final String flixCodecName;
    private final String flixBitrateParamName;
    private final String flixCompressModeParamName;
    private final String flixProfileParamName;
    private final String flixKeyframeTypeParamName;
    private final String flixKeyframeFreqParamName;
    private final String flixRateControlParamName;
    private final String flixMinQParamName;
    private final String flixMaxQParamName;
    private final String flixNoiseReductionParamName;
    private final String flixTemporalResamplingParamName;
    private final String flixStreamPrebufferParamName;
    private final String flixStreamOptimalBufferParamName;
    
    private VideoCodecConfig(String flixCodecName, 
	    		     String flixBitmapParamName, 
	    		     String flixCompressModeParamName, 
	    		     String flixProfileParamName, 
	    		     String flixKeyframeTypeParamName,
	    		     String flixKeyframeFreqParamName,
	    		     String flixRateControlParamName,
	    		     String flixMinQParamName,
                             String flixMaxQParamName,
                             String flixNoiseReductionParamName,
                             String flixTemporalResamplingParamName,
                             String flixStreamPrebufferParamName,
                             String flixStreamOptimalBufferParamName) {
	
	this.flixCodecName = flixCodecName;
	this.flixBitrateParamName = flixBitmapParamName;
	this.flixCompressModeParamName = flixCompressModeParamName;
	this.flixProfileParamName = flixProfileParamName;
	this.flixKeyframeTypeParamName = flixKeyframeTypeParamName;
	this.flixKeyframeFreqParamName = flixKeyframeFreqParamName;
	this.flixRateControlParamName = flixRateControlParamName;
	this.flixMinQParamName = flixMinQParamName;
        this.flixMaxQParamName = flixMaxQParamName;
        this.flixNoiseReductionParamName = flixNoiseReductionParamName;
        this.flixTemporalResamplingParamName = flixTemporalResamplingParamName;
        this.flixStreamPrebufferParamName = flixStreamPrebufferParamName;
        this.flixStreamOptimalBufferParamName = flixStreamOptimalBufferParamName;
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

    public String getFlixRateControlParamName() {
        return flixRateControlParamName;
    }

    public String getFlixMaxQParamName() {
        return flixMaxQParamName;
    }

    public String getFlixMinQParamName() {
        return flixMinQParamName;
    }

    public String getFlixNoiseReductionParamName() {
        return flixNoiseReductionParamName;
    }

    public String getFlixStreamOptimalBufferParamName() {
        return flixStreamOptimalBufferParamName;
    }

    public String getFlixStreamPrebufferParamName() {
        return flixStreamPrebufferParamName;
    }

    public String getFlixTemporalResamplingParamName() {
        return flixTemporalResamplingParamName;
    }
    
}
