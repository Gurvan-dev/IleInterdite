import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class ImageLoader {

    static BufferedImage pion1;

    /* Objets */
    static BufferedImage objet_placeholder;
    static BufferedImage objet_montee_eau;
    static BufferedImage objet_helicoptere;
    // ! Objet clef et objet artefacts doivent être de même taille
    static BufferedImage[] objet_clef = new BufferedImage[4];
    static BufferedImage[] objet_artefacts = new BufferedImage[4];
    static BufferedImage objet_sac_sable;

    /* Personnages */
    static BufferedImage personnage_placeholder_icon;
    static BufferedImage personnage_ingenieur_icone;
    static BufferedImage personnage_pilote_icone;
    static BufferedImage personnage_explorateur_icone;
    static BufferedImage personnage_plongeur_icone;
    static BufferedImage personnage_messager_icone;

    private static final String filePath = "img/";

    static void Setup() {
        try {
            pion1 = ImageIO.read(new File(filePath + "pion1.png"));

            // LECTURE OBJET
            objet_placeholder = ImageIO.read(new File(filePath + "Objet_Helicoptr.png"));
            objet_sac_sable = ImageIO.read(new File(filePath + "Objet_SacSable.png"));
            objet_montee_eau = objet_placeholder;
            objet_helicoptere = ImageIO.read(new File(filePath + "Objet_Helicoptr.png"));
            for (int i = 0; i < objet_clef.length; i++) {
                objet_clef[i] = ImageIO.read(new File(filePath + "Objet_Clef" + (i + 1) + ".png"));
                objet_artefacts[i] = ImageIO.read(new File(filePath + "Objet_Artefact" + (i + 1) + ".png"));
            }

            // LECTURE PERSONNAGE
            personnage_placeholder_icon = ImageIO.read(new File(filePath + "Personnage_Explorateur_Icon.png"));
            personnage_explorateur_icone = ImageIO.read(new File(filePath + "Personnage_Explorateur_Icon.png"));
            personnage_ingenieur_icone = ImageIO.read(new File(filePath + "Personnage_Ingenieur_Icon.png"));
            personnage_messager_icone = ImageIO.read(new File(filePath + "Personnage_Messager_Icon.png"));
            personnage_pilote_icone = ImageIO.read(new File(filePath + "Personnage_Pilote_Icon.png"));
            personnage_plongeur_icone = ImageIO.read(new File(filePath + "Personnage_Plongeur_Icon.png"));

        } catch (IOException e) {
            System.out.println("Erreur lors du chargement des images.");
        }
    }
}
