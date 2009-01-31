package tv.zencoder.flix.util;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * These are the error codes that are in the Flix Engine documentation.  They are certainly
 * vague, but it's better than just looking at an error number.
 * 
 * @author jdl
 *
 */
public class FlixUtil {

    private static Map<Long, String> flixErrorCodes;
    static {
        Map<Long, String> map = new TreeMap<Long, String>();
        map.put(new Long(0), "ErrNone");
        map.put(new Long(-1), "ErrSys");
        map.put(new Long(-2), "ErrFileIO");
        map.put(new Long(-3), "ErrFileOpen");
        map.put(new Long(-4), "ErrFileDecode");
        map.put(new Long(-5), "ErrFileDecodeA");
        map.put(new Long(-6), "ErrFileDecodeV");
        map.put(new Long(-7), "ErrEncodeA");
        map.put(new Long(-8), "ErrEncodeB");
        flixErrorCodes = Collections.unmodifiableMap(map);
    }
    
    
    public static String lookupError(Long errorCode) {
	return flixErrorCodes.get(errorCode);
    }
    
}
