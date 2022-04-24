import java.awt.*;
import javax.swing.*;

public class MainMenu {

    JFrame frame;
    JPanel panel;
    JButton startButton;
    JSlider scaleSlider, nbPlayerSlider, difficulteSlider;
    JTextField scaleTitle, nbPlayerTitle, difficulteTitle;
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

        startButton.addActionListener(e -> {
            StartGame();
        });

        nbPlayerSlider.setMinorTickSpacing(1);
        nbPlayerSlider.setPaintTicks(true);
        nbPlayerSlider.setSnapToTicks(true);

        difficulteSlider.setMinorTickSpacing(1);
        difficulteSlider.setPaintTicks(true);
        difficulteSlider.setSnapToTicks(true);

        scaleTitle = new JTextField("Scale du Jeu");
        scaleTitle.setEditable(false);
        nbPlayerTitle = new JTextField("Nombre de joueurs");
        nbPlayerTitle.setEditable(false);
        difficulteTitle = new JTextField("Difficulté");
        difficulteTitle.setEditable(false);

        mainTitle = new JLabel(new ImageIcon(ImageLoader.mainTitle));

        panel.add(mainTitle);
        panel.add(startButton);

        panel.add(nbPlayerTitle);
        panel.add(nbPlayerSlider);

        panel.add(scaleTitle);
        panel.add(scaleSlider);

        panel.add(difficulteTitle);
        panel.add(difficulteSlider);

        /* Finition fenêtre */
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void StartGame() {
        GameSettings.JOUEUR_NOMBRE = nbPlayerSlider.getValue();
        GameSettings.PARTIE_NOMBRE_INONDATION = difficulteSlider.getValue();
        GameSettings.changeGameScale((float) scaleSlider.getValue() / 10f);
        WindowManager.LaunchGame();
    }

}
