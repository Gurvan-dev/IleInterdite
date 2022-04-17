import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VueInput extends JPanel implements Observer {
    private Modele modele;
    private Dimension dim = new Dimension(20, 100);

    public VueInput(Modele modele) {
        this.modele = modele;
        modele.addObserver(this);

        JButton boutonHaut = new JButton("^");
        JButton boutonBas = new JButton("v");
        JButton boutonGauche = new JButton("<");
        JButton boutonDroite = new JButton(">");

        boutonHaut.addActionListener(e -> {
            modele.Haut();
        });
        boutonBas.addActionListener(e -> {
            modele.Bas();
        });
        boutonGauche.addActionListener(e -> {
            modele.Gauche();
        });
        boutonDroite.addActionListener(e -> {
            modele.Droite();
        });

        this.add(boutonHaut);
        this.add(boutonBas);
        this.add(boutonGauche);
        this.add(boutonDroite);
        int CASE_TAILLE_TOTALE = VueMain.CASE_ESPACE + VueMain.CASE_TAILLE;
        Dimension dim = new Dimension(CASE_TAILLE_TOTALE, CASE_TAILLE_TOTALE * modele.plateau.taille.y);
        this.setPreferredSize(dim);

    }

    public void update() {
    }

}
