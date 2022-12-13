package Presentation;

import Business.Manager.AdventureManager;
import Business.Manager.CharacterManager;
import Business.Manager.MonsterManager;

import java.io.IOException;

public class MenuController {

    MainView mainView;

    CharacterManager characterManager;
    AdventureManager adventureManager;
    MonsterManager monsterManager;

    public MenuController(MainView mainView, CharacterManager characterManager, AdventureManager adventureManager, MonsterManager monsterManager) {
        this.mainView = mainView;
        this.characterManager = characterManager;
        this.adventureManager = adventureManager;
        this.monsterManager = monsterManager;
    }

    public void start(){
        int option;

        do {
            printMenu();
            option = mainView.askUserOptionBetweenNumbers("", 1, 5);

            switch (option){
                case 1:
                    characterManager.createCharacter();
                    break;
                case 2:
                    characterManager.listCharacters();
                    break;
                case 3:
                    adventureManager.createAdventure(monsterManager.getMonsters());
                    break;
                case 4:
                    System.out.println("start adventure");
                    break;
                case 5:
                    System.out.println("\nTavern keeper: “Are you leaving already? See you soon, adventurer.”");
                    break;
            }

        } while (option != 5);

    }

    private void printMenu() {
        System.out.println("The tavern keeper looks at you and says:");
        System.out.println("“Welcome adventurer! How can I help you?”\n");
        System.out.println("    1) Character creation");
        System.out.println("    2) List characters");
        System.out.println("    3) Create an adventure");
        System.out.println("    4) Start an adventure");
        System.out.println("    5) Exit\n");
        System.out.print("Your answer: ");
    }


}
