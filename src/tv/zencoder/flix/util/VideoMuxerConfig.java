package tv.zencoder.flix.util;

import com.on2.flix.flixengine2_internalConstants;

/**
 * Muxers which are available, and their various configuration constants.
 * @author jdl
 *
 */
public enum VideoMuxerConfig {
    FLV(flixengine2_internalConstants.FE2_MUXER_FLV),
    MOV(flixengine2_internalConstants.FE2_MUXER_MOV),
    MP4(flixengine2_internalConstants.FE2_MUXER_MP4);
    
    private final String flixMuxerName;
    
    private VideoMuxerConfig(String flixMuxerName) {
	this.flixMuxerName = flixMuxerName;
    }

    public String getFlixMuxerName() {
        return flixMuxerName;
    }
}
