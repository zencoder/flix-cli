package tv.zencoder.flix.util;

import com.on2.flix.flixengine2_internalConstants;

/**
 * Muxers which are available, and their various configuration constants.
 * @author jdl
 *
 */
public enum VideoMuxerConfig {
    FLV(flixengine2_internalConstants.FE2_MUXER_FLV,
	null,
	flixengine2_internalConstants.FE2_FLV_CUEPT_NAV,
	flixengine2_internalConstants.FE2_FLV_CUEPT_EVENT,
	flixengine2_internalConstants.FE2_FLV_CUEPT_PARAM),
    
    MOV(flixengine2_internalConstants.FE2_MUXER_MOV,
	flixengine2_internalConstants.FE2_MOV_FASTSTART,
	null,
	null,
	null),
    
    MP4(flixengine2_internalConstants.FE2_MUXER_MP4,
	flixengine2_internalConstants.FE2_MP4_FASTSTART,
	null,
	null,
	null),
	
    _3GP(flixengine2_internalConstants.FE2_MUXER_3GP,
	flixengine2_internalConstants.FE2_3GP_FASTSTART,
	null,
	null,
	null),

    _3G2(flixengine2_internalConstants.FE2_MUXER_3G2,
        flixengine2_internalConstants.FE2_3G2_FASTSTART,
	null,
	null,
	null),
    
    FXM(flixengine2_internalConstants.FE2_MUXER_FXM,
	null,
	null,
	null,
	null);
    
    private final String flixMuxerName;
    private final String flixFaststartParamName;
    private final String flixCuepointNavParamName;
    private final String flixCuepointEventParamName;
    private final String flixCuepointParamParamName;
    
    
    private VideoMuxerConfig(String flixMuxerName, String flixFaststartParamName, String flixCuepointNavParamName, String flixCuepointEventParamName, String flixCuepointParamParamName) {
	this.flixMuxerName = flixMuxerName;
	this.flixFaststartParamName = flixFaststartParamName;
	this.flixCuepointNavParamName = flixCuepointNavParamName;
	this.flixCuepointEventParamName = flixCuepointEventParamName;
	this.flixCuepointParamParamName = flixCuepointParamParamName;
    }

    public String getFlixMuxerName() {
        return flixMuxerName;
    }

    public String getFlixFaststartParamName() {
        return flixFaststartParamName;
    }

    public String getFlixCuepointEventParamName() {
        return flixCuepointEventParamName;
    }

    public String getFlixCuepointNavParamName() {
        return flixCuepointNavParamName;
    }

    public String getFlixCuepointParamParamName() {
        return flixCuepointParamParamName;
    }
}
