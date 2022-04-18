public class Deck {
    public int[] composant;
    private int current = 0; // On pourrait modifier tout l'array composant, mais il est bien plus efficace
                             // de ne garde que la on on est dans la pile au lieu de décaler tout l'array a
                             // chaque fois.

    public Deck(int[] composant) {
        this.composant = composant;
    }

    public void Melange() {
        // TODO
    }

    /* Retire la carte au dessus de la pile */
    public void Next() {
        current = (current + 1) % composant.length;
    }

    /* Renvoie le numéro de carte au dessus de la pile */
    public int Top() {
        return composant[current];
    }

    /* Renvoie la carte au dessus de la pile, et la repasse en dessous de la pile */
    public int RetireTop() {
        int top = composant[current];
        Next();
        return top;
    }
}
