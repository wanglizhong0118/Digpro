
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JToolTip;

/**
 * This separated Class contains all the JButton implemented in the main GUI.
 *
 * @author Lizhong Wang
 */
public class Buttons {

    private static JButton aboutButton;
    private static JLabel jdabout;

    private static JButton refreshButton;
    private static JButton cancelButton;
    private static JButton restartButton;
    private static JButton exitButton;
    private static JDialog jd;

    private static final Font buttonFont = new Font("Times New Roman", 0, 30);
    private static final Dimension buttonSize = new Dimension(150, 50);
    private static final Font jdFont = new Font("Times New Roman", 1, 25);

    /**
     * Clicking the refreshButton to manually update the data.
     *
     * @param gui the gui
     */
    public static void refreshButton(Gui gui) {

        refreshButton = new JButton("Refresh") {
            @Override
            public JToolTip createToolTip() {
                return new Buttons.NewJToolTip(gui);
            }
        };
        refreshButton.addActionListener((ActionEvent e) -> {
            gui.refreshDataPanel();
        });
        refreshButton.setPreferredSize(buttonSize);
        refreshButton.setFont(buttonFont);
        refreshButton.setToolTipText("Click here to manually refresh the data");
        gui.buttonPanel.add(refreshButton);
        refreshButton.setEnabled(true);
    }

    /**
     * Clicking the cancelButton to stop the data auto-updating
     *
     * @param gui the gui
     */
    public static void cancelButton(Gui gui) {

        cancelButton = new JButton("Cancel") {
            @Override
            public JToolTip createToolTip() {
                return new Buttons.NewJToolTip(gui);
            }
        };
        cancelButton.addActionListener((ActionEvent e) -> {
            gui.cancelAutoUpdate();
        });
        cancelButton.setPreferredSize(buttonSize);
        cancelButton.setFont(buttonFont);
        cancelButton.setToolTipText("Click here to cancel the data auto updating");
        gui.buttonPanel.add(cancelButton);
        cancelButton.setEnabled(true);
    }

    /**
     * Clicking the restartButton to restart the data auto-updating
     *
     * @param gui the gui
     */
    public static void restartButton(Gui gui) {

        restartButton = new JButton("Restart") {
            @Override
            public JToolTip createToolTip() {
                return new Buttons.NewJToolTip(gui);
            }
        };
        restartButton.addActionListener((ActionEvent e) -> {
            gui.startAutoUpdate();
        });
        restartButton.setPreferredSize(buttonSize);
        restartButton.setFont(buttonFont);
        restartButton.setToolTipText("Click here to restart the data auto updating");
        gui.buttonPanel.add(restartButton);
        restartButton.setEnabled(true);

    }

    /**
     * Clicking the aboutButton to show the author's information
     *
     * @param gui the gui
     */
    public static void aboutButton(Gui gui) {

        aboutButton = new JButton("About") {
            @Override
            public JToolTip createToolTip() {
                return new Buttons.NewJToolTip(gui);
            }
        };
        aboutButton.addActionListener((ActionEvent e) -> {
            JDialog();
        });
        aboutButton.setPreferredSize(buttonSize);
        aboutButton.setFont(buttonFont);
        aboutButton.setToolTipText("Click here to show the author information");
        gui.buttonPanel.add(aboutButton);
        aboutButton.setEnabled(true);
    }

    /**
     * Clicking the exitButton to safely and correctly exit the application
     *
     * @param gui the gui
     */
    public static void exitButton(Gui gui) {

        exitButton = new JButton("Exit") {
            @Override
            public JToolTip createToolTip() {
                return new Buttons.NewJToolTip(gui);
            }
        };
        exitButton.addActionListener((ActionEvent evt) -> {
            System.exit(0);
        });
        exitButton.setPreferredSize(buttonSize);
        exitButton.setFont(buttonFont);
        exitButton.setToolTipText("Click here to exit the application");
        gui.buttonPanel.add(exitButton);
        exitButton.setEnabled(true);
    }

    /**
     * The JDialog shows the author's information by clicking the aboutButton.
     *
     */
    public static void JDialog() {

        jd = new JDialog();
        jd.setTitle("About me");
        jd.setBounds(300, 200, 400, 300);
        jdabout = new JLabel("<html>" + " Author Information " + "<br>" + "<br>"
                + "Name: Lizhong Wang" + "<br>"
                + "Email: lizhongw@kth.se" + "<br>"
                + "Phone: 0046-721558484 " + "</html>");
        jdabout.setFont(jdFont);
        jd.add(jdabout);
        jd.setVisible(true);
    }

    /**
     * Constructed JToolTip. It extends the default JToolTip, but the Font size
     * is larger than the default one in order to make a better user experience
     *
     */
    public static class NewJToolTip extends JToolTip {

        public NewJToolTip(JComponent component) {
            super();
            setComponent(component);
            component.setForeground(Color.black);
            setFont(new Font("Times New Roman", 1, 20));
        }
    }
}
