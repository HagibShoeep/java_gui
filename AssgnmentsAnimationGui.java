import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;

/* write from shoaib faisl
for more information 

https://t.me/assestanceProgramings
 */
public class AssgnmentsAnimationGui extends JFrame {

    static JLabel imageLabel;

    //draw the header panel
    public static JPanel drawBannelHeader(JFrame frame) {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label1 = new JLabel("Data Entry");
        
        label1.setFont(changeFont());
        panel.setPreferredSize(new Dimension(frame.getWidth(), 45));
        label1.setForeground(Color.WHITE);
        panel.add(label1);
          panel.setBackground(new Color(0x4472C4));
        return panel;
    }

    //create panel body for store right and left
    public static JPanel drawBannelBody(JFrame frame) {

        JPanel bodyBannel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //bodyBannel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bodyBannel.setPreferredSize(new Dimension((int) (frame.getWidth() * .995), 452));
        bodyBannel.setBackground(Color.white);
        bodyBannel.add(createJpanelLeftBody(frame));
        bodyBannel.add(createJpaneRightBody(frame));

        return bodyBannel;
    }

    //create font 
    public static Font changeFont() {
        Font font = new Font("Courier", Font.PLAIN, 20);
        return font;
    }

    /**
     * ***draw panel for content left panel and right in body panel
     *
     *
     *
     *
     *
     */
    public static JPanel createJPanelSidBar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JSlider slider = new JSlider(0, 40);
        slider.setMinorTickSpacing(5);
        slider.setMajorTickSpacing(40);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setLabelTable(slider.createStandardLabels(5, 0));
        slider.addChangeListener((ChangeEvent e) -> {
            textArea.setFont(new java.awt.Font("Courier", Font.PLAIN, slider.getValue()));
        });
        JLabel label1 = new JLabel("Font Size  ");
        label1.setFont(changeFont());
        panel.add(label1);

        panel.add(slider);
        return panel;
    }
    //draw button right radio button

    public static JPanel drawRadioButton() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ButtonGroup group = new ButtonGroup();

        JRadioButton horseButton = new JRadioButton("Horse");

        group.add(horseButton);
        horseButton.addActionListener((ActionEvent e) -> {
            // Change the image in the label when the radio button is clicked
            for (int i = 0; i < images.length; i++) {
                images[i] = new ImageIcon(pathimg + "\\horse_" + (i + 1) + ".png");
            }
        });
        panel.add(horseButton);
        JRadioButton birdButton = new JRadioButton("Bird");
        birdButton.addActionListener((ActionEvent e) -> {
            // Change the image in the label when the radio button is clicked
            for (int i = 0; i < images.length; i++) {
                images[i] = new ImageIcon(pathimg + "\\bird_" + (i + 1) + ".png");
            }

            //imageLabel.res
        });
        group.add(birdButton);

        panel.add(birdButton);
        JRadioButton aeroplanButton = new JRadioButton("Aeroplan");
        aeroplanButton.addActionListener((ActionEvent e) -> {
            // Change the image in the label when the radio button is clicked
            for (int i = 0; i < images.length; i++) {
                images[i] = new ImageIcon(pathimg + "\\aerplain_" + (i + 1) + ".png");
            }
        });

        group.add(aeroplanButton);
        panel.add(aeroplanButton);
        return panel;
    }
    //draw button fo run and cancel right
    static Thread thread;
    static int count = 0;
    static int minte = 0;
    static ImageIcon imageIcon;

    static Thread animationThread;
    static boolean running;

    private static void startAnimation() {
        if (animationThread == null) {
            running = true;
            animationThread = new Thread() {
                public void run() {
                    while (running) {
                        currentImage = (currentImage + 1) % images.length;
                        imageLabel.setIcon(images[currentImage]);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            animationThread.start();
        }
    }
    static ImageIcon[] images;
    static int currentImage;
    //stop animation

    private static void stopAnimation() {
        running = false;
        animationThread.stop();
        animationThread = null;

    }

    public static JPanel panelButtonRight() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton startButton = new JButton("Start");
        startButton.setBackground(Color.BLUE);
        startButton.setForeground(Color.WHITE);

        JButton stopButton = new JButton("Stop");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAnimation();
            }
        });

        panel.add(startButton);
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(null, "Thank You for using my program for **:** time");
                stopAnimation();
                thread.stop();
            }
        });
        stopButton.setBackground(Color.RED);
        stopButton.setForeground(Color.WHITE);
        panel.add(stopButton);
        return panel;
    }

    //draw  panel left body
    public static JPanel createJpanelLeftBody(JFrame frame) {

        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel1.setPreferredSize(new Dimension((int) (frame.getWidth() * .6), (int) (frame.getHeight() * 0.78)));
        panel1.add(readRowPanelOneLeft());
        panel1.add(createRowPanelYourNameLeft());
        panel1.add(createJPanelSidBar());
        panel1.add(createRowTextArea());
        return panel1;

    }
    //draw  panel right body

    //create pannel Right Body
    static String pathimg = "C:\\Users\\pc\\Desktop\\imges";

    public static JPanel createJpaneRightBody(JFrame frame) {
        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel1.setPreferredSize(new Dimension((int) (frame.getWidth() * .33), (int) (frame.getHeight() * 0.78)));
        panel1.add(drawRadioButton());
        panel1.add(panelButtonRight());
        images = new ImageIcon[5];
        //set array image horses 
        for (int i = 0; i < images.length; i++) {
            images[i] = new ImageIcon(pathimg + "\\horse_" + (i + 1) + ".png");
        }

        imageLabel = new JLabel("add Image");
        Border border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "Animation");
        panel1.setBorder(border);
        JPanel panelImage = new JPanel(new BorderLayout());
        panelImage.setPreferredSize(new Dimension(230, 250));
        imageLabel.setPreferredSize(new Dimension(250, 250));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        imageLabel.setForeground(Color.white);
        imageIcon = new ImageIcon("C:\\Users\\pc\\Desktop\\imges\\horse_1.png");
        //imageLabel.setIcon(imageIcon);

        panelImage.setBackground(new Color(0x4472C4));
        panelImage.add(imageLabel);
        panel1.add(panelImage);
        return panel1;

    }
    static JLabel label = new JLabel("Time :00:00");

    //draw left row of one
    public static JPanel readRowPanelOneLeft() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        panel.add(label);

        label.setFont(changeFont());

        thread = new Thread(() -> {
            while (true) {
                count++;
                label.setText("Time:" + String.valueOf(minte) + ":" + count);
                if (count == 60) {
                    minte++;
                    count = 0;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        thread.start();

        return panel;
    }

    //row tow for panel
    public static JPanel createRowPanelYourNameLeft() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label1 = new JLabel("Your name");
        label1.setFont(changeFont());
        panel.add(label1);
        JTextField textField = new JTextField(12);
        textField.setForeground(Color.BLUE);
        textField.setFont(changeFont());
        panel.add(textField);
        JButton submitButton = new JButton("Submit ");
       // submitButton.setBorder(BorderFactory.createBevelBorder(10));
        submitButton.setFont(changeFont());
        submitButton.setBackground(new Color(0x4472C4));
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener((ActionEvent e) -> {
            String text = textField.getText();
            textField.setText("");
            if (!text.isEmpty()) {
                textArea.append(text + "\n");
            }
        });
        panel.add(submitButton);
        return panel;
    }
    static JTextArea textArea;

    //display panel with text Area
    public static JPanel createRowTextArea() {

        JPanel panelTextArea = new JPanel(new FlowLayout(FlowLayout.CENTER));
        textArea = new JTextArea();
        textArea.setFont(changeFont());
        textArea.setBackground(new Color(0x0ED8FA));
        panelTextArea.setSize(new Dimension(450, 230));
        textArea.setPreferredSize(new Dimension(430, 230));
        panelTextArea.add(textArea);
        return panelTextArea;
    }

    public AssgnmentsAnimationGui() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(840, 510);
        
        this.setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new FlowLayout());
        panel.setPreferredSize(new Dimension(this.getWidth(), (int) (this.getHeight())));
       
        panel.setBackground(new Color(0x4472C4));
        
        panel.add(drawBannelHeader(this));
        
        panel.add(drawBannelBody(this));
        this.add(panel);
        this.setUndecorated(true);
        this.setVisible(true);
    }
}
