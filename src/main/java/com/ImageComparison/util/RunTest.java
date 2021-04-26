package com.ImageComparison.util;

import java.io.File;
import org.apache.commons.io.FilenameUtils;
import com.ImageComparison.Api.Compare;
import com.ImageComparison.Convert.Images;
import com.ImageComparison.Convert.processPDF;





public class RunTest {
	
    public static void RunShell(File file, Param conf) {
      
         run(file, conf);
    }

    
    private static void run(File file, Param conf)
    {
        if (!file.exists())
            throw new RuntimeException(
                    String.format("Fatal! The path %s does not exists \n", file.getAbsolutePath()));
        
        String myExtension = getExtensionByApacheCommonLib(file.getName());
        
        myExtension.isEmpty();
        try {
        	 String testUID ="";
            if (file.isFile()) {
               
                	if(isImage(myExtension))
                	{
                		testUID = Compare.getUID(conf.endPoint, conf.projectKey, conf.testName,conf.mode);
                		Compare.makeComparison(testUID,file.getName(), Images.encodeFileToBase64Binary(file),conf.endPoint);
                	}
                	else if(isPDF(myExtension))
                	{
                		testUID = Compare.getUID(conf.endPoint, conf.projectKey, conf.testName,conf.mode);
                		processPDF.processPDF(file, testUID, conf);
                	}
                 

                return;
            } else{
            	testUID = Compare.getUID(conf.endPoint, conf.projectKey, conf.testName,conf.mode);
                for (final File fileEntry : file.listFiles()) {
                    if (!fileEntry.isDirectory()) {
                    	myExtension = getExtensionByApacheCommonLib(fileEntry.getName());

                    	if(isImage(myExtension))
                    	{
                    		Compare.makeComparison(testUID,fileEntry.getName(), Images.encodeFileToBase64Binary(fileEntry), conf.endPoint);
                    	}
                    	else if(isPDF(myExtension))
                    	{                    	
                    		processPDF.processPDF(fileEntry, testUID, conf);
                    	}                    	
                    } 
                }
                
            }

           
        } catch (Exception e) {
        	
        	System.out.println(e.toString());
           
        }
        
    }
    
    
    private static boolean isImage(String fName) {
        return "|JPEG|JPG|PNG|BMP|GIF|".toUpperCase().contains(("|"+fName+"|").toUpperCase());
    }
    
    private static boolean isPDF(String fName) {
        return "|PDF|".toUpperCase().contains(("|"+fName+"|").toUpperCase());
    }
    
    public static String getExtensionByApacheCommonLib(String filename) {
        return FilenameUtils.getExtension(filename);
    }
    
}
