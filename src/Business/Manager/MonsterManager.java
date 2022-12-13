package Business.Manager;

import Business.Entity.Monster;
import Persistance.DAO.MonsterDAO;
import Presentation.MainView;

import java.util.ArrayList;

public class MonsterManager {

    MainView mainView;
    MonsterDAO monsterDAO;

    public MonsterManager(MainView mainView, MonsterDAO monsterDAO) {
        this.mainView = mainView;
        this.monsterDAO = monsterDAO;
    }


    public ArrayList<Monster> getMonsters() {
        return monsterDAO.getMonstersFromFile();
    }
}
