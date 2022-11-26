package Presentation;

import java.util.Scanner;

public class MainView {

    Scanner scanner = new Scanner(System.in);

    public MainView() {
    }

    public void printLine(String line){
        System.out.println(line);
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
        System.out.println("“What’s your name?”\n");
        System.out.print("-> Enter your name:");
        return scanLine();
    }

    public String askUserForValidPlayerName() {
        String playerName;
        System.out.println("“And now, if I may break the fourth wall, who is your Player?”\n");
        System.out.print("-> Enter the player’s name: ");
        playerName = scanner.nextLine();


        return playerName;

    }
}
