import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ControlView implements ControlInt, ActionListener {
  private ModelInterface model;

  private IView view;


  /**
   * This represents a constructor for the controller.
   */
  public ControlView(ModelInterface model, IView view) {
    this.model = model;
    this.view = view;
    view.setListener(this);

  }





  /**
   * Operations that receive input and send them to model and store the output.
   *
   * @param image
   */
  @Override
  public void controlScript(ModelInterface image) throws IOException {

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



     @Override
  public void actionPerformed(ActionEvent e) {
      switch (e.getActionCommand()) {
        case "Open file": {
          final JFileChooser fchooser = new JFileChooser(".");
          FileNameExtensionFilter filter = new FileNameExtensionFilter(
                  "JPG & GIF Images", "jpg", "gif");
          fchooser.setFileFilter(filter);
          int retvalue = fchooser.showOpenDialog((Component) this.view);
          if (retvalue == JFileChooser.APPROVE_OPTION) {
            File f = fchooser.getSelectedFile();
            String path = "";
            path = f.getAbsolutePath();
            //return file
            System.out.println(path);
          }
        }
        break;
        case "Save file": {
          final JFileChooser fchooser = new JFileChooser(".");
          int retvalue = fchooser.showSaveDialog((Component) this.view);
          if (retvalue == JFileChooser.APPROVE_OPTION) {
            File f = fchooser.getSelectedFile();
            String path = "";
            path = f.getAbsolutePath();
            System.out.println(path);
          }
        }
        break;
      }
     }





  }



