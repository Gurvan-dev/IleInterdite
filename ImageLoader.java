import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class ImageLoader {

    static BufferedImage pion1;

    static void Setup() {
        try {
            pion1 = ImageIO.read(new File("img/pion1.png"));
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement des images."); // TODO : EXIT
        }
    }
}
