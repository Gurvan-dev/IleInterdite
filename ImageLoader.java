import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class ImageLoader {

    static BufferedImage pion1;
    static BufferedImage objetPlaceholder;
    static BufferedImage objetHelico;
    static BufferedImage objetMonteeEau;

    private static final String filePath = "img/";

    static void Setup() {
        try {
            pion1 = ImageIO.read(new File(filePath + "pion1.png"));
            objetHelico = ImageIO.read(new File(filePath + "Objet_Helicoptr.png"));
            objetPlaceholder = ImageIO.read(new File(filePath + "Objet_Helicoptr.png"));
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement des images."); // TODO : EXIT
        }
    }
}
