import java.io.IOException;

/**
 * Main class that runs everything.
 */
public class MvcRead {

  /**
   * a main method that operates mvc.
   *
   * @param arg an argument.
   * @throws IOException return an exception.
   */
  public static void main(String[] arg) throws IOException {

    ImageUtil model = new ImageUtil();
    View view = new View();
    ControlInt controller = new ControlView(model, view);


    //ControlInt aController = new Control(null, null);
    //aController.controlScript(new ImageUtil());

  }
}
