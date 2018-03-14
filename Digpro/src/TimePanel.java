
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;

/**
 * It is a time panel that shows the current time in the main gui.
 *
 *
 * @author Lizhong Wang
 */
public final class TimePanel extends JPanel {

    private final JLabel timeLabel;

    /**
     * Construction of the TimePanel;
     */
    public TimePanel() {
        setLayout(new BorderLayout());
        timeLabel = new JLabel();
        timeLabel.setHorizontalAlignment(JLabel.RIGHT);
        timeLabel.setFont(UIManager.getFont("Label.font").deriveFont(Font.BOLD, 15f));
        getCurrentTime();
        add(timeLabel);

        Timer timer = new Timer(500, (ActionEvent e) -> {
            getCurrentTime();
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        timer.start();
    }

    /**
     * Generating the data format and putting into the Label
     */
    public void getCurrentTime() {

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String thisTime = dateFormat.format(now);
        timeLabel.setText(thisTime + "  ");
    }
}
