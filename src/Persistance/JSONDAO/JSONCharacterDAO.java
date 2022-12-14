package Persistance.JSONDAO;

import Business.Entity.MyCharacter;
import Persistance.DAO.CharacterDAO;
import Presentation.MainView;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
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

    MainView mainView = new MainView(); //TODO: RETIRAR ESTA RELACION PUES ES SOLO PARA HACER PRUEBAS (RETIRAR EL DIA DE ENTREGA)

    private static String route = "Files/characters.json";
    private static Path path = Path.of(route);

    Gson gson= new Gson();
    JsonParser parser = new JsonParser();

    public JSONCharacterDAO(){}

    @Override
    public void createFile(File file) {
        try {
            if(file.exists()){

            } else {
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
        System.out.println("Full character array is: \n");
        for (int i = 0; i < myCharacters.size(); i++) {
            mainView.printFullMyCharacter(myCharacters.get(i));
        }
        return writeArrayObjectToJavaFile(myCharacters);
    }

    @Override
    public boolean gonzaloRemoveMyCharacterFromList(MyCharacter auxCharacter) {
        //TODO: QUIZAS ESTARIA BIEN HACER LA FUNCION BOOLEANA PARA CHEQUEAR QUE SE HA ELIMINADO BIEN EL CHARACTER
        ArrayList<MyCharacter> myCharacters = gonzaloReadCharactersFromJSON();

        for (int i = 0; i < myCharacters.size(); i++) {
            if (myCharacters.get(i).getName().equals(auxCharacter.getName())){
                myCharacters.remove(i);
            }
        }


        return writeArrayObjectToJavaFile(myCharacters);
    }

    private boolean writeArrayObjectToJavaFile(ArrayList<MyCharacter> myCharacters) {

        JSONArray list = new JSONArray();
        for (MyCharacter myCharacter : myCharacters) {
            JSONObject obj = new JSONObject();
            obj.put("name", myCharacter.getName());
            obj.put("player", myCharacter.getPlayer());
            obj.put("xp", myCharacter.getExperience());
            obj.put("body", myCharacter.getBody());
            obj.put("mind", myCharacter.getMind());
            obj.put("spirit", myCharacter.getSpirit());
            obj.put("class", myCharacter.getType());
            list.add(obj);
        }

        try (FileWriter file = new FileWriter(route)) {
            file.write(list.toJSONString());
        } catch (IOException e) {
            System.out.println("ERROR: file characters not possible to write");
            return false;
        }
        return true;
    }


}
