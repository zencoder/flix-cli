package tv.zencoder.flix.filter;

import org.junit.Before;

import com.on2.flix.FlixEngine2;

/**
 * Common setUp() and tearDown() code for the filter tests.
 * @author jdl
 *
 */
public class FilterBuilderTestHelper {

    protected FlixEngine2 flix;
    protected FilterBuilder filterBuilder;

    @Before
    public void setUp(FilterBuilder filterBuilder) throws Exception {
	setFilterBuilder(filterBuilder);
	flix = new FlixEngine2("localhost", 0);
	flix.Connect();
    }

    public void tearDown() throws Exception {
	flix.Destroy();
	flix = null;
	filterBuilder = null;
    }

    public FilterBuilder getFilterBuilder() {
	return filterBuilder;
    }

    public void setFilterBuilder(FilterBuilder filterBuilder) {
	this.filterBuilder = filterBuilder;
    }

    public FlixEngine2 getFlix() {
	return flix;
    }

    public void setFlix(FlixEngine2 flix) {
	this.flix = flix;
    }
}
