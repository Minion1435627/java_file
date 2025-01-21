import Constants.Constants;
import Constants.Messages;

import java.util.Scanner;

public class GameEngine {

    public static Scanner keyboard = new Scanner(System.in);
    private Maze[] completedMazes = new Maze[0];
    private Maze[] tempMazes = new Maze[2];

    public void setCompletedMazes(Maze[] completedMazes) {
        this.completedMazes = completedMazes;
    }

    /**
     * run the playerMode and the main menu.
     * @param args the inputs from the command line
     */
    public static void main(String[] args) {

        GameEngine engine = new GameEngine();

        if (engine.isInvalidArgs(args)) {
            System.out.println(Messages.INVALID_ARGS);
        } else {
            engine.displayMessage();
            String playerModeOption;
            Maze maze = null;
            boolean gameContinue = true;
            while (gameContinue) {
                playerModeOption = engine.playerMenu();
                if (playerModeOption.equals(Constants.PLAYER_MODE3)) {
                    gameContinue = false;
                } else {
                    String option;
                    Boolean runMainMenu = true;
                    while (runMainMenu) {
                        engine.printMenu();
                        option = keyboard.nextLine();
                        switch (option) {
                            case Constants.SELECT_MAZE:
                                maze = engine.selectMaze(args, playerModeOption);
                                System.out.println(Messages.CREATED_MAZE);
                                break;
                            case Constants.START_GAME:
                                maze = engine.startGame(maze, args, playerModeOption);
                                break;
                            case Constants.RESUME_GAME:
                                maze = engine.resumeGame(maze, playerModeOption);
                                break;
                            case Constants.VIEW_SCORES:
                                engine.viewScore();
                                break;
                            case Constants.QUIT_MAIN:
                                System.out.println(Messages.EXIT_MAIN_MENU);
                                break;
                            default:
                                System.out.println(Messages.INVALID_INPUT);

                        }
                        if (option.equalsIgnoreCase(Constants.QUIT_MAIN)) {
                            runMainMenu = false;
                        }
                    }
                }


            }
        }


    }

    /**
     * choose the playerMode: single-player, multiplayer or exit the game
     * once you exit from the main menu, the program will again prompt for this method.
     * @return mode. Return the user input.
     */
    private String playerMenu() {
        String mode = "";
        boolean correctModeChoose = false;
        while (!correctModeChoose) {
            System.out.print("Make player selection.\n" +
                    "Press 1 for Single Player.\n" +
                    "Press 2 for Multi Player.\n" +
                    "Press 3 to exit.\n" +
                    "> ");
            mode = keyboard.nextLine();
            switch (mode) {
                case Constants.PLAYER_MODE1, Constants.PLAYER_MODE2:
                    correctModeChoose = true;
                    break;
                case Constants.PLAYER_MODE3:
                    System.out.println(Messages.EXIT_MESSAGE);
                    correctModeChoose = true;
                    break;

                default:
                    System.out.println(Messages.INVALID_INPUT);
                    break;
            }
        }
        return mode;

    }

    /**
     * Print the main menu options for the user.
     */
    private void printMenu() {
        System.out.println("Select an option to get started.");
        System.out.println("Press 1 to select a pacman maze type.");
        System.out.println("Press 2 to play the game.");
        System.out.println("Press 3 to resume the game.");
        System.out.println("Press 4 to view the scores.");
        System.out.println("Press 5 to exit.");
        System.out.print("> ");
    }

    /**
     * Check the length and value from command line.
     * @param args The inputs from the command line.
     * @return invalidInput. If the invalidInput is true, the program will print the error messages.
     */
    private boolean isInvalidArgs(String[] args) {
        boolean invalidInput = false;
        if (args.length != Constants.CMD_ARGS) {
            invalidInput = true;
        }
        for (int i = 0; i < args.length; i++) {
            if (Integer.parseInt(args[i]) <= 0) {
                invalidInput = true;
            }
        }
        return invalidInput;
    }

    /**
     * If the inputs are valid, the program will show the welcome message.
     */
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

    /**
     * Select the maze that the user want to play and create new mazes.
     * If the playerModeOption is single-player, create one maze. Otherwise, create two mazes.
     * If the playerModeOption equals to two, put the created maze to the temporary maze array.
     * @param args to create the new maze, needs the maze width and length from the command line
     * @param playerModeOption Decide the number of mazes being created.
     * @return maze. Return the created maze.
     */
    // option 1
    private Maze selectMaze(String[] args, String playerModeOption) {
        Boolean checkMapType = true;
        int mazeType = 0;
        while (checkMapType) {
            System.out.print("Please select a maze type.\n" +
                    "Press 1 to select lower triangle maze.\n" +
                    "Press 2 to select upper triangle maze.\n" +
                    "Press 3 to select horizontal maze.\n" +
                    "> ");
            mazeType = keyboard.nextInt();
            keyboard.nextLine();
            if (mazeType == Constants.LOWER_TRIANGLE_MAZE || mazeType == Constants.UPPER_TRIANGLE_MAZE || mazeType == Constants.HORIZONTAL_MAZE) {
                checkMapType = false;
            } else {
                System.out.println(Messages.INVALID_INPUT);
            }
        }
        Maze maze = new Maze(mazeType, Integer.parseInt(args[0]), Integer.parseInt(args[1]), Long.parseLong(args[2]));
        if (playerModeOption.equals(Constants.PLAYER_MODE1)) {
            maze.setPlayerName(Messages.PLAYER1);
            return maze;
        } else {
            maze.setPlayerName(Messages.PLAYER1);
            Maze maze2 = new Maze(mazeType, Integer.parseInt(args[0]), Integer.parseInt(args[1]), Long.parseLong(args[2]));
            maze2.setPlayerName(Messages.PLAYER2);
            maze2.gameNo -= 1;
            maze2.setGameCurrentNO(maze2.gameNo);
            tempMazes[0] = maze;
            tempMazes[1] = maze2;
            return maze;
        }

    }

    /**
     * Print the game rules for the players.
     */
    private void gameRules() {
        System.out.println("Move the Pacman towards the food pellet and gain super power to kill monsters.\n" +
                "  > You gain 20 points if Pacman finishes the game without dying.\n" +
                "  > You gain 10 more points for every monster you killed.\n" +
                "  > You gain 5 points for every special food that you have eaten.\n" +
                "  > You lose 0.5 point when you hit the wall/boundary.\n" +
                "  > You lose 0.25 points for every move.\n" +
                "  > Score = 5 * foodEaten + 10 * monsterKilled - 0.5 * numOfHits - 0.25 * numOfMoves  +  20 if not dead.");
    }


    /**
     * Put the ended game into the completedMaze.
     * Change the completedMaze Array length by add 1 each time and put the old completedMaze array into the new completedMaze.
     * @param maze The maze which is completed.
     */
    private void completeMazes(Maze maze){
        Maze[] newMaze = new Maze[completedMazes.length + 1];
        for (int i = 0; i < completedMazes.length; i++){
            newMaze[i] = completedMazes[i];
        }
        newMaze[newMaze.length -1] = maze;
        setCompletedMazes(newMaze);
    }

    /**
     * Check player 1 or player 2 wins the game.
     * And put the winner's maze records into the completedMaze Array.
     */
    private void checkWinner(){
        if (tempMazes[0].calculateScore() >= tempMazes[1].calculateScore()){
            System.out.println(Messages.PLAYER1 + Messages.CHECK_WINNER);
            completeMazes(tempMazes[0]);
        }
        else{
            System.out.println(Messages.PLAYER2 + Messages.CHECK_WINNER);
            completeMazes(tempMazes[1]);
        }
    }

    /**
     * In multiplayer mode, after finishing the player 1 maze, we should directly start the player 2 maze.
     * @param maze Need to set the player 1 maze to not ended, or it won't resume the game after the player 2 quit the game.
     *             After two players completed the games. Both mazes will set to end game.
     */
    private void multiPlayerGame(Maze maze){
        maze.setEndGame(false);
        tempMazes[0] = maze;
        gameRules();
        if(tempMazes[1].playGame(keyboard)){
            maze.setEndGame(true);
            checkWinner();
        }
    }

    /**
     * First, check the maze is not null or created mazes are not completed.
     * Then start to play the game.
     * If it is single player, once the game is ended, put the maze into the completedMaze array.
     * If it is multiplayer, start the player 2 game after the player 1 finished the game.
     * @param maze The created maze from the select maze.
     * @param args If the player quit the game and want to start a new maze, it should go back to the selectMaze method.
     * @param playerModeOption single-player or multiplayer
     * @return maze. Return the maze with the new records.
     */
    // option 2
    private Maze startGame(Maze maze, String[] args, String playerModeOption) {
        if (maze == null || maze.isEndGame()) {
            System.out.println(Messages.MAZE_NOT_CREATED);
        } else {
            if (maze.getPacman().getMoves() == 0) {
                gameRules();
                if(maze.playGame(keyboard)){
                    if (playerModeOption.equals(Constants.PLAYER_MODE1)){
                        completeMazes(maze);
                    }
                    else{
                        multiPlayerGame(maze);
                    }
                }
            }else {
                System.out.print("Previous game hasn't ended yet. Do you want to discard previous game?\n" +
                        "Press N to go back to main menu to resume the game or else press any key to discard.\n" +
                        "> ");
                String discardMaze = keyboard.nextLine();
                if (!discardMaze.equalsIgnoreCase(Constants.NOT_DISCARD_MAZE)) {
                    maze = selectMaze(args, playerModeOption);
                    System.out.println(Messages.NEW_MAZE_CREATED);
                    gameRules();
                    if(maze.playGame(keyboard)){
                        if(playerModeOption.equals(Constants.PLAYER_MODE1)){
                            completeMazes(maze);
                        }
                        else{
                            multiPlayerGame(maze);
                        }

                    }
                }
            }
        }
        return maze;
    }

    /**
     * To check the maze is created first. And there is the maze is paused.
     * If there is not completed maze, resume the game.
     * @param maze The created maze from the select maze.
     * @param playerModeOption single-player or multiplayer
     * @return maze. Return the maze with the new records.
     */
    // option 3
    private Maze resumeGame(Maze maze, String playerModeOption){
        if (maze == null){
            System.out.println(Messages.MAZE_NOT_CREATED);
        }
        else if (maze.isEndGame()){
            System.out.println(Messages.NO_PAUSE_GAME);
        }
        else{
            System.out.println(Messages.RESTART_GAME);
            if (playerModeOption.equals(Constants.PLAYER_MODE1)){
                if (maze.playGame(keyboard)){
                    completeMazes(maze);
                }

            }
            else{
                if(tempMazes[1].isStartGame()){
                    if(tempMazes[1].playGame(keyboard)){
                        checkWinner();
                    }
                }
                else{
                    if (maze.playGame(keyboard)){
                        multiPlayerGame(maze);
                    }
                }
            }
        }
        return maze;
    }

    /**
     * View the scores in the completedMaze and print them out.
     */
    // option 4
    private void viewScore(){
        if (completedMazes.length == 0){
            System.out.println(Messages.NO_COMPLETED_GAME);
        }
        else{
            ScoreBoard scoreBoard = new ScoreBoard();
            scoreBoard.showScoreBoard(completedMazes);
        }
    }

}

