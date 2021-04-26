package com.ImageComparison;

import java.io.File;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.ImageComparison.util.Param;
import com.ImageComparison.util.RunTest;


public class ImagiumAI {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        CommandLineParser parser = new DefaultParser();
        Options inputOptions = getArguments();
        Param config = new Param();
        
        CommandLine cmd;
		try {
			cmd = parser.parse(inputOptions, args);
			config.projectKey = cmd.getOptionValue("k");
	        config.testName = cmd.getOptionValue("t");
	        config.endPoint = cmd.getOptionValue("u");
	        config.path = cmd.getOptionValue("f");
	        config.mode = cmd.getOptionValue("m","Default");	    
	        config.dpi =  Integer.parseInt(cmd.getOptionValue("d", "-1"));  	        	       
	        File root = new File(cmd.getOptionValue("f", "."));
	        String mystr = root.getCanonicalPath();
	        System.out.println(mystr);
	        
	        RunTest.RunShell(root,config);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}            
    
        
	}
	
    //k = Key 
	//t = Test Name
	//f = File Path/Folder
	//u = Endpoint
	//m = mode
	//d = DPI (Only for PDF)
    private static Options getArguments() {
        Options options = new Options();

        options.addOption(Option.builder("k")
                .longOpt("ProjectKey")
                .desc("Imagium project key")
                .hasArg()
                .argName("ProjectKey")
                .build());

        options.addOption(Option.builder("t")
                .longOpt("Testname")
                .desc("The name of test case")
                .hasArg()
                .argName("Testname")
                .build());

        options.addOption(Option.builder("f")
                .longOpt("filePath")
                .desc("Path to files or individual file path")
                .hasArg()
                .argName("filePath")
                .build());


        options.addOption(Option.builder("u")
                .longOpt("URL")
                .desc("Set Imagium server url")
                .hasArg()
                .argName("url")
                .build()
        );

        options.addOption(Option.builder("d")
                .longOpt("DPI")
                .desc("Only for PDF files. Dots per inch")
                .hasArg()
                .argName("DPI")
                .build()
        );

        options.addOption(Option.builder("m")
                .longOpt("mode")
                .desc("Set comparison mode")
                .hasArg()
                .argName("mode")
                .build());

        return options;
    }

}
