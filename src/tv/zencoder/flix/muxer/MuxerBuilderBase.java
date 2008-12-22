package tv.zencoder.flix.muxer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tv.zencoder.flix.cli.CommandLineHelper;
import tv.zencoder.flix.util.LogWrapper;

import com.on2.flix.FlixException;
import com.on2.flix.Muxer;

public abstract class MuxerBuilderBase implements MuxerBuilder {
    protected LogWrapper log = LogWrapper.getInstance();
    protected List<MuxerModifier> children = new ArrayList<MuxerModifier>();
 
    public List<MuxerModifier> children() {
	return children;
    }
    
    public void addChild(MuxerModifier child) {
	children.add(child);
    }

    /**
     * Helper that parent builders can use to trigger their children when its
     * time to use them to modify the existing Muxer.
     */
    protected void applyChildBuilders(Muxer muxer) throws FlixException {
	if (children().size() > 0) {
	    CommandLineHelper clHelper = CommandLineHelper.getInstance();
            Iterator<MuxerModifier> childIter = children().iterator();
            while (childIter.hasNext()) {
        	MuxerModifier child = childIter.next();
        
        	// Was this particular child called for on the command line?
        	if (clHelper.isOptionInUse(child)) {
        	    String optionArgument = clHelper.getLine().getOptionValue(child.getSwitch());
        	    log.debug("CodecBuilderBase.applyChildBuilders(): Modifying codec with " + child.getFriendlyName() + " and option: " + optionArgument);
        	    child.modifyMuxer(muxer, optionArgument);
        	}
            }
	}
    }
    
    
}
