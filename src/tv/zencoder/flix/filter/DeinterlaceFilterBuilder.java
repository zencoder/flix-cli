package tv.zencoder.flix.filter;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.deintmode_t;
import com.on2.flix.flixengine2_internalConstants;

/**
 * Deinterlaces the video pictures.
 * <p>
 * The options set which type of deinterlacing to perform.
 * a - adaptive
 * b - 1:2:1 blur
 * d - drop field
 * 
 * @author jdl
 *
 */
public class DeinterlaceFilterBuilder extends FilterBuilderBase {

    public DeinterlaceFilterBuilder() {
	super();
    }

    /* (non-Javadoc)
     * @see tv.zencoder.flix.filter.FilterBuilder#applyFilter(com.on2.flix.FlixEngine2, java.lang.String)
     */
    public Filter apply(FlixEngine2 flix, String options) {
	Filter filter = null;
	try {
	    deintmode_t deinterlaceType = null;
	    if (options.equals("a")) {
		deinterlaceType = deintmode_t.DEINTERLACE_ADAPTIVE;
	    } else if (options.equals("b")) {
		deinterlaceType = deintmode_t.DEINTERLACE_1_2_1_BLUR;
	    } else if (options.equals("d")) {
		deinterlaceType = deintmode_t.DEINTERLACE_DROP_FIELD;
	    }

	    if (deinterlaceType != null) {
		log.debug("DeinterlaceFilterBuilder.applyFilter(): Adding deinterlace filter of type: " + options);
		filter = new Filter(flix, flixengine2_internalConstants.FE2_FILTER_ADAPTIVE_DEINTERLACE);
		filter.add();
		filter.setParam(flixengine2_internalConstants.FE2_ADAPTIVE_DEINTERLACE_MODE, deinterlaceType.swigValue());
	    } else {
		log.debug("DeinterlaceFilterBuilder.applyFilter(): Deinterlace type was not valid. type: " + options);
	    }
	} catch (FlixException e) {
	    //
	}
	return filter;
    }

    public String getFriendlyName() {
	return "Deinterlace Filter Builder (-" + getSwitch() + ")";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	return OptionBuilder.withArgName("a|b|d")
                            .hasArg()
                            .withDescription("deinterlace mode (a)adaptive, (b)1:2:1 blur, (d)drop field")
                            .create(getSwitch());
    }

    public String getSwitch() {
	return "deinterlace";
    }

    public boolean isPrimaryOption() {
	return true;
    }
}
