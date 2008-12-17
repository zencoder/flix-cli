package tv.zencoder.flix;

import java.io.File;
import java.lang.reflect.Field;

import tv.zencoder.flix.filter.FilterBuilder;
import tv.zencoder.flix.filter.ScaleFilterBuilder;

import com.on2.flix.Codec;
import com.on2.flix.FE2_VideoBitrateControls;
import com.on2.flix.Filter;
import com.on2.flix.FlixEngine2;
import com.on2.flix.FlixException;
import com.on2.flix.Muxer;
import com.on2.flix.flixengine2_internalConstants;
import com.on2.flix.on2sc;

public class FooDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FlixEngine2 flix;
		
		System.out.println("Using library path: "+
		                   System.getProperty("java.library.path"));
		System.out.println("\nFlix Engine client library v"+
		                   FlixEngine2.Version());
		System.out.println(FlixEngine2.Copyright()+"\n");
		
		if(args.length < 2) {
		    System.out.println("usage: java cli_encode <infile> <outfile>\n");
		    System.out.println(
		       "NOTE cli_encode uses libflixengine2.so which is a client\n"+
		       "NOTE side rpc library. All paths must be accessible to the\n"+
		       "NOTE server side, i.e., flixd, thus relative paths will most\n"+
		       "NOTE likely give undesired results. The same can be said\n"+
		       "NOTE for clients running on different machines.");
		    System.exit(-1);
		}
		
		System.out.println("Connecting to Flix...");
		final int timeout_s = 0; //rpc timeout in seconds,
		                         //0=use default (25s)
		flix = new FlixEngine2("localhost", timeout_s);
		try {
		    flix.Connect();
		
		    File f = new File(args[0]);
		    System.out.println("Input file  : "+args[0]);
		    if(!f.isAbsolute())
		        System.out.println("WARNING: path to input file is not absolute");
		    flix.SetInputFile(args[0]);
		
		    //input file information
		    System.out.println(
		        "              Width:    "+flix.video_options_GetSourceWidth()+"\n"+
		        "              Height:   "+flix.video_options_GetSourceHeight()+"\n"+
		        "              Duration: "+flix.GetSourceDuration());
		
		    f = new File(args[1]);
		    System.out.println("Output file : "+args[1]);
		    if(!f.isAbsolute())
		        System.out.println("WARNING: path to output file is not absolute");
		    flix.SetOutputFile(args[1]);

		    /*
                Options may be set and codecs/filters/muxers may be added prior to Encode()
            */

			/* Add the scale filter */
//		    Filter filter = new Filter(flix, lookupInternalConstant(flix, "FE2_FILTER_SCALE"));
//			filter.add();
//			
//			filter.setParam(lookupInternalConstant(flix, "FE2_SCALE_WIDTH"), 480.0);
//			filter.setParam(lookupInternalConstant(flix, "FE2_SCALE_HEIGHT"), 320.0);
			 
		    FilterBuilder fb = new ScaleFilterBuilder();
		    fb.applyFilter(flix, "foo");
		    
			
			/*Add the vp6 codec. Though it is the default, you must add it in order
			  to modify its settings */
			Codec codec = new Codec(flix, lookupInternalConstant(flix, "FE2_CODEC_VP6"));
			codec.add();
			
			codec.setParam(flixengine2_internalConstants.FE2_VP6_RC_MODE, FE2_VideoBitrateControls.VBR_1PASSControl.swigValue());

			/*Use the FLV muxer (default) */
			Muxer muxer = new Muxer(flix, flixengine2_internalConstants.FE2_MUXER_FLV);
			muxer.add();
            
			System.out.println();
		    flix.Encode();
		
		    boolean ier;
		    do {
		        ier = flix.IsEncoderRunning();
		        System.out.print("\rEncoding..." +
		            flix.encoding_status_PercentComplete() + "%  ");
		        try {Thread.sleep(1000);}
		        catch(InterruptedException e) {}
		    } while(ier);
		    System.out.println("Done!");
		    printEncoderStatus(flix);
		
		    flix.Destroy();
		} catch (FlixException e) {
		    System.out.println("Flix call failed: "+e);
		    e.printStackTrace();
		
		    //if e == ON2_NET_ERROR Flix2_Errno will return the specific
		    //rpc error encountered as flixerrno along with the client lib's errno value
		    try {
		        long[] flixerr = flix.Errno();
		        System.out.println("\tFlixEngine2.Errno: "+
		            (e.equals(on2sc.ON2_NET_ERROR)?
		             "rpcerr":"flixerrno")+": "+flixerr[0]+
		            " syserrno:"+flixerr[1]);
		    } catch (FlixException ex) {}
		} catch (NoSuchFieldException e) {
			System.out.println("NoSuchFieldException: " + e);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	private static String lookupInternalConstant(FlixEngine2 flix, String filterName) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field filterConstantField = flixengine2_internalConstants.class.getDeclaredField(filterName);
		return (String) filterConstantField.get(flix);
	}
	
}
