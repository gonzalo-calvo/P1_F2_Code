package Persistance.JSONDAO;

import Business.Entity.MyCharacter;
import Persistance.DAO.CharacterDAO;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class JSONCharacterDAO implements CharacterDAO {
    private static String route = "Files/characters.json";
    private static Path path = Path.of(route);

    Gson gson= new Gson();
    JsonParser parser = new JsonParser();

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
            String text= Files.readString(path);
            List<MyCharacter> characters=gson.fromJson(text,ArrayList.class);
            characters.add(myCharacter);
            String json = gson.toJson(characters);
            Files.write(path, json.getBytes());
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<MyCharacter> readCharactersFromJson(){

        String text;
        try {
            text = Files.readString(path);
            List<MyCharacter> jsonCharacters = Arrays.asList(gson.fromJson(text, MyCharacter[].class));
            return (ArrayList<MyCharacter>) jsonCharacters;
        } catch (IOException e) {
            System.out.println("ERROR: error while reading file");
        }
        return null;
    }

    @Override
    public ArrayList<MyCharacter> gonzaloReadCharactersFromJSON() {
        ArrayList<MyCharacter> myCharacters = new ArrayList<>();

        try {
            String content = Files.readString(path);

            // Obtain Array
            JsonArray gsonArr = parser.parse(content).getAsJsonArray();

            // for each element of array
            for (JsonElement obj : gsonArr) {
                MyCharacter myCharacter = new MyCharacter();
                // Object of array
                JsonObject gsonObj = obj.getAsJsonObject();

                // Primitives elements of object
                myCharacter.setName(gsonObj.get("name").getAsString());
                myCharacter.setPlayer(gsonObj.get("player").getAsString());
                myCharacter.setExperience(gsonObj.get("xp").getAsInt());
                myCharacter.setBody(gsonObj.get("body").getAsInt());
                myCharacter.setMind(gsonObj.get("mind").getAsInt());
                myCharacter.setSpirit(gsonObj.get("spirit").getAsInt());
                myCharacter.setType(gsonObj.get("class").getAsString());

                myCharacters.add(myCharacter);
            }

        } catch (IOException e) {
            System.out.println("ERROR: file characters not possible to read");
        }
        return myCharacters;
    }

    @Override
    public boolean gonzaloAddMyCharacterToList(MyCharacter myCharacter) {
        ArrayList<MyCharacter> myCharacters = gonzaloReadCharactersFromJSON();
        myCharacters.add(myCharacter);
        return true;
    }

    @Override
    public void gonzaloRemoveMyCharacterFromList(MyCharacter auxCharacter) {
        //TODO: QUIZAS ESTARIA BIEN HACER LA FUNCION BOOLEANA PARA CHEQUEAR QUE SE HA ELIMINADO BIEN EL CHARACTER
        ArrayList<MyCharacter> myCharacters = gonzaloReadCharactersFromJSON();
        myCharacters.remove(auxCharacter);
    }


}
