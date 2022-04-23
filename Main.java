public class Main {

    public static void main(String[] args) {
        GameSettings.changeGameScale(1.25f);
        ImageLoader.Setup();
        MainMenu mm = new MainMenu();
    }
}