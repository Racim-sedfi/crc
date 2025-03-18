package crc.Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import crc.Controllers.LogCapture;
import crc.Models.CrcCalcul;

/**
 * <p>CrcGenerationView class.</p>
 *
 * @author racim
 * @author Rayan
 */
public class CrcGenerationView extends JFrame {
    private JTextField messageField, diviseurField;
    private JTextArea resultArea, logArea;

    /**
     * <p>Constructor for CrcGenerationView.</p>
     */
    public CrcGenerationView() {
        super("Génération CRC");
        initMenus();
        init();
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void initMenus() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Options");
         
        JMenuItem quitterItem = new JMenuItem("Quitter");

       
        quitterItem.addActionListener(e -> System.exit(0));

 
        menu.add(quitterItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void init() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Message binaire :"));
        messageField = new JTextField();
        inputPanel.add(messageField);

        inputPanel.add(new JLabel("Polynôme générateur :"));
        diviseurField = new JTextField();
        inputPanel.add(diviseurField);

        JButton genererButton = new JButton("Générer CRC");
        inputPanel.add(genererButton);

        add(inputPanel, BorderLayout.NORTH);

        resultArea = new JTextArea(3, 50);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("SansSerif", Font.BOLD, 14));
        resultArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        logArea = new JTextArea(8, 50);
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(logArea), BorderLayout.SOUTH);

        genererButton.addActionListener(e -> genererCRC());
    }

    private void genererCRC() {
        String message = messageField.getText().trim();
        String diviseur = diviseurField.getText().trim();

        if (message.isEmpty() || diviseur.isEmpty()) {
            resultArea.setText("Veuillez remplir tous les champs.");
            return;
        }

        logArea.setText("");

        LogCapture logCapture = new LogCapture(logArea);
        logCapture.startCapture();

        String messageCRC = CrcCalcul.genererCRC(message, diviseur);

        logCapture.stopCapture();

        resultArea.setText("Message avec CRC : " + messageCRC);
    }

 
}
