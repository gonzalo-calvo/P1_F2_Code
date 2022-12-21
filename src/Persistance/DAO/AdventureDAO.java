package Persistance.DAO;

import Business.Entity.Adventure;

import java.util.ArrayList;

public interface AdventureDAO {

    ArrayList<Adventure> getAdventureList();

    void addAdventureListToJson(Adventure adventures);

}
