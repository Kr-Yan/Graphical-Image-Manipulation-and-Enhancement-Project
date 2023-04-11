# Text-based commandsd IME

## program should support loading, manipulating and saving images using simple text-based commands as following:

### load image-path image-name: Load an image from the specified path and refer it to henceforth in the program by the given image name.
example: load-other testingImage.jpg loaded

### save image-path image-name: Save the image with the given name to the specified path which should include the name of the file.
example: save-file loaded-brighter-grey-hor jpg output

### greyscale component image-name dest-image-name: Create a greyscale image with the red-component of the image with the given name, and refer to it henceforth in the program by the given destination name. Similar commands for green, blue, value, luma, intensity components should be supported.
example: greyscale value-component loaded-brighter loaded-brighter-grey

### horizontal-flip image-name dest-image-name: Flip an image horizontally to create a new image, referred to henceforth by the given destination name.
example: horizontal-flip loaded-brighter-grey loaded-brighter-grey-hor

### vertical-flip image-name dest-image-name: Flip an image vertically to create a new image, referred to henceforth by the given destination name.
example: vertical-flip loaded-brighter-grey loaded-brighter-grey-vert

### brighten increment image-name dest-image-name: brighten the image by the given increment to create a new image, referred to henceforth by the given destination name. The increment may be positive (brightening) or negative (darkening).
example: brighten 10 loaded loaded-brighter

### rgb-split image-name dest-image-name-red dest-image-name-green dest-image-name-blue: split the given image into three greyscale images containing its red, green and blue components respectively.
example: rgb-split loaded loadred loadgreen loadblue

### rgb-combine image-name red-image green-image blue-image: Combine the three greyscale images into a single image that gets its red, green and blue components from the three images respectively.
example: rgb-combine loaded loadred loadgreen loadblue

run script-file: Load and run the script commands in the specified file.

### blur image-name dest-image-name: blur an image.
example: blur loaded-brighter-grey-hor blurred

### sharpen image-name dest-image-name: sharpen an image.
example: sharpen loaded sharpenOne

### color-grade sepia dest-image-name: color grade sepia in an image.
example: color-grade loaded sepia sepiaImage

### color-grade greyscale dest-image-name: color grade grayscale in an image.
example: color-grade loaded greyscale greyscaleImage

### dithering image-name dest-image-name: dithering an image.
example: dithering loaded ditheringImage


