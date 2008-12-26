package tv.zencoder.flix.filter;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

/**
 * Creates a scale filter.  
 * <p>
 * The options are of the form "wxh" such as "480x360".

 * @author	jdl
 *
 */
public class ScaleFilterBuilder extends FilterBuilderBase {
    /**
     * Pattern to recognize "wxh" such as "240x160".
     */
    protected static final Pattern widthHeightPattern = Pattern.compile("(\\d+)x(\\d+)");
    
    /**
     * Shortcuts for sizes that can be set on the command line.  If we see any of these, we simply
     * substitute the wxh value.  These are compatible with ffmpeg.
     */
    protected static Map<String, String> scaleShortcuts;
    static {
        Map<String, String> map = new TreeMap<String, String>();
        map.put("sqcif",  "128x96");
        map.put("qcif",   "176x144"); 
        map.put("cif",    "352x288"); 
        map.put("4cif",   "704x576");
        map.put("qqvga",  "160x120"); 
        map.put("qvga",   "320x240"); 
        map.put("vga",    "640x480");
        map.put("svga",   "800x600"); 
        map.put("xga",    "1024x768");
        map.put("uxga",   "1600x1200");
        map.put("qxga",   "2048x1536");
        map.put("sxga",   "1280x1024");
        map.put("qsxga",  "2560x2048");
        map.put("hsxga",  "5120x4096");
        map.put("wvga",   "852x480");
        map.put("wxga",   "1366x768");
        map.put("wsxga",  "1600x1024");
        map.put("wuxga",  "1920x1200");
        map.put("woxga",  "2560x1600");
        map.put("wqsxga", "3200x2048");
        map.put("wquxga", "3840x2400");
        map.put("whsxga", "6400x4096");
        map.put("whuxga", "7680x4800");
        map.put("cga",    "320x200");
        map.put("ega",    "640x350");
        map.put("hd480",  "852x480");
        map.put("hd720",  "1280x720");
        map.put("hd1080", "1920x1080");
        scaleShortcuts = Collections.unmodifiableMap(map);
    }
    
    
    public ScaleFilterBuilder() {
	super();
    }

    /* (non-Javadoc)
     * @see tv.zencoder.flix.filter.FilterBuilder#applyFilter(com.on2.flix.FlixEngine2, java.lang.String)
     */
    public void apply(FlixEngine2 flix, String options) {

	// Check to see if the user is supplying one of the shortcut sizes.
	if (scaleShortcuts.containsKey(options)) {
	    options = scaleShortcuts.get(options);
	}
	
	try {
	    filter = new Filter(flix, flixengine2_internalConstants.FE2_FILTER_SCALE);
	    filter.add();
	    filter.setParam(flixengine2_internalConstants.FE2_SCALE_WIDTH, getWidth(options));
	    filter.setParam(flixengine2_internalConstants.FE2_SCALE_HEIGHT, getHeight(options));
	} catch (FlixException e) {
	    //
	}
    }

    protected double getWidth(String options) {
	return getDimension(options, 1);
    }

    protected double getHeight(String options) {
	return getDimension(options, 2);
    }

    /**
     * 
     * @param options	
     * @param dimensionPosition	1 or 2 for before or after the "x" respectively.
     * @return	The value at the dimension position in the options.
     */
    protected double getDimension(String options, int dimensionPosition) {
	Matcher m = widthHeightPattern.matcher(options);
	m.matches();
	return Double.parseDouble(m.group(dimensionPosition));
    }

    public String getFriendlyName() {
	return "Scale Filter Builder (-" + getSwitch() + ")";
    }

    @SuppressWarnings("static-access")
    public Option getOption() {
	
	StringBuffer msg = new StringBuffer("Sets the output video size. ");
	msg.append("Enter a width and height in the form of 'wxh' or use one of the following shortcuts. ");
	msg.append("(These are compatible with ffmpeg.)\n");
	Iterator<String> keyIter = scaleShortcuts.keySet().iterator();
	while (keyIter.hasNext()) {
	    String key = keyIter.next();
	    msg.append("  " + key + ":\t" + scaleShortcuts.get(key) + "\n");
	}
	msg.append("\n");
	
	return OptionBuilder.withArgName("wxh")
			    .hasArg()
            		    .withDescription(msg.toString())
            		    .create(getSwitch());
    }

    public String getSwitch() {
	return "s";
    }
    
    public boolean isPrimaryOption() {
	return true;
    }
}
