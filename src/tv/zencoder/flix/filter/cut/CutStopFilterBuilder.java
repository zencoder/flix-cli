package tv.zencoder.flix.filter.cut;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.FlixEngine2;
import com.on2.flix.flixengine2_internalConstants;

import tv.zencoder.flix.filter.FilterBuilderBase;
import tv.zencoder.flix.util.BuilderCache;

public class CutStopFilterBuilder extends FilterBuilderBase {

    public CutStopFilterBuilder() {
	super();
    }

    public void apply(FlixEngine2 flix, String options) {
	modifyFilter(BuilderCache.getInstance().getCutFilterBuilder(flix).getFilter(), options, flixengine2_internalConstants.FE2_CUT_STOP_SEC, "double");
    }

    public String getFriendlyName() {
	return "Cut Start Filter Builder";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("second")
                            .hasArg()
                            .withDescription("Sets the stop second for a cut.")
                            .create(getSwitch());
    }

    public String getSwitch() {
	return "cutstop";
    }

    public boolean isPrimaryOption() {
	return true;
    }
}
