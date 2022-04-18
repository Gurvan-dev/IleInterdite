import java.awt.*;
import java.util.*;

public class Plateau {

    private Case[] cases;
    public final Vector2 taille;
    public static Color PLATEAU_COULEUR = Color.blue;
    private static Random rand = new Random();

    public Plateau(Vector2 taille) {
        this.taille = taille;
        cases = new Case[taille.x * taille.y];
        for (int x = 0; x < taille.x; x++) {
            for (int y = 0; y < taille.y; y++) {
                int num = x + y * taille.x;
                cases[num] = new Case(new Vector2(x, y));
            }
        }
    }

    public Case GetCase(int x, int y) {
        int num = x + y * taille.y;
        return cases[num];
    }

    public void Inonde() {
        int r = rand.nextInt(cases.length);
        int MAX_TRY = 100;
        int CURRENT_TRY = 0;
        while (CURRENT_TRY < MAX_TRY && !cases[r].Inonde()) {
            r = rand.nextInt(cases.length);
            CURRENT_TRY++;
        }
    }

    public boolean InBounds(Vector2 pos) {
        return pos.x >= 0 && pos.x < taille.x && pos.y >= 0 && pos.y < taille.y;
    }

}
