
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

/**
 * This is the main GUI (graphic user interface) of the application. It contains
 * three main-panels, a control-panel placed in north, a status-panel in south
 * and a data-panel located in the center.
 *
 * @author Lizhong Wang
 */
public final class Gui extends JPanel implements ActionListener {

    //control panel
    public JPanel controlPanel;
    public JPanel titlePanel;
    public JPanel buttonPanel;
    public JLabel title;

    //data panel
    public JPanel dataPanel;
    public JPanel textPanel;
    public JPanel coordinatePanel;
    public JLabel dataLabel;
    public static Timer timer;

    //status panel
    public JPanel statusPanel;
    public JScrollPane scrollPanel;
    public JPanel timePanel;
    public static JTextArea statusText;

    public final Font titleFont = new Font("Times New Roman", 1, 50);

    public final Font statusFont = new Font("Times New Roman", 1, 15);

    /**
     * Initializing the GUI.
     */
    Gui() {
        buildGui();
    }

    /**
     * Building the GUI by adding three main panels.
     */
    void buildGui() {
        setLayout(new BorderLayout());
        add(createStatusPanel(), BorderLayout.SOUTH);
        add(createDataPanel(), BorderLayout.CENTER);
        add(createControlPanel(), BorderLayout.NORTH);
    }

    /**
     * Creating the control-panel by adding a title-panel and a button-panel.
     * The titile-panel shows the title of the application. The button-panel is
     * created to store all the buttons that control the application.
     *
     * @return the constructed control-panel
     */
    public Component createControlPanel() {

        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(0, 1));
        controlPanel.setSize(1000, 400);
        controlPanel.setBackground(Color.lightGray);

        titlePanel = new JPanel();
        titlePanel.setSize(1000, 200);
        titlePanel.setBackground(new Color(0, 90, 131));
        controlPanel.add(titlePanel);

        buttonPanel = new JPanel();
        buttonPanel.setSize(1000, 200);
        buttonPanel.setBackground(new Color(207, 143, 0));
        controlPanel.add(buttonPanel);

        title = new JLabel("     Digpro Webpage Coordinate Reader     ");
        title.setFont(titleFont);
        title.setForeground(Color.white);
        titlePanel.add(title);

        Buttons.refreshButton(this);
        Buttons.cancelButton(this);
        Buttons.restartButton(this);
        Buttons.aboutButton(this);
        Buttons.exitButton(this);

        return controlPanel;
    }

    /**
     * Constructing the data-panel that shows the data points in both
     * text-format and graphical coordinate system in both text and coordinate.
     * A timer is set up in order to automatically update the data-panel.
     *
     * @return the constructed data-panel.
     */
    public Component createDataPanel() {
        dataPanel = new JPanel();
        dataPanel.setLayout(new GridLayout(1, 1));
        dataPanel.setSize(1000, 600);

        setupDataPanel();

        timer = new Timer(30000, this);
        startAutoUpdate();

        setVisible(true);
        return dataPanel;
    }

    /**
     * Constructing the status-panel that shows the connection status and time.
     * The connection status is presented using a scrollpanel and the time panel
     * is an instance of the Object TimePanel.
     *
     * @return the constructed status-panel.
     */
    public Component createStatusPanel() {

        statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout(0, 1));
        statusPanel.setPreferredSize(new Dimension(1000, 100));
        statusPanel.setBackground(Color.lightGray);

        statusText = new JTextArea();
        statusText.setFont(statusFont);
        statusText.setEditable(false);
        scrollPanel = new JScrollPane(statusText);
        scrollPanel.setPreferredSize(new Dimension(1000, 70));
        statusPanel.add(scrollPanel);

        timePanel = new TimePanel();
        timePanel.setPreferredSize(new Dimension(1000, 30));
        statusPanel.add(timePanel);

        return statusPanel;
    }

    /**
     * Refreshing the data panel by calling two other metods. It first clean the
     * panel, and the re-draw the panel.
     */
    public void refreshDataPanel() {
        clearDataPanel();
        setupDataPanel();
    }

    /**
     * Clearing the data-panel by remove the all the sub-panels.
     */
    public void clearDataPanel() {

        dataPanel.remove(coordinatePanel);
        dataPanel.remove(textPanel);
        dataPanel.revalidate();
        dataPanel.repaint();
    }

    /**
     * Setuping the data-panel by re-building the panel.
     */
    public void setupDataPanel() {

        List<String[]> tempData = Connection.initialization();

        textPanel = new DataTablePanel(tempData);
        dataPanel.add(textPanel);

        coordinatePanel = Coordinate.drawer(tempData);
        coordinatePanel.setSize(1000, 600);
        dataPanel.add(coordinatePanel);
    }

    /**
     * starting the data auto-updating when the application is executed, or
     * restarting the auto-updating while the button "Restart" is clicked.
     */
    public void startAutoUpdate() {
        timer.start();
    }

    /**
     * Canceling the data auto updating while the button "Cancel" is pressed.
     */
    public void cancelAutoUpdate() {
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        refreshDataPanel();
    }

}
