import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

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
        this.setBorderPainted(false);
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
        /* Si pas d'objet : Dispose, sinon dessiner. */
        if (o == null) {
            g.clearRect(0, 0, this.getWidth(), this.getHeight());
            g.dispose();
        } else {
            BufferedImage img = o.toImage();
            g.drawImage(img, 0, 0, GameSettings.OBJET_WIDTH,
                    GameSettings.OBJET_WIDTH, 0, 0,
                    img.getWidth(),
                    img.getHeight(), null);
            Graphics2D g2d = (Graphics2D) g;
            if (modele.GetObjJoueur() == currentJoueur && modele.GetObjNumber() == objNumber) {
                g2d.setStroke(new BasicStroke(GameSettings.PLATEAU_SELECT_WIDTH));
                g2d.setColor(GameSettings.PLATEAU_SELECT_COULEUR);
                g2d.drawRect(0, 0, this.getWidth(), this.getHeight());
            }
        }

    }

    public void Update() {
        repaint();
    }
}
