# ImagiumAI

ImagiumAI.jar is a supplementary utility to Imagium.io which allows comparison of standalone images and PDF files.
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

k     -     Project Key 

t     -     Test Name (Unique Identifier)

f     -     File Name/Folder Name

u     -     url/endpoint

d     -     DPI for PDf's (Optional)

m     -     Mode (Default|Strict) - Optional

