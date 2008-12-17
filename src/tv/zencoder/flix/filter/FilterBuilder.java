package tv.zencoder.flix.filter;

import com.on2.flix.FlixEngine2;

public interface FilterBuilder {
	/* 
	 * Given a FlixEngine2 object and an option String, which would have 
	 * most likely come directly from the command line, add a new filter
	 * to the FlixEngine2.  This returns the FlixEngine2, so that multiple
	 * calls can be chained together.
	 */		
	public FlixEngine2 applyFilter(FlixEngine2 flix, String options);
}
