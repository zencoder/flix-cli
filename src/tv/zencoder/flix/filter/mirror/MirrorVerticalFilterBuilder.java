package tv.zencoder.flix.filter.mirror;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

import com.on2.flix.FlixEngine2;
import com.on2.flix.flixengine2_internalConstants;

/**
 * Flips the video along the vertical axis.
 * @author jdl
 *
 */
public class MirrorVerticalFilterBuilder extends FilterBuilderBase {

    public MirrorVerticalFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	modifyFilter(BuilderCache.getInstance().getMirrorFilterBuilder(flix).getFilter(), options, flixengine2_internalConstants.FE2_MIRROR_VERTICAL, "true");
    }

    public String getFriendlyName() {
	return "Mirror Vertical Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withDescription("Flips the video along the vertical axis.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "mirror_v";
    }

    public boolean isPrimaryOption() {
	return true;
    }
}
