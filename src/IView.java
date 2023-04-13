import java.awt.image.BufferedImage;

import javax.swing.*;

public interface IView {
  String getFileName();
  void setListener(ControlView controlView);
  void display();
  void setLoad(String path);
  JLabel getInFileName();
  JLabel getOutFileName();
  void loadBufferedImage(BufferedImage temp);

  void update();

  void setRedData(int [] r);
  void setGreenData(int [] r);
  void setBlueData(int [] r);
  void setIntensityData(int [] r);

  BufferedImage getCurrentImage();

  BufferedImage completedHistogram();
}
