import java.util.*;

public class Plateau {

    private Case[] cases;
    public final Vector2 taille;

    private static Random rand = new Random();

    private Deck<Integer> deck;

    public Plateau(Vector2 taille) {
        this.deck = new Deck<Integer>(GameSettings.DECK_TERRAIN);
        this.deck.Melange();
        this.taille = taille;
        cases = new Case[taille.x * taille.y];
        for (int x = 0; x < taille.x; x++) {
            for (int y = 0; y < taille.y; y++) {
                int num = x + y * taille.x;
                cases[num] = new Case(new Vector2(x, y), deck.RetireTop());
            }
        }
    }

    public Vector2 GetSpawnPoint() {
        return new Vector2(rand.nextInt(taille.x), rand.nextInt(taille.y));
    }

    public Case GetCase(int x, int y) {
        int num = x + y * taille.y;
        return cases[num];
    }

    public Case GetCase(Vector2 pos) {
        return GetCase(pos.x, pos.y);
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

    public Vector2 posFromNumber(int i) {
        int x = i % taille.x;
        int y = (i - x) / taille.y;
        return new Vector2(x, y);
    }

    public Vector2 getHeliportPos() {
        for (int x = 0; x < taille.x; x++) {
            for (int y = 0; y < taille.y; y++) {
                if (GetCase(x, y).type == CaseType.HELIPORT) {
                    return new Vector2(x, y);
                }
            }
        }
        return Vector2.zero;
    }

}
