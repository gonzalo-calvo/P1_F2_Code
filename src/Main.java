import Business.Manager.CharacterManager;
import Presentation.MainView;
import Presentation.MenuController;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        printLogo();


        System.out.println("Loading data...");
        System.out.println("Data was successfuly loaded.");

        MainView mainView = new MainView();

        CharacterManager characterManager = new CharacterManager(mainView);

        MenuController menuController = new MenuController(mainView, characterManager);
        menuController.start();


    }

    private static void printLogo() {
        System.out.println("Logo");
        System.out.println("Welcome to simple LSRPG");
    }
}