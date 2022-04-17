public class Modele extends Observable {

    /* PARAMETRE PLATEAU */
    static Vector2 PLATEAU_TAILLE = new Vector2(20, 20);

    /* PARAMETRE JOUEUR */
    static int JOUEUR_NOMBRE = 4;
    static int JOUEUR_NOMBRE_ACTION = 3;
    static int JOUEUR_TAILLE_INVENTAIRE = 5;

    /* PARAMETRE PARTIE */
    static int PARTIE_NOMBRE_INONDATION = 3; // Nombre d'inondation a la fin de chaque tour
    static boolean PARTIE_AUTO_END_TURN = true;

    public final Plateau plateau;
    public final Joueur[] joueurs;

    private int currentPlayer;
    private int actionRestante;

    public Modele() {
        plateau = new Plateau(PLATEAU_TAILLE);
        joueurs = new Joueur[JOUEUR_NOMBRE];
        for (int i = 0; i < joueurs.length; i++)
            joueurs[i] = new Joueur(this, JOUEUR_TAILLE_INVENTAIRE, Vector2.zero, i);
        currentPlayer = -1;
        EndTurn();
    }

    void EndTurn() {
        currentPlayer++;
        currentPlayer %= joueurs.length;
        System.out.println(currentPlayer);
        actionRestante = JOUEUR_NOMBRE_ACTION;
        Inonde();
        notifyObservers();
    }

    void Inonde() {
        for (int i = 0; i < PARTIE_NOMBRE_INONDATION; i++) {
            plateau.Inonde();
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

    public void Haut() {
        EndTurn();
    }

    public void Bas() {
        EndTurn();
    }

    public void Gauche() {
        EndTurn();
    }

    public void Droite() {
        EndTurn();
    }

    public void Asseche() {
        EndTurn();
    }

}
