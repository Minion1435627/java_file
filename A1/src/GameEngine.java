/**
 * Author: Yang, Shu-Han
 * Student Id: 1435627
 * Email: shuhyang3@student.unimelb.edu.au
 */

import java.util.Scanner;

public class GameEngine {

    private int gameNo = 0;
    private String report = "";
    private boolean completedGame = false;


    public  static void main(String[] args){

        GameEngine engine = new GameEngine();

        // check command line
        if (args.length != 3){
            System.out.println("Invalid Inputs to set layout. Exiting the program now.");
        }
        else{
            if (Integer.parseInt(args[0]) <= 0 || Integer.parseInt(args[1]) <= 0 || Integer.parseInt(args[2]) <= 0){
                System.out.println("Invalid Inputs to set layout. Exiting the program now.");
            }
            else{
                engine.displayMessage();
                Boolean  runMainMenu = true;
                Scanner input = new Scanner(System.in);
                int choice = -1;

                Maze maze = new Maze();
                while (runMainMenu){
                    engine.printMenu();
                    choice = Integer.parseInt(input.nextLine());
                    switch (choice){
                        case 1:
                            maze = engine.chooseTypeMaze(input, Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
                            System.out.println("Maze created. Proceed to play the game.");
                            break;
                        case 2:
                            engine.playGame(maze, input);
                            break;
                        case 3:
                            engine.resumeMaze(maze, input);
                            break;
                        case 4:
                            engine.viewScore();
                            break;
                        case 5:
                            System.out.println("Pacman says - Bye Bye Player.");
                            break;
                        default:
                            System.out.println("Invalid Input.");

                    }
                    if (choice == 5){
                        runMainMenu = false;
                    }

                }
            }

        }



    }



    private void displayMessage() {
        System.out.println(" ____         __          ___        _  _         __         __ _ \n" +
                "(  _ \\       / _\\        / __)      ( \\/ )       / _\\       (  ( \\\n" +
                " ) __/      /    \\      ( (__       / \\/ \\      /    \\      /    /\n" +
                "(__)        \\_/\\_/       \\___)      \\_)(_/      \\_/\\_/      \\_)__)");
        System.out.println("");
        System.out.println("Let the fun begin");
        System.out.println("(`<    ...   ...  ...  ..........  ...");
        System.out.println("");
    }
    private void printMenu(){
        System.out.print("Select an option to get started.\n" +
                "Press 1 to select a pacman maze type.\n" +
                "Press 2 to play the game.\n" +
                "Press 3 to resume the game.\n" +
                "Press 4 to view the scores.\n" +
                "Press 5 to exit.\n" +
                "> ");
    }

    // option 1
    private Maze chooseTypeMaze(Scanner input, int length, int width, long seed){
        Boolean check = true;
        int mazeType = -1;
        while (check){
            System.out.print("Please select a maze type.\n" +
                    "Press 1 to select lower triangle maze.\n" +
                    "Press 2 to select upper triangle maze.\n" +
                    "Press 3 to select horizontal maze.\n" +
                    "> ");
            mazeType = input.nextInt();
            input.nextLine();
            if (mazeType == 1 || mazeType == 2 || mazeType == 3){
                check = false;
            }
            else{
                System.out.println("Invalid Input.");
            }
        }
        Maze maze = new Maze(seed, length, width, mazeType);
        return maze;

    }
    // option 2
    private void playGame(Maze maze, Scanner input){
        if (maze.isMazeCreate()){
            if (!maze.isGameContinue()){
                maze.setGameContinue(true);
                gameNo += 1;
                gameRule();
                createMaze(maze, maze.getMazeLength(), maze.getMazeWidth(), maze.getColPosFood(), maze.getRowPosFood(),maze.getMazeType(),input);
            }
            else{
                System.out.print("Previous game hasn't ended yet. Do you want to discard previous game?\n" +
                        "Press N to go back to main menu to resume the game or else press any key to discard.\n" +
                        "> ");
                String discard_old = input.nextLine();
                discard_old = discard_old.toUpperCase();
                if (!discard_old.equals("N")){
                    gameRule();
                    maze.setPacman_row(1);
                    maze.setPacman_col(1);
                    maze.setHit(0);
                    maze.setMoveTime(0);
                    gameNo+=1;
                    createMaze(maze, maze.getMazeLength(), maze.getMazeWidth(), maze.getColPosFood(), maze.getRowPosFood(),maze.getMazeType(), input);

                }
            }
        }
        else{
            System.out.println("Maze not created. Select option 1 from main menu.");
        }

    }

    private void createMaze (Maze maze, int mazeLength, int mazeWidth, int colPosFood, int rowPosFood, int mazeType, Scanner input){
        String direction = "";
        while (!direction.equals("Q") && !direction.equals("q") ){
            maze.buildMaze(mazeLength, mazeWidth, colPosFood, rowPosFood, mazeType);
            System.out.print("Press W to move up.\n" +
                    "Press A to move left.\n" +
                    "Press S to move down.\n" +
                    "Press D to move right.\n" +
                    "Press Q to exit.\n" +
                    "> ");
            direction = input.nextLine();
            maze.movement(direction, mazeLength, mazeWidth, mazeType);
            if (maze.getPacmanCol() == colPosFood && maze.getPacmanRow() == rowPosFood){
                maze.buildMaze(mazeLength, mazeWidth, colPosFood, rowPosFood, mazeType);
                maze.setGameContinue(false);
                maze.setPacman_row(1);
                maze.setPacman_col(1);
                double score = 20 - maze.getMoveTime() * 0.25 - maze.getHit() * 0.5;
                report=report+String.format("|%8s|%8s|%8s|%8.2f|%n",gameNo, maze.getHit(),maze.getMoveTime(),score);
                System.out.printf("Game has ended! Your score for this game is %s\n", (score * 10 % 1 == 0) ? String.format("%.1f", score) : String.format("%.2f", score));
                maze.setHit(0);
                maze.setMoveTime(0);
                completedGame = true;
                break;
            }
        }

    }

    private void gameRule(){
        System.out.println("Move the Pacman towards the food pellet.\n" +
                "  > You gain 20 points when Pacman get the food.\n" +
                "  > You lose 0.5 point when you hit the wall/boundary.\n" +
                "  > Score = 20 * Food - 0.5 * hits - 0.25 * moves.");
    }

    // option 3
    private void resumeMaze(Maze maze, Scanner input){
        if (maze.isMazeCreate()){
            if (maze.isGameContinue()){
                System.out.println("Restart your game from the last position you saved.");
                createMaze(maze, maze.getMazeLength(), maze.getMazeWidth(), maze.getColPosFood(), maze.getRowPosFood(),maze.getMazeType(),input);
            }
            else{
                System.out.println("No paused game found. Select option 2 from main menu to start a new game.");
            }
        }
        else{
            System.out.println("Maze not created. Select option 1 from main menu.");
        }

    }

    // option 4
    private void viewScore(){
        if (completedGame == false){
            System.out.println("No completed games found.");
        }
        else{
            System.out.print(String.format("|%8s|%8s|%8s|%8s|%n","# Game", "# Hits","# Moves","# Score"));
            System.out.print(String.format("|%8s|%8s|%8s|%8s|%n","========", "========","========","========"));
            System.out.print(report);
        }


    }



}

