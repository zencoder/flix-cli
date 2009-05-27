package tv.zencoder.flix.filter.mirror;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

import com.on2.flix.FlixEngine2;
import com.on2.flix.flixengine2_internalConstants;

/**
 * Flips the video along the horizontal axis.
 * @author jdl
 *
 */
public class MirrorHorizontalFilterBuilder extends FilterBuilderBase {

    public MirrorHorizontalFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	modifyFilter(BuilderCache.getInstance().getMirrorFilterBuilder(flix).getFilter(), options, flixengine2_internalConstants.FE2_MIRROR_HORIZONTAL, "true");
    }

    public String getFriendlyName() {
	return "Mirror Horizontal Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withDescription("Flips the video along the horizontal axis.")
	    		    .create(getSwitch());
    }

    public String getSwitch() {
	return "mirror_h";
    }

    public boolean isPrimaryOption() {
	return true;
    }

}
