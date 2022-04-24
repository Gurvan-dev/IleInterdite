import java.awt.*;
import javax.swing.*;

public class MainMenu {

    JFrame frame;
    JPanel panel;
    JButton startButton;
    JSlider scaleSlider, nbPlayerSlider, difficulteSlider, nbClefArtefactSlider;
    JTextField scaleTitle, nbPlayerTitle, difficulteTitle, nbClefArtefactTitle;
    JLabel mainTitle;

    public MainMenu() {
        /* Fenêtre. */
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setTitle("L'île interdite.");
        WindowManager.openedWindow.add(frame);

        /* On crée les boutons et les panels */
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        startButton = new JButton("Start Game");
        scaleSlider = new JSlider(5, 15);
        nbPlayerSlider = new JSlider(1, 4, GameSettings.JOUEUR_NOMBRE);
        difficulteSlider = new JSlider(1, 4, GameSettings.PARTIE_NOMBRE_INONDATION);
        nbClefArtefactSlider = new JSlider(1, 5, GameSettings.PARTIE_NOMBRE_CLEF_POUR_ARTEFACT);

        startButton.addActionListener(e -> {
            StartGame();
        });

        nbPlayerSlider.setMinorTickSpacing(1);
        nbPlayerSlider.setMajorTickSpacing(1);
        nbPlayerSlider.setPaintTicks(true);
        nbPlayerSlider.setPaintLabels(true);
        nbPlayerSlider.setSnapToTicks(true);

        difficulteSlider.setMinorTickSpacing(1);
        difficulteSlider.setMajorTickSpacing(1);
        difficulteSlider.setPaintTicks(true);
        difficulteSlider.setPaintLabels(true);
        difficulteSlider.setSnapToTicks(true);

        nbClefArtefactSlider.setMinorTickSpacing(1);
        nbClefArtefactSlider.setMajorTickSpacing(1);
        nbClefArtefactSlider.setPaintTicks(true);
        nbClefArtefactSlider.setPaintLabels(true);
        nbClefArtefactSlider.setSnapToTicks(true);

        scaleSlider.setMajorTickSpacing(1);

        scaleTitle = new JTextField("Scale du Jeu");
        scaleTitle.setEditable(false);
        nbPlayerTitle = new JTextField("Nombre de joueurs");
        nbPlayerTitle.setEditable(false);
        difficulteTitle = new JTextField("Difficulté");
        difficulteTitle.setEditable(false);
        nbClefArtefactTitle = new JTextField("Nombre de clé pour artefacts.");
        nbClefArtefactTitle.setEditable(false);

        mainTitle = new JLabel(new ImageIcon(ImageLoader.mainTitle));

        panel.add(mainTitle);
        panel.add(startButton);

        panel.add(nbPlayerTitle);
        panel.add(nbPlayerSlider);

        panel.add(difficulteTitle);
        panel.add(difficulteSlider);

        panel.add(nbClefArtefactTitle);
        panel.add(nbClefArtefactSlider);

        panel.add(scaleTitle);
        panel.add(scaleSlider);

        /* Finition fenêtre */
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void StartGame() {
        GameSettings.JOUEUR_NOMBRE = nbPlayerSlider.getValue();
        GameSettings.PARTIE_NOMBRE_INONDATION = difficulteSlider.getValue();
        GameSettings.PARTIE_NOMBRE_CLEF_POUR_ARTEFACT = nbClefArtefactSlider.getValue();
        GameSettings.changeGameScale((float) scaleSlider.getValue() / 10f);

        WindowManager.LaunchGame();
    }

}
