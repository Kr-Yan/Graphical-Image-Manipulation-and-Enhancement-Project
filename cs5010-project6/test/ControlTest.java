import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

/**
 * A class includes all Test cases for controller.
 */
public class ControlTest {
  @Test
  public void testReadPPM() throws Exception {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load testing.ppm loadingTest");
    ControlInt controller = new Control(in, out);
    StringBuilder log = new StringBuilder();
    String[][] subjects1 = {{"D", "P", "T"}, {"T", "M"}, {"S", "E"}};
    controller.controlScript(new MockModel(log, subjects1));
    assertEquals("Input: testing.ppm", log.toString());
    assertEquals("[[D, P, T], [T, M], [S, E]]", out.toString());
  }

  @Test
  public void testSave() throws Exception {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load testing.ppm loadingTest save images/testing.ppm test");
    StringBuilder log = new StringBuilder();
    String[][] subjects1 = {{"D", "P", "T"}, {"T", "M"}, {"S", "E"}};
    ControlInt controller = new Control(in, out);
    controller.controlScript(new MockModel(log, subjects1));
    assertEquals("Input: testing.ppm", log.toString());
    assertEquals("[[D, P, T], [T, M], [S, E]]", out.toString());
  }

  @Test
  public void testFlipPPMVert() throws Exception {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("vertical-flip test test");
    Control controller = new Control(in, out);
    StringBuilder log = new StringBuilder();
    String[][] subjects1 = {{"D", "P"}, {"T", "M"}, {"S", "E"}};
    controller.controlScript(new MockModel(log, subjects1));
    assertEquals("[[D, P], [T, M], [S, E]]", log.toString());
    assertEquals("[[P, D], [M, T], [E, S]]", out.toString());
  }

  @Test
  public void testFlipPPMHorizontal() throws Exception {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("horizontal-flip koala-vertical koala-vertical-horizontal");
    ControlInt controller = new Control(in, out);
    StringBuilder log = new StringBuilder();
    String[][] subjects1 = {{"D", "P"}, {"T", "M"}, {"S", "E"}};
    controller.controlScript(new MockModel(log, subjects1));
    assertEquals("[[D, P], [T, M], [S, E]]", log.toString());
    assertEquals("[[S, E], [T, M], [D, P]]", out.toString());
  }

  @Test
  public void testRedScale() throws Exception {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("greyscale red-component koala koala-greyscale");
    ControlInt controller = new Control(in, out);
    StringBuilder log = new StringBuilder();
    String[][] subjects1 = {{"D", "P"}, {"T", "M"}, {"S", "E"}};
    controller.controlScript(new MockModel(log, subjects1));

    assertEquals("[[D, P], [T, M], [S, E]]", log.toString());
    assertEquals("[[r, E], [r, M], [r, P]]",
            out.toString());
  }

  @Test
  public void testGreenScale() throws Exception {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("greyscale green-component koala koala-greyscale");
    ControlInt controller = new Control(in, out);
    StringBuilder log = new StringBuilder();
    String[][] subjects1 = {{"D", "P"}, {"T", "M"}, {"S", "E"}};
    controller.controlScript(new MockModel(log, subjects1));
    assertEquals("[[D, P], [T, M], [S, E]]", log.toString());
    assertEquals("[[g, E], [g, M], [g, P]]", out.toString());

  }


  @Test
  public void testBlueScale() throws Exception {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("greyscale blue-component koala koala-greyscale");
    ControlInt controller = new Control(in, out);
    StringBuilder log = new StringBuilder();
    String[][] subjects1 = {{"D", "P"}, {"T", "M"}, {"S", "E"}};
    controller.controlScript(new MockModel(log, subjects1));
    assertEquals("[[D, P], [T, M], [S, E]]", log.toString());
    assertEquals("[[b, E], [b, M], [b, P]]",
            out.toString());

  }

  @Test
  public void testValueScale() throws Exception {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("greyscale value-component koala koala-greyscale");
    ControlInt controller = new Control(in, out);
    StringBuilder log = new StringBuilder();
    String[][] subjects1 = {{"D", "P"}, {"T", "M"}, {"S", "E"}};
    controller.controlScript(new MockModel(log, subjects1));
    assertEquals("[[D, P], [T, M], [S, E]]", log.toString());
    assertEquals("[[value, E], [value, M], [value, P]]", out.toString());
  }

  @Test
  public void testLumaScale() throws Exception {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("greyscale luma-component koala koala-greyscale");
    ControlInt controller = new Control(in, out);
    StringBuilder log = new StringBuilder();
    String[][] subjects1 = {{"D", "P"}, {"T", "M"}, {"S", "E"}};
    controller.controlScript(new MockModel(log, subjects1));
    assertEquals("[[D, P], [T, M], [S, E]]", log.toString());
    assertEquals("[[luma, E], [luma, M], [luma, P]]",
            out.toString());

  }

  @Test
  public void testIntensityScale() throws Exception {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("greyscale intensity-component koala koala-greyscale");
    ControlInt controller = new Control(in, out);
    StringBuilder log = new StringBuilder();
    String[][] subjects1 = {{"D", "P"}, {"T", "M"}, {"S", "E"}};
    controller.controlScript(new MockModel(log, subjects1));
    assertEquals("[[D, P], [T, M], [S, E]]", log.toString());
    assertEquals("[[intensity, E], [intensity, M], [intensity, P]]", out.toString());

  }

  @Test
  public void testLightenDarken() throws Exception {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("brighten 100 koala-red koala-red");
    ControlInt controller = new Control(in, out);
    StringBuilder log = new StringBuilder();
    String[][] subjects1 = {{"D", "P"}, {"T", "M"}, {"S", "E"}};
    controller.controlScript(new MockModel(log, subjects1));
    assertEquals("[[D, P], [T, M], [S, E]]", log.toString());
    assertEquals("[[DarkenLighten, E], [DarkenLighten, M], [DarkenLighten, P]]",
            out.toString());

  }

  @Test
  public void testSplitComp() throws Exception {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("rgb-split koala koala-red koala-green koala-blue");
    ControlInt controller = new Control(in, out);
    StringBuilder log = new StringBuilder();
    String[][] subjects1 = {{"D", "P"}, {"T", "M"}, {"S", "E"}};
    controller.controlScript(new MockModel(log, subjects1));
    String exp = "[[[r, E], [r, M], [r, P]], [[g, E], [g, M], [g, P]], [[b, E], [b, M], [b, P]]]";
    assertEquals("[[D, P], [T, M], [S, E]][[D, P], [T, M], [S, E]][[D, P], [T, M], [S, E]]"
            , log.toString());
    assertEquals(exp, out.toString());
  }


  @Test
  public void testCombineColorScale() throws Exception {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("rgb-combine koala-red-tint koala-red koala-green koala-blue");
    ControlInt controller = new Control(in, out);
    StringBuilder log = new StringBuilder();
    String[][] subjects1 = {{"D", "P"}, {"T", "M"}, {"S", "E"}};
    controller.controlScript(new MockModel(log, subjects1));
    String e = "[[D, P], [T, M], [S, E]][[D, P], [T, M], [S, E]][[D, P], [T, M], "
            + "[S, E]][[r, E], [r, M], [r, P]][[g, E], [g, M], [g, P]][[b, E], [b, M], [b, P]]";
    assertEquals(e, log.toString());
    assertEquals("[[cP, cP], [cP, cP], [cP, cP]]", out.toString());
  }

  @Test
  public void testLoadImageFileIntoArr() throws Exception {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load-other testingImage.jpg loaded");
    ControlInt controller = new Control(in, out);
    StringBuilder log = new StringBuilder();
    String[][] subjects1 = {{"D", "P"}, {"T", "M"}, {"S", "E"}};
    controller.controlScript(new MockModel(log, subjects1));
    assertEquals("Input: testingImage.jpg", log.toString());
    assertEquals("[[D, P], [T, M], [S, E]]", out.toString());
  }

  @Test
  public void testSaveFileImBuf() throws Exception {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("save-file loaded-brighter-grey-hor jpg output");
    ControlInt controller = new Control(in, out);
    StringBuilder log = new StringBuilder();
    String[][] subjects1 = {{"D", "P"}, {"T", "M"}, {"S", "E"}};
    controller.controlScript(new MockModel(log, subjects1));
    assertEquals("Input: output", log.toString());
  }

  @Test
  public void testBlur() throws Exception {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("blur loaded-brighter-grey-hor blurred");
    ControlInt controller = new Control(in, out);
    StringBuilder log = new StringBuilder();
    String[][] subjects1 = {{"D", "P"}, {"T", "M"}, {"S", "E"}};
    controller.controlScript(new MockModel(log, subjects1));
    assertEquals("[[D, P], [T, M], [S, E]]", log.toString());
    assertEquals("[[Blur, Blur], [Blur, Blur], [Blur, Blur]]", out.toString());
  }

  @Test
  public void testSharpen() throws Exception {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("sharpen blurredThree sharpenOne");
    ControlInt controller = new Control(in, out);
    StringBuilder log = new StringBuilder();
    String[][] subjects1 = {{"D", "P"}, {"T", "M"}, {"S", "E"}};
    controller.controlScript(new MockModel(log, subjects1));
    assertEquals("[[D, P], [T, M], [S, E]]", log.toString());
    assertEquals("[[Sharpen, Sharpen], [Sharpen, Sharpen], [Sharpen, Sharpen]]",
            out.toString());
  }

  @Test
  public void testColorGrade() throws Exception {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("color-grade test sepia sepiatest");
    ControlInt controller = new Control(in, out);
    StringBuilder log = new StringBuilder();
    String[][] subjects1 = {{"D", "P"}, {"T", "M"}, {"S", "E"}};
    controller.controlScript(new MockModel(log, subjects1));
    assertEquals("Input: sepia", log.toString());
    assertEquals("[[D, P], [T, M], [S, E]]", out.toString());
  }

  @Test
  public void testDithering() throws Exception {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("dithering ditheringone ditheringOne");
    ControlInt controller = new Control(in, out);
    StringBuilder log = new StringBuilder();
    String[][] subjects1 = {{"D", "P"}, {"T", "M"}, {"S", "E"}};
    controller.controlScript(new MockModel(log, subjects1));
    assertEquals("[[D, P], [T, M], [S, E]]", log.toString());
    assertEquals("[[dithering, dithering], [dithering, dithering], [dithering, dithering]]"
            , out.toString());
  }


}

