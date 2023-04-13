import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.image.BufferedImage;

import javax.swing.JLabel;

/**
 * A interface for view.
 */
public interface IView {
  void setListener(ControlView controlView);

  void display();

  void setLoad(String path);

  String getOutFileName();

  void loadBufferedImage(BufferedImage temp);

  void getLineChart(DefaultCategoryDataset buildDataset);

  JLabel getSaveJLabel();
}
