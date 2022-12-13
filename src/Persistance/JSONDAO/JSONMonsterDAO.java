package Persistance.JSONDAO;

import Business.Entity.Monster;
import Business.Entity.MyCharacter;
import Persistance.DAO.MonsterDAO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class JSONMonsterDAO implements MonsterDAO {

    Gson gson= new Gson();

    public JSONMonsterDAO() {
    }

    @Override
    public ArrayList<Monster> getMonstersFromFile() {
        Path filePath = Path.of("Files/monsters.json");
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(filePath.toUri()), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            return null;
        }

        String fileContent = contentBuilder.toString();
        System.out.println("File content is: " + fileContent);

        Type userListType = new TypeToken<ArrayList<Monster>>(){}.getType();


        return gson.fromJson(fileContent, userListType);
    }
}
