import Constants.Constants;
import Entities.Pacman;
import Entities.Food;
import Entities.Monster;
import Entities.Entity;
import java.util.Scanner;
import Constants.Messages;

public class Maze {

    /* maze details */
    private int mazeType;
    private int mazeLength;
    private int mazeWidth;
    private char[][] maze;

    /* define other data variables here */
    private Pacman pacman;
    private Food[] foods;
    private Monster[] monsters;
    private String playerName;
    public static int gameNo;
    private int gameCurrentNO;
    private boolean endGame;
    private boolean startGame;
    private int numOfMonster;



    // Constructor for the Maze
    public Maze(int mazeType, int mazeLength, int mazeWidth, long seed) {
        // initialize the maze here
        //add the position of all the entities
        LocationGenerator generator = new LocationGenerator(seed);
        this.mazeType = mazeType;
        this.mazeLength = mazeLength;
        this.mazeWidth = mazeWidth;
        maze = new char[mazeWidth][mazeLength];
        pacman = new Pacman();
        generateMaze(mazeType);
        maze[pacman.getRow()][pacman.getCol()] = getPacman().getSymbol();
        setMonstersLocation(generator);
        numOfMonster = Constants.ENTITY_NUMBER;
        setFoodsLocation(generator);
        gameNo ++;
        setGameCurrentNO(gameNo);
        startGame = false;
        endGame = false;

    }

    /**
     * Generate the maze based on the player choice.
     * @param mazeType lower_triangle maze, upper_triangle maze or horizontal maze.
     */
    private void generateMaze(int mazeType){
        for(int i = 0; i < this.mazeWidth;i++){
            for(int j=0; j <this.mazeLength; j++){
                if(i == 0 || i== this.mazeWidth -1 || j ==0 || j == this.mazeLength -1){
                    maze[i][j] = Constants.BOUNDARY;
                }
                else if ((mazeType == Constants.LOWER_TRIANGLE_MAZE && j > i) || (mazeType ==Constants.UPPER_TRIANGLE_MAZE && j <i) || (mazeType ==Constants.HORIZONTAL_MAZE && (i%2 == 0 && j!= 1 && j!= this.mazeLength-2))){
                    maze[i][j] = Constants.WALL;
                }else{
                    maze[i][j] = Constants.ROAD;
                }
            }

        }
    }

    /**
     * Generate and set the position for the entity.
     * @param generator to create the position for the entity.
     * @param entity the entity that we created.
     */
    private void generatePosition(LocationGenerator generator ,Entity entity) {
        while(true) {
            int colPos = generator.generatePosition(1, this.mazeLength-2);
            int rowPos = generator.generatePosition(2, this.mazeWidth-2);
            if (this.maze[rowPos][colPos] == '.'){
                entity.setCol(colPos);
                entity.setRow(rowPos);
                break;
            }
        }
    }

    /**
     * Initialise the locations for four different monsters
     * @param generator the position for the entity
     */
    private void setMonstersLocation(LocationGenerator generator){
        monsters = new Monster[Constants.ENTITY_NUMBER];
        for (int i = 0; i < Monster.Color.values().length; i++){
            monsters[i] = new Monster(Monster.Color.values()[i]);
            generatePosition(generator, monsters[i]);
            maze[monsters[i].getRow()][monsters[i].getCol()] = monsters[i].getSymbol();
        }
    }

    /**
     * Initialise the locations for foods
     * @param generator the position for the entity
     */
    private void setFoodsLocation(LocationGenerator generator){
        foods = new Food[Constants.ENTITY_NUMBER];
        for (int i = 0; i < Constants.ENTITY_NUMBER; i++){
            foods[i] = new Food();
            generatePosition(generator, foods[i]);
            maze[foods[i].getRow()][foods[i].getCol()] = foods[i].getSymbol();
        }
    }

    /**
     * Display the maze.
     */
    private void displayMaze(){
        for (char[] row: maze){
            for (char item: row){
                System.out.print(item);
            }
            System.out.println();
        }
    }

    /**
     * The rules for Pacman movement.
     */
    private void movePacmanMenu(){
        System.out.println("Press W to move up.");
        System.out.println("Press A to move left.");
        System.out.println("Press S to move down.");
        System.out.println("Press D to move right.");
        System.out.println("Press Q to exit.");
        System.out.print("> ");
    }

    /**
     * The next position of the Pacman based on the input direction
     * @param direction up, down, left or right
     * @return nextPosition. If the Pacman does not hit anything, we know where the Pacman should move next.
     */
    private int[] pacmanMovement(String direction) {
        int[] nextPosition = null;
        switch (direction.toUpperCase()) {
            case Constants.MOVE_UP:
                nextPosition = new int[]{pacman.getRow() - 1, pacman.getCol()};
                break;
            case Constants.MOVE_DOWN:
                nextPosition = new int[]{pacman.getRow() + 1, pacman.getCol()};
                break;
            case Constants.MOVE_LEFT:
                nextPosition = new int[]{pacman.getRow(), pacman.getCol() - 1};
                break;
            case Constants.MOVE_RIGHT:
                nextPosition = new int[]{pacman.getRow(), pacman.getCol() + 1};
                break;
            default:
                System.out.println(Messages.INVALID_INPUT);
        }
        return nextPosition;
    }

    /**
     * To calculate the score based on the game rules.
     * @return the score for each game
     */
    public double calculateScore(){
        return  - Constants.HIT_MULTIPLIER * pacman.getHits()
                - Constants.MOVE_MULTIPLIER * pacman.getMoves()
                + Constants.FOOD_MULTIPLIER * pacman.getEats()
                + Constants.KILLED_MONSTER_MULTIPLIER * pacman.getMonsterKillNo()
                + (numOfMonster == 0? Constants.TOTAL_SCORE : 0);
    }

    /**
     * Start to play the game.
     * If the direction is valid, check what Pacman will move next.
     * If Pacman hits the wall or boundary, the hit of Pacman adds one and Pacman won't move.
     * If Pacman eats the food, the move and superpower of Pacman add one. And Pacman will move to the next position.
     * If Pacman meets the monster and the superpower of Pacman is greater than one, the Pacman kills the monster and moves
     * to the next position. The number of Monster minus one and move of adding one. Otherwise, the game ended.
     * @param keyboard The scanner for the player input, to know which direction should go next.
     * @return endGame Return the game is finished or not.
     */
    public boolean playGame(Scanner keyboard){
        String direction = "";
        System.out.println(playerName + " game begins.");
        setStartGame(true);
        while(!endGame){
            displayMaze();
            movePacmanMenu();
            direction = keyboard.nextLine();
            if (!direction.equalsIgnoreCase(Constants.MOVE_LEFT) && !direction.equalsIgnoreCase(Constants.MOVE_UP)
                    && !direction.equalsIgnoreCase(Constants.MOVE_DOWN) && !direction.equalsIgnoreCase(Constants.MOVE_RIGHT)
                    && !direction.equalsIgnoreCase(Constants.QUIT_MOVING)){
                System.out.println(Messages.INVALID_INPUT);
                continue;
            }
            if (direction.equalsIgnoreCase(Constants.QUIT_MOVING)){
                System.out.println(Messages.PAUSE_GAME);
                break;
            }
            else{
                int[] nextPos = pacmanMovement(direction);
                int[] currPacPos = {pacman.getRow(), pacman.getCol()};
                switch(maze[nextPos[0]][nextPos[1]]){
                    case Constants.WALL, Constants.BOUNDARY:
                        pacman.setHits(pacman.getHits()+1);
                        if (maze[nextPos[0]][nextPos[1]] == Constants.WALL){
                            System.out.println(Messages.HIT_WALL);
                        }else{
                            System.out.println(Messages.HIT_BOUNDARY);
                        }
                        break;
                    case Constants.ROAD:
                        maze[nextPos[0]][nextPos[1]] = pacman.getSymbol();
                        maze[currPacPos[0]][currPacPos[1]] = Constants.ROAD;
                        pacman.setRow(nextPos[0]);
                        pacman.setCol(nextPos[1]);
                        pacman.setMoves(pacman.getMoves() + 1);
                        break;
                    case Food.FOOD:
                        maze[nextPos[0]][nextPos[1]] = pacman.getSymbol();
                        maze[currPacPos[0]][currPacPos[1]] = Constants.ROAD;
                        pacman.setRow(nextPos[0]);
                        pacman.setCol(nextPos[1]);
                        pacman.setMoves(pacman.getMoves() + 1);
                        pacman.setEats(pacman.getEats() + 1);
                        pacman.setSuperpower(pacman.getSuperpower() + 1);
                        System.out.println(Messages.PACMAN_EAT);
                        break;
                    case 'R', 'G', 'Y', 'B':
                        if (pacman.getSuperpower() > 0){
                            numOfMonster = numOfMonster - 1;
                            maze[nextPos[0]][nextPos[1]] = pacman.getSymbol();
                            maze[currPacPos[0]][currPacPos[1]] = Constants.ROAD;
                            pacman.setRow(nextPos[0]);
                            pacman.setCol(nextPos[1]);
                            pacman.setSuperpower(pacman.getSuperpower() - 1);
                            pacman.setMoves(pacman.getMoves() + 1);
                            pacman.setMonsterKillNo(pacman.getMonsterKillNo() + 1);
                            System.out.println(Messages.KILLED_MONSTER);
                            if (numOfMonster == 0){
                                System.out.printf(Messages.END_GAME, calculateScore());
                                endGame = true;
                            }
                        }
                        else{
                            maze[currPacPos[0]][currPacPos[1]] = Constants.ROAD;
                            System.out.println(Messages.KILLED_PACMAN);
                            System.out.printf(Messages.END_GAME, calculateScore());
                            endGame = true;
                        }

                }
            }
        }
        return endGame;

    }


    public Pacman getPacman() {
        return pacman;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    public int getGameCurrentNO() {
        return gameCurrentNO;
    }

    public void setGameCurrentNO(int gameCurrentNO) {
        this.gameCurrentNO = gameCurrentNO;
    }

    public boolean isStartGame() {
        return startGame;
    }

    public void setStartGame(boolean startGame) {
        this.startGame = startGame;
    }
}

