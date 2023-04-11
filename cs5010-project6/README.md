# cs5010-project4

## description:

in this project, we are building a MVC(Model-View-Controller) design pattern for a image
manipulation and enhancement application(IME).

##

The image used was created by Kyle Domingos, a compressed version of artwork I had
created in a seperate class. It is "protected" under CC0, which means no rights are reserved and you
are free
to do with this image as you like.

### packages classes

The application has four mian packages: Control, ImageUtil, GUItest and mockModel.
Control is the Controller calss that receive inputs and store the outputs.
ImageUtil is the model class that contains all the image manipulation and enhance methods.
GUItest is the view class that contains the implementation for GUI.
mockModel is a similar model as ImageUtil and it helps Controller testing.

### interfaces

The application has two interfaces: ControlInt and modelInterterface.
ControlInt is implemented by Control class.
modelInterface is implemented by ImageUtil and mockModel.

### test classes

The application has two test classes: controlTest and modelTest.
controlTest tests the controller using the mockModel created.
modelTest tests the methods implements in the model.

## Using the file loader with a script

In order to run a script, simply type in run followed by the name of the script and hit enter.
For example, to run the provided script.txt, start the program and enter "run script.txt" into
the console and then hit enter.

## Usage

Load an image from an ASCII PPM file (see below).

Create images that visualize individual R,G,B components of an image.

Create images that visualize the value, intensity or luma of an image as defined above.

Flip an image horizontally or vertically.

Brighten or darken an image.

Split a single image into 3 images representing each of the three channels.

Combine three greyscale image into a single color image whose R,G,B values come from the three images.

Filtering methods that can sharpen or blur an image.

Color transformation image into greyscale or sepia tone.

Dithering an image.

Save an image to an ASCII PPM file (see below).

Allow a user to interact with your program to use these operations, using text-based scripting (see below).


