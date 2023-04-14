import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This represents a controller class.
 */
public class Control extends ImageUtil implements ControlInt {
  static HashMap<String, String[][]> arrayStorage = new HashMap<>();
  //string to image?
  //if the model has everything but the data, just put data in the imageutil lol

  static String[][] loadedPicArray;
  static String[][] loadedPicArrayRed;
  static String[][] loadedPicArrayGreen;
  static String[][] loadedPicArrayBlue;

  final Readable in;

  final Appendable out;


  /**
   * This represents a constructor for the controller.
   */
  public Control(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }


  /**
   * This represents a method that gets input and send them to model and store the outputs.
   */
  public void controlScript(ModelInterface image) throws IOException {

    Scanner scanner = null;
    Scanner testing = new Scanner(System.in);
    if (image.getClass().equals(MockModel.class)) {
      testing = new Scanner(this.in);
    }
    String str = testing.nextLine();
    try {
      scanner = new Scanner(new File(str));
    } catch (FileNotFoundException e) {
      scanner = new Scanner(str);
      //e.printStackTrace();
    }
    StringBuilder builder = new StringBuilder();

    while (scanner.hasNextLine()) {
      String s = scanner.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());

        String[] commandArray = s.split(" ");
        String command = commandArray[0];

        String file;
        String newName;
        String filePath;
        String fileName;

        switch (command) {
          case "load":
            file = commandArray[1];
            System.out.println(file + " is value of file");
            newName = commandArray[2];
            loadedPicArray = image.readPPM(newName, file);
            System.out.println(newName + " The value of the newName var");
            if (image.getClass().equals(MockModel.class)) {
              this.out.append(Arrays.deepToString(loadedPicArray));
            }
            break;

          case "brighten":
            //brighten increment image-name dest-image-name
            //brighten 50 koala koala-brighter
            fileName = commandArray[2];
            System.out.println(fileName + " is value of file");
            int brightLevel = Integer.parseInt(commandArray[1]);
            System.out.println("" + brightLevel + " The value of the read brightness");
            loadedPicArray = image.brightenDarkenImage(fileName, loadedPicArray, brightLevel);
            newName = commandArray[3];
            System.out.println(newName + " The value of the newName var");
            if (image.getClass().equals(MockModel.class)) {
              this.out.append(Arrays.deepToString(loadedPicArray));
            }
            break;

          case "save":
            filePath = commandArray[1];
            fileName = commandArray[2];
            System.out.println(filePath + " is filepath of file");
            loadedPicArray = buffConvertToArr(image.getImages().get(filePath));
            int height = loadedPicArray[0].length;
            int width = loadedPicArray.length;
            //dont forget about changing this, you can't just assume 255
            int max = 255;
            //is this breaking saving?
            image.savePPM(width, height, max, fileName + ".ppm", loadedPicArray);
            if (image.getClass().equals(MockModel.class)) {
              this.out.append(Arrays.deepToString(loadedPicArray));
            }
            break;

          case "vertical-flip":
            fileName = commandArray[1];
            filePath = commandArray[2];
            loadedPicArray = image.flipPPMVert(fileName, loadedPicArray);
            if (image.getClass().equals(MockModel.class)) {
              this.out.append(Arrays.deepToString(loadedPicArray));
            }
            break;

          case "horizontal-flip":
            fileName = commandArray[1];
            filePath = commandArray[2];
            loadedPicArray = image.flipPPMHor(fileName, loadedPicArray);
            if (image.getClass().equals(MockModel.class)) {
              this.out.append(Arrays.deepToString(loadedPicArray));
            }
            break;

          case "greyscale":
            String scaleType = commandArray[1];
            fileName = commandArray[2];
            filePath = commandArray[3];
            if (scaleType.equals("red-component")) {
              loadedPicArray = image.redScale(fileName, loadedPicArray);
            } else if (scaleType.equals("green-component")) {
              loadedPicArray = image.greenScale(fileName, loadedPicArray);
            } else if (scaleType.equals("blue-component")) {
              loadedPicArray = image.blueScale(fileName, loadedPicArray);
            } else if (scaleType.equals("intensity-component")) {
              loadedPicArray = image.intensityScale(fileName, loadedPicArray);
            } else if (scaleType.equals("value-component")) {
              loadedPicArray = image.valueScale(fileName, loadedPicArray);
            } else {
              if (!scaleType.equals("luma-component")) {
                throw new IllegalArgumentException("Invalid operation " + command);
              }
              loadedPicArray = image.lumaScale(fileName, loadedPicArray);
            }
            if (image.getClass().equals(MockModel.class)) {
              this.out.append(Arrays.deepToString(loadedPicArray));
            }
            break;

          case "rgb-split":
            //rgb-split image-name dest-image-name-red dest-image-name-green dest-image-name-blue:
            // split the given image into three greyscale images containing its red, green and blue
            // components respectively.
            fileName = commandArray[1];
            String redName;
            String greenName;
            String blueName;
            ArrayList<String[][]> holding;
            redName = commandArray[2];
            greenName = commandArray[3];
            blueName = commandArray[4];
            holding = image.splitComponent(redName, greenName, blueName, loadedPicArray);
            if (image.getClass().equals(MockModel.class)) {
              this.out.append(Arrays.deepToString(holding.toArray()));
            }
            break;

          case "rgb-combine":
            //rgb-combine image-name red-image green-image blue-image: Combine the three
            // greyscale images into a single image that gets its red, green and blue components
            // from the three images respectively.
            fileName = commandArray[1];
            redName = commandArray[2];
            greenName = commandArray[3];
            blueName = commandArray[4];
            loadedPicArray = image.combineColorScale(fileName, loadedPicArrayRed,
                    loadedPicArrayGreen, loadedPicArrayBlue);
            if (image.getClass().equals(MockModel.class)) {
              this.out.append(Arrays.deepToString(loadedPicArray));
            }
            break;

          case "run":
          case "-file":
            //run script-file
            fileName = commandArray[1];
            try {
              scanner = new Scanner(new File(fileName));
            } catch (FileNotFoundException e) {
              e.printStackTrace();
            }
            break;


          case "load-other":
            fileName = commandArray[1];
            newName = commandArray[2];
            loadedPicArray = image.loadImageFileIntoArr(newName, fileName);
            if (image.getClass().equals(MockModel.class)) {
              this.out.append(Arrays.deepToString(loadedPicArray));
            }
            break;

          case "save-file":
            filePath = commandArray[1];
            String fileType = commandArray[2];
            fileName = commandArray[3];
            System.out.println(filePath + " is filepath of file");
            loadedPicArray = arrayStorage.get(filePath);
            image.saveFileImBuf(loadedPicArray, fileType, fileName);
            if (image.getClass().equals(MockModel.class)) {
              this.out.append(Arrays.deepToString(loadedPicArray));
            }
            break;

          case "blur":
            filePath = commandArray[1];
            fileName = commandArray[2];
            System.out.println(filePath + " is filepath of file");
            loadedPicArray = image.blur(fileName, loadedPicArray);
            if (image.getClass().equals(MockModel.class)) {
              this.out.append(Arrays.deepToString(loadedPicArray));
            }
            break;

          case "sharpen":
            filePath = commandArray[1];
            fileName = commandArray[2];
            System.out.println(filePath + " is filepath of file");
            loadedPicArray = image.sharpen(fileName, loadedPicArray);
            if (image.getClass().equals(MockModel.class)) {
              this.out.append(Arrays.deepToString(loadedPicArray));
            }
            break;

          case "color-grade":
            filePath = commandArray[1];
            fileType = commandArray[2];
            fileName = commandArray[3];
            System.out.println(filePath + " is filepath of file");
            loadedPicArray = image.colorGrade(fileName, loadedPicArray, fileType);
            if (image.getClass().equals(MockModel.class)) {
              this.out.append(Arrays.deepToString(loadedPicArray));
            }
            break;

          case "dithering":
            filePath = commandArray[1];
            fileName = commandArray[2];
            System.out.println(filePath + " is filepath of file");
            //fix later
            loadedPicArray = image.dithering(fileName, loadedPicArray);
            arrayStorage.put(fileName, loadedPicArray);
            if (image.getClass().equals(MockModel.class)) {
              this.out.append(Arrays.deepToString(loadedPicArray));
            }
            break;

          default:
            throw new IllegalArgumentException("Invalid operation " + command);
        }
      }
    }
    //do everything here
    System.out.println(builder);
    if (!image.getClass().equals(MockModel.class)) {
      controlScript(image);
    }
    scanner.close();
  }


}
