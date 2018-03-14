
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Recruitment Test @ Digpro
 *
 * @author Lizhong Wang
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Digpro");
            frame.setContentPane(new Gui());
            frame.pack();
            frame.setSize(1000, 800);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

}
