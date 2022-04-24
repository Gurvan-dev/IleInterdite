import java.awt.*;
import javax.swing.*;

public class MainMenu {

    JFrame frame;
    JPanel panel;
    JButton startButton;
    JSlider scaleSlider, nbPlayerSlider;
    JTextField scaleTitle, nbPlayerTitle;

    public MainMenu() {
        /* Fenêtre. */
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setTitle("L'île interdite.");

        /* On crée les boutons et les panels */
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        startButton = new JButton("Start Game");
        scaleSlider = new JSlider(5, 15);
        nbPlayerSlider = new JSlider(1, 4, GameSettings.JOUEUR_NOMBRE);

        startButton.addActionListener(e -> {
            StartGame();
        });

        nbPlayerSlider.setMinorTickSpacing(1);
        nbPlayerSlider.setPaintTicks(true);
        nbPlayerSlider.setSnapToTicks(true);

        scaleTitle = new JTextField("Scale du Jeu");
        scaleTitle.setEditable(false);
        nbPlayerTitle = new JTextField("Nombre de joueurs");
        nbPlayerTitle.setEditable(false);

        panel.add(startButton);
        panel.add(nbPlayerTitle);
        panel.add(nbPlayerSlider);
        panel.add(scaleTitle);
        panel.add(scaleSlider);

        /* Finition fenêtre */
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void StartGame() {
        frame.dispose();

        GameSettings.JOUEUR_NOMBRE = nbPlayerSlider.getValue();
        GameSettings.changeGameScale((float) scaleSlider.getValue() / 10f);

        Modele modele = new Modele();
        VueMain vueMain = new VueMain(modele);

    }

}
