# Duplicate Image Remover #
This program is designed to compare separate images with each other (either in bulk or individually) and notify 
the user when a potential duplicate had been found. The user will then be able to see details about each image, 
as well as the images themselves, and choose which one to remove, if either.

## Features ##
* When a potential match has been found...
    * The program displays information about each potential duplicate, including the name of each image, the image
    paths, the image dimensions, the amount of storage space used by each image, and the file extensions.
    * The program shows where differences between the two images are, which are both displayed in the "Pixel Differences"
    and "Subtracted Differences" tabs on the right.
      * In the Pixel Differences tab, pixels that are 100% identical are black, while all other pixels are white.
      * In the Subtracted Differences tab, what is seen is a result of subtracting the colors from each image. This means
      that black pixels are more similar to each other, while other colors represent differences between the images.
    * The program shows how similar the two images are in a percentage form.

* Three different options to choose from when deciding how many images to check...
    * Two images: Choose two individual images and compare them.
    * One folder: Choose a folder with the images you would like to check, and all possible combinations of
    images in that folder will be compared.
    * Two folders: Choose two different folders containing images, and each image in the first folder will be
    compared with all images in the second.

* The program can compare images of different sizes with each other, but only as long as they both have the same
height/width ratio.

* There is an option to notify the user when a potential match is found with a beeping noise, and another option to
periodically repeat this notification sound. This can be helpful when the user wishes to have other tabs open while 
the program is running, or if the user is AFK and wishes to be notified when they return.

* When choosing to compare images in folders, there is an option to include all images in subfolders as well.

* If the user is unable to finish going through a folder (or folders) with the amount of time they have, they can
stop the program and continue where they left off later. Inside the progress bar is a fraction showing your current
progress for that folder (or folders), with the current progress on the left and total progress on the right. (This
progress can also be seen if the user has the "Display general information..." checkbox checked in the settings page).
If the number of possible combinations is above 1,000, the program will ask for a starting position before it begins 
comparing. All you have to do is remember where you stopped and enter that number to continue.
   * Note: If the contents of the folder(s) are changed by adding/removing images, the order the images are compared 
   in will also change. If the user then skips to where they last ended, the program may miss image combinations that 
   have not been tested for similarity yet.

## Limitations ##
* When choosing to delete a file, the program cannot send the file directly to the Recycle Bin. Any files deleted
using this program will skip the Recycle Bin and be deleted *permenantly!*

* The program can only compare JPEG and PNG images. You must convert images of any other image format into one of these 
types before comparing them.

* The program cannot match an image with a cropped, rotated, stretched, or otherwise highly edited version of itself.
The program will most-likely think these are two unique images and will not mark them as potential duplicates.

* If you are working with a lot of images that have the same or similar background color, the program may come up 
with a lot of false positives and display images that are not supposed to be paired. This is because the majority of 
the image has the same color in it, which results in a higher percentage of similarity. The best solution to this 
is to increase the percent similarity threshold, which should reduce the number of false positives.

* The user cannot set the percent similarity threshold to 100% if they want the program to compare images of
different sizes. The reason for this is that the program must resize these images (not the originals, just a copy
of them) so that they have the same height and width before it can compare them pixel-by-pixel. Unfortunately, the
program cannot scale these images without distorting the image slightly. Most of the time these distortions are
too small to for the human eye to notice, but they do reduce the percentage of similarity between the images below 100%.

* The image in the Pixel Differences tab on the right does not accurately show pixel differences when the program
is comparing two images of different sizes. This is because of the previously mentioned resizing issue, where 
resizing the image slightly changes the copy the program is comparing. This is enough to make the pixels between 
the two images unique, resulting in the mostly-white image the user sees. If this happens, just use the image 
generated on the Subtracted Differences tab instead, since it is less effected by this issue.

## Requirements ##
**Requires the latest version of Java.**
