package Persistance.DAO;

import Business.Entity.MyCharacter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface CharacterDAO {

    void createFile(File file);

    boolean createJson(MyCharacter myCharacter);


    ArrayList<MyCharacter> readCharactersFromJson() throws IOException;

    ArrayList<MyCharacter> readCharactersFromJsonByName(String name) throws IOException;
}


