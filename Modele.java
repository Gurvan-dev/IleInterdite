import java.awt.*;
import java.util.*;

public class Modele extends Observable {

    /* PARAMETRE PLATEAU */
    static Vector2 PLATEAU_TAILLE = new Vector2(6, 6);

    /* PARAMETRE JOUEUR */
    static int JOUEUR_NOMBRE = 4;
    static int JOUEUR_NOMBRE_ACTION = 3;
    static int JOUEUR_TAILLE_INVENTAIRE = 5;

    /* PARAMETRE PARTIE */
    static int PARTIE_NOMBRE_INONDATION = 3; // Nombre d'inondation a la fin de chaque tour
    static int PARTIE_NOMBRE_CARTE_PIOCHE = 2;
    static boolean PARTIE_AUTO_END_TURN = true;
    static int PARTIE_NOMBRE_CLEF_POUR_ARTEFACT = 1;
    static int PARTIE_NIVEAU_EAU_MAX = 10;

    /* PARAMETRE DECKS */
    static Integer[] DECK_TERRAIN = new Integer[] { 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
            5,
            5,
            5, 5, 5,
            5, 5, 5, 5, 5, 5, 5 };
    static Objet[] DECK_ITEM = new Objet[] { new Clef(CaseType.FEU), new Clef(CaseType.PIERRE),
            new Clef(CaseType.TERRE), new Clef(CaseType.VENT), new MonteeEau() };

    public final Plateau plateau;
    public final Joueur[] joueurs;
    private Deck<Objet> itemDeck;

    private Random randomizer = new Random();

    private int currentPlayer = -1;
    private int actionRestante;
    private boolean asseche = false;
    private int objNumber = -1;

    private int niveauEauActuel = 2;

    public Modele() {
        plateau = new Plateau(PLATEAU_TAILLE);
        joueurs = new Joueur[JOUEUR_NOMBRE];
        itemDeck = new Deck<Objet>(DECK_ITEM);
        itemDeck.Melange();
        for (int i = 0; i < joueurs.length; i++)
            joueurs[i] = new Joueur(this, JOUEUR_TAILLE_INVENTAIRE, Vector2.zero, i);
        niveauEauActuel = PARTIE_NOMBRE_INONDATION;
        EndTurn();
    }

    /* Gestion de la simulation */
    void EndTurn() {

        /* Ajout d'item au joueur qui vient de finir son tour */
        if (currentPlayer >= 0) // On n'ajoute pas d'Objets si le joueur est -1 (En début de partie)
            for (int i = 0; i < niveauEauActuel; i++) {
                Objet item = itemDeck.RetireTop();
                if (item.autoUse()) {
                    item.utiliseObjet(joueurs[currentPlayer], plateau.GetCase(joueurs[currentPlayer].pos), this);
                } else {
                    joueurs[currentPlayer].ajouteItem(item);
                }
            }

        /* Inondation du terrain */
        Inonde();

        /* On passe au joueur d'après */
        currentPlayer++;
        currentPlayer %= joueurs.length;
        actionRestante = JOUEUR_NOMBRE_ACTION;

        notifyObservers();
    }

    void Inonde() {
        for (int i = 0; i < PARTIE_NOMBRE_INONDATION; i++) {
            plateau.Inonde();
        }
        // On va vérifier si un des joueur est dans une case qui a été submergé. Si
        // c'est le cas on va le déplacer dans une direction au hasard et si il n'y en a
        // pas a proximtié, c'est GameOver.
        for (Joueur j : joueurs) {
            if (plateau.GetCase(j.pos).etat == CaseEtat.SUBMERGE) {
                int first = randomizer.nextInt(4);
                int cur = (first + 1) % 4;
                boolean reussite = false;
                while (cur != first && !reussite) {
                    Vector2 newPos = j.pos.copy();
                    newPos.plus(Vector2.FromDir(Dir.FromInt(cur)));
                    if (j.Move(newPos))
                        reussite = true;
                    cur = (cur + 1) % 4;
                }
                if (!reussite)
                    GameOver();

            }

        }
    }

    public void EffectueAction(int numJoueur, int coutAction) {
        if (coutAction <= actionRestante && numJoueur == currentPlayer) {
            actionRestante -= coutAction;
        }
        if (actionRestante <= 0 && PARTIE_AUTO_END_TURN) {
            EndTurn();
        }
        notifyObservers();
    }

    public void MonteeEau() {
        niveauEauActuel++;
        if (niveauEauActuel >= PARTIE_NIVEAU_EAU_MAX)
            GameOver();
        notifyObservers();
    }

    /*
     * Appelée quand la partie est terminée, les joueurs ont perdu
     */
    public void GameOver() {
        System.out.println("C'est perdu.");
    }

    /*
     * Appelée quand la partie est terminée, les joueurs ont gagné
     */
    public void Bravo() {
        System.out.println("Bravo!!!!!!!! bogoss");
    }

    /* Gestion d'input */

    // Cliqué sur une direction
    public void Direction(Dir direction) {
        Vector2 newPos = joueurs[currentPlayer].pos.copy();
        newPos.plus(Vector2.FromDir(direction));
        Case(newPos);
    }

    // Cliqué sur une case
    public void Case(Vector2 pos) {
        if (objNumber >= 0) {
            if (!joueurs[currentPlayer].UtiliseObjet(objNumber, pos))
                objNumber = -1;
        } else if (asseche) {
            joueurs[currentPlayer].Asseche(pos);
        } else {
            joueurs[currentPlayer].Move(pos);
        }
    }

    // Cliqué sur le bouton pour activer / Désactiver le mode 'Assecher'
    public void SetAsseche(boolean newStatus) {
        asseche = newStatus;
    }

    // Cliqué sur le bouton pour utiliser l'objet numero num
    public void UtiliseObjet(int num) {
        System.out.println(num);
        objNumber = num;
    }

    /* GETTERS */
    public int GetCurrentPlayer() {
        return currentPlayer;
    }

    public int GetNombreAction() {
        return actionRestante;
    }

    public Joueur GetCurrentJoueur() {
        return currentPlayer >= 0 ? joueurs[currentPlayer] : null;
    }

    public ArrayList<Joueur> GetJoueursOnCase(Vector2 pos) {
        ArrayList<Joueur> returned = new ArrayList<Joueur>();
        for (int i = 0; i < joueurs.length; i++) {
            if (joueurs[i].pos.equals(pos))
                returned.add(joueurs[i]);
        }
        return returned;
    }

    public int GetNombreJoueurSurCase(Vector2 pos) {
        return GetJoueursOnCase(pos).size();
    }

    public int GetNiveauEau() {
        return niveauEauActuel;
    }
}
