
import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * Re-directing the output stream from Java Console to JTextArea
 *
 * @author Lizhong Wang
 */
public class OutputGui extends OutputStream {

    private final JTextArea destination;

    /**
     * Initializing the destination (JTextArea) of the output stream
     *
     * @param destination the destination of the output stream
     */
    public OutputGui(JTextArea destination) {
        if (destination == null) {
            throw new IllegalArgumentException("Destination is null");
        }
        this.destination = destination;
    }

    /**
     * Overwite the default method wirte of OutputStream. Writes len bytes from
     * the specified byte array starting at offset off to this output stream.
     *
     * Reference:
     * https://docs.oracle.com/javase/7/docs/api/java/io/OutputStream.html
     *
     * @param buffer the data
     * @param offset the start offset in the data
     * @param length the number of bytes to write
     * @throws IOException if an I/O error occurs. In particular, an IOException
     * is thrown if the output stream is closed.
     */
    @Override
    public void write(byte[] buffer, int offset, int length) throws IOException {
        final String text = new String(buffer, offset, length);
        SwingUtilities.invokeLater(() -> {
            destination.append(text);
        });
    }

    /**
     * Override the default method wirte of OutputStream. Writes b.length bytes
     * from the specified byte array to this output stream.
     *
     * Reference:
     * https://docs.oracle.com/javase/7/docs/api/java/io/OutputStream.html
     *
     * @param b the data
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void write(int b) throws IOException {
        write(new byte[]{(byte) b}, 0, 1);
    }
}
