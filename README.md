# Duplicate Image Remover #
Whether it is because you are running low on storage space, want to remove lower-resolution images on your hard drive, or because you had not realized you downloaded that cute cat picture five too many times already, this program can help you clear the clutter. The program is entirely designed to remove the tedious work of finding and removing those duplicate images manually, and instead does that work for you. It finds duplicates, displays them to you along with some basic information about each image, and lets you decide which one, if either, you wish to remove.

## Features ##
* There are three different options to choose from when deciding how many images to check.
    * Two images: Choose two individual images and compare them.
    * One folder: Choose a folder with the images you would like to check, and all possible combinations of images in that folder will be compared.
    * Two folders: Choose two different folders containing images, and each image in the first folder will be compared with all images in the second.

* When the program finds a potential duplicate:
    * It displays information about each image, including the name of each image, the image paths, the image dimensions, the amount of storage space used by each image, and the file type.
    * It shows where differences between the two images are, which are displayed in both the "Absolute Differences" and "Subtracted Differences" tabs on the right.
      * In the Absolute Differences tab, pixels that are 100% identical are black, while all other pixels are white. 
      * The Subtracted Differences tab is a result of subtracting the images from each other. This means that darker pixels indicate similarity between the images, while other colors represent differences between the images.
    * It shows how similar the two images are in a percentage form. (This is based on the image in the Subtracted Differences tab, not the one in the Absolute Differences tab.)

* The program can compare images of different sizes with each other, but only as long as they both have the same height/width ratio.

* There is an option to notify the user with a beeping noise when a potential match is found, and another option to periodically repeat this notification sound. This can be helpful when the user has other tabs open while the program is running, or if the user is AFK and wishes to be notified when they return.

* When choosing to compare images in folders, there is an option to include all images in subfolders as well.

* If the user is unable to finish going through a folder (or folders) with the amount of time they have, they can stop the program and continue where they left off later. Inside the progress bar is a fraction showing your current progress for that folder (or folders), with the current progress on the left and total progress on the right. (This progress can also be seen if the user has the "Display general information..." checkbox checked in the settings page.) If the number of possible combinations is above 1,000, the program will ask for a starting position before it begins comparing. All you have to do is remember where you stopped and enter that number to continue.
   * Note: If the contents of the folder(s) are changed by adding/removing images, the order the images are compared in will also change. If the user then skips to where they last ended, the program may miss image combinations that have not been tested for similarity yet.

## Limitations ##
* When choosing to delete a file, the program cannot send the file directly to the Recycle Bin. All files deleted using this program will skip the Recycle Bin and be deleted *permenantly!*

* The program can only compare JPEG and PNG images. You must convert images of any other image format into one of these types before comparing them.

* The program cannot match an image with a cropped, rotated, stretched, or otherwise highly edited version of itself. Both images *must* have the same height/width ratio and they must look similar enough to meet the percentage requirements set by the user. If either of these two conditions are not met, the program will think these are two unique images and will not display them to the user.

* If you are working with a lot of images that have the same or similar background color, the program may come up with a lot of false positives and display images that are not supposed to be paired. This is because the majority of the image has the same color in it, which results in a higher percentage of similarity. The best solution to this is to increase the percent similarity threshold, which should reduce the number of false positives.

* You cannot set the percent similarity threshold to 100% if you want the program to compare images of different sizes. The reason for this is that the program must resize these images (not the originals, just a copy of them) so that they have the same height and width before it can compare them pixel-by-pixel. Unfortunately, the program cannot scale these images without distorting the image slightly. Most of the time these distortions are too small to for the human eye to notice, but they do reduce the percentage of similarity between the images below 100%.

* The image in the Absolute Differences tab on the right does not accurately show pixel differences when the program is comparing two images of different sizes. This is because of the previously mentioned resizing issue, where resizing the image slightly changes the copy the program is comparing. This is enough to make the pixels between the two images unique, which results in the mostly-white image the user sees. If this happens, just use the image generated on the Subtracted Differences tab instead, since it is less effected by this issue.

## Requirements ##
Requires the latest version of Java.

## Bug reporting ##
You can report any bugs or issues to https://github.com/esamford/Duplicate_Image_Remover/issues. Please provide as much information as possible about the issue, including things like the steps required to recreate the issue and screenshots of the problem (if applicable). Thank you in advance for your help!

## MIT License ##
Copyright (c) 2020, Ethan Samford

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
