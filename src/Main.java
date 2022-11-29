import Business.Manager.CharacterManager;
import Persistance.DAO.CharacterDAO;
import Persistance.JSONDAO.JSONCharacterDAO;
import Presentation.MainView;
import Presentation.MenuController;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        printLogo();


        System.out.println("Loading data...");
        System.out.println("Data was successfuly loaded.\n");

        MainView mainView = new MainView();
        CharacterDAO characterDAO = new JSONCharacterDAO();

        CharacterManager characterManager = new CharacterManager(mainView, characterDAO);


        MenuController menuController = new MenuController(mainView, characterManager);
        menuController.start();


    }

    private static void printLogo() {
        System.out.println("   ____ _               __       __    ____ ___   ___   _____");
        System.out.println("  / __/(_)__ _   ___   / /___   / /   / __// _ \\ / _ \\ / ___/");
        System.out.println(" _\\ \\ / //  ' \\ / _ \\ / // -_) / /__ _\\ \\ / , _// ___// (_ /");
        System.out.println("/___//_//_/_/_// .__//_/ \\__/ /____//___//_/|_|/_/    \\___/");
        System.out.println("              /_/\n");
        System.out.println("Welcome to simple LSRPG\n");
    }
}