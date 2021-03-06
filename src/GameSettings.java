import java.awt.*;

public class GameSettings {

    /* PARAMETRE PLATEAU */
    static Vector2 PLATEAU_TAILLE = new Vector2(6, 6);

    /* PARAMETRE JOUEUR */
    static int JOUEUR_NOMBRE = 4;
    static int JOUEUR_NOMBRE_ACTION = 3;
    static int JOUEUR_TAILLE_INVENTAIRE = 5;

    /* PARAMETRE PARTIE */
    static int PARTIE_NOMBRE_INONDATION = 3; // Nombre d'inondation a la fin de chaque tour
    static int PARTIE_NOMBRE_CARTE_PIOCHE = 2;
    static boolean PARTIE_AUTO_END_TURN = false;
    static int PARTIE_NOMBRE_CLEF_POUR_ARTEFACT = 1;
    static int PARTIE_NIVEAU_EAU_MAX = 10;
    static int PARTIE_NOMBRE_ARTEFACT_REQUIS = 4;

    /* PARAMETRE DECKS */
    static Integer[] DECK_TERRAIN = new Integer[] { 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
            5,
            5,
            5, 5, 5,
            5, 5, 5, 5, 5, 5, 5 };

    /*
     * Le deck item actuel représente fidèlement le jeu de base a savoir qu'il est
     * composé de 28 cartes avec:
     * - 5 pour chacune des 4 trésors
     * - 3 cartes montée des eaux
     * - 3 cartes Hélicoptère
     * - 2 cartes sac de sable
     */
    static Objet[] DECK_ITEM = new Objet[] {
            new Clef(CaseType.BISOUS),
            new Clef(CaseType.BISOUS),
            new Clef(CaseType.BISOUS),
            new Clef(CaseType.BISOUS),
            new Clef(CaseType.BISOUS),
            new Clef(CaseType.GEOMETRIQUE),
            new Clef(CaseType.GEOMETRIQUE),
            new Clef(CaseType.GEOMETRIQUE),
            new Clef(CaseType.GEOMETRIQUE),
            new Clef(CaseType.GEOMETRIQUE),
            new Clef(CaseType.ROYAL),
            new Clef(CaseType.ROYAL),
            new Clef(CaseType.ROYAL),
            new Clef(CaseType.ROYAL),
            new Clef(CaseType.ROYAL),
            new Clef(CaseType.CHOCOLAT),
            new Clef(CaseType.CHOCOLAT),
            new Clef(CaseType.CHOCOLAT),
            new Clef(CaseType.CHOCOLAT),
            new Clef(CaseType.CHOCOLAT),
            new MonteeEau(),
            new MonteeEau(),
            new MonteeEau(),
            new Helicoptere(),
            new Helicoptere(),
            new Helicoptere(),
            new SacSable(),
            new SacSable() };

    /* PARAMETRE AFFICHAGE PLATEAU */
    static Color PLATEAU_COULEUR = new Color(9, 168, 205);
    static Color PLATEAU_SELECT_COULEUR = Color.green; // La couleur pour mettre en avant la case ou le joueur
                                                       // qui joue actuellement se situe
    static int PLATEAU_SELECT_WIDTH = 15;

    /* PARAMETRE VUE : CASE */
    static int CASE_TAILLE = 120; // Taille de chaque cases de terrain
    static int CASE_ESPACE = 5; // Espace entre les cases.
    static int INPUT_WIDTH = 90; // Taille de la barre a droite ou il y a les boutons
    static int EAU_WIDTH = 50; //
    static int OBJET_ESPACE = 2; // Espace entre chaque case d'objet
    static int OBJET_WIDTH = 115; // Taille de chaque case d'objets
    static float WATER_OVERLAY_OPACITY = 0.75f;

    /* PARAMETRE MAIN MENU */
    static int MM_TAILLE_X = 750;
    static int MM_TAILLE_Y = 500;

    /*
     * @desc Resize la fenêtre en multipliant la taille de tout les élements par
     * 'factor'
     * 
     * @param factor Le facteur a laquelle rescale la fenêtre
     */
    static void changeGameScale(float factor) {
        CASE_TAILLE *= factor;
        CASE_ESPACE *= factor;
        INPUT_WIDTH *= factor;
        EAU_WIDTH *= factor;
        OBJET_ESPACE *= factor;
        OBJET_WIDTH *= factor;
        PLATEAU_SELECT_WIDTH *= factor;
    }
}
