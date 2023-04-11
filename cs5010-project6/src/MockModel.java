import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * a mock class that implements the similar methods as ImageUtil.
 */
public class MockModel implements ModelInterface {
  private StringBuilder log;
  private final String[][] images;

  /**
   * This represents a constructor for the mock model.
   */
  MockModel(StringBuilder log, String[][] images) {
    this.log = log;
    this.images = images;
  }

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param newFile  the new name of the file.
   * @param filename the path of the file.
   * @return A 2D Array of strings that represents the given PPM file.
   */
  @Override
  public String[][] readPPM(String newFile, String filename) {
    log.append("Input: " + filename);
    return images;
  }

  /**
   * Converts a 2D array of strings into a ppm file, saving it with the given filename.
   *
   * @param width    the width of the file to be saved.
   * @param height   the height of the file to be saved.
   * @param max      the max value of the file to be saved (generally 255).
   * @param filename the filename of the file to be saved.
   * @param values   A 2D array of strings that has the data of a ppm file inside.
   */
  @Override
  public void savePPM(int width, int height, int max, String filename, String[][] values) {
    log.append("Input: " + filename);
  }

  /**
   * Flips the given 2D array Vertically by reading pixel by pixel and writing to a new 2D array.
   *
   * @param newFile  the new name of the file.
   * @param picArray The 2D array to be modified.
   * @return the modified 2D array.
   */
  @Override
  public String[][] flipPPMVert(String newFile, String[][] picArray) {
    picArray = images;
    log.append(Arrays.deepToString(picArray));
    int width = picArray.length;
    int height = picArray[0].length;
    String[][] returnArray = new String[width][height];
    String val = "";
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        val = picArray[j][i];
        //returnArray[height-1 - i][width-1 - j] = val;
        //returnArray[height-1 - i][j] = val;
        returnArray[j][height - 1 - i] = val;
      }
    }
    return returnArray;
  }

  /**
   * Flips the given 2D array horizontally by reading pixel by pixel and writing to a new 2D array.
   *
   * @param newFile  the new name of the file.
   * @param picArray The 2D array to be modified.
   * @return the modified 2D array.
   */
  @Override
  public String[][] flipPPMHor(String newFile, String[][] picArray) {
    picArray = images;
    log.append(Arrays.deepToString(picArray));
    int width = picArray.length;
    int height = picArray[0].length;
    String[][] returnArray = new String[width][height];
    int tempVal;
    String val = "";

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        val = picArray[j][i];
        returnArray[width - 1 - j][i] = val;
      }
    }

    return returnArray;
  }

  /**
   * Converts the array into an array that first column is 'red'.
   *
   * @param newFile  the new name of the file.
   * @param picArray the 2D array to be modified.
   * @return the modified 2D array.
   */
  @Override
  public String[][] redScale(String newFile, String[][] picArray) {
    picArray = images;
    log.append(Arrays.deepToString(picArray));
    int width = picArray.length;
    int height = picArray[0].length;
    String[][] returnArray = new String[width][height];
    String val;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        val = picArray[j][i];
        returnArray[width - 1 - j][i] = val;
        returnArray[j][0] = "r";
      }
    }
    return returnArray;
  }

  /**
   * Converts the array into an array that first column is 'green'.
   *
   * @param newFile  the new name of the file.
   * @param picArray the 2D array to be modified.
   * @return the modified 2D array.
   */
  @Override
  public String[][] greenScale(String newFile, String[][] picArray) {
    picArray = images;
    log.append(Arrays.deepToString(picArray));
    int width = picArray.length;
    int height = picArray[0].length;
    String[][] returnArray = new String[width][height];
    String val;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        val = picArray[j][i];
        returnArray[width - 1 - j][i] = val;
        returnArray[j][0] = "g";
      }
    }
    return returnArray;
  }

  /**
   * Converts the array into an array that first column is 'blue'.
   *
   * @param newFile  the new name of the file.
   * @param picArray the 2D array to be modified.
   * @return the modified 2D array.
   */
  @Override
  public String[][] blueScale(String newFile, String[][] picArray) {
    picArray = images;
    log.append(Arrays.deepToString(picArray));
    int width = picArray.length;
    int height = picArray[0].length;
    String[][] returnArray = new String[width][height];
    String val;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        val = picArray[j][i];
        returnArray[width - 1 - j][i] = val;
        returnArray[j][0] = "b";
      }
    }
    return returnArray;
  }

  /**
   * Converts the array into an array that first column is 'value'.
   *
   * @param newFile  the new name of the file.
   * @param picArray the 2D array to be modified.
   * @return the modified 2D array.
   */
  @Override
  public String[][] valueScale(String newFile, String[][] picArray) {
    picArray = images;
    log.append(Arrays.deepToString(picArray));
    int width = picArray.length;
    int height = picArray[0].length;
    String[][] returnArray = new String[width][height];
    String val;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        val = picArray[j][i];
        returnArray[width - 1 - j][i] = val;
        returnArray[j][0] = "value";
      }
    }
    return returnArray;
  }

  /**
   * Converts the array into an array that first column is 'luma'.
   *
   * @param newFile  the new name of the file.
   * @param picArray the 2D array to be modified.
   * @return the modified 2D array.
   */
  @Override
  public String[][] lumaScale(String newFile, String[][] picArray) {
    picArray = images;
    log.append(Arrays.deepToString(picArray));
    int width = picArray.length;
    int height = picArray[0].length;
    String[][] returnArray = new String[width][height];
    String val;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        val = picArray[j][i];
        returnArray[width - 1 - j][i] = val;
        returnArray[j][0] = "luma";
      }
    }
    return returnArray;
  }

  /**
   * Converts the array into an array that first column is 'intensity'.
   *
   * @param newFile  the new name of the file.
   * @param picArray the 2D array to be modified.
   * @return the modified 2D array.
   */
  @Override
  public String[][] intensityScale(String newFile, String[][] picArray) {
    picArray = images;
    log.append(Arrays.deepToString(picArray));
    int width = picArray.length;
    int height = picArray[0].length;
    String[][] returnArray = new String[width][height];
    String val;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        val = picArray[j][i];
        returnArray[width - 1 - j][i] = val;
        returnArray[j][0] = "intensity";
      }
    }
    return returnArray;
  }

  /**
   * Converts the array into an array that first column is 'DarkenLighten'.
   *
   * @param newFile  the new name of the file.
   * @param picArray the 2D array to be modified.
   * @param degree   the degree.
   * @return the modified 2D array.
   */
  @Override
  public String[][] brightenDarkenImage(String newFile, String[][] picArray, int degree) {
    picArray = images;
    log.append(Arrays.deepToString(picArray));
    int width = picArray.length;
    int height = picArray[0].length;
    String[][] returnArray = new String[width][height];
    String val;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        val = picArray[j][i];
        returnArray[width - 1 - j][i] = val;
        returnArray[j][0] = "DarkenLighten";
      }
    }
    return returnArray;
  }

  /**
   * Converts the array into an array that first column is 'DarkenLighten'.
   *
   * @param newFile  the new name of the file.
   * @param picArray the 2D array to be modified.
   * @return the modified 2D array.
   */
  @Override
  public String[][] visualizeComponent(String newFile, char component, String[][] picArray) {
    picArray = images;
    log.append(Arrays.deepToString(picArray));
    int width = picArray.length;
    int height = picArray[0].length;
    String[][] returnArray = new String[width][height];
    String val;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        val = picArray[j][i];
        returnArray[width - 1 - j][i] = val;
        if (component == 'r') {
          returnArray[j][0] = "r";
        } else if (component == 'g') {
          returnArray[j][0] = "g";
        } else if (component == 'b') {
          returnArray[j][0] = "b";

        } else {
          throw new IllegalArgumentException("the component is invalid!");
        }
      }
    }
    return returnArray;
  }

  /**
   * Visualize a given 2D array into three different parts, the red-greyscale, green-greyscale, and
   * blue-greyscale components, then puts them in an array list and sends it back to the controller.
   *
   * @param newFile1 the new name of the red file.
   * @param newFile2 the new name of the green file.
   * @param newFile3 the new name of the blue file.
   * @param picArray The 2D array to be split.
   * @return An array list containing all three components.
   */
  @Override
  public ArrayList<String[][]> splitComponent(String newFile1, String newFile2, String newFile3,
                                              String[][] picArray) {
    picArray = images;
    String[][] redPPM = redScale(newFile1, picArray);
    String[][] greenPPM = greenScale(newFile2, picArray);
    String[][] bluePPM = blueScale(newFile3, picArray);
    ArrayList<String[][]> container = new ArrayList<>();
    container.add(redPPM);
    container.add(greenPPM);
    container.add(bluePPM);
    return container;
  }

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
  @Override
  public String[][] combineColorScale(String newFile, String[][] red, String[][] green,
                                      String[][] blue) {
    red = redScale(newFile, images);
    green = greenScale(newFile, images);
    blue = blueScale(newFile, images);
    log.append(Arrays.deepToString(red));
    log.append(Arrays.deepToString(green));
    log.append(Arrays.deepToString(blue));
    int width = red.length;
    int height = red[0].length;
    String[][] returnArray = new String[width][height];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        String tempTest = "cP";
        returnArray[j][i] = tempTest;
      }
    }
    return returnArray;

  }

  @Override
  public HashMap<String, BufferedImage> getImages() {
    return null;
  }

  @Override
  public String[][] loadImageFileIntoArr(String newFile, String fileName) {
    log.append("Input: " + fileName);
    return images;
  }

  @Override
  public void saveFileImBuf(String[][] loadedPicArray, String fileType, String fileName) {
    log.append("Input: " + fileName);
  }

  @Override
  public String[][] blur(String newFile, String[][] loadedPicArray) {
    loadedPicArray = images;
    log.append(Arrays.deepToString(loadedPicArray));
    int width = loadedPicArray.length;
    int height = loadedPicArray[0].length;
    String[][] returnArray = new String[width][height];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        String tempTest = "Blur";
        returnArray[j][i] = tempTest;
      }
    }
    return returnArray;
  }

  @Override
  public String[][] sharpen(String newFile, String[][] loadedPicArray) {
    loadedPicArray = images;
    log.append(Arrays.deepToString(loadedPicArray));
    int width = loadedPicArray.length;
    int height = loadedPicArray[0].length;
    String[][] returnArray = new String[width][height];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        String tempTest = "Sharpen";
        returnArray[j][i] = tempTest;
      }
    }
    return returnArray;
  }

  @Override
  public String[][] colorGrade(String newFile, String[][] loadedPicArray, String type) {
    log.append("Input: " + type);
    return images;
  }

  @Override
  public String[][] dithering(String newFile, String[][] loadedPicArray) {
    loadedPicArray = images;
    log.append(Arrays.deepToString(loadedPicArray));
    int width = loadedPicArray.length;
    int height = loadedPicArray[0].length;
    String[][] returnArray = new String[width][height];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        String tempTest = "dithering";
        returnArray[j][i] = tempTest;
      }
    }
    return returnArray;
  }

}
