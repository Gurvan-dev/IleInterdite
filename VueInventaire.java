import java.awt.*;
import javax.swing.*;

public class VueInventaire extends JPanel implements Observer {
    Modele modele;
    IconButton donneObjetBoutton;
    InventaireButton[] inventaire;
    Joueur currentJoueur;

    public VueInventaire(Modele modele, Joueur currentJoueur) {
        this.modele = modele;
        this.currentJoueur = currentJoueur;
        this.setLayout(
                new GridLayout(GameSettings.JOUEUR_TAILLE_INVENTAIRE + 1, 1, GameSettings.OBJET_ESPACE,
                        GameSettings.OBJET_ESPACE));
        modele.addObserver(this);

        /* DONNE OBJET BOUTTON */

        donneObjetBoutton = new IconButton(modele, currentJoueur);
        donneObjetBoutton.setSize(GameSettings.OBJET_WIDTH, GameSettings.OBJET_WIDTH);
        this.add(donneObjetBoutton);

        /* BOUTON D'INVENTAIRE */

        inventaire = new InventaireButton[GameSettings.JOUEUR_TAILLE_INVENTAIRE];
        for (int i = 0; i < inventaire.length; i++) {
            inventaire[i] = new InventaireButton(modele, currentJoueur, i);
            inventaire[i].setSize(GameSettings.OBJET_WIDTH, GameSettings.OBJET_WIDTH);
            this.add(inventaire[i]);
        }

        Update();

        // Note : Comme le gridlayout stretch ses components, on doit bien vérifier
        // qu'il fait la hauteur que l'on souhaiterais
        Dimension dim = new Dimension(GameSettings.OBJET_WIDTH,
                GameSettings.OBJET_WIDTH * (GameSettings.JOUEUR_TAILLE_INVENTAIRE + 1));
        this.setPreferredSize(dim);

    }

    public void Update() {
    }
}
