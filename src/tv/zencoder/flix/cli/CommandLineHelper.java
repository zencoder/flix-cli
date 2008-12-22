package tv.zencoder.flix.cli;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import tv.zencoder.flix.codec.CodecBuilder;
import tv.zencoder.flix.codec.CodecModifier;
import tv.zencoder.flix.codec.VideoCodecBuilder;
import tv.zencoder.flix.codec.config.VideoCodecConfig;
import tv.zencoder.flix.filter.DeinterlaceFilterBuilder;
import tv.zencoder.flix.filter.FilterBuilder;
import tv.zencoder.flix.filter.FilterModifier;
import tv.zencoder.flix.filter.FramerateFilterBuilder;
import tv.zencoder.flix.filter.ScaleFilterBuilder;
import tv.zencoder.flix.filter.bchs.BchsFilterBuilder;
import tv.zencoder.flix.util.LogWrapper;

/**
 * Container for the Apache Commons CLI objects that we need to build.  Also supplies some
 * helper methods that our builders can use.
 * 
 * @author jdl
 *
 */
public class CommandLineHelper {
    // logger
    private LogWrapper log = LogWrapper.getInstance();
    
    // Usually the args that were passed into main().  When Apache Commons CLI
    // parses the command line, this is the array that we send in for parsing.
    private String [] args;
    
    // Apache Commons CLI Options - holds the list of options that are recognized
    // from the command line.
    private Options options;
    
    // Holds the results of parsing the command line.
    private CommandLine line;
    
    // Handles building of Flix Filters.
    private List<FilterBuilder> filterBuilders;
    
    // Handles building of Flix Codecs.
    private List<CodecBuilder> codecBuilders;
    
    // Stores the choice of video codecs.  This is here, because the codec modifiers
    // need to know which codec they're dealing with.
    private VideoCodecConfig chosenVideoCodec;
    
    private static CommandLineHelper instance;
    
    private CommandLineHelper() {
	super();
	populateParentFilterBuilders();
	populateParentCodecBuilders();
	defineCommandLineOptions();
	parseCommandLine();
    }

    public static CommandLineHelper getInstance() {
	if (instance == null) {
	    synchronized (CommandLineHelper.class) {
		if (instance == null) {
		    instance = new CommandLineHelper();
		}
	    }
	}
	return instance;
    }
    
    /**
     * Populates the list of filter builders.
     * 
     * Note: This approach is OK since we're just dealing with 
     *       dozens of filters, not thousands. [JDL]
     */
    private void populateParentFilterBuilders() {
	// Only add the primary filter builders here. Let the constructor for those with 
	// children take care of adding those.
	filterBuilders = new ArrayList<FilterBuilder>();
	filterBuilders.add(new DeinterlaceFilterBuilder());
	filterBuilders.add(new FramerateFilterBuilder());
	filterBuilders.add(new ScaleFilterBuilder());
	filterBuilders.add(new BchsFilterBuilder());
    }
    
    
    /**
     * Populates the list of codec builders.
     */
    private void populateParentCodecBuilders() {
        // Only add the primary codec builders here.
	codecBuilders = new ArrayList<CodecBuilder>();
	codecBuilders.add(new VideoCodecBuilder());
    }
    
    
    /**
     * If the particular option handler has been called for on the command
     * line, this returns true.
     * 
     * @param optionHandler
     * @return boolean
     */
    public boolean isOptionInUse(OptionHandler optionHandler) {
	return getLine().hasOption(optionHandler.getSwitch());
    }
    
    /**
     * Defines the command line options that are available.
     * @return	populated Options object
     */
    @SuppressWarnings("static-access")
    private Options defineCommandLineOptions() {
	options = new Options();
	options.addOption(new Option( "help", "print this message"));

	// i
	options.addOption(OptionBuilder.withArgName("filename")
		.hasArg()
		.withDescription("sets the input file (use an absolute path)")
		.create("i"));

	// o
	options.addOption(OptionBuilder.withArgName("filename")
		.hasArg()
		.withDescription("sets the output file (use an absolute path)")
		.create("o"));

	
	/* Filters */
	Iterator<FilterBuilder> fbIter = getFilterBuilders().iterator();
	while (fbIter.hasNext()) {
	    FilterBuilder fb = fbIter.next();
	    options.addOption(fb.getOption());
	    
	    // If this builder has any children, we want to add them to the options list.
	    if (fb.children() != null && fb.children().size() > 0) {
		Iterator<FilterModifier> childIter = fb.children().iterator();
		while (childIter.hasNext()) {
		    options.addOption(childIter.next().getOption());
		}
	    }
	}
	
	/* Codecs */
	Iterator<CodecBuilder> cbIter = getCodecBuilders().iterator();
	while (cbIter.hasNext()) {
	    CodecBuilder cb = cbIter.next();
	    options.addOption(cb.getOption());
	    
	    // And any children (codec modifiers) that this builder may have.
	    if (cb.children() != null && cb.children().size() > 0) {
		Iterator<CodecModifier> childIter = cb.children().iterator();
		while (childIter.hasNext()) {
		    options.addOption(childIter.next().getOption());
		}
	    }
	}

	return options;
    }


    /**
     * Parse the command line, triggering the appropriate actions as switches are found.
     */
    private CommandLine parseCommandLine() {
	CommandLineParser parser = new GnuParser();
	try {
	    line = parser.parse(getOptions(), getArgs());
	} catch (ParseException e1) {
	    // TODO: This should probably be thrown upwards.
	    log.error("FlixEngineApiSriver.parseCommandLine(): Failed to parse the command line. e=" + e1.getLocalizedMessage());
	}
	return line;
    }

    /**
     * Returns the list of parent filter builders.  The child modifiers are not
     * returned by this.  Each builder will return any available children via its
     * <code>children()</code> method.
     * 
     * @return List<FilterBuilder>
     */
    public List<FilterBuilder> getFilterBuilders() {
        return filterBuilders;
    }
    
    /**
     * Returns the list of parent codec builders.  The child modifiers are not
     * returned by this.  Each builder will return any available children via its
     * <code>children()</code> method.
     * 
     * @return List<CodecBuilder>
     */
    public List<CodecBuilder> getCodecBuilders() {
        return codecBuilders;
    }

    /**
     * Returns the Apache Commons CLI CommandLine, which is the object
     * that gets created after the command line is parsed.
     * 
     * @return	CommandLine
     */
    public CommandLine getLine() {
        return line;
    }

    /**
     * Returns the Apache Commons CLI Options list.  This defines the potential 
     * options available on the command line. 
     * 
     * @return	Options
     */
    public Options getOptions() {
        return options;
    }

    /**
     * The command line args that were passed into the main() method for the app.
     * <p>
     * Calling this will also cause the command line to be re-parsed.
     * 
     * @param args
     */
    public void setArgs(String[] args) {
        this.args = args;
        parseCommandLine();
    }
    
    /** 
     * Returns the command line argument list.
     * 
     * @return String[]
     */
    public String[] getArgs() {
        return args;
    }

    /**
     * Returns the video codec that we're working on.  Video codec modifiers
     * need to know which specific codec they're trying to configure.
     * 
     * @return VideoCodecConfig
     */
    public VideoCodecConfig getChosenVideoCodec() {
        return chosenVideoCodec;
    }

    /**
     * When a VideoCodecBuilder decides on a particular codec, it should set that
     * value here, so that codec modifiers will behave properly.
     * 
     * @param chosenVideoCodec
     */
    public void setChosenVideoCodec(VideoCodecConfig chosenVideoCodec) {
        this.chosenVideoCodec = chosenVideoCodec;
    }
    
}
