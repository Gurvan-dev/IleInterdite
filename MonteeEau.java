public class MonteeEau extends Objet {
    public boolean autoUse() {
        return true;
    }

    public boolean utiliseObjet(Joueur j, Case c, Modele m) {
        m.MonteeEau();
        return true;
    }

    public String toString() {
        return "Montee Eau";
    }
}
