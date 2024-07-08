# ImagiumAI  [Download here](https://github.com/blobai/ImagiumAI/releases/download/1.26/ImagiumAI.jar)

ImagiumAI.jar is a supplementary utility to Imagium.io which allows comparison of standalone images and PDF files.

**The attached JAR is compiled for Java 11. If you are working on any other Java framework, simply download the repository and build a new JAR using 'mvn clean install' or 'mvn clean package'**

This utility can easily be run via command line and has following features:
1. Can compare on individual image 
2. Can compare multiple images in a folder all at once
3. Can compare individual PDF File
4. Can compare multiple PDF files
5. Can compare muliiple PDF and Image files in a folder all at once



The first run creates a baseline and subsequent run are automatically compared against each other.

The image from baseline is comared with current image having same **Test Name** and **Step Name**

---------------------------------------------------------------

**Compare All the files in the folder**

Java -jar ImagiumAI.jar -k 4e282cc0-52a9-4a31-bec9-1ce396d24a10 -u http://192.168.10.13:90   -t Regression -f "C:\baseline" -m Default


**Compare individual files in the folder**

Java -jar ImagiumAI.jar -k 4e282cc0-52a9-4a31-bec9-1ce396d24a10 -u http://192.168.10.13:90   -t Regression -f "C:\baseline\Image1.png" -m Default

---------------------------------------------------------------

**Arguments**

k - Project Key

t - Test Name (Unique Identifier)

f - File Name/Folder Name

u - url/endpoint

d - DPI for PDf's (Optional)

p - Passowrd for protected PDF's (Optional)

m - Mode (Default|Strict) - Optional

ty - Type (Positive|Negative) - Optional

tr - Target (x,y,width,height|x1,y1,width1,height1;x2,y2,width2,height2)   (Only for Negative)

ex - Exclude (x,y,width,height|x1,y1,width1,height1;x2,y2,width2,height2)  (Optional)

ig - Ignore (Integer - Square pixels to ignore - Optional)

tags - Assign tags to a step (e.g. "SIT,English,Release1" etc. - Optional)

