package Business;

import Business.Entity.Adventure;
import Business.Entity.Monster;
import Business.Entity.MyCharacter;
import Business.Manager.AdventureManager;
import Business.Manager.CharacterManager;
import Business.Manager.MonsterManager;
import Persistance.DAO.CharacterDAO;
import Persistance.DAO.MonsterDAO;
import Presentation.MainView;

import java.util.ArrayList;

public class AdventureExecuter {

    MainView mainView;
    AdventureManager adventureManager;

    MonsterDAO monsterDAO;

    CharacterDAO characterDAO;

    CharacterManager characterManager;

    public AdventureExecuter(MainView mainView, AdventureManager adventureManager, MonsterDAO monsterDAO, CharacterManager characterManager, CharacterDAO characterDAO) {
        this.mainView = mainView;
        this.adventureManager = adventureManager;
        this.monsterDAO = monsterDAO;
        this.characterManager = characterManager;
        this.characterDAO = characterDAO;
    }



    public void adventureExecuter() {

       // ArrayList<Adventure> adventureList = AdventureManager.getAdventuresList();//TODO: crear metodo que devuelva array de aventuras.
        mainView.printLine("Tavern keeper: “So, you are looking to go on an adventure?”");
        mainView.printLine("Where do you fancy going?");
        mainView.printLine("Available adventures:");


        System.out.print(" Choose a number of characters [3..5]: ");
        int adventureNumber = mainView.askUserOptionBetweenNumbers("", 3, 5);

        ArrayList<MyCharacter> characterParty = createParty(adventureNumber);


    }


    public ArrayList<MyCharacter> createParty(int adventureNumber){
        ArrayList<MyCharacter> charactersList = characterDAO.gonzaloReadCharactersFromJSON();
        ArrayList<MyCharacter> yourParty = new ArrayList<>();
        ArrayList<String> yourPartyNames = new ArrayList<>();

        for (int i = 0; i<adventureNumber; i++){
            yourPartyNames.add("Empty");
        }

        for (int i = 0; i<adventureNumber; i++){
            System.out.println("Your Party ("+i+" / "+ adventureNumber + "):");

            for (int j = 0; j < adventureNumber; j++){

                System.out.println(j+1 + ". " + yourPartyNames.get(j));
            }
            System.out.println("--------------------------------");
            characterManager.showCharacterList(charactersList);
            System.out.print("\n Choose character " + (i+1) + " in your party: ");
            int characterNumber =  mainView.askUserOptionBetweenNumbers("", 1, charactersList.size());

            yourParty.add(charactersList.get(characterNumber-1));
            yourPartyNames.set(i, charactersList.get(characterNumber-1).getName());

        }

        System.out.println("--------------------------------");
        System.out.println("Your Party ("+(yourPartyNames.size())+" / "+ adventureNumber + "):");
        for (int j = 0; j < adventureNumber; j++){

            System.out.println(j+1 + ". " + yourPartyNames.get(j));
        }

    return charactersList;
    }

}
