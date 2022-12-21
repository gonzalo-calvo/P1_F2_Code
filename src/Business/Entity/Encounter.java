package Business.Entity;

import java.util.ArrayList;

public class Encounter {

    private ArrayList<Monster> monsterList = new ArrayList<>();

    public Encounter(ArrayList<Monster> monsterList) {
        this.monsterList = monsterList;
    }

    public Encounter() {
    }

    public ArrayList<Monster> getMonsterList() {
        return monsterList;
    }

    public void setMonsterList(ArrayList<Monster> monsterList) {
        this.monsterList = monsterList;
    }

    public void addMonsterToList(Monster monster){
        monsterList.add(monster);
    }
}
