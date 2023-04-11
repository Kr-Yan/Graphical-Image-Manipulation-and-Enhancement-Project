import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This interface represents all the operations offered by a model.
 */
public interface ModelInterface {

  //notes
  //put the pixel data inside model.

  /**
   * Get the hashmap.
   *
   * @return the hashmap.
   */
  HashMap<String, BufferedImage> getImages();

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param newFile  the new name of the file.
   * @param filename the path of the file.
   * @return A 2D Array of strings that represents the given PPM file.
   */
  String[][] readPPM(String newFile, String filename);

  /**
   * Converts a 2D array of strings into a ppm file, saving it with the given filename.
   *
   * @param width    the width of the file to be saved.
   * @param height   the height of the file to be saved.
   * @param max      the max value of the file to be saved (generally 255).
   * @param filename the filename of the file to be saved.
   * @param values   A 2D array of strings that has the data of a ppm file inside.
   */
  void savePPM(int width, int height, int max, String filename, String[][] values);


  /**
   * Flips the given 2D array Vertically by reading pixel by pixel and writing to a new 2D array.
   *
   * @param newFile  the new name of the file.
   * @param picArray The 2D array to be modified.
   * @return the modified 2D array.
   */
  String[][] flipPPMVert(String newFile, String[][] picArray);


  /**
   * Flips the given 2D array horizontally by reading pixel by pixel and writing to a new 2D array.
   *
   * @param newFile  the new name of the file.
   * @param picArray The 2D array to be modified.
   * @return the modified 2D array.
   */
  String[][] flipPPMHor(String newFile, String[][] picArray);


  /**
   * Converts the image into a greyscale image based off of the red component in a 2D array.
   *
   * @param newFile  the new name of the file.
   * @param picArray the 2D array to be modified.
   * @return the modified 2D array.
   */
  String[][] redScale(String newFile, String[][] picArray);

  /**
   * Converts the image into a greyscale image based off of the green component in a 2D array.
   *
   * @param newFile  the new name of the file.
   * @param picArray the 2D array to be modified.
   * @return the modified 2D array.
   */
  String[][] greenScale(String newFile, String[][] picArray);

  /**
   * Converts the image into a greyscale image based off of the blue component in a 2D array.
   *
   * @param newFile  the new name of the file.
   * @param picArray the 2D array to be modified.
   * @return the modified 2D array.
   */
  String[][] blueScale(String newFile, String[][] picArray);

  /**
   * Converts the image into a greyscale image based off of the maximum rgb component in a 2D array.
   *
   * @param newFile  the new name of the file.
   * @param picArray the 2D array to be modified.
   * @return the modified 2D array.
   */
  String[][] valueScale(String newFile, String[][] picArray);

  /**
   * Converts the image into a greyscale image based off of the luma in a 2D array.
   * Luma = 0.2126r + 0.7152g + 0.0722b.
   *
   * @param newFile  the new name of the file.
   * @param picArray the 2D array to be modified.
   * @return the modified 2D array.
   */
  String[][] lumaScale(String newFile, String[][] picArray);

  /**
   * Converts the image into a greyscale image based off of the average of the rgb values.
   *
   * @param newFile  the new name of the file.
   * @param picArray the 2D array to be modified.
   * @return the modified 2D array.
   */
  String[][] intensityScale(String newFile, String[][] picArray);

  /**
   * Brightens (or darkens) a 2D array representing a picture by reading and changing each
   * component. If the pixel has a component at 255, it stays at 255 and if the pixel is at 0,
   * it stays at zero.
   *
   * @param newFile  the new name of the file.
   * @param picArray The 2D array to be modified.
   * @param degree   The integer value of change. Can be positive or negative.
   * @return The modified 2D array.
   */
  String[][] brightenDarkenImage(String newFile, String[][] picArray, int degree);

  /**
   * Visualize a given 2D array into three different parts, the red-greyscale, green-greyscale, and
   * blue-greyscale components, then puts them in an array list and sends it back to the controller.
   *
   * @param newFile  the new name of the file.
   * @param picArray The 2D array to be split.
   * @return An array list containing all three components.
   */
  String[][] visualizeComponent(String newFile, char component, String[][] picArray);

  /**
   * Splits a given 2D array into three different parts, the red-greyscale, green-greyscale, and
   * blue-greyscale components, then puts them in an array list and sends it back to the controller.
   *
   * @param newFile1 the new name of the file.
   * @param newFile2 the new name of the file.
   * @param newFile3 the new name of the file.
   * @param picArray The 2D array to be split.
   * @return An array list containing all three components.
   */
  ArrayList<String[][]> splitComponent(String newFile1, String newFile2,
                                       String newFile3, String[][] picArray);

  /**
   * Combines three 2D arrays into one 2D array object by taking the red, green and blue components
   * from each respective array. Returns back the combined 2D array.
   *
   * @param newFile the new name of the file.
   * @param red     The 2D array representing the greyscaled  red pixles.
   * @param green   The 2D array representing the greyscaled  green pixles.
   * @param blue    The 2D array representing the greyscaled blue pixles.
   * @return The 2D array with the red, green and blue values combined.
   */
  String[][] combineColorScale(String newFile, String[][] red, String[][] green, String[][] blue);

  /**
   * load Image files in different formats.
   *
   * @param newName  the new name of the file.
   * @param fileName the filename.
   * @return the loaded image 2D array.
   */
  String[][] loadImageFileIntoArr(String newName, String fileName);

  /**
   * Save the file.
   *
   * @param loadedPicArray loaded 2D array.
   * @param fileType       type of file.
   * @param fileName       the file name.
   */
  void saveFileImBuf(String[][] loadedPicArray, String fileType, String fileName);

  /**
   * blur an image.
   *
   * @param fileName       the file name.
   * @param loadedPicArray a loaded array.
   * @return a 2D array.
   */
  String[][] blur(String fileName, String[][] loadedPicArray);

  /**
   * sharpen an image.
   *
   * @param fileName       the file name.
   * @param loadedPicArray a loaded array.
   * @return a 2D array.
   */
  String[][] sharpen(String fileName, String[][] loadedPicArray);

  /**
   * make a color grade operations on an image.
   *
   * @param fileName       a file name.
   * @param loadedPicArray a loaded array.
   * @param fileType       a file type.
   * @return the 2D array after color grade operation.
   */
  String[][] colorGrade(String fileName, String[][] loadedPicArray, String fileType);

  /**
   * dithering an image.
   *
   * @param fileName       a file name.
   * @param loadedPicArray a loaded array.
   * @return a 2D array after dithering operation.
   */
  String[][] dithering(String fileName, String[][] loadedPicArray);

}



