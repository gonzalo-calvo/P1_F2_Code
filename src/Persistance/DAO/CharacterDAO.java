package Persistance.DAO;

import Business.Entity.MyCharacter;
import java.io.File;
import java.util.ArrayList;

public interface CharacterDAO {

    void createFile(File file);

    boolean createJson(MyCharacter myCharacter);
    boolean readJson();
}


