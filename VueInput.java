import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VueInput extends JPanel implements Observer {
    private Modele modele;
    private JButton boutonHaut, boutonBas, boutonGauche, boutonDroite;
    private JButton boutonEndTurn;
    private JTextField currentInfo;

    public VueInput(Modele modele) {
        this.modele = modele;
        modele.addObserver(this);

        boutonHaut = new JButton("^");
        boutonBas = new JButton("v");
        boutonDroite = new JButton(">");
        boutonGauche = new JButton("<");
        boutonEndTurn = new JButton("End Turn");
        currentInfo = new JTextField("");

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
        boutonEndTurn.addActionListener(e -> {
            modele.EndTurn();
        });

        this.add(boutonHaut);
        this.add(boutonBas);
        this.add(boutonGauche);
        this.add(boutonDroite);
        this.add(boutonEndTurn);
        int CASE_TAILLE_TOTALE = VueMain.CASE_ESPACE + VueMain.CASE_TAILLE;
        Dimension dim = new Dimension(VueMain.INPUT_WIDTH, CASE_TAILLE_TOTALE * modele.plateau.taille.y);
        this.setPreferredSize(dim);

    }

    public void update() {
        currentInfo.setText("" + modele.GetCurrentPlayer());
    }

}
