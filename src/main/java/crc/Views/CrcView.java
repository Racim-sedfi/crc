package crc.Views;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * <p>CrcView class.</p>
 *
 * @author racim
 * @author Rayan
 */
public class CrcView extends JFrame {
    /**
     * <p>Constructor for CrcView.</p>
     */
    public CrcView() {
        super("CRC - Vérification et Génération");
        initMenu();
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        JMenuItem genererItem = new JMenuItem("Générer");
        genererItem.addActionListener(e -> new CrcGenerationView());

        JMenuItem verifierItem = new JMenuItem("Vérifier");
        verifierItem.addActionListener(e -> new CrcVerificationView());
        JMenuItem quitterItem = new JMenuItem("Quitter");

        
        quitterItem.addActionListener(e -> System.exit(0));

 
        
        menu.add(genererItem);
        menu.add(verifierItem);
        menu.add(quitterItem);
        menuBar.add(menu);

        setJMenuBar(menuBar);
    }


}
