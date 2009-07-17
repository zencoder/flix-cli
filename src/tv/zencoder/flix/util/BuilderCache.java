package tv.zencoder.flix.util;

import tv.zencoder.flix.filter.bchs.BchsFilterBuilder;
import tv.zencoder.flix.filter.blur.BlurFilterBuilder;
import tv.zencoder.flix.filter.crop.CropFilterBuilder;
import tv.zencoder.flix.filter.cut.CutFilterBuilder;
import tv.zencoder.flix.filter.mirror.MirrorFilterBuilder;
import tv.zencoder.flix.filter.overlay.OverlayFilterBuilder;
import tv.zencoder.flix.filter.resample.AudioResampleFilterBuilder;
import tv.zencoder.flix.filter.thumbnail.ThumbnailFilterBuilder;

import com.on2.flix.FlixEngine2;

/**
 * Some builders and options are referenced multiple times from different command line switches, 
 * but we don't want to instantiate them multiple times.  This is a repository for those things which
 * we wish to cache.
 * 
 * @author jdl
 *
 */
public class BuilderCache {

    // Stores the choice of audio codecs.  This is here, because the codec modifiers
    // need to know which codec they're dealing with.
    private AudioCodecConfig chosenAudioCodec;
    
    // Parent for the AudioResample filter builders.
    private AudioResampleFilterBuilder audioResampleFilterBuilder;

    // BlurType and BlurMasksize filters use this as a parent.
    private BlurFilterBuilder blurFilterBuilder;
    
    // The brightness, contrast, hue, and sturation filters all need this parent filter
    // to be built first.  They should fetch it from here when needed, so that we only 
    // have one BCHS filter created.
    private BchsFilterBuilder bchsFilterBuilder;
    
    // Top, Bottom, Left, and Right Crop filters all use this parent filter.
    private CropFilterBuilder cropFilterBuilder;
    
    // Parent for CutStart and CutStop filter builders
    private CutFilterBuilder cutFilterBuilder;
    
    // Parent for MirrorHorizontal and MirrorVertical filter builders.
    private MirrorFilterBuilder mirrorFilterBuilder;
    
    // Stores the overlay filter builder (a.k.a. watermark options)
    private OverlayFilterBuilder overlayFilterBuilder;

    // Parent for the Thumbnail filter builders.
    private ThumbnailFilterBuilder thumbnailFilterBuilder;
    
    // Stores the choice of video codecs.  This is here, because the codec modifiers
    // need to know which codec they're dealing with.
    private VideoCodecConfig chosenVideoCodec;
    
    // Stores the choice of video muxers.  This is here, because the muxer modifiers
    // need to know which muxer they're dealing with.
    private VideoMuxerConfig chosenVideoMuxer;
    
    // Stores an external identifier for the current job.
    private String jobId;
    
    
    private static BuilderCache instance;
    
    public static BuilderCache getInstance() {
	if (instance == null) {
	    synchronized (BuilderCache.class) {
		if (instance == null) {
		    instance = new BuilderCache();
		}
	    }
	}
	return instance;
    }
    
    private BuilderCache() {
	super();
    }

    
    /**
     * Returns the parent BlurFilterBuilder.
     * @return BlurFilterBuilder
     */
    public BlurFilterBuilder getBlurFilterBuilder(FlixEngine2 flix) {
	if (blurFilterBuilder == null) {
	    blurFilterBuilder = new BlurFilterBuilder();
	    blurFilterBuilder.apply(flix, "");
	}
        return blurFilterBuilder;
    }
    
    /**
     * When the brightness, contrast, hue, and saturation filter builders need the BCHS filter
     * builder, they should fetch it from here so that we only have one created.
     * @return BchsFilterBuilder
     */
    public BchsFilterBuilder getBchsFilterBuilder(FlixEngine2 flix) {
	if (bchsFilterBuilder == null) {
	    bchsFilterBuilder = new BchsFilterBuilder();
	    bchsFilterBuilder.apply(flix, "");
	}
        return bchsFilterBuilder;
    }

    /**
     * Returns the parent CropFilterBuilder.
     * @return CropFilterBuilder
     */
    public CropFilterBuilder getCropFilterBuilder(FlixEngine2 flix) {
	if (cropFilterBuilder == null) {
	    cropFilterBuilder = new CropFilterBuilder();
	    cropFilterBuilder.apply(flix, "");
	}
        return cropFilterBuilder;
    }
    
    /**
     * Returns the parent CutFilterBuilder.
     * @return CutFilterBuilder
     */
    public CutFilterBuilder getCutFilterBuilder(FlixEngine2 flix) {
	if (cutFilterBuilder == null) {
	    cutFilterBuilder = new CutFilterBuilder();
	    cutFilterBuilder.apply(flix, "");
	}
        return cutFilterBuilder;
    }

    
    /**
     * Returns the parent MirrorFilterBuilder.
     * @return MirrorFilterBuilder
     */
    public MirrorFilterBuilder getMirrorFilterBuilder(FlixEngine2 flix) {
        if (mirrorFilterBuilder == null) {
            mirrorFilterBuilder = new MirrorFilterBuilder();
            mirrorFilterBuilder.apply(flix, "");
        }
	return mirrorFilterBuilder;
    }

    
    /**
     * Returns the parent AudioResampleFilterBuilder.
     * @return AudioResampleFilterBuilder
     */
    public AudioResampleFilterBuilder getAudioResampleFilterBuilder(FlixEngine2 flix) {
	if (audioResampleFilterBuilder == null) {
	    audioResampleFilterBuilder = new AudioResampleFilterBuilder();
	    audioResampleFilterBuilder.apply(flix, "");
	}
        return audioResampleFilterBuilder;
    }

 
    /**
     * Returns the parent OverlayFilterBuild.
     * @return OverlayFilterBuilder 
     */
    public OverlayFilterBuilder getOverlayFilterBuilder(FlixEngine2 flix) {
	if (overlayFilterBuilder == null) {
	    overlayFilterBuilder = new OverlayFilterBuilder();
	    overlayFilterBuilder.apply(flix, "");
	}
	return overlayFilterBuilder;
    }
    
    
    /**
     * Parent thumbnail filter builder.
     * @return ThumbnailFilterBuilder
     */
    public ThumbnailFilterBuilder getThumbnailFilterBuilder(FlixEngine2 flix) {
        if (thumbnailFilterBuilder == null) {
            thumbnailFilterBuilder = new ThumbnailFilterBuilder();
            thumbnailFilterBuilder.apply(flix, "");
        }
	return thumbnailFilterBuilder;
    }

    
    
    /**
     * Returns the video codec that we're working on.  Video codec modifiers
     * need to know which specific codec they're trying to configure.
     * 
     * @return VideoCodecConfig
     */
    public VideoCodecConfig getChosenVideoCodec() {
        return chosenVideoCodec;
    }

    /**
     * When a VideoCodecBuilder decides on a particular codec, it should set that
     * value here, so that codec modifiers will behave properly.
     * 
     * @param chosenVideoCodec
     */
    public void setChosenVideoCodec(VideoCodecConfig chosenVideoCodec) {
        this.chosenVideoCodec = chosenVideoCodec;
    }
    
    /**
     * Returns the audio codec that we're working with.
     * 
     * @return AudioCodecConfig
     */
    public AudioCodecConfig getChosenAudioCodec() {
        return chosenAudioCodec;
    }

    /**
     * When an AudioCodecBuilder decides on a particular codec, it should set that value here
     * so that the modifiers can behave properly.
     * 
     * @param chosenAudioCodec
     */
    public void setChosenAudioCodec(AudioCodecConfig chosenAudioCodec) {
        this.chosenAudioCodec = chosenAudioCodec;
    }

    /**
     * Returns the video muxer that we're working on.  Video muxer modifiers
     * need to know which specific muxer they're trying to configure.
     * 
     * @return VideoMuxerConfig
     */
    public VideoMuxerConfig getChosenVideoMuxer() {
        return chosenVideoMuxer;
    }

    /**
     * When a VideoMuxerBuilder decides on a particular muxer, it should set that
     * value here, so that muxer modifiers will behave properly.
     * 
     * @param chosenVideoMuxer
     */
    public void setChosenVideoMuxer(VideoMuxerConfig chosenVideoMuxer) {
        this.chosenVideoMuxer = chosenVideoMuxer;
    }

    /**
     * Returns the external ID of the job that is being worked on.
     */
    public String getJobId() {
	return jobId;
    }
    
    /**
     * Sets the external ID of the job that is being worked on.
     */
    public void setJobId(String jobId) {
	this.jobId = jobId;
    }

}
