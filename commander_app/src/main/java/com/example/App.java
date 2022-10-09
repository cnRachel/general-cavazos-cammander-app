package com.example;

import java.util.*;

import org.json.simple.*;

public class App {

  static Stack<String> commandHistory = new Stack<String>();
  public static void main(String[] args) {
    String fileName =
      "/Users/rache/Documents/GitHub/general-cavazos-cammander-app/commander_app/src/main/java/com/example/commands.json";

    // read coammands
    JSONArray commandJSONArray = JSONFile.readArray(fileName);
    String[] commandArray = getCommandArray(commandJSONArray);

    Scanner scan = new Scanner(System.in);
    Character command = '_';

    while (command != 'q') {
      printMenu();
      System.out.print("Enter a command: ");
      command = menuGetCommand(scan);
      executeCommand(scan, command, commandArray);
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
  private static void executeCommand(Scanner scan, Character command, String[] commandArray) {

    switch (command) {
      case 'q':
        System.out.println("Thank you General Cavazos!");
        break;
      case 'l':
        print(commandArray);
        break;
      case 'i':
        issueCommand(commandArray);
        break;
      case 'r':
        if (commandHistory.size() == 0) {
          System.out.println("ERROR: There are no commands to redo. Please issue a command.");
          break;
        }
        System.out.println("[REDO COMMAND ISSUED]: General Cavazos orders the troops to redo: " + commandHistory.peek());
        break;
      case 'u':
        if (commandHistory.size() == 0) {
          System.out.println("ERROR: There are no commands to undo. Please issue a command.");
          break;
        }
        System.out.println("[UNDO COMMAND ISSUED]: General Cavazos orders the troops to undo: " + commandHistory.peek());
        commandHistory.pop();
        break;
      default:
        System.out.println("ERROR: Unknown command");
        break;
    }

  }

  // randomly issue command from General Cavazos
  public static void issueCommand(String[] commandArray) {
    Random rand = new Random();
    System.out.print("[COMMAND ISSUED]: General Cavazos orders the troops to do: ");
    int randIndex = rand.nextInt(commandArray.length);
    System.out.println(commandArray[randIndex]);
    commandHistory.push(commandArray[randIndex]);
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
