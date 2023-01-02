package Business;

import Business.Entity.Adventure;
import Business.Entity.Encounter;
import Business.Entity.Monster;
import Business.Entity.MyCharacter;
import Business.Manager.AdventureManager;
import Business.Manager.CharacterManager;
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
        

        ArrayList <MyCharacter> party = chosenAdventure.getCharacterParty();
        
        mainView.printLine("-------------------------");
        mainView.printLine("*** Preparation stage ***");
        mainView.printLine("-------------------------");
        prepPhase(party);
        //TODO pasarle el objeto encounter a la funcion para poder mostrar los monstruos en la fase de preparacion
        chosenAdventure.sortByInitiative(party,encounter);

    }

    private void preparationStage(Adventure chosenAdventure) {




    }

    public ArrayList <MyCharacter> createParty(int numCharacters, ArrayList<MyCharacter> charactersList){
        ArrayList <MyCharacter> yourParty = new ArrayList<>();
        ArrayList<String> yourPartyNames = new ArrayList<>();

        /*************Print actual party**************/
        for (int i = 0; i<numCharacters; i++){
            yourPartyNames.add("Empty");
        }
        for (int i = 0; i<numCharacters; i++){
            mainView.printLine("Your Party ("+i+" / "+ numCharacters + "):");

            for (int j = 0; j < numCharacters; j++){

                mainView.printLine(j+1 + ". " + yourPartyNames.get(j));
            }
            mainView.printLine("--------------------------------");
            mainView.showCharacterList(charactersList);

            /*********Select next character for party*********/
            int chosenCharacter =  mainView.askUserOptionBetweenNumbers("\n-> Choose character " + (i+1) + " in your party: ", 1, charactersList.size());

            yourParty.add(charactersList.get(chosenCharacter-1));
            yourPartyNames.set(i, charactersList.get(chosenCharacter-1).getName());
        }
        mainView.printLine("--------------------------------");

        /************** Print party ******************/

        mainView.printLine("Your Party ("+(yourPartyNames.size())+" / "+ numCharacters + "):");
        for (int j = 0; j < numCharacters; j++){

            mainView.printLine(j+1 + ". " + yourPartyNames.get(j));
        }
        mainView.printLine("--------------------------------");
        return yourParty;
    }

    private Adventure selectAdventure(ArrayList<Adventure> adventuresList){
        int advNum;

        mainView.showAdventureList(adventuresList);
        advNum =  mainView.askUserOptionBetweenNumbers("\n -> Choose an adventure: ", 1, adventuresList.size());

        return adventuresList.get(advNum - 1);
    }

    public ArrayList<MyCharacter> prepPhase(ArrayList<MyCharacter> party){
        ArrayList<MyCharacter> prepPhaseCharacters = new ArrayList<>();
        for (MyCharacter myCharacter : party) {
            switch (myCharacter.getType()) {
                case "Adventurer":
                    myCharacter.setSpirit(myCharacter.getSpirit() + 1);
                    System.out.println("\n" + myCharacter.getName() + " uses Self-Motivated. Their Spirit increases in +1.");
                    //TODO añadir aqui la funcion que ordena por iniciativa que esta arriba
                    break;
                //TODO: Esto se puede usar para las siguientes fases con diferentes clases
            }
            prepPhaseCharacters.add(myCharacter);
        }
        return prepPhaseCharacters;
    }



}
