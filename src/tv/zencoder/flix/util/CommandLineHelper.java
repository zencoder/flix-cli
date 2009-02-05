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

import tv.zencoder.flix.cli.FlixBuilder;
import tv.zencoder.flix.cli.OptionHandler;
import tv.zencoder.flix.codec.audio.AudioCodecBuilder;
import tv.zencoder.flix.codec.video.VideoCodecBuilder;
import tv.zencoder.flix.filter.DeinterlaceFilterBuilder;
import tv.zencoder.flix.filter.DenoiseFilterBuilder;
import tv.zencoder.flix.filter.FramerateFilterBuilder;
import tv.zencoder.flix.filter.HighpassFilterBuilder;
import tv.zencoder.flix.filter.LowpassFilterBuilder;
import tv.zencoder.flix.filter.ScaleFilterBuilder;
import tv.zencoder.flix.filter.bchs.BrightnessFilterBuilder;
import tv.zencoder.flix.filter.bchs.ContrastFilterBuilder;
import tv.zencoder.flix.filter.bchs.HueFilterBuilder;
import tv.zencoder.flix.filter.bchs.SaturationFilterBuilder;
import tv.zencoder.flix.filter.crop.CropBottomFilterBuilder;
import tv.zencoder.flix.filter.crop.CropLeftFilterBuilder;
import tv.zencoder.flix.filter.crop.CropRightFilterBuilder;
import tv.zencoder.flix.filter.crop.CropTopFilterBuilder;
import tv.zencoder.flix.filter.cut.CutStartFilterBuilder;
import tv.zencoder.flix.filter.cut.CutStopFilterBuilder;
import tv.zencoder.flix.filter.overlay.OverlayFilePathFilterBuilder;
import tv.zencoder.flix.filter.overlay.OverlayMaskBFilterBuilder;
import tv.zencoder.flix.filter.overlay.OverlayMaskGFilterBuilder;
import tv.zencoder.flix.filter.overlay.OverlayMaskRFilterBuilder;
import tv.zencoder.flix.filter.overlay.OverlayMaskXFilterBuilder;
import tv.zencoder.flix.filter.overlay.OverlayMaskYFilterBuilder;
import tv.zencoder.flix.filter.overlay.OverlayPositionXFilterBuilder;
import tv.zencoder.flix.filter.overlay.OverlayPositionYFilterBuilder;
import tv.zencoder.flix.filter.resample.AudioResampleChannelsFilterBuilder;
import tv.zencoder.flix.filter.resample.AudioResampleRateFilterBuilder;
import tv.zencoder.flix.muxer.VideoMuxerBuilder;

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
    private List<FlixBuilder> filterBuilders;
    
    // Handles building of Flix Codecs.
    private List<FlixBuilder> codecBuilders;
    
    // Handles building of the Flix Muxers.
    private List<FlixBuilder> muxerBuilders;
    
    private static CommandLineHelper instance;
    
    private CommandLineHelper() {
	super();
	populateParentFilterBuilders();
	populateParentCodecBuilders();
	populateParentMuxerBuilders();
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
	filterBuilders = new ArrayList<FlixBuilder>();
	
	filterBuilders.add(new DeinterlaceFilterBuilder());
	filterBuilders.add(new DenoiseFilterBuilder());
	filterBuilders.add(new FramerateFilterBuilder());
	filterBuilders.add(new ScaleFilterBuilder());

	// BCHS
	filterBuilders.add(new BrightnessFilterBuilder());
	filterBuilders.add(new ContrastFilterBuilder());
	filterBuilders.add(new HueFilterBuilder());
	filterBuilders.add(new SaturationFilterBuilder());
	
	// Crop
	filterBuilders.add(new CropTopFilterBuilder());
	filterBuilders.add(new CropRightFilterBuilder());
	filterBuilders.add(new CropBottomFilterBuilder());
	filterBuilders.add(new CropLeftFilterBuilder());
	
	// Cut
	filterBuilders.add(new CutStartFilterBuilder());
	filterBuilders.add(new CutStopFilterBuilder());
	
	// Overlay (watermark)
	filterBuilders.add(new OverlayFilePathFilterBuilder());
	filterBuilders.add(new OverlayMaskXFilterBuilder());
	filterBuilders.add(new OverlayMaskYFilterBuilder());
	filterBuilders.add(new OverlayMaskRFilterBuilder());
	filterBuilders.add(new OverlayMaskGFilterBuilder());
	filterBuilders.add(new OverlayMaskBFilterBuilder());
	filterBuilders.add(new OverlayPositionXFilterBuilder());
	filterBuilders.add(new OverlayPositionYFilterBuilder());
	
	// Audio Filters
	filterBuilders.add(new HighpassFilterBuilder());
	filterBuilders.add(new LowpassFilterBuilder());
	filterBuilders.add(new AudioResampleChannelsFilterBuilder());
	filterBuilders.add(new AudioResampleRateFilterBuilder());
    }
    
    
    /**
     * Populates the list of codec builders.
     */
    private void populateParentCodecBuilders() {
        // Only add the primary codec builders here.
	codecBuilders = new ArrayList<FlixBuilder>();
	codecBuilders.add(new VideoCodecBuilder());
	codecBuilders.add(new AudioCodecBuilder());
    }
    
    
    /**
     * Populates the list of muxer builders.
     */
    private void populateParentMuxerBuilders() {
	muxerBuilders = new ArrayList<FlixBuilder>();
	muxerBuilders.add(new VideoMuxerBuilder());
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

	
	/*  Add the command line Options from the Filter, Codec, and Muxer builders. */
	addBuilderOptions(options, getFilterBuilders());
	addBuilderOptions(options, getCodecBuilders());
	addBuilderOptions(options, getMuxerBuilders());

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
     * Iterates over a list of FlixBuilders, and adds the command line Option to the Options
     * list.
     * 
     * @param options
     * @param builderList
     */
    private void addBuilderOptions(Options options, List<FlixBuilder> builderList) {
	Iterator<FlixBuilder> fbIter = builderList.iterator();
	while (fbIter.hasNext()) {
	    FlixBuilder fb = fbIter.next();
	    options.addOption(fb.getOption());
	    
	    // If this builder has any children, we want to add them to the options list.
	    if (fb.children() != null && fb.children().size() > 0) {
		Iterator childIter = fb.children().iterator();
		while (childIter.hasNext()) {
		    options.addOption(((OptionHandler) childIter.next()).getOption());
		}
	    }
	}
    }
    
    /**
     * Returns the list of parent filter builders.  The child modifiers are not
     * returned by this.  Each builder will return any available children via its
     * <code>children()</code> method.
     * 
     * @return List<FlixBuilder>
     */
    public List<FlixBuilder> getFilterBuilders() {
        return filterBuilders;
    }
    
    /**
     * Returns the list of parent codec builders.  The child modifiers are not
     * returned by this.  Each builder will return any available children via its
     * <code>children()</code> method.
     * 
     * @return List<FlixBuilder>
     */
    public List<FlixBuilder> getCodecBuilders() {
        return codecBuilders;
    }

    /**
     * Returns the list of parent muxer builders.  The child modifiers are not
     * returned by this.  Each builder will return any available children via its
     * <code>children()</code> method.
     * 
     * @return List<FlixBuilder>
     */
    public List<FlixBuilder> getMuxerBuilders() {
        return muxerBuilders;
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
    
}
