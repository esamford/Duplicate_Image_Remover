# Duplicate Image Remover #
This program is designed to search folders for duplicate images and notify the user when a potential duplicate 
has been found. The user will then be able to see details about each image, as well as the images themselves, 
and choose which one to remove, if either.

## Features ##
* Displays information about each potential duplicate, including the name of each image, the image paths, the 
image dimensions, the amount of storage space used by each image, and the file extensions.

* Shows where differences between the two matching images are found, both by highlighting pixels that are not 
exactly the same and by subtracting the images from each other. These can be seen in the "Pixel Differences" 
and "Subtracted Differences" tabs on the right.

* The program can compare images of different sizes with each other, but only as long as they both have the same
height/width ratio.

* Displays how similar the two images are in a percentage.

* Has an option to notify the user when a potential match is found with a beeping noise, and another option to
periodically repeat this notification sound if the user is AFK and wishes to hear it when they return. This can
be helpful when the user wishes to have other tabs open while the program is running.

## Limitations ##
* The program cannot match an image with a cropped, rotated, or otherwise distorted version of itself.
If, and only if, the images look similar to a human, the program will notify the user of a potential duplicate.

* If you are working with a lot of images that have the same background color, the program may come up with
a lot of false positives and display images that are not supposed to be paired. This is because the 
majority of the image has the same color in it, which results in a higher percentage of similarity. The best
solution to this is to increase the percent similarity threshold, which should reduce the number of false
positives.

* The user cannot set the percent similarity threshold to 100% if they want the program to compare images of
different sizes. The reason for this is that the program must resize these images (not the originals, just 
a copy of them) so that they have the same height and width before it can compare them pixel-by-pixel.
Unfortunately, the program cannot scale these images without distorting the image slightly. Sometimes these
distortions are too small to notice, but they do reduce the percentage of similarity between the images so that
they are always below 100%, which is why they do not get shown to the user at a 100% similarity threshold.

* The image in the Pixel Differences tab on the right does not accurately show pixel differences when the program
is comparing two images of different sizes. This is because of the previously mentioned resizing issue, where 
resizing the image slightly changes the copy the program is comparing. This is enough to make the pixels between 
the two images different from each other, resulting in the mostly-white image the user sees. If this happens,
just use the image generated on the Subtracted Differences tab instead.

## Requirements ##
...
