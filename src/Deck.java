import java.util.*;

public class Deck<T> {
    public T[] composant;
    private int current = 0; // On pourrait modifier tout l'array composant, mais il est bien plus efficace
                             // de ne garde que la on on est dans la pile au lieu de décaler tout l'array a
                             // chaque fois.
    private boolean melangeQuandVide;

    private static final Random rand = new Random();
    private static final int MELANGE_SCALE = 3; // On va effectuer MELANGE_SCALE * Composant.length mélange a chaque
                                                // fois qu'on mélangera le deck

    public Deck(T[] composant) {
        this(composant, true);
    }

    public Deck(T[] composant, boolean melangeQuandVide) {
        this.composant = composant;
    }

    public void Melange() {
        for (int i = 0; i < composant.length * MELANGE_SCALE; i++) { // On va échanger X cartes, ou X est le nombre de
                                                                     // cartes dans le
            // paquet.
            int a = rand.nextInt(composant.length);
            int b = rand.nextInt(composant.length);
            T swap = composant[a];
            composant[a] = composant[b];
            composant[b] = swap;
        }
    }

    /* Retire la carte au dessus de la pile */
    public void Next() {
        current++;
        if (current >= composant.length) {
            current = 0;
            if (melangeQuandVide)
                Melange();
        }
    }

    /* Renvoie le numéro de carte au dessus de la pile */
    public T Top() {
        return composant[current];
    }

    /* Renvoie la carte au dessus de la pile, et la repasse en dessous de la pile */
    public T RetireTop() {
        T top = composant[current];
        Next();
        return top;
    }
}
