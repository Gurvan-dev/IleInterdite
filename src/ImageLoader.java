import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class ImageLoader {

    static BufferedImage pion1;

    /* UI */
    static BufferedImage bravo, pasbravo;
    static BufferedImage mainTitle;
    static BufferedImage eclair, poubelle;

    /* Terrain */
    static BufferedImage[][] terrain_temple = new BufferedImage[4][2];
    static BufferedImage[] terrain_random = new BufferedImage[11];
    static BufferedImage terrain_heliport;
    static BufferedImage terrain_water_overlay;
    static int random_count;
    static int[] temple_count;

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
    static BufferedImage personnage_placeholder_pion;

    static BufferedImage personnage_ingenieur_icone;
    static BufferedImage personnage_ingenieur_pion;

    static BufferedImage personnage_pilote_icone;
    static BufferedImage personnage_pilote_pion;

    static BufferedImage personnage_explorateur_icone;
    static BufferedImage personnage_explorateur_pion;

    static BufferedImage personnage_plongeur_icone;
    static BufferedImage personnage_plongeur_pion;

    static BufferedImage personnage_messager_icone;
    static BufferedImage personnage_messager_pion;

    private static final String filePath = "img/";

    static void Setup() {
        random_count = 0;
        temple_count = new int[terrain_temple.length];
        for (int i = 0; i < temple_count.length; i++)
            temple_count[i] = 0;
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
            personnage_placeholder_pion = ImageIO.read(new File(filePath + "Personnage_Explorateur_Pion.png"));

            personnage_explorateur_icone = ImageIO.read(new File(filePath + "Personnage_Explorateur_Icon.png"));
            personnage_explorateur_pion = ImageIO.read(new File(filePath + "Personnage_Explorateur_Pion.png"));

            personnage_ingenieur_icone = ImageIO.read(new File(filePath + "Personnage_Ingenieur_Icon.png"));
            personnage_ingenieur_pion = ImageIO.read(new File(filePath + "Personnage_Ingenieur_Pion.png"));

            personnage_messager_icone = ImageIO.read(new File(filePath + "Personnage_Messager_Icon.png"));
            personnage_messager_pion = ImageIO.read(new File(filePath + "Personnage_Messager_Pion.png"));

            personnage_pilote_icone = ImageIO.read(new File(filePath + "Personnage_Pilote_Icon.png"));
            personnage_pilote_pion = ImageIO.read(new File(filePath + "Personnage_Pilote_Pion.png"));

            personnage_plongeur_icone = ImageIO.read(new File(filePath + "Personnage_Plongeur_Icon.png"));
            personnage_plongeur_pion = ImageIO.read(new File(filePath + "Personnage_Plongeur_Pion.png"));

            // LECTURE TERRAIN

            terrain_heliport = ImageIO.read(new File(filePath + "Terrain_Heliport.png"));
            terrain_water_overlay = ImageIO.read(new File(filePath + "Terrain_Water_Overlay.png"));

            for (int i = 0; i < terrain_random.length; i++) {
                terrain_random[i] = ImageIO.read(new File(filePath + "Terrain_Random" + (i + 1) + ".png"));
            }

            for (int i = 0; i < terrain_temple.length; i++) {
                for (int j = 0; j < terrain_temple[i].length; j++) {
                    terrain_temple[i][j] = ImageIO
                            .read(new File(filePath + "Terrain_Temple" + (i + 1) + "_" + (j + 1) + ".png"));
                }
            }

            // LECTURE UI

            bravo = ImageIO.read(new File(filePath + "Bravo.png"));
            pasbravo = ImageIO.read(new File(filePath + "PasBravo.png"));
            mainTitle = ImageIO.read(new File(filePath + "MainMenu_Logo.png"));
            eclair = ImageIO.read(new File(filePath + "UI_Eclair.png"));
            poubelle = ImageIO.read(new File(filePath + "UI_Trashcan.png"));

        } catch (IOException e) {
            System.out.println("Erreur lors du chargement des images.");
        }
    }

    static int getCaseID(CaseType type) {
        switch (type) {
            case HELIPORT:
                return 0;
            case BISOUS:
                temple_count[0] = (temple_count[0] + 1) % terrain_temple[0].length;
                return temple_count[0];
            case CHOCOLAT:
                temple_count[1] = (temple_count[1] + 1) % terrain_temple[1].length;
                return temple_count[1];
            case ROYAL:
                temple_count[2] = (temple_count[2] + 1) % terrain_temple[2].length;
                return temple_count[2];
            case GEOMETRIQUE:
                temple_count[3] = (temple_count[3] + 1) % terrain_temple[3].length;
                return temple_count[3];
            default: // NORMAL
                random_count = (random_count + 1) % terrain_random.length;
                return random_count;

        }
    }
}
