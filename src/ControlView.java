import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * this is a controller for view.
 */
public class ControlView implements ControlInt, ActionListener {
  private ModelInterface model;
  private IView view;
  BufferedImage temp;
  private String[][] loadedPicArray;


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

  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Open file": {
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF & "
                + "PPM & PNG Images", "jpg", "gif", "ppm", "png");
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
      }

      break;
      case "Save file": {
        final JFileChooser fochooser = new JFileChooser(".");

        fochooser.setFileFilter(new FileFilter() {
          @Override
          public boolean accept(File file) {
            return file.getName().toUpperCase().equals(".ppm");
          }

          @Override
          public String getDescription() {
            return ".ppm";
          }
        });

        fochooser.addChoosableFileFilter(new FileFilter() {
          @Override
          public boolean accept(File file) {
            return file.getName().toUpperCase().equals(".jpg");
          }

          @Override
          public String getDescription() {
            return ".jpg";
          }
        });

        fochooser.addChoosableFileFilter(new FileFilter() {
          @Override
          public boolean accept(File file) {
            return file.getName().toUpperCase().equals(".png");
          }

          @Override
          public String getDescription() {
            return ".png";
          }
        });

        int restvalue = fochooser.showSaveDialog((Component) this.view);
        if (restvalue == JFileChooser.APPROVE_OPTION) {
          System.out.println("FILECHOOSER FILTER TOSTRING IS "
                  + fochooser.getFileFilter().getDescription());
          String filetype = fochooser.getFileFilter().getDescription();
          File f = fochooser.getSelectedFile();
          view.getSaveJLabel().setText(f.getAbsolutePath());
          String[] aPath1 = view.getOutFileName().toString().split("\\\\");
          String fileName1 = aPath1[aPath1.length - 1];
          System.out.println("filename 1 from save is " + fileName1);
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

      case "flip-vertical": {
        model.flipPPMVert("flipped-v", loadedPicArray);
        loadedPicArray = model.flipPPMVert("flipped-v", loadedPicArray);
        temp = model.getImages().get("flipped-v");
        view.loadBufferedImage(temp);
        view.getLineChart(model.buildDataset(model.getArrays(loadedPicArray)));
        view.display();
      }
      break;
      case "flip-horizontal": {
        model.flipPPMHor("flipped-h", loadedPicArray);
        loadedPicArray = model.flipPPMHor("flipped-h", loadedPicArray);
        temp = model.getImages().get("flipped-h");
        view.loadBufferedImage(temp);
        view.getLineChart(model.buildDataset(model.getArrays(loadedPicArray)));
        view.display();
      }
      break;

      case "red-component": {
        model.visualizeComponent("componentVisual", 'r', loadedPicArray);
        loadedPicArray = model.visualizeComponent("componentVisual", 'r',
                loadedPicArray);
        temp = model.getImages().get("componentVisual");
        view.loadBufferedImage(temp);
        view.getLineChart(model.buildDataset(model.getArrays(loadedPicArray)));
        view.display();
      }
      break;
      case "green-component": {
        model.visualizeComponent("componentVisual", 'g', loadedPicArray);
        loadedPicArray = model.visualizeComponent("componentVisual", 'g',
                loadedPicArray);
        temp = model.getImages().get("componentVisual");
        view.loadBufferedImage(temp);
        view.getLineChart(model.buildDataset(model.getArrays(loadedPicArray)));
        view.display();
      }
      break;

      case "blue-component": {
        model.visualizeComponent("componentVisual", 'b', loadedPicArray);
        loadedPicArray = model.visualizeComponent("componentVisual", 'b',
                loadedPicArray);
        temp = model.getImages().get("componentVisual");
        view.loadBufferedImage(temp);
        view.getLineChart(model.buildDataset(model.getArrays(loadedPicArray)));
        view.display();
      }
      break;

      case "greyscale": {
        model.colorGrade("greyscale", loadedPicArray, "greyscale");
        loadedPicArray = model.colorGrade("greyscale", loadedPicArray,
                "greyscale");
        temp = model.getImages().get("greyscale");
        view.loadBufferedImage(temp);
        view.getLineChart(model.buildDataset(model.getArrays(loadedPicArray)));
        view.display();
      }
      break;
      case "blurring": {
        model.blur("blur", loadedPicArray);
        loadedPicArray = model.blur("blur", loadedPicArray);
        temp = model.getImages().get("blur");
        view.loadBufferedImage(temp);
        view.getLineChart(model.buildDataset(model.getArrays(loadedPicArray)));
        view.display();
      }
      break;
      case "sharpening": {
        model.sharpen("sharpen", loadedPicArray);
        loadedPicArray = model.sharpen("sharpen", loadedPicArray);
        temp = model.getImages().get("sharpen");
        view.loadBufferedImage(temp);
        view.getLineChart(model.buildDataset(model.getArrays(loadedPicArray)));
        view.display();
      }
      break;
      case "sepia": {
        model.colorGrade("sepia", loadedPicArray, "sepia");
        loadedPicArray = model.colorGrade("sepia", loadedPicArray, "sepia");
        temp = model.getImages().get("sepia");
        view.loadBufferedImage(temp);
        view.getLineChart(model.buildDataset(model.getArrays(loadedPicArray)));
        view.display();
      }
      break;

      default:
        throw new IllegalArgumentException("Invalid operation ");
    }
  }
}








