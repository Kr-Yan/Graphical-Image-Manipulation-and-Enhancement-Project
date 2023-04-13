import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
//import

/**
 * The implementation of the view interface.
 */
public class View extends JFrame implements ActionListener, ItemListener,
        ListSelectionListener, IView {
  private BufferedImage image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
  JFreeChart chart;
  private JLabel imageLabel;
  private final JLabel fileSaveDisplay;
  private ChartPanel chartPanel;
  JButton fileOpenButton = new JButton("Open a file");
  JButton fileSaveButton = new JButton("Save a file");
  JButton flipVerticalButton;
  JButton flipHorizontalButton;
  JButton greyscaleButton;
  JButton blurringButton;
  JButton sharpeningButton;
  JButton sepiaButton;
  JButton redButton;
  JButton greenButton;
  JButton blueButton;

  /**
   * View class.
   *
   * @throws IOException throw if any exception
   */
  public View() throws IOException {


    super();
    setTitle("Swing features");
    setSize(600, 600);


    JPanel mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);


    //dialog boxes
    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Dialog boxes"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(dialogBoxesPanel);

    //file open
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileopenPanel);
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener(this);
    fileopenPanel.add(fileOpenButton);
    JLabel fileOpenDisplay = new JLabel("File path will appear here");
    fileopenPanel.add(fileOpenDisplay);

    //file save
    JPanel filesavePanel = new JPanel();
    filesavePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(filesavePanel);
    //JButton fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");
    fileSaveButton.addActionListener(this);
    filesavePanel.add(fileSaveButton);
    fileSaveDisplay = new JLabel("File path will appear here");
    filesavePanel.add(fileSaveDisplay);

    // flip a image
    flipVerticalButton = new JButton("flip vertically");
    flipVerticalButton.setActionCommand("flip-vertical");
    flipVerticalButton.addActionListener(this);
    mainPanel.add(flipVerticalButton);

    // flip a image
    flipHorizontalButton = new JButton("flip horizontally");
    flipHorizontalButton.setActionCommand("flip-horizontal");
    flipHorizontalButton.addActionListener(this);
    mainPanel.add(flipHorizontalButton);

    //red
    redButton = new JButton("red-component");
    redButton.setActionCommand("red-component");
    redButton.addActionListener(this);
    mainPanel.add(redButton);

    //green
    greenButton = new JButton("green-component");
    greenButton.setActionCommand("green-component");
    greenButton.addActionListener(this);
    mainPanel.add(greenButton);

    //blue
    blueButton = new JButton("blue-component");
    blueButton.setActionCommand("blue-component");
    blueButton.addActionListener(this);
    mainPanel.add(blueButton);

    //greyscale
    greyscaleButton = new JButton("greyscale");
    greyscaleButton.setActionCommand("greyscale");
    greyscaleButton.addActionListener(this);
    mainPanel.add(greyscaleButton);

    //blurring
    blurringButton = new JButton("blurring");
    blurringButton.setActionCommand("blurring");
    blurringButton.addActionListener(this);
    mainPanel.add(blurringButton);

    //sharpening
    sharpeningButton = new JButton("sharpening");
    sharpeningButton.setActionCommand("sharpening");
    sharpeningButton.addActionListener(this);
    mainPanel.add(sharpeningButton);

    //sepia
    sepiaButton = new JButton("sepia");
    sepiaButton.setActionCommand("sepia");
    sepiaButton.addActionListener(this);
    mainPanel.add(sepiaButton);


    //show an image with a scrollbar
    JPanel imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imageLabel = new JLabel("");
    imagePanel.add(imageLabel);
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(100, 600));
    imagePanel.add(imageScrollPane);
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    mainPanel.add(imagePanel);


    chartPanel = new ChartPanel(chart);
    chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    chartPanel.setBackground(Color.DARK_GRAY);
    mainPanel.add(chartPanel);
    Canvas histogram = new Canvas();
    mainPanel.add(histogram);
    mainPanel.setVisible(true);
  }


  /**
   * Display method.
   */
  public void display() {
    ImageIcon icon = new ImageIcon(image);
    imageLabel.setIcon(icon);
    chartPanel.setChart(chart);
    chartPanel.repaint();
    this.revalidate();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    SwingUtilities.updateComponentTreeUI(this);

  }

  @Override
  public void setListener(ControlView listener) {
    fileOpenButton.addActionListener(listener);
    fileSaveButton.addActionListener(listener);
    flipVerticalButton.addActionListener(listener);
    flipHorizontalButton.addActionListener(listener);
    redButton.addActionListener(listener);
    greenButton.addActionListener(listener);
    blueButton.addActionListener(listener);
    greyscaleButton.addActionListener(listener);
    blurringButton.addActionListener(listener);
    sharpeningButton.addActionListener(listener);
    sepiaButton.addActionListener(listener);
  }

  /**
   * setLoad method.
   *
   * @param filename The path to the file in question.
   */
  public void setLoad(String filename) {
    String load = filename;
  }

  @Override
  public void loadBufferedImage(BufferedImage temp) {
    this.image = temp;
    System.out.println("Buffered image was sent, it's now" + this.image);
  }

  /**
   * Get line chart from a given dataset.
   *
   * @param dataset a givin dataset.
   */
  public void getLineChart(DefaultCategoryDataset dataset) {
    JFreeChart chart = ChartFactory.createLineChart(
            "Site Traffic", // Chart title
            "Date", // X-Axis Label
            "Number of Visitor", // Y-Axis Label
            dataset
    );
    this.chart = chart;
  }

  @Override
  public String getOutFileName() {
    return fileSaveDisplay.getText();
  }

  /**
   * get save jLabel.
   *
   * @return save jLabel.
   */
  public JLabel getSaveJLabel() {
    return fileSaveDisplay;
  }

  /**
   * Invoked when an item has been selected or deselected by the user.
   * The code written for this method performs the operations
   * that need to occur when an item is selected (or deselected).
   *
   * @param e the event to be processed
   */
  @Override
  public void itemStateChanged(ItemEvent e) {
    //not used
  }

  /**
   * Called whenever the value of the selection changes.
   *
   * @param e the event that characterizes the change.
   */
  @Override
  public void valueChanged(ListSelectionEvent e) {
    // not used
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    //not used
  }
}
