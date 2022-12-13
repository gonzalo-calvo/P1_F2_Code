package Persistance.DAO;

import Business.Entity.MyCharacter;
import java.io.File;
import java.util.ArrayList;

public interface CharacterDAO {

    void createFile(File file);

    boolean createJson(MyCharacter myCharacter);

    ArrayList<MyCharacter> readCharactersFromJson();


    ArrayList<MyCharacter> gonzaloReadCharactersFromJSON();

    boolean gonzaloAddMyCharacterToList(MyCharacter myCharacter);

    void gonzaloRemoveMyCharacterFromList(MyCharacter auxCharacter);
}


