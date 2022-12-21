package Business.Entity;

import java.util.ArrayList;

public class Adventure {

    private String name;
    private int numEncounters;
    ArrayList<Encounter> encounterList = new ArrayList<>();

    public Adventure(String name, int numEncounters, ArrayList<Encounter> encounterList) {
        this.name = name;
        this.numEncounters = numEncounters;
        this.encounterList = encounterList;
    }

    public Adventure() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumEncounters() {
        return numEncounters;
    }

    public void setNumEncounters(int numEncounters) {
        this.numEncounters = numEncounters;
    }

    public ArrayList<Encounter> getEncountersList() {
        return encounterList;
    }

    public void setEncountersList(ArrayList<Encounter> encounterList) {
        this.encounterList = encounterList;
    }
}
