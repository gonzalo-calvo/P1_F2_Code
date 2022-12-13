package Business.Manager;

import Business.Entity.Adventure;
import Business.Entity.Monster;
import Persistance.DAO.AdventureDAO;
import Presentation.MainView;

import java.util.ArrayList;

public class AdventureManager {

    MainView mainView;
    AdventureDAO adventureDAO;

    public AdventureManager(MainView mainView, AdventureDAO adventureDAO) {
        this.mainView = mainView;
    }

    public void  createAdventure(ArrayList<Monster> monsters) {
        Adventure adventure = new Adventure();

        mainView.printLine("\nTavern keeper: “Planning an adventure? Good luck with that!”\n");
        mainView.printLine("-> Name your adventure: ");
        adventure.setName(mainView.scanLine()); //TODO: FALTA GESTIONAR QUE EL NOMBRE DE LA AVENTURA SEA UNICO

        mainView.printLine("Tavern keeper: “You plan to undertake " + adventure.getName() + ", really?”\nHow long will that take?”\n");

        adventure.setNumEncounters(mainView.askUserOptionBetweenNumbers("-> How many encounters do you want [1..4]: ", 1, 4));

        mainView.printLine("Tavern keeper: “" + adventure.getNumEncounters() + " encounters? That is too much for me...”\n\n");

        //TODO: GESTIONAR TEMA DE COMBATES. AHORA SE LLAMAN ENCOUNTERS PARA HACERLO TODO EN INGLES

        for (int i = 0; i < monsters.size(); i++) {
            mainView.printFullMyMonster(monsters.get(i));
        }

    }


}
