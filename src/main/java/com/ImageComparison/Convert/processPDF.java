package com.ImageComparison.Convert;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.ImageComparison.Api.Compare;
import com.ImageComparison.util.Param;

public class processPDF {

	
	public static void processPDF(File path,String testUID, Param conf) throws InterruptedException
	{

        try {
        	final PDDocument document = PDDocument.load(path);
        	PDFRenderer pdfRenderer = new PDFRenderer(document);
        	
            PDPageTree list = document.getPages();
        	int pageCount = document.getNumberOfPages();
        	
        	for(int i=0; i< pageCount; i++)
        	{
        		pdfRenderer.renderImage(i);
           		ByteArrayOutputStream out = new  ByteArrayOutputStream();      
           		
           		if(conf.dpi>0)
           		{
            		ImageIO.write(pdfRenderer.renderImageWithDPI(i, conf.dpi),"PNG", out);
           		}
           		else
           		{
           			ImageIO.write(pdfRenderer.renderImage(i),"PNG", out);
           		}
           		  		
        		byte[] bytes = out.toByteArray();
        		String imagebase64 = Base64.encodeBase64String(bytes);	  
        		Compare.Iterations = 0;
                Compare.makeComparison(testUID,path.getName()+"_Page"+ Integer.toString(i+1) , imagebase64,conf.endPoint);
        	}
        	
        } catch (IOException e){
            System.err.println("Exception while trying to create pdf document - " + e);
        }
		
	}
	
	
}


