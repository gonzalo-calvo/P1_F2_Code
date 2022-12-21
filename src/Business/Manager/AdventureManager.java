package Business.Manager;

import Business.AdventureExecuter;
import Business.Entity.Adventure;
import Business.Entity.Encounter;
import Business.Entity.Monster;
import Persistance.DAO.AdventureDAO;
import Presentation.MainView;

import java.util.ArrayList;

public class AdventureManager {

    MainView mainView;
    AdventureDAO adventureDAO;
    AdventureExecuter adventureExecuter;

    public AdventureManager(MainView mainView, AdventureDAO adventureDAO, AdventureExecuter adventureExecuter) {
        this.mainView = mainView;
        this.adventureDAO = adventureDAO;
        this.adventureExecuter = adventureExecuter;
    }

    public void  createAdventure(ArrayList<Monster> monsters) {
        Adventure adventure = new Adventure();

        mainView.printLine("\nTavern keeper: “Planning an adventure? Good luck with that!”\n");
        adventure.setName(mainView.getValidAdventureName(adventureDAO.getAdventureList()));

        mainView.printLine("\nTavern keeper: “You plan to undertake " + adventure.getName() + ", really?”\nHow long will that take?”\n");

        adventure.setNumEncounters(mainView.askUserOptionBetweenNumbers("-> How many encounters do you want [1..4]: ", 1, 4));

        mainView.printLine("Tavern keeper: “" + adventure.getNumEncounters() + " encounters? That is too much for me...”");

        //TODO: GESTIONAR TEMA DE COMBATES. AHORA SE LLAMAN ENCOUNTERS PARA HACERLO TODO EN INGLES

        /*for (int i = 0; i < monsters.size(); i++) {
            mainView.printFullMyMonster(monsters.get(i));
        }*/

        adventure.setEncountersList(manageEncounters(adventure.getNumEncounters(), monsters));

        System.out.println("Teoricamente aqui se ha rellenado toda la aventura");

        mainView.printFullAdventure(adventure);

        adventureDAO.addAdventureListToJson(adventure);

    }






    private ArrayList<Encounter> manageEncounters(int numEncounters, ArrayList<Monster> monsters) {
        ArrayList<Encounter> encounters = new ArrayList<>();
        int option, monsterNum, amount;


        for (int i = 1; i <= numEncounters; i++) {
            Encounter encounter = new Encounter();

            mainView.printEncounterMenu(i, numEncounters, encounter);
            do{
                option = mainView.askUserOptionBetweenNumbers("-> Enter an option [1..3]: ", 1, 3);
                switch (option){
                    case 1:
                        mainView.printMonstersToChoose(monsters);
                        monsterNum = mainView.askUserOptionBetweenNumbers("-> Choose a monster to add [1.." + monsters.size() + "]: ", 1, monsters.size())-1;
                        amount = mainView.askUserOptionBetweenNumbers("-> How many " + monsters.get(monsterNum).getName() + "(s) do you want to add (max 100): ", 1, 100);
                        Monster monster = new Monster();
                        setInfoFromIndexToMonster(monster, monsters, monsterNum, amount);
                        encounter.addMonsterToList(monster);
                        mainView.printEncounterMenu(i, numEncounters, encounter);
                        break;
                    case 2:
                        System.out.println("FALTA IMPLEMENTAR: remove monsters");
                        break;
                }
            } while (option != 3);
            encounters.add(encounter);
        }

        return encounters;
    }



    private void setInfoFromIndexToMonster(Monster monster, ArrayList<Monster> monsters, int monsterNum, int amount) {
        monster.setName(monsters.get(monsterNum).getName());
        monster.setChallenge(monsters.get(monsterNum).getChallenge());
        monster.setDamageDice(monsters.get(monsterNum).getDamageDice());
        monster.setExperience(monsters.get(monsterNum).getExperience());
        monster.setDamageType(monsters.get(monsterNum).getDamageType());
        monster.setInitiative(monsters.get(monsterNum).getInitiative());
        monster.setHitPoints(monsters.get(monsterNum).getHitPoints());
        monster.setAmount(amount);
    }

}
