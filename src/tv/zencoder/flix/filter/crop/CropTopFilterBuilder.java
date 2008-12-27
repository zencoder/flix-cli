package tv.zencoder.flix.filter.crop;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.FlixEngine2;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

/**
 * Defines the top of a crop frame on the input video.
 * 
 * @author jdl
 *
 */
public class CropTopFilterBuilder extends FilterBuilderBase {

    public CropTopFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	modifyFilter(BuilderCache.getInstance().getCropFilterBuilder(flix).getFilter(), options, flixengine2_internalConstants.FE2_CROP_TOP);
    }

    public String getFriendlyName() {
	return "Crop Top Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("size")
                            .hasArg()
                            .withDescription("Isolates the top of a frame to crop within the input video.")
                            .create(getSwitch());
    }

    public String getSwitch() {
	return "croptop";
    }

    public boolean isPrimaryOption() {
	return true;
    }

}
