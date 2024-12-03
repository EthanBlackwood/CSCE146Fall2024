/*
 * Ethan Blackwood
 */

import java.io.File;
import java.util.Scanner;

public class RobotFrontEnd {
    public static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        
       

        //PROGRAM LOOP
        boolean quit = false;
            while(!quit){
                printMenu();
                int choice = keyboard.nextInt();
                keyboard.nextLine();

                switch(choice){
                    case 1:
                        System.out.println("Enter the name of your Commands File(Existing: 'robotCommands.txt'):");
                        String commandsFileName = keyboard.nextLine();
                        File commandsFile = new File(commandsFileName);
                        LLQueue<String> commandQueue = new LLQueue<>();

                    //read files and add to queue
                    try (Scanner fileScanner = new Scanner(commandsFile)) {
                        while (fileScanner.hasNextLine()) {
                            String command = fileScanner.nextLine();
                            commandQueue.enqueue(command);
                        }
                    } catch (Exception e) {
                        System.out.println("An error occurred");
                        break;
                    }

                        Board board = new Board();//SUBMIT BOARD FILE HERE


                        //run commands from queue on 'board'
                        while (commandQueue.size() > 0) {
                            String command = commandQueue.dequeue();
                            board.move(command);
                            board.printFullMaze(); //print board each move
                            System.out.println("========================================================");
                            try {
                                Thread.sleep(2000); //time between prints
                            } catch (Exception e) {
                                
                            }
                        }


                        break;
                    case 2:
                        quit = true;
                        break;
                    default:
                        System.out.println("Invalid Input");

                }

            }
        

    }


    public static void printMenu(){//print main menu
        System.out.println("Welcome to the Robot Command Simulator!");
        System.out.println("Please select an option to continue.\n");
        System.out.println("1.  Submit command files and Simulate");
        System.out.println("2.  Quit");
       
    }
}
