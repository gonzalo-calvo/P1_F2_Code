package Business.Manager;

import Business.Entity.MyCharacter;
import Persistance.DAO.CharacterDAO;
import Presentation.MainView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class CharacterManager {

    MainView mainView;
    CharacterDAO characterDAO;

    int diceOne, diceTwo, diceSum;

    public CharacterManager(MainView mainView, CharacterDAO characterDAO) {
        this.mainView = mainView;
        this.characterDAO = characterDAO;
    }

    public void createCharacter() {
        MyCharacter myCharacter = new MyCharacter();
        mainView.printLine("Tavern keeper: “Oh, so you are new to this land.”");

        myCharacter.setName(mainView.askUserForValidCharacterName(characterDAO.gonzaloReadCharactersFromJSON()));
        mainView.printLine("\nTavern keeper: “Hello, " + myCharacter.getName() + ", be welcome.”");

        myCharacter.setPlayer(mainView.askUserForValidPlayerName());
        mainView.printLine("Tavern keeper: “I see, I see...”");

        mainView.printLine("“Now, are you an experienced adventurer?”\n");
        myCharacter.setExperience(((mainView.askUserOptionBetweenNumbers("-> Enter the character’s level [1..10]: ", 1, 10))*100)-100);
        mainView.printLine("Tavern keeper: “Oh, so you are level " + myCharacter.getExperience() + "!”");
        mainView.printLine("“Great, let me get a closer look at you...”");

        mainView.printLine("\nGenerating your stats...\n");
        diceOne = throwDice(6);
        diceTwo = throwDice(6);
        myCharacter.setBody(setStatByDiceNumber(diceOne + diceTwo));
        diceSum = diceOne + diceTwo;
        System.out.println("Body: You rolled " + diceSum + " (" + diceOne + " and " + diceTwo + ").");

        diceOne = throwDice(6);
        diceTwo = throwDice(6);
        myCharacter.setMind(setStatByDiceNumber(diceOne + diceTwo));
        diceSum = diceOne + diceTwo;
        System.out.println("Mind: You rolled " + diceSum + " (" + diceOne + " and " + diceTwo + ").");

        diceOne = throwDice(6);
        diceTwo = throwDice(6);
        myCharacter.setSpirit(setStatByDiceNumber(diceOne + diceTwo));
        diceSum = diceOne + diceTwo;
        System.out.println("Spirit: You rolled " + diceSum + " (" + diceOne + " and " + diceTwo + ").");

        myCharacter.setType("Adventurer");

        int healthPoints = calculateHealthPoints(myCharacter);
        myCharacter.setHealthPoints(healthPoints);

        int initiative = calculateInitiative(myCharacter);
        myCharacter.setInitiative(initiative);


        mainView.printStats(myCharacter);

        mainView.printLine("\nThe new character " + myCharacter.getName() + " has been created.\n");

        if (characterDAO.gonzaloAddMyCharacterToList(myCharacter)){
            System.out.println("character created correctly");
        } else {
            System.out.println("Error creating character");
        }
    }

    private int setStatByDiceNumber(int i) {
        if (i == 2){
            return -1;
        } else if (i<=5){
            return 0;
        } else if (i<=9){
            return 1;
        } else if (i<=11){
            return 2;
        } else {
            return 3;
        }
    }

    private int throwDice(int numFaces) {
        Random rand = new Random();
        return rand.nextInt(numFaces-1)+1;
    }


    public void listCharacters(){
        int meet;
        MyCharacter auxCharacter;
        ArrayList<MyCharacter> nameMyCharacterList = characterDAO.gonzaloReadCharactersFromJSON();
        String confirmationDelite;

        mainView.printLine("\nTavern keeper: “Lads! They want to see you!”");
        mainView.printLine("“Who piques your interest?”\n");
        mainView.printLineWithNoCarrilReturn("-> Enter the name of the Player to filter: ");
        String playerName = mainView.scanLine();

        if (playerName.equals("")){
            mainView.printLine("\nYou watch as all adventurers get up from their chairs and approach you.\n");
            //Print full list
            mainView.printForListCharacters(nameMyCharacterList);
            meet = mainView.askUserOptionBetweenNumbers("Who would you like to meet [0.." + nameMyCharacterList.size() + "]: ", 0, nameMyCharacterList.size());
            if (meet == 0){
                return;
            } else {
                auxCharacter = nameMyCharacterList.get(meet - 1);
            }
        } else {
            mainView.printLine("\nYou watch as some adventurers get up from their chairs and approach you.\n");
            ArrayList<MyCharacter> nameMyCharacter = getCharactersListByPlayerName(nameMyCharacterList, playerName);
            if (!nameMyCharacter.isEmpty()) {
                mainView.printForListCharacters(nameMyCharacter);
                meet = mainView.askUserOptionBetweenNumbers("Who would you like to meet [0.." + nameMyCharacter.size() + "]: ", 0, nameMyCharacter.size());
                if (meet == 0){
                    return;
                } else {
                    auxCharacter = nameMyCharacter.get(meet - 1);
                }
            } else {
                mainView.printLine("No character with name: " + playerName + " in database.");
                return;
            }
        }

        mainView.printLine("\nTavern keeper: “Hey " + auxCharacter.getName() + " get here; the boss wants to see you!”\n");

        mainView.printFullMyCharacter(auxCharacter);

        mainView.printLine("\n[Enter name to delete, or press enter to cancel]");
        mainView.printLine("Do you want to delete " + auxCharacter.getName() + "?");

        confirmationDelite = mainView.scanLine();

        if (confirmationDelite.equals(auxCharacter.getName())){
            mainView.printLine("\nTavern keeper: “I’m sorry kiddo, but you have to leave.”\n");
            mainView.printLine("Character " + auxCharacter.getName() + " left the Guild.\n");
            if(!characterDAO.gonzaloRemoveMyCharacterFromList(auxCharacter)){
                System.out.println("Error while expelling character");
            }
        } else {
            mainView.printLine("Tavern keeper: “No alarms kiddo, you can go in peace.”");
        }

    }

    private ArrayList<MyCharacter> getCharactersListByPlayerName(ArrayList<MyCharacter> myCharacters, String name) {
        ArrayList<MyCharacter> auxCharacters = new ArrayList<>();
        for (MyCharacter myCharacter : myCharacters) {
            if (myCharacter.getPlayer().toLowerCase().contains(name.toLowerCase())) {
                auxCharacters.add(myCharacter);
            }
        }
        return auxCharacters;
    }



    public boolean checkNumberOfCharacters() {
        boolean startAdventure;
        ArrayList<MyCharacter> myCharacterList = characterDAO.gonzaloReadCharactersFromJSON();
        int numberOfCharacters = myCharacterList.size();

        startAdventure = numberOfCharacters >= 3;
        return startAdventure;
    }

    public ArrayList<MyCharacter> filterByName(List<MyCharacter> myCharactersList, String name) {
        ArrayList<MyCharacter> filteredCharacters = new ArrayList<>();

        for (int i=0; i<    myCharactersList.size(); i++ ) {
            if (name.equals(myCharactersList.get(i).getName())){
                filteredCharacters.add(myCharactersList.get(i));
                /*for (int z=0; z< filteredCharacters.size(); z++ ) {
                    System.out.println(filteredCharacters.get(z).getPlayer());
                }*/
            };
        }
        return filteredCharacters;

    }

    public ArrayList<MyCharacter> getMyCharacterList(){
        return characterDAO.gonzaloReadCharactersFromJSON();
    }

    private int calculateHealthPoints(MyCharacter myCharacter){
        String characterClass = myCharacter.getType();
        int bodyPoints = myCharacter.getBody();
        int characterLevel = myCharacter.getExperience();
        int healthPoints = 0;
        switch (characterClass) {
            case "Adventurer":
                healthPoints = (10 + bodyPoints) * characterLevel;
                break;
        }
        return healthPoints;
    }

    private int calculateInitiative(MyCharacter myCharacter){
        String characterClass = myCharacter.getType();
        int spiritPoints = myCharacter.getSpirit();
        int initiative = 0;
        switch (characterClass) {
            case "Adventurer":
                initiative = (throwDice(12) + spiritPoints);
                break;
        }
        return initiative;
    }
}