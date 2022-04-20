import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VueMain implements Observer {

    /* PARAMETRE CASE */
    public static final int CASE_TAILLE = 100; // Taille de la cases.
    public static final int CASE_ESPACE = 10; // Espace entre les cases.
    public static final int INPUT_WIDTH = 125; // Taille de la barre a droite ou il y a les boutons
    public static final int EAU_WIDTH = 100;
    public static final int OBJET_ESPACE = 10;
    public static final int OBJET_WIDTH = 100;

    private JFrame frame;

    private VuePlateau vuePlateau;
    private VueInput vueInput;
    private VueEau vueEau;
    private VueInventaire[] vueInventaires;

    private Modele modele;

    public VueMain(Modele modele) {
        this.modele = modele;
        modele.addObserver(this);

        /* Mise en place des différentes vues */
        vuePlateau = new VuePlateau(modele);
        vueInput = new VueInput(modele);
        vueEau = new VueEau(modele);
        vueInventaires = new VueInventaire[Modele.JOUEUR_NOMBRE];
        for (int i = 0; i < Modele.JOUEUR_NOMBRE; i++)
            vueInventaires[i] = new VueInventaire(modele, modele.GetJoueur(i));

        /* Fenêtre. */
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setTitle("L'île interdite.");

        // frame.add(vueEau);
        frame.add(vueEau);
        frame.add(vuePlateau);
        frame.add(vueInput);
        for (int i = 0; i < vueInventaires.length; i++)
            frame.add(vueInventaires[i]);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void Update() {
    }

}
