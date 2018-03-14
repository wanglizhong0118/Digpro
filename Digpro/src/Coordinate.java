
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.List;
import javax.swing.JPanel;

/**
 * This Class extends JPanel drawing a simple X/Y coordinate system wiht a list
 * of data points read from web page. ,
 *
 * @author Lizhong Wang
 */
public class Coordinate extends JPanel {

    private static final Font axelFont = new Font("Cambria Math", 1, (int) 25f);
    private static final Font dataToolTipFont = new Font("Cambria Math", 0, (int) 20f);
    
    /**
     * Drawing a coordinate system. The axels are in black lines, and the data
     * points are red with their name presented as tooltips.
     *
     * @param dataList the input data points in list format.
     * @return A JPanel consisting of X/Y coordinate system
     */
    public static JPanel drawer(List<String[]> dataList) {

        JPanel pl = new JPanel() {

            @Override
            public void paint(Graphics g) {

                super.paint(g);

                Graphics2D g2d = (Graphics2D) g;

                g2d.setFont(axelFont);

                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(2f));

                g2d.drawLine(0, 400, 400, 400);
                g2d.drawString("X-axel", 380, 390);

                g2d.drawLine(200, 0, 200, 600);
                g2d.drawString("Y-axel", 210, 20);

                g2d.setColor(Color.RED);
                g2d.setStroke(new BasicStroke(10f));

                for (String[] st : dataList) {

                    int x_orignal = Integer.parseInt(st[0]);
                    int y_orignal = Integer.parseInt(st[1]);
                    String name = st[2];

                    double x = x_orignal / 5 + 200;
                    double y = -y_orignal / 5 + 400;

                    g2d.draw(new Line2D.Double(x, y, x, y));
                    g2d.setFont(dataToolTipFont);
                    g2d.drawString(name, (float) x, (float) y);

                }

            }
        };
        pl.setSize(400, 400);

        return pl;
    }

}
