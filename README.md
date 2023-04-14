# assignment5 GRIME: Graphical Image Manipulation and Enhancement (Part 3)

In this project, we are designing a view for your image processing application, featuring a graphical user interface. This will allow a user
to interactively load, process and save images. use Java Swing to build the graphical user interface. 
The file saved an image as a PNG/PPM/JPG.

## Histogram
The histogram of the visible image is visible as a line chart on the screen at all times. If the image is manipulated, the histogram 
automatically refresh. The histogram show the red, green, blue and intensity components.

## Grphic View
### Graphic User Interface
The GUI shows the image that is currently being worked on. The image may be bigger than the area allocated to it in our graphical 
user interface. In this case, the user should be able to scroll the image. Any changes to the current image as a result of the image 
operations should be visible in the GUI.
The user interface expose following features of the program:
flips, component visualization, greyscale, blurring, sharpening and sepia


## Model, View, Controller
Model: ImageUtil
View: View
Controller: Control, ControlView
the View and Controller components work together to handle user input and display information to the user. The View presents data to the user, 
while the Controller handles user input and updates the Model and View components accordingly.
