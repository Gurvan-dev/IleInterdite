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
    private final Vector2 heliportPos;

    public Modele() {
        newJoueurCount = randomizer.nextInt(5);
        plateau = new Plateau(GameSettings.PLATEAU_TAILLE);
        heliportPos = plateau.getHeliportPos();
        joueurs = new Joueur[GameSettings.JOUEUR_NOMBRE];
        itemDeck = new Deck<Objet>(GameSettings.DECK_ITEM);
        itemDeck.Melange();
        for (int i = 0; i < joueurs.length; i++)
            joueurs[i] = newJoueur(i);
        niveauEauActuel = GameSettings.PARTIE_NOMBRE_INONDATION;
        EndTurn();
    }

    /* Gestion de la simulation */
    void EndTurn() {

        for (int i = 0; i < joueurs.length; i++)
            if (plateau.GetCase(joueurs[i].pos).etat == CaseEtat.SUBMERGE) // Pour éviter qu'on puisse finir le tour
                                                                           // avec le plongeur sous l'eau
                return;
        // On réinitialise l'utilisation d'objet entre les tours pour éviter de
        // missclick.
        objNumber = -1;
        objJoueur = null;
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

        /* Si l'héliport a coulé, c'est fini. */
        if (plateau.GetCase(heliportPos).etat == CaseEtat.SUBMERGE) {
            EndGame(false);
        }

        /* On passe au joueur d'après */
        currentPlayer++;
        currentPlayer %= joueurs.length;
        joueurs[currentPlayer].StartTurn();
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
                    // On revérifie ici si l'état n'estpas submergé car le plongeurpourrait se
                    // déplacer quand même sinon
                    if (plateau.InBounds(newPos)
                            && plateau.GetCase(newPos).etat != CaseEtat.SUBMERGE
                            && j.Move(newPos, true)) {
                        reussite = true;
                    }
                    cur = (cur + 1) % 4;
                }
                if (!reussite)
                    EndGame(false);
            }

        }
    }

    /*
     * Considère que tout les joueurs sont déjà sur la case héliport
     */
    void VerifieVictoire() {
        ArrayList<CaseType> dejaEu = new ArrayList<CaseType>();
        for (int i = 0; i < joueurs.length; i++) {
            for (Objet o : joueurs[i].inventaire) {
                if (o instanceof Clef) {
                    Clef c = (Clef) o;
                    if (!dejaEu.contains(c.type)) {
                        dejaEu.add(c.type);
                    }
                }
            }
        }
        if (dejaEu.size() == GameSettings.PARTIE_NOMBRE_ARTEFACT_REQUIS) {
            EndGame(true);
        }
    }

    public void EffectueAction(int numJoueur, int coutAction) {
        objJoueur = null;
        objNumber = -1;
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
            EndGame(false);
        notifyObservers();
    }

    /**
     * @param won true si la partie a été gagnée, false sinon
     **/
    public void EndGame(boolean won) {
        WindowManager.LaunchEndMenu(won);
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

    // Jette l'objet séléctionné a la poubelle
    public void Poubelle() {
        if (objNumber >= 0 && objJoueur != null) {
            objJoueur.removeItem(objNumber);
            objNumber = -1;
            objJoueur = null;
        }
    }

    // Donne l'objet séléctionné au joueur en parametre
    public void DonneObjet(Joueur joueur) {
        if (objNumber >= 0 && objJoueur != null)
            objJoueur.DonneObjet(objNumber, joueur);
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

    public Joueur GetObjJoueur() {
        return objJoueur;
    }

    public int GetObjNumber() {
        return objNumber;
    }

    static int newJoueurCount = 0;

    private Joueur newJoueur(int jNumber) {
        newJoueurCount = (newJoueurCount + 1) % 5;
        switch (newJoueurCount) {
            case 0:
                return new Explorateur(this, GameSettings.JOUEUR_TAILLE_INVENTAIRE, plateau.GetSpawnPoint(), jNumber);
            case 1:
                return new Ingenieur(this, GameSettings.JOUEUR_TAILLE_INVENTAIRE, plateau.GetSpawnPoint(), jNumber);
            case 2:
                return new Messager(this, GameSettings.JOUEUR_TAILLE_INVENTAIRE, plateau.GetSpawnPoint(), jNumber);
            case 3:
                return new Pilote(this, GameSettings.JOUEUR_TAILLE_INVENTAIRE, plateau.GetSpawnPoint(), jNumber);
            case 4:
                return new Plongeur(this, GameSettings.JOUEUR_TAILLE_INVENTAIRE, plateau.GetSpawnPoint(), jNumber);
            default:
                return new Ingenieur(this, GameSettings.JOUEUR_TAILLE_INVENTAIRE, plateau.GetSpawnPoint(), jNumber);
        }
    }
}
