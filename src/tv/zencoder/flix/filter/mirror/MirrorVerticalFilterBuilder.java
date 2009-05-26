package tv.zencoder.flix.filter.mirror;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix._on2bool;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

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
	Filter f = BuilderCache.getInstance().getMirrorFilterBuilder(flix).getFilter();
	try {
	    f.setParam(flixengine2_internalConstants.FE2_MIRROR_VERTICAL, new Double(_on2bool.on2true.swigValue()));
	} catch (FlixException e) {
	    log.error("MirrorVerticalFilterBuilder.apply(): e=" + e.getLocalizedMessage());
	}
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
