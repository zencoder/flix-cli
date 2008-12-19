package tv.zencoder.flix;

import java.io.File;
import java.lang.reflect.Field;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import tv.zencoder.flix.filter.FramerateFilterBuilder;
import tv.zencoder.flix.filter.ScaleFilterBuilder;
import tv.zencoder.flix.util.LogWrapper;

import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.flixengine2_internalConstants;

public class FlixEngineApiDriver {

	private static LogWrapper log = LogWrapper.getInstance();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/* Process the command line */
		Options options = defineCommandLineOptions();
       	CommandLine line = parseCommandLine(options, args);
       	
		
       	/* Connect to Flix Engine */
       	FlixEngine2 flix;
		log.debug("FlixEngineApiDriver.main(): Connecting to Flix...");
		
        // rpc timeout in seconds,
        // 0=use default (25s)
		final int timeout_s = 0; 
		flix = new FlixEngine2("localhost", timeout_s);
		try {
			flix.Connect();
		
			/* Setup the flix object, based on the passed in options. */
			applyCommandLineOptions(flix, line, options);
			
			// debug
			printFlixEngineInfo();
			
			
			/* Process the file */
			flix.Encode();
		    boolean ier;
		    do {
		        ier = flix.IsEncoderRunning();
		        log.info("FlixEngineApiDriver.main(): \rEncoding..." + flix.encoding_status_PercentComplete() + "%  ");
		        try {Thread.sleep(1000);}
		        catch(InterruptedException e) {}
		    } while(ier);
		    log.info("FlixEngineApiDriver.main(): Done!");
		    
		    printEncoderStatus(flix);
			
			/* Cleanup */
			flix.Destroy();
		} catch (FlixException e) {
			log.error("FlixEngineApiDriver.main(): Caught a Flix exception. e=" + e.getLocalizedMessage());
		}
	}

	
	/**
	 * Defines the command line options that are available.
	 * @return	populated Options object
	 */
	@SuppressWarnings("static-access")
	private static Options defineCommandLineOptions() {
		Options options = new Options();
		
		options.addOption(new Option( "help", "print this message"));
		
		options.addOption(OptionBuilder.withArgName("fps")
				                       .hasArg()
				                       .withDescription("sets the framerate")
				                       .create("r"));
		options.addOption(OptionBuilder.withArgName("wxh")
						               .hasArg()
						               .withDescription("sets the output video size")
						               .create("s"));
		options.addOption(OptionBuilder.withArgName("filename")
						               .hasArg()
						               .withDescription("sets the input file (use an absolute path)")
						               .create("i"));
		options.addOption(OptionBuilder.withArgName("filename")
						               .hasArg()
						               .withDescription("sets the output file (use an absolute path)")
						               .create("o"));
		return options;
	}
	
	
	/**
 	 * Parse the command line, triggering the appropriate actions as switches are found.
 	 * 
	 * @param options	The Options object that defines what the command line is supposed
	 * 					to "look" like.
	 * @param args		The array of arguments that were passed into the application on the
	 *                  command line.
	 */
    private static CommandLine parseCommandLine(Options options, String[] args) {
    	CommandLineParser parser = new GnuParser();
    	CommandLine line = null;
		try {
			line = parser.parse(options, args);
		} catch (ParseException e1) {
			// TODO: This should probably be thrown upwards.
			log.error("FlixEngineApiSriver.parseCommandLine(): Failed to parse the command line. e=" + e1.getLocalizedMessage());
		}
		return line;
    }

    /**
     * Once the command line has been parsed, we need to configure the flix object based on the
     * desired options.
     * 
     * @param flix
     * @param line
     * @param options
     */
    private static void applyCommandLineOptions(FlixEngine2 flix, CommandLine line, Options options) throws FlixException {
		/* Help */
    	if(line.hasOption("help")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp( "zencoder_flixengine.sh", options);
		}
    	
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
    	
    	/* Framerate */
		if(line.hasOption("r") ) {
			String value = line.getOptionValue("r");
		    log.debug("FlixEngineApiDriver.applyCommandLineOptions(): Setting framerate: " + value);
		    (new FramerateFilterBuilder()).applyFilter(flix, value);
		}
		
		/* Video dimensions */
		if(line.hasOption("s") ) {
		    String value = line.getOptionValue("s");
		    log.debug("FlixEngineApiDriver.applyCommandLineOptions(): Setting dimensions: " + value);
		    (new ScaleFilterBuilder()).applyFilter(flix, value);
		}
    }
	
	private static void printEncoderStatus(final FlixEngine2 flix)
	{
	    try {
	        System.out.println("\nEncoder Status");
	        System.out.println(" FlixEngine2.GetEncoderState:"+
	            flix.GetEncoderState());
	        long[] flixerr = flix.Errno();
	        System.out.println(" FlixEngine2.Errno: flixerrno:"+
	            flixerr[0]+" syserrno:"+flixerr[1]);
	    } catch (FlixException e) {}
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
	
	
	
}
