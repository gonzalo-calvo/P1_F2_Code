package Business.Manager;

import Business.Entity.MyCharacter;
import Persistance.DAO.CharacterDAO;
import Presentation.MainView;

import java.util.ArrayList;
import java.util.List;
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
        myCharacter.setExperience(mainView.askUserOptionBetweenNumbers("-> Enter the character’s level [1..10]: ", 1, 10));
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
        MyCharacter auxCharacter = null;
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
            ArrayList<MyCharacter> nameMyCharacter = getCharactersListByName(nameMyCharacterList, playerName);
            if (!nameMyCharacter.isEmpty()) {
                mainView.printForListCharacters(nameMyCharacter);
                meet = mainView.askUserOptionBetweenNumbers("Who would you like to meet [0.." + nameMyCharacterList.size() + "]: ", 0, nameMyCharacterList.size());
                if (meet == 0){
                    return;
                } else {
                    auxCharacter = nameMyCharacterList.get(meet - 1);
                }
            } else {
                mainView.printLine("No character with name: " + playerName + "in database.");
            }
        }

        mainView.printLine("\nTavern keeper: “Hey " + auxCharacter.getName() + " get here; the boss wants to see you!”\n");

        mainView.printFullMyCharacter(auxCharacter);

        mainView.printLine("\n[Enter name to delete, or press enter to cancel]");
        mainView.printLine("Do you want to delete " + auxCharacter.getName() + "?");

        confirmationDelite = mainView.scanLine();

        if (!confirmationDelite.equals("")){
            mainView.printLine("\nTavern keeper: “I’m sorry kiddo, but you have to leave.”\n");
            mainView.printLine("Character Jinx left the Guild.\n");
            characterDAO.gonzaloRemoveMyCharacterFromList(auxCharacter);
        }

    }

    private ArrayList<MyCharacter> getCharactersListByName(ArrayList<MyCharacter> gonzaloReadCharactersFromJSON, String name) {
        ArrayList<MyCharacter> namedCharacterList = null;

        for (int i = 0; i < gonzaloReadCharactersFromJSON.size(); i++) {
            if (gonzaloReadCharactersFromJSON.get(i).getName().contains(name)){
                namedCharacterList.add(gonzaloReadCharactersFromJSON.get(i));
            }
        }

        return namedCharacterList;

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
}
