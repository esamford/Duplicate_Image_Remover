# Duplicate Image Remover
Designed to search folders for duplicate images and notify the user when a potential duplicate has been found. 
The user will then be able to see details about each image, as well as the images themselves, and choose which 
one to remove, if either.

# Features
Can compare images of different sizes with each other.

Displays information about each image, including the name of the image, the image path, the image
dimensions, the amount of storage space used by the image, and the file extension.

Shows where differences between the two matching images are found, both by highlighting pixels 
that are not exactly the same and by subtracting the images from each other. This can be seen in the "Pixel
Differences" and "Subtracted Differences" tabs on the right.

Displays how similar the two images are in a percentage format.

Has the option to make a noise to notify the user when a potential match is found, and another option to
periodically repeat this notification noise if the user is AFK and wishes to hear it when they return.

# Limitations
The program cannot match an image with a cropped, rotated, or otherwise highly distorted version of itself.
If, and only if, the images look the similar to a human, the program will notify the user of a potential duplicate.

If you are working with a lot of images that have the same background color, the program may come up with
a lot of false positives and display images that are not supposed to be paired. This is because the 
majority of the image has the same color in it, which means a higher percentage of similarity. The best
solution to this is to set the percent similarity threshold to 95%, which should reduce the number of false
positives.

The user cannot set the percent similarity threshold to 100% if they want the program to compare images of
different sizes. The reason for this is that the program must resize these images (not the originals, just 
a copy of them) so that they have the same height and width before it can compare them. The program cannot
scale these images perfectly without distorting the image slightly. These distortions are too small to notice,
but they do reduce the percentage of similarity between the images so that they are always below 100%, which
is why they do not get shown to the user.

# Requirements
...
