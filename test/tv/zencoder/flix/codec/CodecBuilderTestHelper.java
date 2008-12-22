package tv.zencoder.flix.codec;

import org.junit.After;
import org.junit.Before;

import com.on2.flix.Codec;
import com.on2.flix.FlixEngine2;

public class CodecBuilderTestHelper {

    protected FlixEngine2 flix;
    protected CodecBuilder codecBuilder;

    @Before
    public void setUp(CodecBuilder codecBuilder) throws Exception {
	setCodecBuilder(codecBuilder);
	flix = new FlixEngine2("localhost", 0);
	flix.Connect();
    }

    @After
    public void tearDown() throws Exception {
	flix.Destroy();
	flix = null;
	codecBuilder = null;
    }

    /**
     * Runs the <code>apply()</code> method of the builder.
     * @return	Codec
     */
    public Codec apply(String options) {
	return getCodecBuilder().apply(getFlix(), options);
    }
    
    public CodecBuilder getCodecBuilder() {
        return codecBuilder;
    }

    public void setCodecBuilder(CodecBuilder codecBuilder) {
        this.codecBuilder = codecBuilder;
    }

    public FlixEngine2 getFlix() {
        return flix;
    }

    public void setFlix(FlixEngine2 flix) {
        this.flix = flix;
    }

}
