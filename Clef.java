public class Clef extends Objet {
    public final CaseType type;

    public Clef(CaseType type) {
        this.type = type;
    }

    public String toString() {
        return "Clef (" + type + ")";
    }
}
