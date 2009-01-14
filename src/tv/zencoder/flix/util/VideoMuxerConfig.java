package tv.zencoder.flix.util;

import com.on2.flix.flixengine2_internalConstants;

/**
 * Muxers which are available, and their various configuration constants.
 * @author jdl
 *
 */
public enum VideoMuxerConfig {
    FLV(flixengine2_internalConstants.FE2_MUXER_FLV,
	null),
    
    MOV(flixengine2_internalConstants.FE2_MUXER_MOV,
	flixengine2_internalConstants.FE2_MOV_FASTSTART),
    
    MP4(flixengine2_internalConstants.FE2_MUXER_MP4,
	flixengine2_internalConstants.FE2_MP4_FASTSTART),
	
    _3GP(flixengine2_internalConstants.FE2_MUXER_3GP,
	flixengine2_internalConstants.FE2_3GP_FASTSTART),

    _3G2(flixengine2_internalConstants.FE2_MUXER_3G2,
        flixengine2_internalConstants.FE2_3G2_FASTSTART);
    
    
    private final String flixMuxerName;
    private final String flixFaststartParamName;
    
    private VideoMuxerConfig(String flixMuxerName, String flixFaststartParamName) {
	this.flixMuxerName = flixMuxerName;
	this.flixFaststartParamName = flixFaststartParamName;
    }

    public String getFlixMuxerName() {
        return flixMuxerName;
    }

    public String getFlixFaststartParamName() {
        return flixFaststartParamName;
    }
}
