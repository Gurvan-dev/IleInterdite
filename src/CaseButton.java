
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
        Case thisCase = modele.plateau.GetCase(pos.x, pos.y);

        /* DESSIN DU BACKGROUND */
        if (thisCase.etat != CaseEtat.SUBMERGE) {
            BufferedImage caseGraphics = thisCase.GetImage();
            g.drawImage(caseGraphics, 0, 0, GameSettings.CASE_TAILLE,
                    GameSettings.CASE_TAILLE, 0, 0,
                    caseGraphics.getWidth(),
                    caseGraphics.getHeight(), null);
        }
        /* Dessiner les pions sur la case... */
        ArrayList<Joueur> jList = modele.GetJoueursOnCase(pos);
        if (jList.contains(modele.GetCurrentJoueur())) {
            g2d.setStroke(new BasicStroke(GameSettings.PLATEAU_SELECT_WIDTH));
            g2d.setColor(GameSettings.PLATEAU_SELECT_COULEUR);
            g2d.drawRect(0, 0, this.getWidth(), this.getHeight());
        }
        if (jList.size() > 0) { // ... Si il y a au moins un joueur sur la case
            int tailleDessin = GameSettings.CASE_TAILLE / jList.size(); // La taille du dessin du pion est
            // proportionelle au nombre de pions sur ce point
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

        // On dessine l'overlay d'eau si y'a de l'eau sur la case
        if (thisCase.etat == CaseEtat.INONDE) {
            BufferedImage waterOverlay = ImageLoader.terrain_water_overlay;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, GameSettings.WATER_OVERLAY_OPACITY));
            g.drawImage(waterOverlay, 0, 0, GameSettings.CASE_TAILLE,
                    GameSettings.CASE_TAILLE, 0, 0,
                    waterOverlay.getWidth(),
                    waterOverlay.getHeight(), null);

        }

    }
}