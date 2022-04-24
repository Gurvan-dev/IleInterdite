import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

public class EndMenu {

    JFrame frame;
    JPanel panel;
    JButton startButton;
    JLabel gagneOuQuoi;

    public EndMenu(boolean won) {
        /* Fenêtre. */
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setTitle("L'île interdite.");
        WindowManager.openedWindow.add(frame);

        /* On crée les boutons et les panels */
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        startButton = new JButton("Retour menu principal");
        BufferedImage resultImage = won ? ImageLoader.bravo : ImageLoader.pasbravo;
        gagneOuQuoi = new JLabel(new ImageIcon(resultImage));
        startButton.addActionListener(e -> {
            WindowManager.LaunchMainMenu();
        });

        panel.add(gagneOuQuoi);
        panel.add(startButton);

        /* Finition fenêtre */
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
