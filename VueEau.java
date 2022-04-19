import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class VueEau extends JPanel implements Observer {

    /* PARAMETRE VUE EAU */
    static final Color eauColor = Color.BLUE;
    static final Color backgroundColor = Color.DARK_GRAY;

    private Modele modele;
    private final int height;

    public VueEau(Modele modele) {
        modele.addObserver(this);
        this.modele = modele;
        height = (VueMain.CASE_ESPACE + VueMain.CASE_TAILLE) * modele.plateau.taille.y;
        Dimension dim = new Dimension(VueMain.EAU_WIDTH, height);
        this.setPreferredSize(dim);

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(backgroundColor);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(eauColor);
        float scale = (float) modele.GetNiveauEau() / (float) Modele.PARTIE_NIVEAU_EAU_MAX;
        if (scale > 0)
            g.fillRect(0, 0, VueMain.EAU_WIDTH, (int) (height * scale));
    }

    public void Update() {
        super.repaint();
    }
}
