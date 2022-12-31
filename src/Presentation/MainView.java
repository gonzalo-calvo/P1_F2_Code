package Presentation;

import Business.Entity.Adventure;
import Business.Entity.Encounter;
import Business.Entity.Monster;
import Business.Entity.MyCharacter;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainView {

    Scanner scanner = new Scanner(System.in);

    public MainView() {
    }

    public static void printLogo() {
        System.out.println("   ____ _               __       __    ____ ___   ___   _____");
        System.out.println("  / __/(_)__ _   ___   / /___   / /   / __// _ \\ / _ \\ / ___/");
        System.out.println(" _\\ \\ / //  ' \\ / _ \\ / // -_) / /__ _\\ \\ / , _// ___// (_ /");
        System.out.println("/___//_//_/_/_// .__//_/ \\__/ /____//___//_/|_|/_/    \\___/");
        System.out.println("              /_/\n");
        System.out.println("Welcome to simple LSRPG");
    }

    public void printLine(String line){
        System.out.println(line);
    }

    public void printLineWithNoCarrilReturn(String line){
        System.out.print(line);
    }


    public int askUserOptionBetweenNumbers(String text, int min, int max){
        int option;
        String input;


        do{
            System.out.print(text);
            input = scanner.nextLine();
            /*if (input == ""){
                return -1;
            }*/

            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException excepcion) {
                option = -1;
            }


            if (option<min || option>max){
                System.out.println("\nWrong input! Try again.");
            }

        } while (option<min || option>max);
        return option;
    }


    public String askUserForValidCharacterName(ArrayList<MyCharacter> myCharacters) {
        String playerName, finalName="";
        boolean flag = true;
        Matcher hasDigit, hasSpecial;
        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

        System.out.println("“What’s your name?”");

        do {
            System.out.print("\n-> Enter your name:");
            playerName = scanner.nextLine();

            hasDigit = digit.matcher(playerName);
            hasSpecial = special.matcher(playerName);

            if (!hasSpecial.find() && !hasDigit.find()){
                flag = false;
                for (MyCharacter myCharacter : myCharacters) {
                    if (myCharacter.getName().toLowerCase().equals(playerName.toLowerCase())) {
                        flag = true;
                        System.out.println("This name already exists in characters database. Choose another one");
                        break;
                    }
                }
            } else {
                System.out.println("Incorrect player name syntax's.");
            }

        } while (flag);
        finalName = myToUpperCamelCase(playerName);
        System.out.println("no special characters and UCC is: " + finalName);
        return finalName;
    }

    public String myToUpperCamelCase(String string){
        char aux;
        char[] ch = new char[string.length()];

        for (int i = 0; i < string.length(); i++) {
            ch[i] = string.charAt(i);
        }

        for (int i = 0; i < string.length(); i++) {
            if (ch[i] == ' '){
                i++;
                aux = Character.toUpperCase(ch[i]);
                ch[i] = aux;
            } else if (i==0){
                aux = Character.toUpperCase(ch[i]);
                ch[i] = aux;
            }
        }

        return String.valueOf(ch);
    }

    public String askUserForValidPlayerName() {
        System.out.println("“And now, if I may break the fourth wall, who is your Player?”\n");
        System.out.print("-> Enter the player’s name: ");
        return scanner.nextLine();
    }

    public void printStats(MyCharacter myCharacter) {
        System.out.println("\n Your stats are:");
        System.out.println("    - Body: " + myCharacter.getBody());
        System.out.println("    - Mind: " + myCharacter.getMind());
        System.out.println("    - Spirit: " + myCharacter.getSpirit());
    }

    public void printMyCharacter(MyCharacter myCharacter) {
        //TODO: FALTA ACABAR DE IMPLEMENTAR
        System.out.println("* Name:     " + myCharacter.getName());
        System.out.println("* Player:   " + myCharacter.getPlayer());
        System.out.println("* Class:    " + myCharacter.getType());

    }

    public void printFullMyCharacter(MyCharacter myCharacter){
        System.out.println("* Name:     " + myCharacter.getName());
        System.out.println("* Player:   " + myCharacter.getPlayer());
        System.out.println("* XP:       " + myCharacter.getExperience());
        System.out.println("* Body:     " + myCharacter.getBody());
        System.out.println("* Mind:     " + myCharacter.getMind());
        System.out.println("* Spirit:   " + myCharacter.getSpirit());
        System.out.println("* Class:    " + myCharacter.getType());
        System.out.println("* Health Points:    " + myCharacter.getHealthPoints());
        System.out.println("* Initiative:    " + myCharacter.getInitiative());
    }

    public void printFullMyMonster(Monster monster) {
        System.out.println("* Name:           " + monster.getName());
        System.out.println("* Challenge:      " + monster.getChallenge());
        System.out.println("* Experience:     " + monster.getExperience());
        System.out.println("* HitPoints:      " + monster.getHitPoints());
        System.out.println("* Initiative:     " + monster.getInitiative());
        System.out.println("* DamageDice:     " + monster.getDamageDice());
        System.out.println("* DamageType:     " + monster.getDamageType());
        System.out.println("************* OPTIONAL *************");
        System.out.println("* Amount:         " + monster.getAmount());
    }

    public void chooseMonstersForEncounter(ArrayList<Monster> monsterList, Encounter encounter){

    }

    public String scanLine() {
        return scanner.nextLine();
    }

    public void printForListCharacters(ArrayList<MyCharacter> myCharacterList) {
        for (int i = 0; i < myCharacterList.size(); i++) {
            System.out.println("   " + (i+1) + ". " + myCharacterList.get(i).getName());
        }
        System.out.println("\n   0. Back\n");
    }

    public void printEncounterMenu(int i, int numEncounters, Encounter encounter) {
        System.out.println("\n* Encounter " + i + " / " + numEncounters + "");
        System.out.println("* Monsters in encounter");
        printMonstersInEncounter(encounter);
        System.out.println("\n1. Add monster");
        System.out.println("2. Remove monster");
        System.out.println("3. Continue\n");
    }

    public void printMonstersInEncounter(Encounter encounter) {
        if (encounter.getMonsterList() == null){
            System.out.println("  # Empty");
        } else if (encounter.getMonsterList().isEmpty()){
            System.out.println("  # Empty");
        }else {
            for (int i = 1; i <= encounter.getMonsterList().size(); i++) {
                System.out.println("  " +i + ". " + encounter.getMonsterList().get(i-1).getName() +
                        " (x" + encounter.getMonsterList().get(i-1).getAmount() + ")");
            }
        }
    }

    public String getValidAdventureName(ArrayList<Adventure> adventureList) {
        String name;
        boolean flag;

        do{
            flag = true;
            System.out.print("-> Name your adventure: ");
            name = scanLine();
            if (!name.equals("")) {
                if (adventureList == null) {
                    return name;
                } else {
                    for (Adventure adventure : adventureList) {
                        if (adventure.getName().equalsIgnoreCase(name)) {
                            System.out.println("This Adventure name already in database. Try another one\n");
                            flag = false;
                        }
                    }
                }
            } else {
                flag = false;
                System.out.println("Name must contain at least one character. Try again\n");
            }
        } while (!flag);
        return name;
    }

    public void printMonstersToChoose(ArrayList<Monster> monsters) {
        System.out.println();
        for (int i = 1; i < monsters.size()+1; i++) {
            System.out.println(i + ". " + monsters.get(i-1).getName() + " (" + monsters.get(i-1).getChallenge() + ")");
        }
        System.out.println();
    }

    public void printFullAdventure(Adventure adventure) {
        System.out.println("Adventure name: " + adventure.getName());
        System.out.println("Num encounters: " + adventure.getNumEncounters());
        for (int i = 0; i < adventure.getNumEncounters(); i++) {
            System.out.println("Encounter [" + i + "]");
            for (int j = 0; j < adventure.getEncountersList().get(i).getMonsterList().size(); j++) {
                printFullMyMonster(adventure.getEncountersList().get(i).getMonsterList().get(j));
            }
        }
    }

    public void showAdventureList(ArrayList<Adventure> adventures) {
        int i = 0;
        System.out.println("\n Available adventures: ");
        for (Adventure adventure : adventures) {
            System.out.println("  " + (i+1) + ". " + adventure.getName());
            i++;
        }
    }

    public void showCharacterList(ArrayList<MyCharacter> myCharacters) {
        int index = 0;
        System.out.println("Available characters: ");
        for (MyCharacter myCharacter : myCharacters) {
            System.out.println((index+1) + ". " + myCharacter.getName());
            index++;
        }

    }

    public void printEncounterHeather(int numEncounter, Encounter encounter) {
        numEncounter++;
        System.out.println("\n---------------------");
        System.out.println("Starting encounter " + numEncounter + ":");
        for (int i = 0; i < encounter.getMonsterList().size(); i++) {
            System.out.println("  - " + encounter.getMonsterList().get(i).getAmount() + "x " + encounter.getMonsterList().get(i).getName());
        }
        System.out.println("---------------------\n");
    }
}
