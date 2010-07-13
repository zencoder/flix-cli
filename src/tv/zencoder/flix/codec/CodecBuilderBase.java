package tv.zencoder.flix.codec;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tv.zencoder.flix.cli.FlixBuilder;
import tv.zencoder.flix.cli.FlixModifier;
import tv.zencoder.flix.util.CommandLineHelper;
import tv.zencoder.flix.util.LogWrapper;

import com.on2.flix.Codec;
import com.on2.flix.FlixException;

public abstract class CodecBuilderBase implements FlixBuilder {
    protected LogWrapper log = LogWrapper.getInstance();
    protected List<CodecModifier> children = new ArrayList<CodecModifier>();
    protected Codec codec;
 
    public List<CodecModifier> children() {
	return children;
    }
    
    public void addChild(FlixModifier child) {
	children.add((CodecModifier) child);
    }

    /**
     * Helper that parent builders can use to trigger their children when its
     * time to use them to modify the existing codec.
     */
    protected void applyChildBuilders(Codec codec) throws FlixException {
	if (children().size() > 0) {
	    CommandLineHelper clHelper = CommandLineHelper.getInstance();
            Iterator<CodecModifier> childIter = children().iterator();
            while (childIter.hasNext()) {
        	CodecModifier child = childIter.next();
        
        	// Was this particular child called for on the command line?
        	if (clHelper.isOptionInUse(child)) {
        	    String optionArgument = clHelper.getLine().getOptionValue(child.getSwitch());        	    
        	    clHelper.logOptionsMessage("CodecBuilderBase.applyChildBuilders(): Modifying codec with " + child.getFriendlyName() + " and option: " + optionArgument);
        	    child.modifyCodec(codec, optionArgument);
        	}
            }
	}
    }

    public Codec getCodec() {
        return codec;
    }

    protected void setCodec(Codec codec) {
        this.codec = codec;
    }
}
