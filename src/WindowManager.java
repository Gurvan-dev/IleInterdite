import javax.swing.*;
import java.util.*;

public class WindowManager {

    static ArrayList<JFrame> openedWindow = new ArrayList<JFrame>();

    static void closeAll() {
        for (JFrame jp : openedWindow) {
            jp.dispose();
        }
        openedWindow = new ArrayList<JFrame>();
    }

    static void LaunchMainMenu() {
        closeAll();
        MainMenu mm = new MainMenu();
    }

    static void LaunchGame() {
        closeAll();
        Modele modele = new Modele();
        VueMain vueMain = new VueMain(modele);
    }

    static void LaunchEndMenu(boolean won) {
        closeAll();
        EndMenu em = new EndMenu(won);
    }

}
