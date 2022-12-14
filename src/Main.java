import Business.AdventureExecuter;
import Business.Manager.AdventureManager;
import Business.Manager.CharacterManager;
import Business.Manager.MonsterManager;
import Persistance.DAO.AdventureDAO;
import Persistance.DAO.CharacterDAO;
import Persistance.DAO.MonsterDAO;
import Persistance.JSONDAO.JSONAdventureDAO;
import Persistance.JSONDAO.JSONCharacterDAO;
import Persistance.JSONDAO.JSONMonsterDAO;
import Presentation.MainView;
import Presentation.MenuController;

import java.io.IOException;

public class Main {
    public static void main(String[] args){


        System.out.println("Loading data...");
        System.out.println("Data was successfuly loaded.\n");

        MainView mainView = new MainView();
        mainView.printLogo();

        CharacterDAO characterDAO = new JSONCharacterDAO();
        AdventureDAO adventureDAO = new JSONAdventureDAO();
        MonsterDAO monsterDAO = new JSONMonsterDAO();


        CharacterManager characterManager = new CharacterManager(mainView, characterDAO);
        AdventureManager adventureManager = new AdventureManager(mainView, adventureDAO);
        MonsterManager monsterManager = new MonsterManager(mainView, monsterDAO);
        AdventureExecuter adventureExecuter = new AdventureExecuter(mainView);


        MenuController menuController = new MenuController(mainView, characterManager, adventureManager, monsterManager, adventureExecuter);
        menuController.start();


    }

}