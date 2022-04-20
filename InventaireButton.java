import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class InventaireButton extends JButton implements Observer {
    private JPanel img;
    private Modele modele;
    private Joueur currentJoueur;
    private int objNumber;

    public InventaireButton(Modele modele, Joueur currentJoueur, int objNumber) {
        super();
        this.modele = modele;
        modele.addObserver(this);
        this.currentJoueur = currentJoueur;
        this.objNumber = objNumber;
        img = new JPanel();
        img.setOpaque(false);
        img.setPreferredSize(this.getSize());
        this.addActionListener(e -> {
            modele.UtiliseObjet(objNumber, currentJoueur);
        });
        this.add(img);
    }

    public JPanel getImage() {
        return img;
    }

    protected void paintComponent(Graphics g) {
        super.repaint();
        Objet o = currentJoueur.getItem(objNumber);
        if (o == null) {
            g.clearRect(0, 0, this.getWidth(), this.getHeight());
            g.dispose();
        } else {
            BufferedImage img = o.toImage();
            g.drawImage(img, 0, 0, VueMain.OBJET_WIDTH,
                    VueMain.OBJET_WIDTH, 0, 0,
                    img.getWidth(),
                    img.getHeight(), null);
        }
        /* Si pas d'objet : Dispose, sinon dessiner. */
    }

    public void Update() {
        repaint();
    }
}
