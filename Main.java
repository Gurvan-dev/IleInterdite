public class Main {

    public static void main(String[] args) {
        ImageLoader.Setup();
        Modele modele = new Modele();
        VueMain vueMain = new VueMain(modele);
    }
}