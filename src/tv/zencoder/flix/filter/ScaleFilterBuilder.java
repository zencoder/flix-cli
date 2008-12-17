package tv.zencoder.flix.filter;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

public class ScaleFilterBuilder implements FilterBuilder {
	
	public ScaleFilterBuilder() {
		super();
	}

	public FlixEngine2 applyFilter(FlixEngine2 flix, String options) {
		try {
			Filter filter = new Filter(flix, flixengine2_internalConstants.FE2_FILTER_SCALE);
		
			filter.add();
			filter.setParam(flixengine2_internalConstants.FE2_SCALE_WIDTH, getWidth(options));
			filter.setParam(flixengine2_internalConstants.FE2_SCALE_HEIGHT, getHeight(options));
		} catch (FlixException e) {
	        //
	    }
		return flix;
	}

	protected double getWidth(String options) {
		return 240.0;
	}

	protected double getHeight(String options) {
		return 160.0;
	}

}
