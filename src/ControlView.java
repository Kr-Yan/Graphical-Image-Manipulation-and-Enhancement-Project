import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * this is a controller for view.
 */
public class ControlView implements ControlInt, ActionListener {
  private ModelInterface model;

  int[] red = new int[256];
  int[] green = new int[256];
  int[] blue = new int[256];
  int[] intensity = new int[256];
  private IView view;
  BufferedImage temp;
  private String loadedPicArray[][];


  /**
   * This represents a constructor for the controller.
   */
  public ControlView(ModelInterface model, IView view) {
    this.model = model;
    this.view = view;
    view.setListener(this);
    view.display();
  }


  /**
   * Operations that receive input and send them to model and store the output.
   *
   * @param image an image
   */
  @Override
  public void controlScript(ModelInterface image) throws IOException {
    //not need
  }


  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */

  /*
  @Override
  public void actionPerformed(ActionEvent e) {
      switch (e.getActionCommand()) {
        case "File opened":
          String text = view.getFileName();
          System.out.println(text);
          model.readPPM("test", text);
          view.display();
          break;
      }
    } */
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Open file": {
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF & " +
                "PPM & PNG Images", "jpg", "gif", "ppm", "png");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog((Component) this.view);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          String path = "";
          path = f.getAbsolutePath();
          String[] aPath = path.split("\\\\");
          String fileName = aPath[aPath.length - 1];
          String extensionName = fileName.substring(fileName.length() - 3);
          if ("ppm".equals(extensionName)) {
            model.readPPM("test", path);
            loadedPicArray = model.readPPM("test", path);
            BufferedImage temp = model.getImages().get("test");
            System.out.println("This should be the BufferedImage " + temp);
            view.loadBufferedImage(temp);
            view.setLoad(path);
            //System.out.println(path);
            view.getLineChart(model.buildDataset(model.getArrays(loadedPicArray)));
            view.display();
          } else if ("jpg".equals(extensionName) || "png".equals(extensionName)) {
            model.loadImageFileIntoArr("test", path);
            loadedPicArray = model.loadImageFileIntoArr("test", path);
            BufferedImage temp = model.getImages().get("test");
            System.out.println("This should be the BufferedImage " + temp);
            view.loadBufferedImage(temp);
            view.setLoad(path);
            //System.out.println(path);
            view.getLineChart(model.buildDataset(model.getArrays(loadedPicArray)));
            view.display();
          } else {
            JOptionPane.showMessageDialog((Component) this.view, "Not a valid file type!",
                    "Error", JOptionPane.ERROR_MESSAGE);
          }
          System.out.println(path);
        }
        //return file
      }

      break;
      case "Save file": {
        ///  final JFileChooser fochooser = new JFileChooser(".");

/*
        fochooser.setFileFilter(new FileFilter(){ private final FileNameExtensionFilter filter =
        new FileNameExtensionFilter("Compressed files",
            "zip", "jar", "z", "gz", "tar", "bz2", "bz");
          @Override
    public boolean accept(File file) {
        return filter.accept(file);
    }

          @Override
          public String getDescription() {
            return ".ppm files";
          }


        });

        {
          @Override
          public boolean accept(File file)
          {
            return file.getName().toUpperCase().equals(".ppm");
          }

          @Override
          public String getDescription()
          {
            return ".ppm files";
          }
        });*/


        final JFileChooser fochooser = new JFileChooser(".");

        fochooser.setFileFilter(new FileFilter()
        {
          @Override
          public boolean accept(File file)
          {
            return file.getName().toUpperCase().equals(".ppm");
          }

          @Override
          public String getDescription()
          {
            return ".ppm";
          }
        });

        fochooser.addChoosableFileFilter(new FileFilter()
        {
          @Override
          public boolean accept(File file)
          {
            return file.getName().toUpperCase().equals(".jpg");
          }

          @Override
          public String getDescription()
          {
            return ".jpg";
          }
        });

        fochooser.addChoosableFileFilter(new FileFilter()
        {
          @Override
          public boolean accept(File file)
          {
            return file.getName().toUpperCase().equals(".png");
          }

          @Override
          public String getDescription()
          {
            return ".png";
          }
        });

        int restvalue = fochooser.showSaveDialog((Component) this.view);
        if (restvalue == JFileChooser.APPROVE_OPTION) {
          System.out.println("FILECHOOSER FILTER TOSTRING IS " + fochooser.getFileFilter().getDescription());
          String filetype = fochooser.getFileFilter().getDescription();
          File f = fochooser.getSelectedFile();
          view.getSaveJLabel().setText(f.getAbsolutePath());
          String[] aPath1 = view.getOutFileName().toString().split("\\\\");
          String fileName1 = aPath1[aPath1.length - 1];
          //fileName1 = fileName1 + filetype;
          //String lastThree = fileName1.substring(fileName1.length() - 3);
          System.out.println("filename 1 from save is " + fileName1 );
          if (filetype.equals(".jpg") || filetype.equals(".png") || filetype.equals(".gif")) {
            model.saveFileImBuf(loadedPicArray, filetype.substring(1),
                    fileName1);
          } else if (filetype.equals(".ppm")) {
            model.savePPM(loadedPicArray.length, loadedPicArray[0].length,
                    225, fileName1 + filetype, loadedPicArray);
          } else {
            JOptionPane.showMessageDialog((Component) this.view, "Invalid",
                    "Swing Tester", JOptionPane.ERROR_MESSAGE);
          }

        }
      }
      break;

      //Image Histograms:Not sure
//      case "Histogram": {
//        this.updateHistogramArray();
//      }
//      break;

      case "flip-vertical": {
        model.flipPPMVert("flipped-v", loadedPicArray);
        loadedPicArray = model.flipPPMVert("flipped-v", loadedPicArray);
        temp=model.getImages().get("flipped-v");
        view.loadBufferedImage(temp);
       // buildhist(temp);
        //view.setLoad(path);
        //System.out.println(path);
        view.getLineChart(model.buildDataset(model.getArrays(loadedPicArray)));
        view.display();
      }
      break;
      case "flip-horizontal": {
        model.flipPPMHor("flipped-h", loadedPicArray);
        loadedPicArray = model.flipPPMHor("flipped-h", loadedPicArray);
        temp=model.getImages().get("flipped-h");
        view.loadBufferedImage(temp);
        //view.setLoad(path);
        //System.out.println(path);
        view.getLineChart(model.buildDataset(model.getArrays(loadedPicArray)));
        view.display();
      }
      break;
      // the component should be an input bottom that store that input. Now I set it as 'r', but
      // need to change later.
      case "red-component": {
        model.visualizeComponent("componentVisual", 'r', loadedPicArray);
        loadedPicArray = model.visualizeComponent("componentVisual", 'r',
                loadedPicArray);
        temp=model.getImages().get("componentVisual");
        view.loadBufferedImage(temp);
        //view.setLoad(path);
        //System.out.println(path);
        view.getLineChart(model.buildDataset(model.getArrays(loadedPicArray)));
        view.display();
      }
      break;
      case "green-component": {
        model.visualizeComponent("componentVisual", 'g', loadedPicArray);
        loadedPicArray = model.visualizeComponent("componentVisual", 'g',
                loadedPicArray);
        temp=model.getImages().get("componentVisual");
        view.loadBufferedImage(temp);
        //view.setLoad(path);
        //System.out.println(path);
        view.getLineChart(model.buildDataset(model.getArrays(loadedPicArray)));
        view.display();
      }
      break;

      case "blue-component": {
        model.visualizeComponent("componentVisual", 'b', loadedPicArray);
        loadedPicArray = model.visualizeComponent("componentVisual", 'b',
                loadedPicArray);
        temp=model.getImages().get("componentVisual");
        view.loadBufferedImage(temp);
        //view.setLoad(path);
        //System.out.println(path);
        view.getLineChart(model.buildDataset(model.getArrays(loadedPicArray)));
        view.display();
      }
      break;

      case "greyscale": {
        model.colorGrade("greyscale", loadedPicArray, "greyscale");
        loadedPicArray = model.colorGrade("greyscale", loadedPicArray,
                "greyscale");
        temp=model.getImages().get("greyscale");
        view.loadBufferedImage(temp);
        //view.setLoad(path);
        //System.out.println(path);
        view.getLineChart(model.buildDataset(model.getArrays(loadedPicArray)));
        view.display();
      }
      break;
      case "blurring": {
        model.blur("blur", loadedPicArray);
        loadedPicArray = model.blur("blur", loadedPicArray);
        temp=model.getImages().get("blur");
        view.loadBufferedImage(temp);
        //view.setLoad(path);
        //System.out.println(path);
        view.getLineChart(model.buildDataset(model.getArrays(loadedPicArray)));
        view.display();
      }
      break;
      case "sharpening": {
        model.sharpen("sharpen", loadedPicArray);
        loadedPicArray = model.sharpen("sharpen", loadedPicArray);
        temp=model.getImages().get("sharpen");
        view.loadBufferedImage(temp);
        //view.setLoad(path);
        //System.out.println(path);
        view.getLineChart(model.buildDataset(model.getArrays(loadedPicArray)));
        view.display();
      }
      break;
      case "sepia": {
        model.colorGrade("sepia", loadedPicArray, "sepia");
        loadedPicArray = model.colorGrade("sepia", loadedPicArray, "sepia");
        temp=model.getImages().get("sepia");
        view.loadBufferedImage(temp);
        //view.setLoad(path);
        //System.out.println(path);
        view.getLineChart(model.buildDataset(model.getArrays(loadedPicArray)));
        view.display();
      }
      break;
    }
  }

//  /**
//   * a method for histogram.
//   */
//  public void updateHistogramArray() {
//    BufferedImage current = view.getCurrentImage();
//    String[][] values = model.buffConvertToArr(current);
//
//    model.redScale("redval", values);
//    model.greenScale("greenval", values);
//    model.blueScale("blueval", values);
//    model.intensityScale("intensity", values);
//
//    BufferedImage redvalues = model.getImages().get("redval");
//    BufferedImage greenvalues = model.getImages().get("greenval");
//    BufferedImage bluevalues = model.getImages().get("blueval");
//    BufferedImage intensityvalues = model.getImages().get("intensity");
//
//    for (int i = 0; i < redvalues.getHeight(); i++) {
//      for (int j = 0; j < redvalues.getWidth(); j++) {
//        int pixel = redvalues.getRGB(j, i);
//        int redVal = (pixel >> 16) & 0xff;
//        red[redVal] = red[redVal] + 1;
//      }
//    }
//
//    for (int i = 0; i < greenvalues.getHeight(); i++) {
//      for (int j = 0; j < greenvalues.getWidth(); j++) {
//        int pixel = greenvalues.getRGB(j, i);
//        int greenVal = (pixel >> 8) & 0xff;
//        green[greenVal] = green[greenVal] + 1;
//      }
//    }
//
//    for (int i = 0; i < bluevalues.getHeight(); i++) {
//      for (int j = 0; j < bluevalues.getWidth(); j++) {
//        int pixel = bluevalues.getRGB(j, i);
//        int blueVal = (pixel) & 0xff;
//        blue[blueVal] = blue[blueVal] + 1;
//      }
//    }
//
//    for (int i = 0; i < intensityvalues.getHeight(); i++) {
//      for (int j = 0; j < intensityvalues.getWidth(); j++) {
//        int pixel = intensityvalues.getRGB(j, i);
//        int redVal = (pixel >> 16) & 0xff;
//        intensity[redVal] = intensity[redVal] + 1;
//      }
//    }
//
//    view.setRedData(red);
//    view.setGreenData(green);
//    view.setBlueData(blue);
//    view.setIntensityData(intensity);
//
//
//  }
}








