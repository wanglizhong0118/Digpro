
import java.awt.Font;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


/**
 * It is a data table panel that shows the data in a table in the main gui. 
 * 
 * 
 * @author Lizhong Wang
 */
public final class DataTablePanel extends JPanel {

    private final JTable dataTable;
    public final Font columnFont = new Font("Times New Roman", 1, 25);
    public final Font dataFont = new Font("Times New Roman", 1, 20);

    public DataTablePanel(List<String[]> ls) {

        String[] columns = new String[]{" X ", " Y ", " Name "};
        String[][] dataArray = listConverter(ls);

        dataTable = new JTable(dataArray, columns);

        int l = dataArray.length + 1;
        
        for (int i = 0; i < l; i++) {
            dataTable.setRowHeight(25);
        }

        dataTable.getTableHeader().setFont(columnFont);
        dataTable.setFont(dataFont);

        this.add(new JScrollPane(dataTable));

    }

    public static String[][] listConverter(List<String[]> ls) {

        int size = ls.size();
        String[][] temp = new String[size][3];
        temp = ls.toArray(temp);
        return temp;
    }
}
