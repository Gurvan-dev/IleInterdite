
import java.util.ArrayList;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class CaseButton extends JButton {

    private final Vector2 pos; // Position sur le plateau
    private JPanel img;
    private Modele modele;

    public CaseButton(Vector2 pos, Modele modele) {

        super();
        this.pos = pos;
        this.setBorderPainted(false);
        this.modele = modele;
        img = new JPanel();
        img.setOpaque(false);
        img.setPreferredSize(this.getSize());
        this.addActionListener(e -> {
            modele.Case(pos);
        });
        this.add(img);
    }

    public JPanel getImage() {
        return img;
    }

    protected void paintComponent(Graphics g) {
        super.repaint();

        Graphics2D g2d = (Graphics2D) g;
        /*
         * Couleur de la case
         * Color c = modele.plateau.GetCase(pos.x, pos.y).GetColor();
         * if (c.getAlpha() == 0) { // La case a coulée.
         * g.dispose();
         * return;
         * }
         * g.setColor(c);
         * g.fillRect(0, 0, this.getWidth(), this.getHeight());
         */
        if (modele.plateau.GetCase(pos.x, pos.y).etat == CaseEtat.SUBMERGE) {
            g.dispose();
            return;
        }

        BufferedImage caseGraphics = modele.plateau.GetCase(pos.x, pos.y).GetImage();
        g.drawImage(caseGraphics, 0, 0, GameSettings.CASE_TAILLE,
                GameSettings.CASE_TAILLE, 0, 0,
                caseGraphics.getWidth(),
                caseGraphics.getHeight(), null);
        // TODO : Draw water overlay here if INONDE

        /* Dessiner les pions sur la case... */
        ArrayList<Joueur> jList = modele.GetJoueursOnCase(pos);
        if (jList.contains(modele.GetCurrentJoueur())) {
            g2d.setStroke(new BasicStroke(GameSettings.PLATEAU_SELECT_WIDTH));
            g2d.setColor(GameSettings.PLATEAU_SELECT_COULEUR);
            g2d.drawRect(0, 0, this.getWidth(), this.getHeight());
        }
        if (jList.size() > 0) { // ... Si il y a au moins un joueur sur la case
            int tailleDessin = GameSettings.CASE_TAILLE / jList.size(); // La taille du dessin du pion est
            // proportionelle au nombre de pions sur
            // ce point
            for (int i = 0; i < jList.size(); i++) { // On va placer tout les joueurs sur une même case en
                                                     // un seul coup
                Vector2 posFinal = Vector2.zero.copy();
                posFinal.plus(new Vector2(tailleDessin * i, 0));
                BufferedImage img = jList.get(i).getPion();
                g.drawImage(img, posFinal.x, posFinal.y, posFinal.x + tailleDessin,
                        posFinal.y + tailleDessin, 0, 0,
                        img.getWidth(),
                        img.getHeight(), null);
            }
        }

    }
}