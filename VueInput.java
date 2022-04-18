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
    private JButton[] inventaire;

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
        inventaire = new JButton[Modele.JOUEUR_TAILLE_INVENTAIRE];
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
        for (int i = 0; i < inventaire.length; i++) {
            inventaire[i] = new JButton();
            inventaire[i].setSize(VueMain.INPUT_WIDTH, CASE_TAILLE_TOTALE);
            int num = i; // Obligatoire car Java n'est pas content sans
            inventaire[i].addActionListener(e -> {
                modele.UtiliseObjet(num);
            });
        }
        Update();

        /* On ajoute tout en composants */
        this.add(boutonBas);
        this.add(boutonHaut);
        this.add(boutonGauche);
        this.add(boutonDroite);
        this.add(boutonEndTurn);
        this.add(boutonAsseche);
        for (int i = 0; i < inventaire.length; i++) {
            this.add(inventaire[i]);
        }
        this.add(joueurActuel);
        this.add(actionRestante);

        Dimension dim = new Dimension(VueMain.INPUT_WIDTH, CASE_TAILLE_TOTALE * modele.plateau.taille.y);
        this.setPreferredSize(dim);

    }

    public void Update() {
        joueurActuel.setText("Joueur actuel : " + modele.GetCurrentPlayer());
        actionRestante.setText("Action restante : " + modele.GetNombreAction());
        Joueur currentJoueur = modele.GetCurrentJoueur();
        if (currentJoueur != null) {
            int i = 0;
            for (Objet o : currentJoueur.inventaire) {
                inventaire[i].setText("" + o);
                i++;
            }
            for (int j = i; j < inventaire.length; j++) {
                inventaire[j].setText("---------------------");
            }

        }
    }

}
