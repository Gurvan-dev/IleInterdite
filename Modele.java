import java.awt.*;
import java.util.*;

public class Modele extends Observable {

    public final Plateau plateau;
    public final Joueur[] joueurs;
    private Deck<Objet> itemDeck;

    private Random randomizer = new Random();

    private int currentPlayer = -1;
    private int actionRestante;
    private boolean asseche = false;
    private int objNumber = -1;
    private Joueur objJoueur = null;

    private int niveauEauActuel = 2;

    public Modele() {
        plateau = new Plateau(GameSettings.PLATEAU_TAILLE);
        joueurs = new Joueur[GameSettings.JOUEUR_NOMBRE];
        itemDeck = new Deck<Objet>(GameSettings.DECK_ITEM);
        itemDeck.Melange();
        for (int i = 0; i < joueurs.length; i++)
            joueurs[i] = new Joueur(this, GameSettings.JOUEUR_TAILLE_INVENTAIRE, plateau.GetSpawnPoint(), i);
        niveauEauActuel = GameSettings.PARTIE_NOMBRE_INONDATION;
        EndTurn();
    }

    /* Gestion de la simulation */
    void EndTurn() {

        /* Ajout d'item au joueur qui vient de finir son tour */
        if (currentPlayer >= 0) // On n'ajoute pas d'Objets si le joueur est -1 (En début de partie)
            for (int i = 0; i < GameSettings.PARTIE_NOMBRE_CARTE_PIOCHE; i++) {
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
        actionRestante = GameSettings.JOUEUR_NOMBRE_ACTION;

        notifyObservers();
    }

    void Inonde() {
        for (int i = 0; i < GameSettings.PARTIE_NOMBRE_INONDATION; i++) {
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
        if (actionRestante <= 0 && GameSettings.PARTIE_AUTO_END_TURN) {
            EndTurn();
        }
        notifyObservers();
    }

    public void MonteeEau() {
        niveauEauActuel++;
        if (niveauEauActuel >= GameSettings.PARTIE_NIVEAU_EAU_MAX)
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
        if (objNumber >= 0 && objJoueur != null) {
            if (!objJoueur.UtiliseObjet(objNumber, pos)) {
                objNumber = -1;
                objJoueur = null;
            }
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
    public void UtiliseObjet(int num, Joueur j) {
        objJoueur = j;
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

    public Joueur GetJoueur(int jNumber) {
        return joueurs[jNumber];
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
