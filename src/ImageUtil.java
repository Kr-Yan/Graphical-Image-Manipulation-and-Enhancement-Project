import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 */
public class ImageUtil implements ModelInterface {
  protected HashMap<String, BufferedImage> images;

  public ImageUtil() {
    images = new HashMap<String, BufferedImage>();
  }

  public HashMap<String, BufferedImage> getImages() {
    return images;
  }

  float[] blurMatrix = {1 / 16f, 1 / 8f, 1 / 16f, 1 / 8f, 1 / 4f, 1 / 8f, 1 / 16f, 1 / 8f, 1 / 16f};
  float[][] sepiaMatrix = {
          {0.393f, 0.769f, 0.189f},
          {0.349f, 0.686f, 0.168f},
          {0.272f, 0.534f, 0.131f}

  };

  float[][] greyscaleMatrix = {
          {0.2126f, 0.7152f, 0.0722f},
          {0.2126f, 0.7152f, 0.0722f},
          {0.2126f, 0.7152f, 0.0722f}

  };


  float[] sharpenMatrix = {
          -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f,
          -1 / 8f, 1 / 4f, 1 / 4f, 1 / 4f, -1 / 8f,
          -1 / 8f, 1 / 4f, 1, 1 / 4f, -1 / 8f,
          -1 / 8f, 1 / 4f, 1 / 4f, 1 / 4f, -1 / 8f,
          -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f
  };


  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   * @return A 2D Array of strings that represents the given PPM file.
   */
  public String[][] readPPM(String newFile, String filename) {
    System.out.println("readPPM was called");
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Could not find file" + filename);
      // System.out.println("File "+filename+ " not found!");
      // return new String[0][];
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    String[][] picArray = new String[width][height];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {

        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        String tempTest = Integer.toString(r) + "," + Integer.toString(g) + "," +
                Integer.toString(b);
        picArray[j][i] = tempTest;

      }
    }
    BufferedImage readImg = arrayConvertToBuff(picArray);
    images.put(newFile, readImg);
    return picArray;
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
  public void savePPM(int width, int height, int max, String filename, String[][] values) {
    try {
      String header = "P3";
      FileWriter writer = new FileWriter(filename);
      writer.write(header + System.lineSeparator());
      writer.write(width + " " + height + System.lineSeparator());
      writer.write(max + System.lineSeparator());
      String val = "";
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          val = values[j][i];
          String[] arrOfStr = val.split(",", 3);
          for (String a : arrOfStr) {
            writer.write(a + System.lineSeparator());
          }
        }
      }
      writer.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

  }


  /**
   * Flips the given 2D array Vertically by reading pixel by pixel and writing to a new 2D array.
   *
   * @param picArray The 2D array to be modified.
   * @return the modified 2D array.
   */
  public String[][] flipPPMVert(String newFile, String[][] picArray) {
    int width = picArray.length;
    int height = picArray[0].length;
    String[][] returnArray = new String[width][height];
    String val = "";

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        val = picArray[j][i];
        returnArray[j][height - 1 - i] = val;
      }
    }
    BufferedImage flipVImg = arrayConvertToBuff(returnArray);
    images.put(newFile, flipVImg);
    return returnArray;
  }

  /**
   * Flips the given 2D array horizontally by reading pixel by pixel and writing to a new 2D array.
   *
   * @param picArray The 2D array to be modified.
   * @return the modified 2D array.
   */
  public String[][] flipPPMHor(String newFile, String[][] picArray) {
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
    BufferedImage flipHImg = arrayConvertToBuff(returnArray);
    images.put(newFile, flipHImg);
    return returnArray;
  }

  /**
   * Converts the image into a greyscale image based off of the red component in a 2D array.
   *
   * @param picArray the 2D array to be modified.
   * @return the modified 2D array.
   */
  public String[][] redScale(String newFile, String[][] picArray) {
    int width = picArray.length;
    int height = picArray[0].length;
    String[][] returnArray = new String[width][height];
    String val;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        val = picArray[j][i];
        String[] result = val.split(",");
        int r = Integer.parseInt(result[0]);
        returnArray[j][i] = r + "," + r + "," + r;
      }
    }
    BufferedImage redImg = arrayConvertToBuff(returnArray);
    images.put(newFile, redImg);
    return returnArray;
  }

  /**
   * Converts the image into a greyscale image based off of the green component in a 2D array.
   *
   * @param picArray the 2D array to be modified.
   * @return the modified 2D array.
   */
  public String[][] greenScale(String newFile, String[][] picArray) {
    int width = picArray.length;
    int height = picArray[0].length;
    String[][] returnArray = new String[width][height];
    String val;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        val = picArray[j][i];
        String[] result = val.split(",");
        int g = Integer.parseInt(result[1]);
        returnArray[j][i] = g + "," + g + "," + g;
      }
    }
    BufferedImage greenImg = arrayConvertToBuff(returnArray);
    images.put(newFile, greenImg);
    return returnArray;
  }

  /**
   * Converts the image into a greyscale image based off of the blue component in a 2D array.
   *
   * @param picArray the 2D array to be modified.
   * @return the modified 2D array.
   */
  public String[][] blueScale(String newFile, String[][] picArray) {
    int width = picArray.length;
    int height = picArray[0].length;
    String[][] returnArray = new String[width][height];
    String val;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        val = picArray[j][i];
        String[] result = val.split(",");
        int b = Integer.parseInt(result[2]);
        returnArray[j][i] = b + "," + b + "," + b;
      }
    }
    BufferedImage blueImg = arrayConvertToBuff(returnArray);
    images.put(newFile, blueImg);
    return returnArray;
  }

  /**
   * Converts the image into a greyscale image based off of the maximum rgb component in a 2D array.
   *
   * @param picArray the 2D array to be modified.
   * @return the modified 2D array.
   */
  public String[][] valueScale(String newFile, String[][] picArray) {
    int width = picArray.length;
    int height = picArray[0].length;
    String[][] returnArray = new String[width][height];
    String val;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        val = picArray[j][i];
        String[] result = val.split(",");
        int r = Integer.parseInt(result[0]);
        int g = Integer.parseInt(result[1]);
        int b = Integer.parseInt(result[2]);
        //this is hilariously dumb and a terrible way to do this but I don't wanna come up
        //with an elegant solution right now
        Integer[] intArray = new Integer[3];
        intArray[0] = r;
        intArray[1] = g;
        intArray[2] = b;
        int max = Collections.max(Arrays.asList(intArray));
        returnArray[j][i] = max + "," + max + "," + max;
      }
    }
    BufferedImage valueImg = arrayConvertToBuff(returnArray);
    images.put(newFile, valueImg);
    return returnArray;
  }

  /**
   * Converts the image into a greyscale image based off of the luma in a 2D array.
   * Luma = 0.2126r + 0.7152g + 0.0722b.
   *
   * @param picArray the 2D array to be modified.
   * @return the modified 2D array.
   */
  public String[][] lumaScale(String newFile, String[][] picArray) {


    //dont forget! luma = 0.2126r + 0.7152g + 0.0722b
    int width = picArray.length;
    int height = picArray[0].length;
    String[][] returnArray = new String[width][height];
    String val;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        val = picArray[j][i];
        String[] result = val.split(",");
        int r = Integer.parseInt(result[0]);
        int g = Integer.parseInt(result[1]);
        int b = Integer.parseInt(result[2]);
        r = (int) Math.round(0.2126 * r);
        g = (int) Math.round(0.7152 * g);
        b = (int) Math.round(0.0722 * b);
        int luma = r + g + b;
        returnArray[j][i] = luma + "," + luma + "," + luma;
      }
    }
    BufferedImage lumaImg = arrayConvertToBuff(returnArray);
    images.put(newFile, lumaImg);
    return returnArray;
  }

  /**
   * Converts the image into a greyscale image based off of the average of the rgb values.
   *
   * @param picArray the 2D array to be modified.
   * @return the modified 2D array.
   */
  public String[][] intensityScale(String newFile, String[][] picArray) {
    int width = picArray.length;
    int height = picArray[0].length;
    String[][] returnArray = new String[width][height];
    String val;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        val = picArray[j][i];
        String[] result = val.split(",");
        int r = Integer.parseInt(result[0]);
        int g = Integer.parseInt(result[1]);
        int b = Integer.parseInt(result[2]);
        int total = (r / 3) + (g / 3) + (b / 3);
        returnArray[j][i] = total + "," + total + "," + total;
      }
    }
    BufferedImage intsImg = arrayConvertToBuff(returnArray);
    images.put(newFile, intsImg);
    return returnArray;
  }

  /**
   * Brightens (or darkens) a 2D array representing a picture by reading and changing each
   * component. If the pixel has a component at 255, it stays at 255 and if the pixel is at 0,
   * it stays at zero.
   *
   * @param picArray The 2D array to be modified.
   * @param degree   The integer value of change. Can be positive or negative.
   * @return The modified 2D array.
   */
  public String[][] brightenDarkenImage(String newFile, String[][] picArray, int degree) {
    int width = picArray.length;
    int height = picArray[0].length;
    String[][] returnArray = new String[width][height];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        String[] aComp = picArray[j][i].split(",");
        int r = Integer.parseInt(aComp[0]) + degree;
        int g = Integer.parseInt(aComp[1]) + degree;
        int b = Integer.parseInt(aComp[2]) + degree;
        if (r > 255) {
          r = 255;
        }
        if (g > 255) {
          g = 255;
        }
        if (b > 255) {
          b = 255;
        }
        if (r < 0) {
          r = 0;
        }
        if (g < 0) {
          g = 0;
        }
        if (b < 0) {
          b = 0;
        }
        returnArray[j][i] = r + "," + g + "," + b;
      }
    }
    BufferedImage brightenDarkImg = arrayConvertToBuff(returnArray);
    images.put(newFile, brightenDarkImg);
    return returnArray;
  }

  /**
   * Visualize a given 2D array into three different parts, the red-greyscale, green-greyscale, and
   * blue-greyscale components, then puts them in an array list and sends it back to the controller.
   *
   * @param picArray The 2D array to be split.
   * @return An array list containing all three components.
   */
  public String[][] visualizeComponent(String newFile, char component, String[][] picArray) {
    int width = picArray.length;
    int height = picArray[0].length;
    String[][] returnArray = new String[width][height];
    for (int i = 0; i < picArray.length; i++) {
      for (int j = 0; j < picArray[i].length; j++) {
        String[] aComp = picArray[j][i].split(",");
        if (component == 'r') {
          aComp[1] = aComp[0];
          aComp[2] = aComp[0];
        } else if (component == 'g') {
          aComp[0] = aComp[1];
          aComp[2] = aComp[1];
        } else if (component == 'b') {
          aComp[0] = aComp[2];
          aComp[1] = aComp[2];

        } else {
          throw new IllegalArgumentException("the component is invalid!");
        }
        String tempTest = aComp[0] + "," + aComp[1] + "," + aComp[2];
        returnArray[j][i] = tempTest;

      }
    }
    BufferedImage aImg = arrayConvertToBuff(returnArray);
    images.put(newFile, aImg);
    return returnArray;
  }

  /**
   * Splits a given 2D array into three different parts, the red-greyscale, green-greyscale, and
   * blue-greyscale components, then puts them in an array list and sends it back to the controler.
   *
   * @param picArray The 2D array to be split.
   * @return An array list containing all three components.
   */

  public ArrayList<String[][]> splitComponent(String newFile1, String newFile2,
                                              String newFile3, String[][] picArray) {

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
   * @param red   The 2D array representing the greyscaled  red pixles.
   * @param green The 2D array representing the greyscaled  green pixles.
   * @param blue  The 2D array representing the greyscaled blue pixles.
   * @return The 2D array with the red, green and blue values combined.
   */
  public String[][] combineColorScale(String newFile,
                                      String[][] red, String[][] green, String[][] blue) {
    int width = red.length;
    int height = red[0].length;
    String[][] returnArray = new String[width][height];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        String[] redComp = red[j][i].split(",");
        String[] greenComp = green[j][i].split(",");
        String[] blueComp = blue[j][i].split(",");
        String tempTest = redComp[0] + "," + greenComp[1] + "," + blueComp[2];
        returnArray[j][i] = tempTest;
      }
    }
    BufferedImage aImg = arrayConvertToBuff(returnArray);
    images.put(newFile, aImg);
    return returnArray;

  }

  /**
   * load image file.
   *
   * @param newFile  the new name of the file.
   * @param filename the filename.
   * @return return loaded array.
   */
  public String[][] loadImageFileIntoArr(String newFile, String filename) {
    BufferedImage img;
    System.out.println("readImageFile was called");
    FileInputStream file;

    try {
      file = new FileInputStream(filename);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Could not find file" + filename);
    }
    try {
      img = ImageIO.read(file);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    int height = img.getHeight();
    int width = img.getWidth();

    String[][] returnArray = new String[width][height];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {

        int pixel = img.getRGB(j, i);
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;

        String tempTest = red + "," + green + "," + blue;
        returnArray[j][i] = tempTest;
      }
    }
    images.put(newFile, img);
    System.out.println("readImageFile should have activated successfully");
    return returnArray;

  }

  @Override
  public void saveFileImBuf(String[][] picArray, String fileType, String fileName) {
    String val;
    int width = picArray.length;
    int height = picArray[0].length;
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        val = picArray[j][i];
        String[] arrOfStr = val.split(",", 3);
        int r = Integer.parseInt(arrOfStr[0]);
        int g = Integer.parseInt(arrOfStr[1]);
        int b = Integer.parseInt(arrOfStr[2]);
        Color colorAtPixel = new Color(r, g, b);
        int rgb = colorAtPixel.getRGB();
        img.setRGB(j, i, rgb);
      }
    }
    try {
      ImageIO.write(img, fileType, new File(fileName + "." + fileType));
    } catch (IOException ex) {
      //catch an exception
    }

  }

  /**
   * blur an image.
   *
   * @param newFile   the file name.
   * @param loadedImg a loaded array.
   * @return a blurred image array.
   */
  public String[][] blur(String newFile, String[][] loadedImg) {
    BufferedImage blur = arrayConvertToBuff(loadedImg);
    BufferedImageOp op = new ConvolveOp(new Kernel(3, 3, blurMatrix),
            ConvolveOp.EDGE_ZERO_FILL, null);
    BufferedImage i = op.filter(blur, null);
    String[][] strings = buffConvertToArr(i);
    images.put(newFile, i);
    return strings;
  }

  @Override
  public String[][] sharpen(String newFile, String[][] loadedImg) {
    BufferedImage blur = arrayConvertToBuff(loadedImg);
    BufferedImageOp op = new ConvolveOp(new Kernel(5, 5, sharpenMatrix),
            ConvolveOp.EDGE_ZERO_FILL, null);
    BufferedImage i = op.filter(blur, null);
    String[][] strings = buffConvertToArr(i);
    images.put(newFile, i);
    return strings;
  }

  @Override
  public String[][] colorGrade(String newFile, String[][] loadedPicArray, String type) {

    int width = loadedPicArray.length;
    int height = loadedPicArray[0].length;
    String val;

    float[][] opArray;
    if (type.equals("sepia")) {
      opArray = sepiaMatrix;
    } else if (type.equals("greyscale")) {
      opArray = greyscaleMatrix;
    } else {
      throw new RuntimeException("non-existant type");
    }

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        val = loadedPicArray[j][i];
        String[] arrOfStr = val.split(",", 3);
        int r = Integer.parseInt(arrOfStr[0]);
        int g = Integer.parseInt(arrOfStr[1]);
        int b = Integer.parseInt(arrOfStr[2]);
        int rPrime = Math.round(opArray[0][0] * r + opArray[0][1] * g + opArray[0][2] * b);
        int gPrime = Math.round(opArray[1][0] * r + opArray[1][1] * g + opArray[1][2] * b);
        int bPrime = Math.round(opArray[2][0] * r + opArray[2][1] * g + opArray[2][2] * b);

        if (rPrime > 255) {
          rPrime = 255;
        }
        if (gPrime > 255) {
          gPrime = 255;
        }
        if (bPrime > 255) {
          bPrime = 255;
        }
        if (rPrime < 0) {
          rPrime = 0;
        }
        if (gPrime < 0) {
          gPrime = 0;
        }
        if (bPrime < 0) {
          bPrime = 0;
        }

        loadedPicArray[j][i] = rPrime + "," + gPrime + "," + bPrime;
      }
    }
    BufferedImage colorg = arrayConvertToBuff(loadedPicArray);
    images.put(newFile, colorg);
    return loadedPicArray;
  }

  /**
   * dithering an image.
   *
   * @param newFile        a file name.
   * @param loadedPicArray a loaded array.
   * @return return a dithered array.
   */
  public String[][] dithering(String newFile, String[][] loadedPicArray) {
    int width = loadedPicArray.length;
    int height = loadedPicArray[0].length;
    String type = "greyscale";
    String[][] tempArray;
    String val;
    int newColor;
    int error;
    int testingHold;
    tempArray = colorGrade("tempArray", loadedPicArray, type);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        val = tempArray[j][i];
        String[] arrOfStr = val.split(",", 3);
        int r = Integer.parseInt(arrOfStr[0]);
        if (r > 100) {
          newColor = 255;
        } else {
          newColor = 0;
        }
        //newColor = Math.round(r / 255);
        error = r - newColor;
        tempArray[j][i] = newColor + "," + newColor + "," + newColor;

        try {
          //(7/16 * error)
          testingHold = Math.round((7 / 16f * error)); //MAKE SURE ITS FLOAT FIRST AAAHHHH****
          val = tempArray[j + 1][i];
          arrOfStr = val.split(",", 3);
          r = Integer.parseInt(arrOfStr[0]);
          r = r + testingHold;
          if (r > 255) {
            r = 255;
          }
          if (r < 0) {
            r = 0;
          }
          tempArray[j + 1][i] = r + "," + r + "," + r;

          //(3/16 * error)
          testingHold = Math.round((3 / 16f * error));
          val = tempArray[j - 1][i + 1];
          arrOfStr = val.split(",", 3);
          r = Integer.parseInt(arrOfStr[0]);
          r = r + testingHold;
          if (r > 255) {
            r = 255;
          }
          if (r < 0) {
            r = 0;
          }
          tempArray[j - 1][i + 1] = r + "," + r + "," + r;

          //(5/16 * error)
          testingHold = Math.round((5 / 16f * error));
          val = tempArray[j + 1][i];
          arrOfStr = val.split(",", 3);
          r = Integer.parseInt(arrOfStr[0]);
          r = r + testingHold;

          if (r > 255) {
            r = 255;
          }
          if (r < 0) {
            r = 0;
          }
          tempArray[j + 1][i] = r + "," + r + "," + r;

          //(1/16 * error)
          testingHold = Math.round((1 / 16f * error));
          val = tempArray[j + 1][i + 1];
          arrOfStr = val.split(",", 3);
          r = Integer.parseInt(arrOfStr[0]);
          r = r + testingHold;
          if (r > 255) {
            r = 255;
          }
          if (r < 0) {
            r = 0;
          }
          tempArray[j + 1][i + 1] = r + "," + r + "," + r;

        } catch (IndexOutOfBoundsException e) {
          //throw new IllegalArgumentException("Could not find file" + filename);

        }
      }
    }
    BufferedImage dithering = arrayConvertToBuff(tempArray);
    images.put(newFile, dithering);
    return tempArray;
  }

  @Override
  //turn buffered image into 2d array
  public String[][] buffConvertToArr(BufferedImage img) {
    int height = img.getHeight();
    int width = img.getWidth();

    String[][] returnArray = new String[width][height];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {

        int pixel = img.getRGB(j, i);
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;

        String tempTest = red + "," + green + "," + blue;
        returnArray[j][i] = tempTest;
      }
    }
    return returnArray;
  }

  //HELPER METHODS
  //TURN 2D array into buffered image
  BufferedImage arrayConvertToBuff(String[][] imgArray) {

    String val;
    int width = imgArray.length;
    int height = imgArray[0].length;

    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        val = imgArray[j][i];
        String[] arrOfStr = val.split(",", 3);
        int r = Integer.parseInt(arrOfStr[0]);
        int g = Integer.parseInt(arrOfStr[1]);
        int b = Integer.parseInt(arrOfStr[2]);

        Color colorAtPixel = new Color(r, g, b);
        int rgb = colorAtPixel.getRGB();
        img.setRGB(j, i, rgb);
      }
    }
    return img;
  }
}


