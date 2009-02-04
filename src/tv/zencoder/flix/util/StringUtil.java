package tv.zencoder.flix.util;

import java.io.PrintWriter;
import java.io.StringWriter;
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
		b.append(separator);
	    }
	}
	return b.toString();
    }
    
    /**
     * Turns an exception's stack trace into a string.
     * @param exception
     * @return String
     */
    public static String getStackTraceAsString(Exception exception) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.print(" [ ");
        pw.print(exception.getClass().getName());
        pw.print(" ] ");
        pw.print(exception.getMessage());
        exception.printStackTrace(pw);
        return sw.toString();
    }
 
    
}
