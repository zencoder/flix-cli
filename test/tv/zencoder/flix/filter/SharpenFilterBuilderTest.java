package tv.zencoder.flix.filter;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tv.zencoder.flix.BuilderTestHelper;

public class SharpenFilterBuilderTest {
    private BuilderTestHelper btHelper;

    @Before
    public void setUp() throws Exception {
	btHelper = new BuilderTestHelper();
	btHelper.setUp(new SharpenFilterBuilder());
    }

    @After
    public void tearDown() throws Exception {
	btHelper.tearDown();
	btHelper = null;
    }

    @Test
    public void testApply() {
	btHelper.apply("");
    }

}
