import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class VueInput extends JPanel implements Observer {
    private Modele modele;
    private JButton boutonHaut, boutonBas, boutonGauche, boutonDroite;
    private JButton boutonEndTurn;
    private JCheckBox boutonAsseche;
    private JTextField joueurActuel;
    private JTextField actionRestante;

    public VueInput(Modele modele) {
        this.modele = modele;
        modele.addObserver(this);
        int CASE_TAILLE_TOTALE = VueMain.CASE_ESPACE + VueMain.CASE_TAILLE;

        /* Gestion des boutons & inputs */
        // Note : BoutonHaut et boutonBas sont inversé pour l'utilisateur car la
        // vuePlateau est 'inversée', i.e. le bas correspond au haut.
        boutonHaut = new JButton("v");
        boutonBas = new JButton("^");
        boutonDroite = new JButton(">");
        boutonGauche = new JButton("<");
        boutonEndTurn = new JButton("End Turn");
        joueurActuel = new JTextField("");
        actionRestante = new JTextField("");

        boutonAsseche = new JCheckBox("Asseche");

        boutonHaut.addActionListener(e -> {
            modele.Direction(Dir.HAUT);
        });
        boutonBas.addActionListener(e -> {
            modele.Direction(Dir.BAS);
        });
        boutonGauche.addActionListener(e -> {
            modele.Direction(Dir.GAUCHE);
        });
        boutonDroite.addActionListener(e -> {
            modele.Direction(Dir.DROITE);
        });
        boutonEndTurn.addActionListener(e -> {
            modele.EndTurn();
        });
        boutonAsseche.addActionListener(e -> {
            modele.SetAsseche(boutonAsseche.isSelected());
        });

        /* Gestion du texte et info */
        actionRestante.setEditable(false);
        joueurActuel.setEditable(false);
        joueurActuel.setSize(VueMain.INPUT_WIDTH, CASE_TAILLE_TOTALE);

        Update();

        /* On ajoute tout en composants */
        this.add(boutonBas);
        this.add(boutonHaut);
        this.add(boutonGauche);
        this.add(boutonDroite);
        this.add(boutonEndTurn);
        this.add(boutonAsseche);

        this.add(joueurActuel);
        this.add(actionRestante);

        Dimension dim = new Dimension(VueMain.INPUT_WIDTH, CASE_TAILLE_TOTALE * modele.plateau.taille.y);
        this.setPreferredSize(dim);

    }

    public void Update() {
        joueurActuel.setText("Joueur actuel : " + modele.GetCurrentPlayer());
        actionRestante.setText("Action restante : " + modele.GetNombreAction());
    }

}
