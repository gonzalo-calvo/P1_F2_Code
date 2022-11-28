package Persistance.JSONDAO;

import Business.Entity.MyCharacter;
import Persistance.DAO.CharacterDAO;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JSONCharacterDAO implements CharacterDAO {
    private static String route = "files/character.json";
    private static Path path = Path.of(route);

    public JSONCharacterDAO(){}

    @Override
    public void createFile(File file) {
        try {
            if(file.exists()){

            }
            else{
                boolean result = file.createNewFile();
                Files.write(path, "[]".getBytes());
                if(result){
                    System.out.println("File created correctly");
                }
                else{
                    System.out.println("Error creating file");
                }
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public boolean createJson(MyCharacter myCharacter) {
        try {
            createFile(new File(route));
            Gson gson= new Gson();
            String text= Files.readString(path);
            String json=gson.toJson(myCharacter,ArrayList.class);
            Files.write(path, json.getBytes());
        } catch (IOException e) {
            return false;
        }
        return true;
    }


}
