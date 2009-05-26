package tv.zencoder.flix.filter.mirror;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix._on2bool;
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
	Filter f = BuilderCache.getInstance().getMirrorFilterBuilder(flix).getFilter();
	try {
	    f.setParam(flixengine2_internalConstants.FE2_MIRROR_HORIZONTAL, new Double(_on2bool.on2true.swigValue()));
	} catch (FlixException e) {
	    log.error("MirrorHorizontalFilterBuilder.apply(): e=" + e.getLocalizedMessage());
	}
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
