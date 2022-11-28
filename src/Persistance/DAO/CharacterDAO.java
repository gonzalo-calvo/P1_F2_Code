package Persistance.DAO;

import Business.Entity.MyCharacter;
import java.io.File;

public interface CharacterDAO {
    void createFile(File file);
    boolean createJson(MyCharacter myCharacter);
}


