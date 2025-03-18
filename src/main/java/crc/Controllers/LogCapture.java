package crc.Controllers;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * <p>LogCapture class.</p>
 *
 * @author racim
 * @author Rayan
 */
public class LogCapture {
    private JTextArea logArea;
    private PrintStream originalOut;
    private ByteArrayOutputStream logStream;

    /**
     * <p>Constructor for LogCapture.</p>
     *
     * @param logArea a {@link javax.swing.JTextArea} object
     */
    public LogCapture(JTextArea logArea) {
        this.logArea = logArea;
        this.originalOut = System.out;
    }

    /**
     * <p>startCapture.</p>
     */
    public void startCapture() {
        logStream = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(logStream);
        System.setOut(newOut);
    }

    /**
     * <p>stopCapture.</p>
     */
    public void stopCapture() {
        System.setOut(originalOut);
        logArea.setText(logStream.toString());
    }
}
