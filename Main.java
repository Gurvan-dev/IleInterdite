public class Main {

    public static void main(String[] args) {
        GameSettings.changeGameScale(1.25f);
        ImageLoader.Setup();
        Modele modele = new Modele();
        VueMain vueMain = new VueMain(modele);
    }
}