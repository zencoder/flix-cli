package tv.zencoder.flix.filter;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

/**
 * Creates a framerate filter.  
 * 
 * This can either be a straight FPS or a (TODO: integer followed by "d"
 * meaning "decimate."  This is the amount that the original framerate will
 * be divided by.)
 * 
 * Example 1: "15.0"
 * This means "15.0 FPS"
 * 
 * Example 2: "2d"
 * This means "1/2 the framerate of the original"
 * 
 * @author jdl
 *
 */
public class FramerateFilterBuilder implements FilterBuilder {

	public FramerateFilterBuilder() {
		super();
	}

	/* (non-Javadoc)
	 * @see tv.zencoder.flix.filter.FilterBuilder#applyFilter(com.on2.flix.FlixEngine2, java.lang.String)
	 */
	public FlixEngine2 applyFilter(FlixEngine2 flix, String options) {
		try {
	        Filter filter = new Filter(flix, flixengine2_internalConstants.FE2_FILTER_FRAMERATE);
	        filter.add();
	        filter.setParam(flixengine2_internalConstants.FE2_FRAMERATE_FPS, Double.parseDouble(options));
	    } catch (FlixException e) {
	        //
	    }
	    
		return flix;
	}

}
