import java.awt.event.ActionEvent;
import java.io.IOException;

public class ControlView implements ControlInt {
  private ModelInterface model;


  private IView view;


  /**
   * This represents a constructor for the controller.
   */
  public ControlView(ModelInterface model, IView view) {
    this.model = model;
    this.view = view;
  }
  public void go(ActionEvent e){
    switch (e.getActionCommand()) {
      case "Open file":
       String text = view.getFileName();
      //  model.
    }
  }

  /**
   * Operations that receive input and send them to model and store the output.
   *
   * @param image
   */
  @Override
  public void controlScript(ModelInterface image) throws IOException {

  }
}
