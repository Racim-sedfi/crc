package crc.Models;

import java.util.Scanner;

 
/**
 * <p>CrcCalcul class.</p>
 *
 * @author racim
 * @author Rayan
 */
public class CrcCalcul {
	// Méthode pour effectuer une division binaire correcte
    /**
     * <p>divisionBinaire.</p>
     *
     * @param dividende a {@link java.lang.String} object
     * @param diviseur a {@link java.lang.String} object
     * @return a {@link java.lang.String} object
     */
    public static String divisionBinaire(String dividende, String diviseur) {
        int longueurDiviseur = diviseur.length();
        StringBuilder temp = new StringBuilder(dividende.substring(0, longueurDiviseur));
        System.out.println("diviseur = " + diviseur);
        int longueur = dividende.length();
        int cmpt = 0;
        while (longueurDiviseur < longueur) { 
            if (temp.charAt(0) == '1') {
                temp = new StringBuilder(xor(temp.toString(), diviseur));
            } else {
                temp = new StringBuilder(xor(temp.toString(), "0".repeat(diviseur.length())));
            }
            temp.append(dividende.charAt(longueurDiviseur));
            temp.deleteCharAt(0); // Supprimer le bit de gauche après XOR
            longueurDiviseur += 1;
            System.out.println("Le reste de la division n°" + cmpt + " est : "+temp);
            cmpt++;
        }

        if (temp.charAt(0) == '1') {
            temp = new StringBuilder(xor(temp.toString(), diviseur));
        } else {
            temp = new StringBuilder(xor(temp.toString(), "0".repeat(diviseur.length())));
        }
        System.out.println("tmp = " + temp);
        return temp.substring(1); // Extraire uniquement le reste de la division
    }

    // Fonction XOR entre deux chaînes binaires
    /**
     * <p>xor.</p>
     *
     * @param a a {@link java.lang.String} object
     * @param b a {@link java.lang.String} object
     * @return a {@link java.lang.String} object
     */
    public static String xor(String a, String b) {
        StringBuilder resultat = new StringBuilder();
        for (int i = 0; i < b.length(); i++) {
            resultat.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        }
        return resultat.toString();
    }

    // Méthode pour ajouter un CRC à un message avec détails des étapes
    /**
     * <p>genererCRC.</p>
     *
     * @param message a {@link java.lang.String} object
     * @param diviseur a {@link java.lang.String} object
     * @return a {@link java.lang.String} object
     */
    public static String genererCRC(String message, String diviseur) {
        int longueurDiviseur = diviseur.length() - 1;
        String messageAugmente = message + "0".repeat(longueurDiviseur);
        System.out.println("Message après ajout de " + longueurDiviseur + " zéros : " + messageAugmente);
        
        String reste = divisionBinaire(messageAugmente, diviseur);
        System.out.println("Reste après division binaire : " + reste);
        
        String messageFinal = message + reste;
        System.out.println("Message final avec CRC : " + messageFinal);
        
        return messageFinal;
    }

    // Méthode pour vérifier un message avec son CRC et afficher les étapes
    /**
     * <p>verifierCRC.</p>
     *
     * @param message a {@link java.lang.String} object
     * @param diviseur a {@link java.lang.String} object
     * @return a boolean
     */
    public static boolean verifierCRC(String message, String diviseur) {
        System.out.println("Vérification du message reçu : " + message);
        String reste = divisionBinaire(message, diviseur);
        System.out.println("Reste après division : " + reste);
        
        boolean estValide = reste.replace("0", "").isEmpty();
        if (estValide) {
            System.out.println("Le message est valide (pas d'erreurs détectées).");
        } else {
            System.out.println("Le message contient des erreurs !");
        }
        return estValide;
    }

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez un message binaire : ");
        String message = scanner.next();
        
        System.out.print("Entrez un polynôme générateur binaire : ");
        String diviseur = scanner.next();

        // Génération du CRC avec étapes détaillées
        String messageCRC = genererCRC(message, diviseur);
        System.out.println("Message avec CRC : " + messageCRC);

        // Vérification du message reçu avec étapes détaillées
        System.out.print("Entrez un message reçu pour vérification : ");
        String messageRecu = scanner.next();
        verifierCRC(messageRecu, diviseur);
    }
}
