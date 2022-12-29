package Business;

import Business.Entity.Adventure;
import Business.Entity.MyCharacter;
import Business.Manager.AdventureManager;
import Presentation.MainView;
import java.util.ArrayList;

public class AdventureExecuter {

    MainView mainView;
    AdventureManager adventureManager;


    public AdventureExecuter(MainView mainView, AdventureManager adventureManager) {
        this.mainView = mainView;
        this.adventureManager = adventureManager;
    }


    public void executeAdventure(ArrayList<MyCharacter> characterList, ArrayList<Adventure> adventureList) {

        mainView.printLine("Tavern keeper: “So, you are looking to go on an adventure?”");
        mainView.printLine("“Where do you fancy going?”");

        Adventure chosenAdventure = selectAdventure(adventureList);

        mainView.printLine("\n Tavern keeper: “" + chosenAdventure.getName() + " it is!”");
        mainView.printLine("“And how many people shall join you?”");

        int characterNumber = mainView.askUserOptionBetweenNumbers("\n Choose a number of characters [3..5]: ", 3, 5);

        mainView.printLine("\n Tavern keeper: “Great," + characterNumber + " it is.”");
        mainView.printLine("“Who among these lads shall join you?”");

        chosenAdventure.setCharacterParty(createParty(characterNumber, characterList));

        mainView.printLine("Tavern keeper: “Great, good luck on your adventure lads!”");
        mainView.printLine("The “" + chosenAdventure.getName() + "” will start soon...");

        //bucle para encounters
            //fase preparación
            //bucle fase batalla
            //fase descanso

        for (int i = 0; i < chosenAdventure.getNumEncounters(); i++) {
            mainView.printEncounterHeather(i, chosenAdventure.getEncountersList().get(i));
            preparationStage(chosenAdventure);

        }


    }

    private void preparationStage(Adventure chosenAdventure) {




    }

    public ArrayList <MyCharacter> createParty(int numCharacters, ArrayList<MyCharacter> charactersList){
        ArrayList <MyCharacter> yourParty = new ArrayList<>();

        for (int i = 0; i < numCharacters; i++){
            /*************Print actual party**************/
            mainView.printLine("\n\n------------------------------");
            System.out.println("Your Party (" + i + " / " + numCharacters + "):");
            for (int j = 1; j <= numCharacters; j++){
                if (yourParty.get(i-1) == null){
                    mainView.printLine("  " + j + ". Empty");
                } else {
                    mainView.printLine("  " + j + ". " + yourParty.get(j).getName());
                }
            }
            mainView.printLine("------------------------------");

            /*********Select next character for party*********/
            mainView.showCharacterList(charactersList);
            int chosenCharacter =  mainView.askUserOptionBetweenNumbers("\n-> Choose character " + (i+1) + " in your party: ", 1, charactersList.size());
            yourParty.add(charactersList.get(chosenCharacter-1));
        }

        /************** Print party ******************/
        mainView.printLine("--------------------------------");
        mainView.printLine("Your Party ("+ numCharacters + " / " + numCharacters + "):");
        for (int j = 0; j < numCharacters; j++){
            mainView.printLine("  " + (j+1) + ". " + yourParty.get(j));
        }
        System.out.println("--------------------------------");

        return yourParty;
    }

    private Adventure selectAdventure(ArrayList<Adventure> adventuresList){
        int advNum;

        mainView.showAdventureList(adventuresList);
        advNum =  mainView.askUserOptionBetweenNumbers("\n -> Choose an adventure: ", 1, adventuresList.size());

        return adventuresList.get(advNum - 1);
    }

}
