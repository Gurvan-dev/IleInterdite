import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class IconButton extends JButton implements Observer {
    private JPanel img;
    private Modele modele;
    private Joueur currentJoueur;

    public IconButton(Modele modele, Joueur currentJoueur) {
        super();
        this.modele = modele;
        this.currentJoueur = currentJoueur;

        modele.addObserver(this);

        this.addActionListener(e -> {
            modele.DonneObjet(currentJoueur);
        });
        this.setSize(GameSettings.OBJET_WIDTH, GameSettings.OBJET_WIDTH);
        this.setBorderPainted(false);

        img = new JPanel();
        img.setOpaque(false);
        img.setPreferredSize(this.getSize());
        this.add(img);
    }

    public JPanel getImage() {
        return img;
    }

    protected void paintComponent(Graphics g) {
        super.repaint();

        BufferedImage img = currentJoueur.getIcon();
        g.drawImage(img, 0, 0, GameSettings.OBJET_WIDTH,
                GameSettings.OBJET_WIDTH, 0, 0,
                img.getWidth(),
                img.getHeight(), null);

        Graphics2D g2d = (Graphics2D) g;
        if (modele.GetCurrentJoueur() == currentJoueur) {
            g2d.setStroke(new BasicStroke(GameSettings.PLATEAU_SELECT_WIDTH));
            g2d.setColor(GameSettings.PLATEAU_SELECT_COULEUR);
            g2d.drawRect(0, 0, this.getWidth(), this.getHeight());
        }

    }

    public void Update() {
        repaint();
    }
}