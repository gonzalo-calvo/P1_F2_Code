package Presentation;

import Business.AdventureExecuter;
import Business.Entity.MyCharacter;
import Business.Manager.AdventureManager;
import Business.Manager.CharacterManager;
import Business.Manager.MonsterManager;

import java.util.ArrayList;

public class MenuController {

    MainView mainView;

    CharacterManager characterManager;
    AdventureManager adventureManager;
    MonsterManager monsterManager;
    AdventureExecuter adventureExecuter;

    public MenuController(MainView mainView, CharacterManager characterManager, AdventureManager adventureManager, MonsterManager monsterManager, AdventureExecuter adventureExecuter) {
        this.mainView = mainView;
        this.characterManager = characterManager;
        this.adventureManager = adventureManager;
        this.monsterManager = monsterManager;
        this.adventureExecuter = adventureExecuter;
    }

    public void start(){
        int option;
        boolean characters;

        do {
            characters = printMenu();
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
                    if (characters){
                        adventureExecuter.executeAdventure(characterManager.getMyCharacterList(), adventureManager.getAdventureList());
                    }
                    break;
                case 5:
                    System.out.println("\nTavern keeper: “Are you leaving already? See you soon, adventurer.”");
                    break;
            }

        } while (option != 5);

    }

    private boolean printMenu() {
        boolean characters;
        System.out.println("\nThe tavern keeper looks at you and says:");
        System.out.println("“Welcome adventurer! How can I help you?”\n");
        System.out.println("    1) Character creation");
        System.out.println("    2) List characters");
        System.out.println("    3) Create an adventure");

        characters = characterManager.checkNumberOfCharacters();
        if (characters) {
            mainView.printLine("    4) Start an adventure");
        } else {
            mainView.printLine("    4) Start an adventure (disabled: create at least 3 character first)");
        }

        System.out.println("    5) Exit\n");
        System.out.print("Your answer: ");
        return characters;
    }




}
