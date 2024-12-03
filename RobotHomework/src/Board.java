/*
 * Ethan Blackwood
 */
import java.io.File;
import java.util.Scanner;


public class Board {
    public static Scanner keyboard = new Scanner(System.in);
    public static final int BOARD_SIZE = 10;
    public static final int OBST_AMT = (BOARD_SIZE * BOARD_SIZE)/10;
    private char [][] board;
    public static final char EMPTY = '_';
    public static final char OBST = 'X';
    public static final char ROBOT = 'R';
    public static final char PATH = '#';

    public static final String UP = "Move Up";
    public static final String DOWN = "Move Down";
    public static final String LEFT = "Move Left";
    public static final String RIGHT = "Move Right";

    private LLStack<int[]> locations;//stack with robot positions
    private int[] currLoc;

    public Board(){
        this.init();
    }
    private void init(){//CREATES EMPTY BOARD AND ADD OBSTACLES FROM FILE
        board = new char [BOARD_SIZE][BOARD_SIZE];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length;j++){
                board[i][j] = EMPTY;
            }
        }
        this.addObstacles();

        board[0][0] = ROBOT;
        currLoc = new int [] {0,0};
        locations = new LLStack<int[]>();
        
    }

    private boolean isValid(int x){//check if robot can move there
        return x >= 0 && x < board.length;
    }

    private void addObstacles(){
        
        System.out.println("Enter the name of your Board File (Existing: 'board.txt'):");//get board file name
        String boardFileName = keyboard.nextLine();
        File boardFile = new File(boardFileName);
    
        try (Scanner fileScanner = new Scanner(boardFile)) {
            int row = 0;
            while (fileScanner.hasNextLine() && row < BOARD_SIZE) {
                String line = fileScanner.nextLine();

                for (int col = 0; col < line.length() && col < BOARD_SIZE; col++) {
                    char cell = line.charAt(col);
                    if (cell == OBST || cell == EMPTY) {//cjeck if obst or empty
                        board[row][col] = cell; //assign to position
                    } 
                    else {
                        System.out.println("Invalid character");
                    }
                }
                row++;
            }
        } catch (Exception e) {
            System.out.println("Some error occured");
        }
    

        this.printFullMaze();

    }


    public void move(String input){
        board[currLoc[0]][currLoc[1]] = EMPTY;
        int currY = currLoc[0];
        int currX = currLoc[1];

        int [] copyLoc = {currLoc[0], currLoc[1]};

        if(input.equalsIgnoreCase(UP)){
            if(isValid(currY -1) && board[currY-1][currX] != OBST){
                locations.push(copyLoc);
                currLoc[0]--;
            }
            else{
                System.out.println("THE ROBOT CRASHED");
                System.exit(0);
            }
        }
        else if(input.equalsIgnoreCase(DOWN)){
            if(isValid(currY +1) && board[currY+1][currX] != OBST){
                locations.push(copyLoc);
                currLoc[0]++;
            }
            else{
                System.out.println("THE ROBOT CRASHED");
                System.exit(0);
            }
        }
        else if(input.equalsIgnoreCase(LEFT)){
            if(isValid(currX -1) && board[currY][currX-1] != OBST){
                locations.push(copyLoc);
                currLoc[1]--;
            }
            else{
                System.out.println("THE ROBOT CRASHED");
                System.exit(0);
            }
        }
        else if(input.equalsIgnoreCase(RIGHT)){
            if(isValid(currX + 1) && board[currY][currX+1] != OBST){
                locations.push(copyLoc);
                currLoc[1]++;
            }
            else{
                System.out.println("THE ROBOT CRASHED");
                System.exit(0);
            }
        }
        
        else{
            System.out.println("THE ROBOT CRASHED");
            System.exit(0);
        }
        board[currLoc[0]][currLoc[1]] = ROBOT;
    }

    public void printFullMaze(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    
}
