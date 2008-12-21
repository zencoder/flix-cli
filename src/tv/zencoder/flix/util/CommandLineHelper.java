package tv.zencoder.flix.util;

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

import tv.zencoder.flix.cli.OptionHandler;
import tv.zencoder.flix.filter.DeinterlaceFilterBuilder;
import tv.zencoder.flix.filter.FilterBuilder;
import tv.zencoder.flix.filter.FilterModifier;
import tv.zencoder.flix.filter.FramerateFilterBuilder;
import tv.zencoder.flix.filter.ScaleFilterBuilder;
import tv.zencoder.flix.filter.bchs.BchsFilterBuilder;

/**
 * Container for the Apache Commons CLI objects that we need to build.  Also supplies some
 * helper methods that our builders can use.
 * 
 * @author jdl
 *
 */
public class CommandLineHelper {
    private LogWrapper log = LogWrapper.getInstance();
    private String [] args;
    private Options options;
    private CommandLine line;
    private List<FilterBuilder> filterBuilders;
    
    private static CommandLineHelper instance;
    
    private CommandLineHelper() {
	super();
	populateParentFilterBuilders();
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
     * If the particular option handler has been called for on the command
     * line, this returns true.
     * 
     * @param optionHandler
     * @return boolean
     */
    public boolean isOptionInUse(OptionHandler optionHandler) {
	return getLine().hasOption(optionHandler.getSwitch());
    }
    
    
    public List<FilterBuilder> getFilterBuilders() {
        return filterBuilders;
    }

    public CommandLine getLine() {
        return line;
    }

    public Options getOptions() {
        return options;
    }

    public String[] getArgs() {
        return args;
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
	Iterator<FilterBuilder> fbIterator = getFilterBuilders().iterator();
	while (fbIterator.hasNext()) {
	    FilterBuilder fb = fbIterator.next();
	    options.addOption(fb.getOption());
	    
	    // If this builder has any children, we want to add them to the options list.
	    if (fb.children() != null && fb.children().size() > 0) {
		Iterator<FilterModifier> childIter = fb.children().iterator();
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
    

}
