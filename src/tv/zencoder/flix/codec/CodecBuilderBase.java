package tv.zencoder.flix.codec;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tv.zencoder.flix.util.CommandLineHelper;
import tv.zencoder.flix.util.LogWrapper;

import com.on2.flix.Codec;
import com.on2.flix.FlixException;

public abstract class CodecBuilderBase implements CodecBuilder {
    protected LogWrapper log = LogWrapper.getInstance();
    protected List<CodecBuilder> children = new ArrayList<CodecBuilder>();
 

    public void modifyCodec(Codec codec, String options) throws FlixException {}
    
    public List<CodecBuilder> children() {
	return children;
    }
    
    public void addChild(CodecBuilder child) {
	children.add(child);
    }

    /**
     * Helper that parent builders can use to trigger their children when its
     * time to use them to modify the existing codec.
     */
    protected void applyChildBuilders(Codec codec) throws FlixException {
	if (children().size() > 0) {
	    CommandLineHelper clHelper = CommandLineHelper.getInstance();
            Iterator<CodecBuilder> childIter = children().iterator();
            while (childIter.hasNext()) {
        	CodecBuilder child = childIter.next();
        
        	// Was this particular child called for on the command line?
        	if (clHelper.isOptionInUse(child)) {
        	    String optionArgument = clHelper.getLine().getOptionValue(child.getSwitch());
        	    log.debug("CodecBuilderBase.applyChildBuilders(): Modifying codec with " + child.getFriendlyName() + " and option: " + optionArgument);
        	    child.modifyCodec(codec, optionArgument);
        	}
            }
	}
    }
}
