package tv.zencoder.flix;

import tv.zencoder.flix.cli.FlixBuilder;

import com.on2.flix.FlixEngine2;

/**
 * Common setUp() and tearDown() code for the builder tests.
 * @author jdl
 *
 */
public class BuilderTestHelper {

    protected FlixEngine2 flix;
    protected FlixBuilder flixBuilder;

    public void setUp() throws Exception {
	flix = new FlixEngine2("localhost", 0);
	flix.Connect();	
    }
    
    public void setUp(FlixBuilder filterBuilder) throws Exception {
	setFlixBuilder(filterBuilder);
	setUp();
    }

    public void tearDown() throws Exception {
	flix.Destroy();
	flix = null;
	flixBuilder = null;
    }

    /**
     * Runs the <code>apply()</code> method of the builder.
     */
    public void apply(String options) {
	getFlixBuilder().apply(getFlix(), options);
    }
    
    public FlixBuilder getFlixBuilder() {
	return flixBuilder;
    }

    public void setFlixBuilder(FlixBuilder filterBuilder) {
	this.flixBuilder = filterBuilder;
    }

    public FlixEngine2 getFlix() {
	return flix;
    }

    public void setFlix(FlixEngine2 flix) {
	this.flix = flix;
    }
}
