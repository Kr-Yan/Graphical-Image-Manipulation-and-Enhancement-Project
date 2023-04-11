import java.io.IOException;

/**
 * Main class that runs everything.
 */
public class MvcRead {

  /**
   * a main method that operates mvc.
   * @param arg an argument.
   * @throws IOException return an exception.
   */
  public static void main(String[] arg) throws IOException {
    ControlInt aController = new Control(null, null);
    aController.controlScript(new ImageUtil());

  }
}
