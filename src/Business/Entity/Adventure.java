package Business.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Adventure {

    private String name;
    private int numEncounters;
    ArrayList<Encounter> encounterList = new ArrayList<>();
    ArrayList<MyCharacter> characterParty = new ArrayList<>();

    public Adventure(String name, int numEncounters, ArrayList<Encounter> encounterList, ArrayList<MyCharacter> characterParty) {
        this.name = name;
        this.numEncounters = numEncounters;
        this.encounterList = encounterList;
        this.characterParty = characterParty;
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

    public ArrayList<MyCharacter> getCharacterParty() {
        return characterParty;
    }

    public void setCharacterParty(ArrayList<MyCharacter> characterParty) {
        this.characterParty = characterParty;
    }

    public static void sortByInitiative(ArrayList<MyCharacter> characterParty, Encounter encounter) {
        ArrayList<Object> sortedList = new ArrayList<>();
        ArrayList<Monster> monsters = encounter.getMonsterList();

        sortedList.addAll(characterParty);
        sortedList.addAll(monsters);

        Collections.sort(sortedList, new Comparator<Object>() {
            public int compare(Object o1, Object o2) {
                if (o1 instanceof MyCharacter && o2 instanceof MyCharacter) {
                    return ((MyCharacter) o2).getInitiative() - ((MyCharacter) o1).getInitiative();
                }
                if (o1 instanceof Monster && o2 instanceof Monster) {
                    return ((Monster) o2).getInitiative() - ((Monster) o1).getInitiative();
                }
                if (o1 instanceof MyCharacter && o2 instanceof Monster) {
                    return ((MyCharacter) o2).getInitiative() - ((Monster) o1).getInitiative();
                }
                if (o1 instanceof Monster && o2 instanceof MyCharacter) {
                    return ((Monster) o2).getInitiative() - ((MyCharacter) o1).getInitiative();
                }
                return 0;
            }
        });

        for (Object creatures : sortedList) {
            if (creatures instanceof MyCharacter) {
                MyCharacter character = (MyCharacter) creatures;
                System.out.println("- " + character.getInitiative() +  "    " + character.getName());
            }
            if (creatures instanceof Monster) {
                Monster monster = (Monster) creatures;
                System.out.println("- " + monster.getInitiative() +  "    " + monster.getName());
            }
        }
        //TODO ahora mismo solo ordena los personajes de la party porque no puedo pasarle los monstruos

}

}
