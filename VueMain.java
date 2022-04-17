import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VueMain implements Observer {

    /* PARAMETRE CASE */
    public static final int CASE_TAILLE = 100; // Taille de la cases.
    public static final int CASE_ESPACE = 10; // Espace entre les cases.
    public static final int INPUT_WIDTH = 100;

    private JFrame frame;

    private VuePlateau vuePlateau;
    private VueInput vueInput;

    private Modele modele;

    public VueMain(Modele modele) {
        this.modele = modele;
        modele.addObserver(this);

        /* Mise en place des différentes vues */
        vuePlateau = new VuePlateau(modele);
        vueInput = new VueInput(modele);

        /* Fenêtre. */
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setTitle("L'île interdite.");
        frame.add(vuePlateau);
        frame.add(vueInput);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void update() {
    }

}
