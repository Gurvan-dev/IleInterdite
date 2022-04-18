public class Artefact extends Objet {
    public final CaseType type;

    public Artefact(CaseType type) {
        this.type = type;
    }

    public String toString() {
        return "Artefact (" + type + ")";
    }

    public boolean utiliseObjet(Joueur j, Case c) {
        return false; // On ne peut pas utiliser cet objet.
    }
}
