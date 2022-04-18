public abstract class Objet {
    public abstract String toString();

    // Utilise l'objet par le joueur j a la position pos.
    // Retourne vraie si l'objet a pu être utilisé. Si c'est le cas, la carte est
    // retiré.
    public abstract boolean utiliseObjet(Joueur j, Case c);
}
