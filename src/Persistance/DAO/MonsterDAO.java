package Persistance.DAO;

import Business.Entity.Monster;

import java.util.ArrayList;

public interface MonsterDAO {

    ArrayList<Monster> getMonstersFromFile();

}
