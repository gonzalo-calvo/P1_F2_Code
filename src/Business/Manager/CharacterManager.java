package Business.Manager;

import Business.Entity.MyCharacter;
import Persistance.DAO.CharacterDAO;
import Persistance.JSONDAO.JSONCharacterDAO;
import Presentation.MainView;

import java.util.ArrayList;
import java.util.Random;

public class CharacterManager {

    MainView mainView;
    CharacterDAO characterDAO;
    int diceOne, diceTwo, diceSum;

    public CharacterManager(MainView mainView, CharacterDAO characterDAO) {
        this.mainView = mainView;
        this.characterDAO = characterDAO;
    }

    public void createCharacter(){
        MyCharacter myCharacter = new MyCharacter();
        mainView.printLine("Tavern keeper: “Oh, so you are new to this land.”");

        myCharacter.setName(mainView.askUserForValidCharacterName());
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

        mainView.printStats(myCharacter);

        mainView.printLine("\nThe new character Finrod Felagund has been created.\n");

        if (characterDAO.createJson(myCharacter)){
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


    public void listCharacters() {
        int option;

        mainView.printLine("\nTavern keeper: “Lads! They want to see you!”");
        mainView.printLine("“Who piques your interest?”\n");
        mainView.printLineWithNoCarrilReturn("-> Enter the name of the Player to filter: ");
        String name = mainView.scanLine();

        characterDAO.readJson();//TODO: AÑADIR FILTRO POR NOMBRE PARA QUE NO SE MUESTRE EL ARRAY ENTERO

        ArrayList<MyCharacter> myCharactersList = new ArrayList<>();

        //aqui printar solo los characters que tengan el mismo nombre, falta pulir pq lo de debajo no esta bien
        mainView.printLine("\nYou watch as some adventurers get up from their chairs and approach you.\n");
        for (int i = 0; i < myCharactersList.size(); i++) {
            mainView.printLine("    " + i + ". " +  myCharactersList.get(i).getName());
        }
        mainView.printLine("    0. Back\n");
        mainView.printLineWithNoCarrilReturn("Who would you like to meet [0.." + (myCharactersList.size()+1) + "]:");
        option = mainView.askUserOptionBetweenNumbers("", 0, myCharactersList.size()+1);
        if (option == -1){
            //aqui printamos todos los characters de la lista de characters
            mainView.printLine("\nYou watch as all adventurers get up from their chairs and approach you.\n");
        } else {
            mainView.printLine("\nTavern keeper: “Hey " + myCharactersList.get(option-1).getName() + " get here; the boss wants to see you!”\n");
            //aqui se printa el character escogido
            mainView.printMyCharacter(myCharactersList.get(option-1));
        }


    }
}
