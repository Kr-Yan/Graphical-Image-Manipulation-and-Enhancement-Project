import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
//import

/**
 * The implementation of the view interface.
 */
public class View extends JFrame implements ActionListener, ItemListener, ListSelectionListener, IView {
  private ImageIcon icon;
  int[] red = new int[256];
  int[] green = new int[256];
  int[] blue = new int[256];
  int[] intensity = new int[256];
  private BufferedImage image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);

  private final JLabel fileOpenDisplay;
  private JLabel imageLabel;
  private final JLabel fileSaveDisplay;

  private String load = "test.png";
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
  JCheckBox[] componentVButton= new JCheckBox[3];

  //panel
  //histogram method
  //histogram helper-> an array list of hashmap
  //put this in control view
  //save


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

    //
    JPanel


    //dialog boxes
    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Dialog boxes"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(dialogBoxesPanel);
    //file open
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileopenPanel);
    //JButton fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener(this);
    fileopenPanel.add(fileOpenButton);
    fileOpenDisplay = new JLabel("File path will appear here");
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
    flipVerticalButton= new JButton("flip vertically");
    flipVerticalButton.setActionCommand("flip-vertical");
    flipVerticalButton.addActionListener(this);
    mainPanel.add(flipVerticalButton);

    // flip a image
    flipHorizontalButton= new JButton("flip horizontally");
    flipHorizontalButton.setActionCommand("flip-horizontal");
    flipHorizontalButton.addActionListener(this);
    mainPanel.add(flipHorizontalButton);


    //red
    redButton= new JButton("red-component");
    redButton.setActionCommand("red-component");
    redButton.addActionListener(this);
    mainPanel.add(redButton);

    //green
    greenButton= new JButton("green-component");
    greenButton.setActionCommand("green-component");
    greenButton.addActionListener(this);
    mainPanel.add(greenButton);

    //blue
    blueButton= new JButton("blue-component");
    blueButton.setActionCommand("blue-component");
    blueButton.addActionListener(this);
    mainPanel.add(blueButton);


    //greyscale
    greyscaleButton= new JButton("greyscale");
    greyscaleButton.setActionCommand("greyscale");
    greyscaleButton.addActionListener(this);
    mainPanel.add(greyscaleButton);

    //blurring
    blurringButton= new JButton("blurring");
    blurringButton.setActionCommand("blurring");
    blurringButton.addActionListener(this);
    mainPanel.add(blurringButton);

    //sharpening
    sharpeningButton= new JButton("sharpening");
    sharpeningButton.setActionCommand("sharpening");
    sharpeningButton.addActionListener(this);
    mainPanel.add(sharpeningButton);

    //sepia
    sepiaButton= new JButton("sepia");
    sepiaButton.setActionCommand("sepia");
    sepiaButton.addActionListener(this);
    mainPanel.add(sepiaButton);


    //show an image with a scrollbar
    JPanel imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    //JLabel
    imageLabel = new JLabel("");
//    ImageIcon images = new ImageIcon("test.png");
    imagePanel.add(imageLabel);
    JScrollPane imageScrollPane=new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(100, 600));
    imagePanel.add(imageScrollPane);
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    mainPanel.add(imagePanel);


    Canvas histogram = new Canvas();
    //a border around the panel with a caption
    //histogram.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    //imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    //imagePanel.setMaximumSize(null);
    mainPanel.add(histogram);

//    JLabel fileFVDisplay = new JLabel("");
////    mainPanel.add(imagePanel);
//    flipVPanel.add(fileFVDisplay);
//

    mainPanel.setVisible(true);


//4. Size the frame.
    //this.pack();
//5. Show it.
    // mainPanel.setVisible(true);
  }


  /**
   * Display method.
   */
  public void display() {
    icon = new ImageIcon(image);
    imageLabel.setIcon(icon);
    // icon = new ImageIcon(image);
    // imageLableLeft.setIcon(icon);

    //icon is now changed
    //imageLable.
    this.revalidate();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    //imageLableLeft.repaint();
    SwingUtilities.updateComponentTreeUI(this);

  }

  public void setRedData(int[] r) {
    red = r;
  }

  public void setGreenData(int[] g) {
    green = g;
  }

  public void setBlueData(int[] b) {
    blue = b;
  }

  public void setIntensityData(int[] i) {
    intensity = i;
  }


  /**
   * The method that makes the bufferedimage representation of the histogram.
   *
   * @return the bufferedimage.
   */
  public BufferedImage completedHistogram() {
    int rmax;
    int gmax;
    int bmax;
    int intensmax;

    intensmax = Arrays.stream(intensity).max().getAsInt();
    gmax = Arrays.stream(green).max().getAsInt();
    rmax = Arrays.stream(red).max().getAsInt();
    bmax = Arrays.stream(blue).max().getAsInt();

    int max = Math.max(Math.max(rmax, bmax), Math.max(gmax, intensmax));

    BufferedImage output = new BufferedImage(256, max, BufferedImage.TYPE_INT_RGB);

    //this is terrible but I'm sick as hell and out of time.
    for (int i = 0; i < 255; i++) {
      //lets do red first
      output.setRGB(i, red[i], 16711680);
      //green
      output.setRGB(i, green[i], 65280);
      //blue
      output.setRGB(i, blue[i], 255);
      //intensity
      output.setRGB(i, intensity[i], 0);
    }
    return output;
  }


  @Override
  public String getFileName() {
    load = fileOpenDisplay.getText();
    return load;
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
   * @param filename The path to the file in question.
   */
  public void setLoad(String filename) {
    load = filename;
  }


  @Override
  public void update() {
    display();
  }

  @Override
  public BufferedImage getCurrentImage() {
    return image;
  }


  @Override
  public JLabel getOutFileName() {
    return fileSaveDisplay;
  }

  @Override
  public void loadBufferedImage(BufferedImage temp) {
    this.image = temp;
    System.out.println("Buffered image was sent, it's now" + this.image);
  }

  @Override
  public JLabel getInFileName() {
    return fileOpenDisplay;
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

  }

  /**
   * Called whenever the value of the selection changes.
   *
   * @param e the event that characterizes the change.
   */
  @Override
  public void valueChanged(ListSelectionEvent e) {

  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {

  }
}


//histogram should always be seen
//filechooser for selecting where to save and where to open files
//figure out if you need to have the separate controller for the gui or just its own thing.
//histograms always on screen
//radial buttons and drop down menus for file saving
//menu boxes for picking up the string for the name?


//FOR HISTOGRAM
//The horizontal axis of the graph represents the tonal variations,
// while the vertical axis represents the total number of pixels in that particular tone.

//name
//0-255 four arrays