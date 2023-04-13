import org.junit.Before;
import org.junit.Test;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

/**
 * A class includes all Test cases for ImageUtil.
 */
public class ModelTest {
  ImageUtil model;
  String[][] returnArr;
  private String filename;
  private String[][] values;

  @Before
  public void setUp() {
    model = new ImageUtil();
    try {
      returnArr = model.readPPM("testt", "test.ppm");
    } catch (Exception ignored) {
    }
    filename = "test.ppm";
  }


  @Test
  public void testReadPPM_FileNotFound() {
    assertThrows(IllegalArgumentException.class, () -> {
      model.readPPM("testRead", "nonexistent_file.ppm");
    });
  }

  @Test
  public void testReadPPM_InvalidPPMFile() throws FileNotFoundException {
    assertThrows(IllegalArgumentException.class, () -> {
      model.readPPM("testRead1", "Koala.pgn");
    });
  }

  @Test
  public void testSavePPM() {
    String[][] values = {
            {"255,0,0", "0,255,0"},
            {"0,0,255", "255,255,255"}
    };
    int width = values.length;
    int height = values[0].length;
    int max = 255;
    model.savePPM(width, height, max, filename, values);

    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      // Check the file header
      assertEquals("P3", br.readLine());
      assertEquals(width + " " + height, br.readLine());
      assertEquals(String.valueOf(max), br.readLine());

      // Check the pixel values
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          String[] expected = values[j][i].split(",", 3);
          assertEquals(expected[0], br.readLine());
          assertEquals(expected[1], br.readLine());
          assertEquals(expected[2], br.readLine());
        }
      }
    } catch (IOException e) {
      fail("An error occurred while reading the file: " + e.getMessage());
    }
  }

  @Test
  public void testFlipVert() throws IOException {
    String[][] picArray = {
            {"255,0,0", "0,255,0"},
            {"0,0,255", "255,255,255"}
    };

    // Flip the image using the method
    String[][] result = model.flipPPMVert("testVert", picArray);

    // Assert that the dimensions of the result match the input
    assertEquals(picArray.length, result.length);

    // Assert that the values in the result match the expected output
    for (int i = 0; i < picArray[0].length; i++) {
      for (int j = 0; j < picArray.length; j++) {
        assertEquals(picArray[j][i], result[j][picArray[0].length - 1 - i]);
      }
    }
  }

  @Test
  public void testFlipHor() throws IOException {
    String[][] picArray = {
            {"255,0,0", "0,255,0"},
            {"0,0,255", "255,255,255"}
    };

    // Flip the image using the method
    String[][] result = model.flipPPMHor("testHor", picArray);

    // Assert that the dimensions of the result match the input
    assertEquals(picArray.length, result.length);
    assertEquals(picArray[0].length, result[0].length);

    // Assert that the values in the result match the expected output
    for (int i = 0; i < picArray[0].length; i++) {
      for (int j = 0; j < picArray.length; j++) {
        assertEquals(picArray[i][j], result[picArray.length - 1 - i][j]);
      }
    }
  }

  @Test
  public void testredScale() throws IOException {
    String[][] picArray = {
            {"255,0,0", "0,255,0"},
            {"0,0,255", "255,255,255"}
    };

    // Flip the image using the method
    String[][] result = model.redScale("testRed1", picArray);

    assertEquals(picArray.length, result.length);
    assertEquals(picArray[0].length, result[0].length);

    assertEquals("255,255,255", result[0][0]);
    assertEquals("0,0,0", result[0][1]);
    assertEquals("0,0,0", result[1][0]);
    assertEquals("255,255,255", result[1][1]);
  }

  @Test
  public void testgreenScale() throws IOException {
    String[][] picArray = {
            {"255,0,0", "0,255,0"},
            {"0,0,255", "255,255,255"}
    };

    // Flip the image using the method
    String[][] result = model.greenScale("testGreen", picArray);

    assertEquals(picArray.length, result.length);
    assertEquals(picArray[0].length, result[0].length);

    assertEquals("0,0,0", result[0][0]);
    assertEquals("255,255,255", result[0][1]);
    assertEquals("0,0,0", result[1][0]);
    assertEquals("255,255,255", result[1][1]);
  }

  @Test
  public void testblueScale() throws IOException {
    String[][] picArray = {
            {"255,0,0", "0,255,0"},
            {"0,0,255", "255,255,255"}
    };

    // Flip the image using the method
    String[][] result = model.blueScale("testBlue", picArray);

    assertEquals(picArray.length, result.length);
    assertEquals(picArray[0].length, result[0].length);

    assertEquals("0,0,0", result[0][0]);
    assertEquals("0,0,0", result[0][1]);
    assertEquals("255,255,255", result[1][0]);
    assertEquals("255,255,255", result[1][1]);
  }

  @Test
  public void testvalueScale() throws IOException {
    String[][] picArray = {
            {"255,0,0", "0,255,0"},
            {"0,0,255", "255,255,255"}
    };

    // Flip the image using the method
    String[][] result = model.valueScale("testValue", picArray);

    assertEquals(picArray.length, result.length);
    assertEquals(picArray[0].length, result[0].length);

    assertEquals("255,255,255", result[0][0]);
    assertEquals("255,255,255", result[0][1]);
    assertEquals("255,255,255", result[1][0]);
    assertEquals("255,255,255", result[1][1]);
  }

  @Test
  public void testlumaScale() throws IOException {
    String[][] picArray = {
            {"255,0,0", "0,255,0"},
            {"0,0,255", "255,255,255"}
    };

    // Flip the image using the method
    String[][] result = model.lumaScale("testLuma", picArray);

    assertEquals(picArray.length, result.length);
    assertEquals(picArray[0].length, result[0].length);

    assertEquals("54,54,54", result[0][0]);
    assertEquals("182,182,182", result[0][1]);
    assertEquals("18,18,18", result[1][0]);
    assertEquals("254,254,254", result[1][1]);
  }

  @Test
  public void testintensityScale() throws IOException {
    String[][] picArray = {
            {"255,0,0", "0,255,0"},
            {"0,0,255", "255,255,255"}
    };

    // Flip the image using the method
    String[][] result = model.intensityScale("testIntensity", picArray);

    assertEquals(picArray.length, result.length);
    assertEquals(picArray[0].length, result[0].length);

    assertEquals("85,85,85", result[0][0]);
    assertEquals("85,85,85", result[0][1]);
    assertEquals("85,85,85", result[1][0]);
    assertEquals("255,255,255", result[1][1]);
  }

  @Test
  public void testBrighten() throws IOException {
    String[][] picArray = {
            {"255,0,0", "0,255,0"},
            {"0,0,255", "255,255,255"}
    };


    // Flip the image using the method
    String[][] result = model.brightenDarkenImage("testBrighten", picArray, 10);

    assertEquals(picArray.length, result.length);
    assertEquals(picArray[0].length, result[0].length);

    assertEquals("255,10,10", result[0][0]);
    assertEquals("10,255,10", result[0][1]);
    assertEquals("10,10,255", result[1][0]);
    assertEquals("255,255,255", result[1][1]);
  }

  @Test
  public void testDarken() throws IOException {
    String[][] picArray = {
            {"255,0,0", "0,255,0"},
            {"0,0,255", "255,255,255"}
    };


    // Flip the image using the method
    String[][] result = model.brightenDarkenImage("testDarken", picArray, -100);

    assertEquals(picArray.length, result.length);
    assertEquals(picArray[0].length, result[0].length);

    assertEquals("155,0,0", result[0][0]);
    assertEquals("0,155,0", result[0][1]);
    assertEquals("0,0,155", result[1][0]);
    assertEquals("155,155,155", result[1][1]);
  }

  @Test
  public void testRedVisualizeComponent() throws Exception {
    String[][] picArray = {
            {"255,0,0", "0,255,0"},
            {"0,0,255", "255,255,255"}
    };

    // Flip the image using the method
    String[][] result = model.visualizeComponent("testRed", 'r', picArray);

    assertEquals(picArray.length, result.length);
    assertEquals(picArray[0].length, result[0].length);

    assertEquals("255,255,255", result[0][0]);
    assertEquals("0,0,0", result[0][1]);
    assertEquals("0,0,0", result[1][0]);
    assertEquals("255,255,255", result[1][1]);
  }

  @Test
  public void testGreenVisualizeComponent() throws Exception {
    String[][] picArray = {
            {"255,0,0", "0,255,0"},
            {"0,0,255", "255,255,255"}
    };

    // Flip the image using the method
    String[][] result = model.visualizeComponent("testGreen", 'g', picArray);

    assertEquals(picArray.length, result.length);
    assertEquals(picArray[0].length, result[0].length);

    assertEquals("0,0,0", result[0][0]);
    assertEquals("255,255,255", result[0][1]);
    assertEquals("0,0,0", result[1][0]);
    assertEquals("255,255,255", result[1][1]);
  }

  @Test
  public void testBlueVisualizeComponent() throws Exception {
    String[][] picArray = {
            {"255,0,0", "0,255,0"},
            {"0,0,255", "255,255,255"}
    };

    // Flip the image using the method
    String[][] result = model.visualizeComponent("testBlue", 'b', picArray);

    assertEquals(picArray.length, result.length);
    assertEquals(picArray[0].length, result[0].length);

    assertEquals("0,0,0", result[0][0]);
    assertEquals("0,0,0", result[0][1]);
    assertEquals("255,255,255", result[1][0]);
    assertEquals("255,255,255", result[1][1]);
  }

  @Test
  public void testSplitComp() throws Exception {
    String[][] picArray = {
            {"255,0,0", "0,255,0"},
            {"0,0,255", "255,255,255"}
    };

    // Flip the image using the method
    ArrayList<String[][]> result = model.splitComponent("testRed1", "testGreen1",
            "testBlue1", picArray);

    //for red-tint image
    assertEquals(picArray.length, result.get(0).length);
    assertEquals(picArray[0].length, result.get(0)[0].length);

    assertEquals("255,255,255", result.get(0)[0][0]);
    assertEquals("0,0,0", result.get(0)[0][1]);
    assertEquals("0,0,0", result.get(0)[1][0]);
    assertEquals("255,255,255", result.get(0)[1][1]);

    //for green tint
    assertEquals(picArray.length, result.get(1).length);
    assertEquals(picArray[0].length, result.get(1)[0].length);

    assertEquals("0,0,0", result.get(1)[0][0]);
    assertEquals("255,255,255", result.get(1)[0][1]);
    assertEquals("0,0,0", result.get(1)[1][0]);
    assertEquals("255,255,255", result.get(1)[1][1]);

    //for blue tint
    assertEquals(picArray.length, result.get(2).length);
    assertEquals(picArray[0].length, result.get(2)[0].length);

    assertEquals("0,0,0", result.get(2)[0][0]);
    assertEquals("0,0,0", result.get(2)[0][1]);
    assertEquals("255,255,255", result.get(2)[1][0]);
    assertEquals("255,255,255", result.get(2)[1][1]);
  }

  @Test
  public void testCombineColorScale() throws Exception {

    String[][] redArray = {
            {"255,255,255", "0,0,0"},
            {"0,0,0", "255,255,255"}
    };

    String[][] greenArray = {
            {"0,0,0", "255,255,255"},
            {"0,0,", "255,255,255"}
    };

    String[][] blueArray = {
            {"0,0,0", "0,0,0"},
            {"255,255,255", "255,255,255"}
    };
    String[][] result = model.combineColorScale("testCombine", redArray, greenArray, blueArray);

    assertEquals(redArray.length, result.length);
    assertEquals(redArray[0].length, result[0].length);

    assertEquals("255,0,0", result[0][0]);
    assertEquals("0,255,0", result[0][1]);
    assertEquals("0,0,255", result[1][0]);
    assertEquals("255,255,255", result[1][1]);
  }

  @Test
  public void testLoadImageFileIntoArr() {
    // Create a test image with 2x2 pixels
    BufferedImage image = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB);
    image.setRGB(0, 0, 0xFF0000); // red
    image.setRGB(1, 0, 0x00FF00); // green
    image.setRGB(0, 1, 0x0000FF); // blue
    image.setRGB(1, 1, 0xFFFFFF); // white

    try {
      // Save the test image to a file
      File imageFile = File.createTempFile("test-image-", ".png");
      ImageIO.write(image, "png", imageFile);

      // Load the image file into the 2D string array
      String[][] arr = model.loadImageFileIntoArr(imageFile.getName(), imageFile.getAbsolutePath());

      // Assert that the dimensions of the array match the dimensions of the image
      assertEquals(image.getWidth(), arr.length);
      assertEquals(image.getHeight(), arr[0].length);

      // Assert that the array contains the correct color values in string format
      assertEquals("255,0,0", arr[0][0]);
      assertEquals("0,255,0", arr[1][0]);
      assertEquals("0,0,255", arr[0][1]);
      assertEquals("255,255,255", arr[1][1]);

      // Delete the test image file
      imageFile.delete();
    } catch (IOException e) {
      fail("Failed to create or delete test image file: " + e.getMessage());
    }
  }

  @Test
  public void testBlurNotNull() {
    String[][] loadedImg = {{"255,0,0"}, {"0,255,0"}, {"0,0,255"}};
    String[][] blurredImg = model.blur("test1", loadedImg);
    assertNotNull(blurredImg);
  }

  @Test
  public void testBlurDimensions() {
    String[][] loadedImg = {{"255,0,0"}, {"0,255,0"}, {"0,0,255"}};
    String[][] blurredImg = model.blur("test2", loadedImg);
    assertEquals(loadedImg.length, blurredImg.length);
    assertEquals(loadedImg[0].length, blurredImg[0].length);
  }

  @Test
  public void testSharpenNotNull() {
    String[][] loadedImg = {{"255,0,0"}, {"0,255,0"}, {"0,0,255"}};
    String[][] sharpenImg = model.sharpen("test3", loadedImg);
    assertNotNull(sharpenImg);
  }

  @Test
  public void testSharpenDimensions() {
    String[][] loadedImg = {{"255,0,0"}, {"0,255,0"}, {"0,0,255"}};
    String[][] sharpenImg = model.sharpen("test4", loadedImg);
    assertEquals(loadedImg.length, sharpenImg.length);
    assertEquals(loadedImg[0].length, sharpenImg[0].length);
  }

  @Test
  public void testColorGrade() {
    String[][] input = {
            {"255,0,0", "0,255,0"},
            {"0,0,255", "255,255,255"}
    };
    String[][] expectedOutputSepia = {
            {"100,89,69", "196,175,136"},
            {"48,43,33", "255,255,239"}
    };
    String[][] expectedOutputGreyscale = {
            {"90,90,90", "177,177,177"},
            {"43,43,43", "254,254,254"}
    };

    // test sepia conversion
    String[][] actualOutputSepia = model.colorGrade("test5", input, "sepia");
    assertArrayEquals(expectedOutputSepia, actualOutputSepia);

    // test greyscale conversion
    String[][] actualOutputGreyscale = model.colorGrade("test6", input, "greyscale");
    assertArrayEquals(expectedOutputGreyscale, actualOutputGreyscale);

    // test non-existent type
    try {
      model.colorGrade("test7", input, "invalidType");
      fail("Expected RuntimeException to be thrown");
    } catch (RuntimeException e) {
      assertEquals("non-existant type", e.getMessage());
    }
  }

  @Test
  public void testDithering() {
    String[][] input = {{"100,100,100", "150,150,150"}, {"200,200,200", "250,250,250"}};
    String[][] expectedOutput = {{"0,0,0", "255,255,255"}, {"255,255,255", "255,255,255"}};
    String[][] actualOutput = model.dithering("test7", input);
    assertArrayEquals(expectedOutput, actualOutput);
  }


}


