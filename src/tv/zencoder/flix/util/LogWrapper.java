package tv.zencoder.flix.util;

/**
 * Simple output to STDOUT.  I'm using this class in case we ever want to 
 * switch over to a real log system without having to refactor all of the 
 * code.
 * 
 * Exmaple usage:
 * LogWrapper log = LogWrapper.getInstance();
 * log.debug("foo");
 * 
 * debug, info, warn, and error are handled in a simplistic way, but it should suffice.
 * @see LogWrapper.setLogLevel()  
 * 
 * @author jdl
 *
 */
public class LogWrapper {

    public static final int LEVEL_SILENT = 0;
    public static final int LEVEL_ERROR  = 1; 
    public static final int LEVEL_WARN   = 2; 
    public static final int LEVEL_INFO   = 3;
    public static final int LEVEL_DEBUG  = 4;

    /**
     * Default log level
     */ 
    public int logLevel = LEVEL_DEBUG;

    private static LogWrapper instance;
    private LogWrapper() {}

    public static LogWrapper getInstance() {
	if (instance == null) {
	    synchronized (LogWrapper.class) {
		if (instance == null) {
		    instance = new LogWrapper();
		}
	    }
	}
	return instance;
    }

    /**
     * DEBUG level log message
     * @param message
     */
    public void debug(String message) {
	if (getLogLevel() >= LEVEL_DEBUG) { 
	    log("DEBUG: " + message);
	}
    }

    /**
     * DEBUG level log message
     * @param message
     */
    public void info(String message) {
	if (getLogLevel() >= LEVEL_INFO) { 
	    log("INFO: " + message);
	}
    }

    /**
     * WARN level log message
     * @param message
     */
    public void warn(String message) {
	if (getLogLevel() >= LEVEL_WARN) { 
	    log("WARN: " + message);
	}
    }

    /**
     * ERROR level log message
     * @param message
     */
    public void error(String message) {
	if (getLogLevel() >= LEVEL_ERROR) { 
	    log("ERROR: " + message);
	}
    }



    /**
     * Dumps the message to STDOUT.  This is where the "real" logging takes place.
     * @param message
     */
    protected void log(String message) {
	System.out.println(message);
    }

    public int getLogLevel() {
	return logLevel;
    }

    public void setLogLevel(int logLevel) {
	this.logLevel = logLevel;
    }



}
