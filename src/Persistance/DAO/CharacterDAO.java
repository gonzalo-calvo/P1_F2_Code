package Persistance.DAO;

import Business.Entity.MyCharacter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface CharacterDAO {

    void createFile(File file);

    boolean createJson(MyCharacter myCharacter);


    List<MyCharacter> readCharactersFromJson() throws IOException;
}


