import java.awt.*;
import javax.swing.*;

public class VueEau extends JPanel implements Observer {

    /* PARAMETRE VUE EAU */
    static final Color backgroundColor = Color.DARK_GRAY;

    private Modele modele;
    private final int height;

    public VueEau(Modele modele) {
        modele.addObserver(this);
        this.modele = modele;
        height = (GameSettings.CASE_ESPACE + GameSettings.CASE_TAILLE) * modele.plateau.taille.y;
        Dimension dim = new Dimension(GameSettings.EAU_WIDTH, height);
        this.setPreferredSize(dim);

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(backgroundColor);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(GameSettings.PLATEAU_COULEUR);
        float scale = (float) modele.GetNiveauEau() / (float) GameSettings.PARTIE_NIVEAU_EAU_MAX;
        if (scale > 0)
            g.fillRect(0, height - (int) (height * scale), GameSettings.EAU_WIDTH, (int) (height * scale));
    }

    public void Update() {
        super.repaint();
    }
}
