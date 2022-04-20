import javax.swing.*;

public class VuePioche extends JPanel {

    JButton poubelle = new JButton();

    Modele modele;

    public VuePioche(Modele modele) {
        super();
        this.modele = modele;
        poubelle = new JButton("Poubelle");
        poubelle.addActionListener(e -> {
            modele.Poubelle();
        });
        this.add(poubelle);
    }

}
