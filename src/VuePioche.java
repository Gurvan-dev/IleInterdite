import java.awt.*;
import javax.swing.*;

public class VuePioche extends JPanel {

    JButton poubelle = new JButton();

    Modele modele;

    public VuePioche(Modele modele) {
        super();
        this.modele = modele;

        poubelle = new JButton(new ImageIcon(ImageLoader.poubelle));
        poubelle.setFocusable(false);
        poubelle.setOpaque(false);
        poubelle.setBorderPainted(false);
        poubelle.setPreferredSize(new Dimension(100, 100));
        poubelle.addActionListener(e -> {
            modele.Poubelle();
        });
        this.add(poubelle);
    }

}
