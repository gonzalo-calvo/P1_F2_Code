package Presentation;

import Business.Entity.MyCharacter;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainView {

    Scanner scanner = new Scanner(System.in);

    public MainView() {
    }

    public void printLine(String line){
        System.out.println(line);
    }

    public void printLineWithNoCarrilReturn(String line){
        System.out.print(line);
    }

    public String scanLine() {
        return scanner.nextLine();
    }

    public int askUserOptionBetweenNumbers(String text, int min, int max){
        int option;
        String input;

        Scanner scanner = new Scanner(System.in);

        do{
            System.out.print(text);
            input = scanner.nextLine();
            if (input == ""){
                return -1;
            }

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


    public String askUserForValidCharacterName() {
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
                finalName = myToUpperCamelCase(playerName);
                System.out.println("no special characters and UCC is: " + finalName);
            } else {
                System.out.println("Incorrect player name syntax's.");
            }

        } while (flag);

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
        return scanLine();
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
}
