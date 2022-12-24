package Business;

import Business.Entity.Adventure;
import Business.Entity.Monster;
import Business.Entity.MyCharacter;
import Business.Manager.AdventureManager;
import Business.Manager.CharacterManager;
import Business.Manager.MonsterManager;
import Persistance.DAO.AdventureDAO;
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

    AdventureDAO adventureDAO;

    public AdventureExecuter(MainView mainView, AdventureManager adventureManager, MonsterDAO monsterDAO, CharacterManager characterManager, CharacterDAO characterDAO, AdventureDAO adventureDAO) {
        this.mainView = mainView;
        this.adventureManager = adventureManager;
        this.monsterDAO = monsterDAO;
        this.characterManager = characterManager;
        this.characterDAO = characterDAO;
        this.adventureDAO = adventureDAO;
    }



    public void adventureExecuter() {
        mainView.printLine("Tavern keeper: “So, you are looking to go on an adventure?”");
        mainView.printLine("“Where do you fancy going?”");

        Adventure chosenAdventure = selectAdventure();

        mainView.printLine("\n Tavern keeper: “Dagor-nuin-Giliath – The Battle under the Stars it is!”");
        mainView.printLine("“And how many people shall join you?”");

        int characterNumber = mainView.askUserOptionBetweenNumbers("\n Choose a number of characters [3..5]: ", 3, 5);
        mainView.printLine("\n Tavern keeper: “Great," + characterNumber + " it is.”");
        mainView.printLine("“Who among these lads shall join you?”");
        mainView.printLine("\n\n\n------------------------------");

        ArrayList<MyCharacter> characterParty = createParty(characterNumber);
        mainView.printLine("Tavern keeper: “Great, good luck on your adventure lads!”");
        mainView.printLine("The “Dagor-nuin-Giliath – The Battle under the Stars” will start soon...");








    }

    private ArrayList<MyCharacter> createParty(int characterNumber){
        ArrayList<MyCharacter> charactersList = characterDAO.gonzaloReadCharactersFromJSON();
        ArrayList<MyCharacter> yourParty = new ArrayList<>();
        ArrayList<String> yourPartyNames = new ArrayList<>();

        for (int i = 0; i<characterNumber; i++){
            yourPartyNames.add("Empty");
        }

        for (int i = 0; i<characterNumber; i++){
            System.out.println("Your Party ("+i+" / "+ characterNumber + "):");

            for (int j = 0; j < characterNumber; j++){

                System.out.println(j+1 + ". " + yourPartyNames.get(j));
            }
            System.out.println("------------------------------");
            characterManager.showCharacterList(charactersList);
            System.out.print("\n-> Choose character " + (i+1) + " in your party: ");
            int chosenCharacter =  mainView.askUserOptionBetweenNumbers("", 1, charactersList.size());

            yourParty.add(charactersList.get(chosenCharacter-1));
            yourPartyNames.set(i, charactersList.get(chosenCharacter-1).getName());

        }

        System.out.println("--------------------------------");
        System.out.println("Your Party ("+(yourPartyNames.size())+" / "+ characterNumber + "):");
        for (int j = 0; j < characterNumber; j++){

            System.out.println(j+1 + ". " + yourPartyNames.get(j));
        }
        System.out.println("--------------------------------");
    return yourParty;
    }

    public Adventure selectAdventure(){
        ArrayList<Adventure> adventuresList = adventureDAO.getAdventureList();
        ArrayList<String> adventureNames = new ArrayList<>();
        Adventure chosenAdventure = new Adventure();

        adventureManager.showAdventureList(adventuresList);
        int chosenAdventureNumber =  mainView.askUserOptionBetweenNumbers("\n -> Choose an adventure: ", 1, adventuresList.size());

        for (int i = 0; i<chosenAdventureNumber; i++){
            chosenAdventure = adventuresList.get(chosenAdventureNumber-1);

        }
    return chosenAdventure;
    }

}
