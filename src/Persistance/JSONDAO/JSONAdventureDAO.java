package Persistance.JSONDAO;

import Business.Entity.Adventure;
import Persistance.DAO.AdventureDAO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class JSONAdventureDAO implements AdventureDAO {

    private static String route = "Files/adventures.json";

    Gson gson = new Gson();



    public JSONAdventureDAO() {
    }


    @Override
    public ArrayList<Adventure> getAdventureList() {
        Path filePath = Path.of("Files/adventures.json");
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(filePath.toUri()), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            return null;
        }

        String fileContent = contentBuilder.toString();
        //System.out.println("Adventure file content is: " + fileContent);

        Type userListType = new TypeToken<ArrayList<Adventure>>(){}.getType();

        return gson.fromJson(fileContent, userListType);
    }

    @Override
    public void addAdventureListToJson(Adventure adventure) {
        ArrayList<Adventure> adventures = getAdventureList();
        
        if (adventures == null){
            adventures = new ArrayList<>();
        }
        adventures.add(adventure);
        saveAdventureListToJson(adventures);
    }

    private void saveAdventureListToJson(ArrayList<Adventure> adventures) {

        try (FileWriter file = new FileWriter(route)) {
            file.write(gson.toJson(adventures));
        } catch (IOException e) {
            System.out.println("ERROR: file characters not possible to write");
        }
    }
}
