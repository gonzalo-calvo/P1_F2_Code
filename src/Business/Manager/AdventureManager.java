package Business.Manager;

import Business.Entity.Adventure;
import Business.Entity.Encounter;
import Business.Entity.Monster;
import Persistance.DAO.AdventureDAO;
import Presentation.MainView;

import java.util.ArrayList;

public class AdventureManager {

    MainView mainView;
    AdventureDAO adventureDAO;

    public AdventureManager(MainView mainView, AdventureDAO adventureDAO) {
        this.mainView = mainView;
        this.adventureDAO = adventureDAO;
    }

    public void  createAdventure(ArrayList<Monster> monsters) {
        Adventure adventure = new Adventure();

        mainView.printLine("\nTavern keeper: “Planning an adventure? Good luck with that!”\n");
        adventure.setName(mainView.getValidAdventureName(adventureDAO.getAdventureList()));

        mainView.printLine("\nTavern keeper: “You plan to undertake " + adventure.getName() + ", really?”\nHow long will that take?”\n");

        adventure.setNumEncounters(mainView.askUserOptionBetweenNumbers("-> How many encounters do you want [1..4]: ", 1, 4));

        mainView.printLine("Tavern keeper: “" + adventure.getNumEncounters() + " encounters? That is too much for me...”");

        adventure.setEncountersList(manageEncounters(adventure.getNumEncounters(), monsters));

        System.out.println("Teóricamente aquí se ha rellenado toda la aventura");

        mainView.printFullAdventure(adventure);

        adventureDAO.addAdventureListToJson(adventure);

    }

    private ArrayList<Encounter> manageEncounters(int numEncounters, ArrayList<Monster> monsters) {
        ArrayList<Encounter> encounters = new ArrayList<>();
        int option, monsterNum, amount, delNum;


        for (int i = 1; i <= numEncounters; i++) {
            Encounter encounter = new Encounter();

            do{
                mainView.printEncounterMenu(i, numEncounters, encounter);
                option = mainView.askUserOptionBetweenNumbers("-> Enter an option [1..3]: ", 1, 3);

                switch (option) {
                    case 1 -> { //Add monsters to encounter list
                        mainView.printMonstersToChoose(monsters);
                        monsterNum = mainView.askUserOptionBetweenNumbers("-> Choose a monster to add [1.." + monsters.size() + "]: ", 1, monsters.size()) - 1;
                        amount = mainView.askUserOptionBetweenNumbers("-> How many " + monsters.get(monsterNum).getName() + "(s) do you want to add (max 100): ", 1, 100);

                        if (!addMonstersToExistingMonster(encounter, monsters, monsterNum, amount)){
                            Monster monster = new Monster();
                            setInfoFromIndexToMonster(monster, monsters, monsterNum, amount);
                            encounter.addMonsterToList(monster);
                        }
                    }
                    case 2 -> { //Delete monsters from encounter list
                        if (encounter.getMonsterList() == null || !encounter.getMonsterList().isEmpty()) {
                            delNum = mainView.askUserOptionBetweenNumbers("-> Which monster do you want to delete: ", 1, encounter.getMonsterList().size());
                            mainView.printLine(encounter.getMonsterList().get(delNum - 1).getAmount() + " " + encounter.getMonsterList().get(delNum - 1).getName() + " were removed from the encounter.\n");
                            encounter.getMonsterList().remove(delNum - 1);
                        } else {
                            mainView.printLine("Not possible to delete monsters from empty list");
                        }
                    }
                }
            } while (option != 3);
            encounters.add(encounter);
        }

        return encounters;
    }

    private boolean addMonstersToExistingMonster(Encounter encounter, ArrayList<Monster> monsters, int monsterNum, int amount) {
        for (int i = 0; i < encounter.getMonsterList().size(); i++) {
            if (encounter.getMonsterList().get(i).equals(monsters.get(monsterNum))){
                encounter.getMonsterList().get(i).setAmount(encounter.getMonsterList().get(i).getAmount() + amount);
                return true;
            }
        }
        return false;
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


    public ArrayList<Adventure> getAdventureList() {
        return adventureDAO.getAdventureList();
    }
}
