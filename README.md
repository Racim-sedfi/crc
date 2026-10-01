# crc — Génération et vérification de codes CRC

Application Java Swing permettant de **générer** le code de redondance cyclique (CRC) d'un
message binaire à partir d'un polynôme générateur, puis de **vérifier** l'intégrité d'un
message reçu. Le détail de chaque étape de la division binaire (XOR successifs) est affiché
pour suivre le calcul.

## Technologies

- Java 17
- Maven
- Swing (interface graphique)

## Fonctionnalités principales

- **Génération** : ajout de *n − 1* zéros au message (n = longueur du polynôme), division
  binaire modulo 2, concaténation du reste au message.
- **Vérification** : division du message reçu par le polynôme ; le message est valide si le
  reste est nul.
- Journal des étapes intermédiaires de la division affiché dans l'interface
  (capture de la sortie console).
- Mode console disponible via la classe `CrcCalcul`.

## Structure du projet (architecture MVC)

```
├── pom.xml
└── src/main/java/crc
    ├── Main.java                       # Point d'entrée (interface graphique)
    ├── Models/CrcCalcul.java           # Division binaire, génération et vérification du CRC
    ├── Views/CrcView.java              # Fenêtre principale (menu Générer / Vérifier / Quitter)
    ├── Views/CrcGenerationView.java    # Fenêtre de génération
    ├── Views/CrcVerificationView.java  # Fenêtre de vérification
    └── Controllers/LogCapture.java     # Redirection de la sortie console vers l'interface
```

## Compilation et exécution

Prérequis : JDK 17 et Maven.

```bash
mvn compile
java -cp target/classes crc.Main               # interface graphique
java -cp target/classes crc.Models.CrcCalcul   # version console
```

Exemple : message `11010011101100`, polynôme `1011` → reste `100`,
message transmis `11010011101100100`.

## Documentation

La Javadoc peut être générée avec :

```bash
mvn javadoc:javadoc    # résultat dans target/reports/apidocs/
```

## Auteurs

Racim Sedfi et Rayan (travail en binôme)
