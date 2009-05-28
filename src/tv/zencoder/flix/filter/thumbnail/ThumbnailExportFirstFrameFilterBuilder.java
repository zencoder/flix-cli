package tv.zencoder.flix.filter.thumbnail;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.FlixEngine2;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

public class ThumbnailExportFirstFrameFilterBuilder extends FilterBuilderBase {

    public ThumbnailExportFirstFrameFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	modifyFilter(BuilderCache.getInstance().getThumbnailFilterBuilder(flix).getFilter(), options, flixengine2_internalConstants.FE2_PNGEX_EXPORT_FIRST_FRAME_PNG, "true");
    }

    public String getFriendlyName() {
	return "Thumbnail First Frame Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withDescription("Forces thumbnail creation from first frame.")
	    		    .create(getSwitch());
    }
    

    public String getSwitch() {
	return "pngex_export_first_frame";
    }

    public boolean isPrimaryOption() {
	return true;
    }

}
