import java.io.IOException;

/**
 * This interface represents all the operations offered by a controller.
 */
public interface ControlInt {

  /**
   * Operations that receive input and send them to model and store the output.
   */
  void controlScript(ModelInterface image) throws IOException;
}
