package com.example;

import java.util.*;

import org.json.simple.*;

public class App {

  Stack<String> commandHistory = new Stack<String>();
  public static void main(String[] args) {
    String fileName =
      "/Users/rache/Documents/GitHub/general-cavazos-cammander-app/commander_app/src/main/java/com/example/commands.json";

    // read coammands
    JSONArray commandJSONArray = JSONFile.readArray(fileName);
    String[] commandArray = getCommandArray(commandJSONArray);

    // // print list of all commands
    // System.out.println("----- List of all commands -----");
    // print(commandArray);

    // System.out.println(
    //   "----- Issuing 5 random commands from General Cavazos -----"
    // );
    // randomCommand(commandArray, 5);

    Scanner scan = new Scanner(System.in);
    Character command = '_';

    while (command != 'q') {
      printMenu();
      System.out.print("Enter a command: ");
      command = menuGetCommand(scan);
      executeCommand(scan, command);
    }

    scan.close();


  }

  private static void printMenuLine() {
    System.out.println(
      "----------------------------------------------------------"
    );
  }

  // print the commands
  private static void printMenuCommand(Character command, String desc) {
    System.out.printf("%s\t%s\n", command, desc);
  }

  // prints the menu
  public static void printMenu() {
    printMenuLine();
    System.out.println("General Cavazos Commander App");
    printMenuLine();

    printMenuCommand('i', "Issue a command");
    printMenuCommand('l', "List all of the commands");
    printMenuCommand('u', "Undo the last command that was issued");
    printMenuCommand('r', "Redo the last command that was issued");
    printMenuCommand('q', "Quit");    

    printMenuLine();
  }

  // reads user input
  private static Character menuGetCommand(Scanner scan) {
    Character command = '_';
    String rawInput = scan.nextLine();

    if (rawInput.length() > 0) {
      rawInput = rawInput.toLowerCase();
      command = rawInput.charAt(0);
    }

    return command;
  }

  // command functions
  private static void executeCommand(Scanner scan, Character command) {


    switch (command) {
      case 'q':
      System.out.println("Thank you General Cavazos!");
      break;
    }

  }

  // randomly issue commands from General Cavazos
  public static void randomCommand(String[] commandArray, int numCommand) {
    Random rand = new Random();
    System.out.printf("Number\tCommand\n");
    System.out.printf("------\t---------------\n");
    for (int i = 0; i < numCommand; i++) {
      int randIndex = rand.nextInt(commandArray.length);
      System.out.printf("%04d\t%s\n", i, commandArray[randIndex]);
    }
  }

  // print command array
  public static void print(String[] commandArray) {
    System.out.printf("Number\tCommand\n");
    System.out.printf("------\t---------------\n");
    for (int i = 0; i < commandArray.length; i++) {
      System.out.printf("%04d\t%s\n", i, commandArray[i]);
    }
  }

  // get array of commands
  public static String[] getCommandArray(JSONArray commandArray) {
    String[] arr = new String[commandArray.size()];

    // get names from json object
    for (int i = 0; i < commandArray.size(); i++) {
      String command = commandArray.get(i).toString();
      arr[i] = command;
    }
    return arr;
  }
}
