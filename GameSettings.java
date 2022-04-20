import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class GameSettings {

    /* PARAMETRE PLATEAU */
    static Vector2 PLATEAU_TAILLE = new Vector2(6, 6);

    /* PARAMETRE JOUEUR */
    static final int JOUEUR_NOMBRE = 4;
    static final int JOUEUR_NOMBRE_ACTION = 3;
    static final int JOUEUR_TAILLE_INVENTAIRE = 5;

    /* PARAMETRE PARTIE */
    static int PARTIE_NOMBRE_INONDATION = 3; // Nombre d'inondation a la fin de chaque tour
    static final int PARTIE_NOMBRE_CARTE_PIOCHE = 2;
    static final boolean PARTIE_AUTO_END_TURN = true;
    static final int PARTIE_NOMBRE_CLEF_POUR_ARTEFACT = 1;
    static final int PARTIE_NIVEAU_EAU_MAX = 10;

    /* PARAMETRE DECKS */
    static Integer[] DECK_TERRAIN = new Integer[] { 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
            5,
            5,
            5, 5, 5,
            5, 5, 5, 5, 5, 5, 5 };
    static Objet[] DECK_ITEM = new Objet[] { new Clef(CaseType.FEU), new Clef(CaseType.PIERRE),
            new Clef(CaseType.TERRE), new Clef(CaseType.VENT), new MonteeEau(), new Helicoptere(), new Helicoptere() };

    /* PARAMETRE AFFICHAGE PLATEAU */
    static Color PLATEAU_COULEUR = Color.blue;
    static Color PLATEAU_SELECT_COULEUR = Color.green; // La couleur pour mettre en avant la case ou le joueur
                                                       // qui joue actuellement se situe
    static int PLATEAU_SELECT_WIDTH = 20;

    /* PARAMETRE VUE : CASE */
    static final int CASE_TAILLE = 100; // Taille de chaque cases de terrain
    static final int CASE_ESPACE = 10; // Espace entre les cases.
    static final int INPUT_WIDTH = 125; // Taille de la barre a droite ou il y a les boutons
    static final int EAU_WIDTH = 100; //
    static final int OBJET_ESPACE = 10; // Espace entre chaque case d'objet
    static final int OBJET_WIDTH = 100; // Taille de chaque case d'objet
}
