package tv.zencoder.flix.util;

import java.util.Iterator;
import java.util.Map;

public class StringUtil {

    /**
     * Given a Map, returns a String with the Map's keys joined together.
     * 
     * @param map
     * @param separator
     * @return	String
     */
    public static String mapKeysToString(Map map, String separator) {
	StringBuffer b = new StringBuffer();
	Iterator keyIter = map.keySet().iterator();
	while (keyIter.hasNext()) {
	    b.append((String) keyIter.next().toString());
	    if (keyIter.hasNext()) {
		b.append(", ");
	    }
	}
	return b.toString();
    }
    
}
