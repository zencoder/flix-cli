package tv.zencoder.flix;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;

import tv.zencoder.flix.cli.FlixBuilder;
import tv.zencoder.flix.util.BuilderCache;
import tv.zencoder.flix.util.CommandLineHelper;
import tv.zencoder.flix.util.FlixUtil;
import tv.zencoder.flix.util.LogWrapper;
import tv.zencoder.flix.util.StringUtil;

import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.on2sc;

/**
 * Main access point to the app.  Parses the command line, configures flix engine, and
 * triggers the video transcode.
 * 
 * @author jdl
 *
 */
public class FlixEngineApiDriver {
    private static LogWrapper log = LogWrapper.getInstance();
    private static CommandLineHelper clHelper = CommandLineHelper.getInstance();
    
    /**
     * Main app entry point.
     * @param args
     */
    public static void main(String[] args) {
	
	clHelper.setArgs(args);
	CommandLine line = clHelper.getLine();
	if (args == null || args.length == 0 || line.hasOption("help")) {
	    /* Help */
            HelpFormatter formatter = new HelpFormatter();
            formatter.setWidth(200);
            formatter.printHelp("zencoder_flixengine.sh [options]", clHelper.getOptions());	    
	} else {
	    configureFlixAndEncode();
	}
    }

    /**
     * Triggers the encoding in the flix engine service.
     */
    private static void configureFlixAndEncode() {
	/* Connect to Flix Engine and perform encoding. */
	FlixEngine2 flix;
	log.debug("FlixEngineApiDriver.main(): Connecting to Flix...");

	// rpc timeout in seconds,
	// 0=use default (25s)
	final int timeout_s = 0; 
	flix = new FlixEngine2("localhost", timeout_s);
	try {
	    log.debug("FlixEngineApiDriver.configureFlixAndEncode(): Connecting to Flix Engine");
	    flix.Connect();

	    /* Setup the flix object, based on the passed in options. */
	    applyCommandLineOptions(flix);

	    // debug
	    printFlixEngineInfo();
	    
	    /* Process the file */
	    log.debug("FlixEngineApiDriver.configureFlixAndEncode(): Starting the transcode.");
	    flix.Encode();
	    boolean ier;
	    do {
		ier = flix.IsEncoderRunning();
		log.info("FlixEngineApiDriver.main(): Encoding..." + flix.encoding_status_PercentComplete() + "%  ");
		if (shouldStopEncode()) {
		    log.info("FlixEngineApiDriver.main(): Stopping the encode");
		    flix.StopEncoding();
		    
		    log.info("FlixEngineApiDriver.main(): Deleting the kill file marker.");
		    if (cleanUpKillFile()) {
			log.info("FlixEngineApiDriver.main(): Delete succeeded.");
		    } else {
			log.info("FlixEngineApiDriver.main(): Delete failed.");
		    }
		}
		try {Thread.sleep(1000);}
		catch(InterruptedException e) {}
	    } while(ier);
	    log.info("FlixEngineApiDriver.main(): Done!");

	    printEncoderStatus(flix);

	    /* Cleanup */
	    log.debug("FlixEngineApiDriver.configureFlixAndEncode(): Cleanup");
	    flix.Destroy();
	} catch (FlixException e) {
	    log.error("FlixEngineApiDriver.main(): Caught a Flix exception. e=" + e.getLocalizedMessage());
	    log.debug("FlixEngineApiDriver.configureFlixAndEncode(): " + StringUtil.getStackTraceAsString(e));
	}
    }

    /**
     * Once the command line has been parsed, we need to configure the flix object based on the
     * desired options.
     * 
     * @param flix
     */
    private static void applyCommandLineOptions(FlixEngine2 flix) throws FlixException {
	CommandLine line = clHelper.getLine();
	
	/* Input file */
	if (line.hasOption("i")) {
	    String value = line.getOptionValue("i");
	    log.debug("FlixEngineApiDriver.applyCommandLineOptions(): Setting input file: " + value);
	    File f = new File(value);
	    if(!f.isAbsolute()) {
		log.warn("FlixEngineApiDriver.applyCommandLineOptions(): path to input file is not absolute");
	    }

	    flix.SetInputFile(value);

	    log.debug("FlixEngineApiDriver.applyCommandLineOptions(): Input File");
	    log.debug("FlixEngineApiDriver.applyCommandLineOptions():   Width: " + flix.video_options_GetSourceWidth());
	    log.debug("FlixEngineApiDriver.applyCommandLineOptions():   Height:   " + flix.video_options_GetSourceHeight());
	    log.debug("FlixEngineApiDriver.applyCommandLineOptions():   Duration: " + flix.GetSourceDuration());
	}

	/* Output File */
	if (line.hasOption("o")) {
	    String value = line.getOptionValue("o");
	    log.debug("FlixEngineApiDriver.applyCommandLineOptions(): Setting output file: " + value);

	    File f = new File(value);
	    log.debug("FlixEngineApiDriver.applyCommandLineOptions(): Output file: " + value);
	    if(!f.isAbsolute()) {
		log.warn("FlixEngineApiDriver.applyCommandLineOptions(): path to output file is not absolute");
	    }
	    flix.SetOutputFile(value);
	}
	
	/* Job ID (optional) */
	if (line.hasOption("job_id")) {
	    String jobId = line.getOptionValue("job_id");
	    log.debug("FlixEngineApiDriver.applyCommandLineOptions(): Setting external job ID: " + jobId);
	    BuilderCache.getInstance().setJobId(jobId);
	}
	
	/* Filters, Codecs, and Muxers */
	applyBuilders(flix, clHelper.getFilterBuilders());
	applyBuilders(flix, clHelper.getCodecBuilders());
	applyBuilders(flix, clHelper.getMuxerBuilders());
    }
 
    /**
     * Calls the apply() method for a list of builders.
     * @param flix
     * @param builderList
     */
    private static void applyBuilders(FlixEngine2 flix, List<FlixBuilder> builderList) {
	CommandLine line = clHelper.getLine();
	Iterator<FlixBuilder> fbIter = builderList.iterator();
	while (fbIter.hasNext()) {
	    FlixBuilder fb = fbIter.next();
	    if (fb.isPrimaryOption() && line.hasOption(fb.getSwitch())) {
		String optionArgument = line.getOptionValue(fb.getSwitch());
		log.debug("FlixEngineApiDriver.applyBuilders(): Applying filter builder '" + fb.getFriendlyName() + "' with option argument: " + optionArgument);
		fb.apply(flix, optionArgument);
	    }
	}
    }
    
    
    /**
     * Dumps basic info about the Flix encoder.
     * @param flix
     */
    private static void printEncoderStatus(final FlixEngine2 flix)
    {
	boolean success = false;
	try {
	    System.out.println("\nEncoder Status");
	    System.out.println(" FlixEngine2.GetEncoderState:" + flix.GetEncoderState());
	    long[] flixerr = flix.Errno();
	    System.out.println(" FlixEngine2.Errno: flixerrno:" + flixerr[0] + " (" + FlixUtil.lookupError(new Long(flixerr[0])) + ") syserrno:" + flixerr[1]);
	    
	    if(flixerr[0] == 0 && flixerr[1] == 0) {
		success = true;
	    }
	} catch (FlixException e) {
	    // If e == ON2_NET_ERROR Flix2_Errno will return the specific
	    // rpc error encountered as flixerrno along with the client
	    // lib's errno value
	    try {
		long[] flixerr = flix.Errno();
		System.out.println("\nFlixEngine2.Errno: " + (e.equals(on2sc.ON2_NET_ERROR)? "rpcerr":"flixerrno") + ": " + flixerr[0] + " syserrno:" + flixerr[1]);
	    } catch (Exception ex) {}
	}

	if (success) {
	    log.info("--SUCCESS--");
	} else {
	    log.info("--FAIL--");
	}	
    }

    /**
     * Dumps out some basic info about the Flix Engine installation.
     *
     */
    private static void printFlixEngineInfo() {
	log.debug("FlixEngineApiDriver.printFlixEngineInfo(): Using library path: " + System.getProperty("java.library.path"));
	log.debug("FlixEngineApiDriver.printFlixEngineInfo(): Flix Engine client library v" + FlixEngine2.Version());
	log.debug("FlixEngineApiDriver.printFlixEngineInfo(): " + FlixEngine2.Copyright());
    }
    
    
    /**
     * If a job_id was set on the command line, and the kill file marker exists, this 
     * will return true.
     */
    private static boolean shouldStopEncode() {
	boolean result = false;
	
	String jobId = BuilderCache.getInstance().getJobId();
	if (jobId != null) {
	    File f = jobIdToKillFile(jobId);
	    if (f.exists()) {
		result = true;
	    }
	}
	return result;
    }
    
    /**
     * Generates the File that should be used to kill this encode.
     */
    private static File jobIdToKillFile(String jobId) {
	File f = new File("/tmp/kill_job_" + jobId + ".txt");
	return f;
    }
    
    /**
     * Erases the kill file marker, if it exists.
     */
    private static boolean cleanUpKillFile() {
	boolean success = false;
	String jobId = BuilderCache.getInstance().getJobId();
	if (jobId != null) {
    	    File f = jobIdToKillFile(jobId);
    	    if (f.exists()) {
    	        success = f.delete();
    	    }
	}
	return success;
    }
}
