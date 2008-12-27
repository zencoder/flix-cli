package tv.zencoder.flix.filter.crop;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.FlixEngine2;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

public class CropRightFilterBuilder extends FilterBuilderBase {

    public CropRightFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	modifyFilter(BuilderCache.getInstance().getCropFilterBuilder(flix).getFilter(), options, flixengine2_internalConstants.FE2_CROP_RIGHT);
    }

    public String getFriendlyName() {
	return "Crop Right Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("size")
                            .hasArg()
                            .withDescription("Isolates the right of a frame to crop within the input video.")
                            .create(getSwitch());
    }

    public String getSwitch() {
	return "cropright";
    }

    public boolean isPrimaryOption() {
	return true;
    }

}
