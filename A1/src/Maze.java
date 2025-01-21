/**
 * Author: Yang, Shu-Han
 * Student Id: 1435627
 * Email: shuhyang3@student.unimelb.edu.au
 */
public class Maze {

    /* maze details */
    private int mazeLength;
    private int mazeWidth;
    private int mazeType;
    private long seed;
    private boolean mazeCreate = false;
    private boolean gameContinue = false ;

    private final String BOUNDARY = "#";
    private final String WALL = "-";
    private final String ROAD = ".";
    private final String PACMAN = "P";
    private final String FOOD = "*";

    /*pacman position*/
    private int pacmanCol;
    private int pacmanRow;


    /*food position*/
    private int colPosFood;
    private int rowPosFood;


    /*score/game related attributes*/
    private int moveTime;
    private int hit;

    //write a constructor for Maze that invokes the generateFood method with appropriate params if 0 < mazeType < 4.
    // this generate position of the special food
    public Maze(){
    }
    public Maze(long seed, int mazeLength, int mazeWidth, int mazeType) {
        this.mazeLength = mazeLength;
        this.mazeWidth = mazeWidth;
        this.mazeType = mazeType;
        this.seed = seed;
        this.mazeCreate = true;
        pacmanCol = 1;
        pacmanRow = 1;
        moveTime = 0;
        hit = 0;
        generateFood(seed, mazeLength, mazeWidth, mazeType);
    }


    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }


    public boolean isMazeCreate() {
        return mazeCreate;
    }

    public boolean isGameContinue() {
        return gameContinue;
    }

    public void setGameContinue(boolean gameContinue) {
        this.gameContinue = gameContinue;
    }

    public int getMazeLength() {
        return mazeLength;
    }

    public int getMazeWidth() {
        return mazeWidth;
    }

    public int getColPosFood() {
        return colPosFood;
    }

    public int getRowPosFood() {
        return rowPosFood;
    }

    public int getMazeType() {
        return mazeType;
    }

    public int getPacmanCol() {
        return pacmanCol;
    }

    public int getPacmanRow() {
        return pacmanRow;
    }

    public void setPacman_col(int pacmanCol) {
        this.pacmanCol = pacmanCol;
    }

    public void setPacman_row(int pacmanRow) {
        this.pacmanRow = pacmanRow;
    }

    public int getMoveTime() {
        return moveTime;
    }

    public void setMoveTime(int moveTime) {
        this.moveTime = moveTime;
    }




    public void buildMaze(int mazeLength, int mazeWidth, int colPosFood, int rowPosFood, int mazeType){
        if (mazeType == 1){
            for (int row = 0; row < mazeWidth ; row++){
                for (int col=0; col<mazeLength ; col++){
                    if (col == getPacmanCol() && row == getPacmanRow()){
                        System.out.print(PACMAN);
                    }
                    else if (col == colPosFood && row == rowPosFood){
                        System.out.print(FOOD);
                    }
                    else if (col == 0 || col == mazeLength-1 || row == 0 || row == mazeWidth-1){
                        System.out.print(BOUNDARY);
                    }
                    else if (col>row) {
                        System.out.print(WALL);
                    }
                    else{
                        System.out.print(ROAD);
                    }
                }
                System.out.print("\n");
            }
        }
        else if (mazeType == 2){
            for (int row = 0; row < mazeWidth ; row++){
                for (int col=0; col<mazeLength ; col++){
                    if (col == getPacmanCol() && row == getPacmanRow()){
                        System.out.print(PACMAN);
                    }
                    else if (col == colPosFood && row == rowPosFood){
                        System.out.print(FOOD);
                    }
                    else if (col == 0 || col == mazeLength-1 || row == 0 || row == mazeWidth-1){
                        System.out.print(BOUNDARY);
                    }
                    else if (col<row) {
                        System.out.print(WALL);
                    }
                    else{
                        System.out.print(ROAD);
                    }
                }
                System.out.print("\n");
            }
        }
        else {
            for (int row = 0; row < mazeWidth ; row++){
                for (int col=0; col<mazeLength ; col++){
                    if (col == getPacmanCol() && row == getPacmanRow()){
                        System.out.print(PACMAN);
                    }
                    else if (col == colPosFood && row == rowPosFood){
                        System.out.print(FOOD);
                    }
                    else if (col == 0 || col == mazeLength-1 || row == 0 || row == mazeWidth-1){
                        System.out.print(BOUNDARY);
                    }
                    else if (row % 2 != 1 && col != 1 && col != mazeLength -2) {
                        System.out.print(WALL);
                    }
                    else{
                        System.out.print(ROAD);
                    }
                }
                System.out.print("\n");
            }
        }
    }

    private Boolean hitMaze(int mazeLength, int mazeWidth, int mazeType) {
        if (mazeType == 1) {
            if (getPacmanCol() == 0 || getPacmanCol() == mazeLength - 1 || getPacmanRow() == 0 || getPacmanRow() == mazeWidth - 1) {
                setHit(getHit()+1);
                System.out.println("You have hit the boundary.");
                return true;
            }else if (getPacmanCol() > getPacmanRow()) {
                setHit(getHit()+1);
                System.out.println("You have hit a wall.");
                return true;
            }
            else{
                setMoveTime(getMoveTime() + 1);
                return false;
            }
        }else if (mazeType == 2) {
            if (getPacmanCol() == 0 || getPacmanCol() == mazeLength - 1 || getPacmanCol() == 0 || getPacmanRow() == mazeWidth - 1) {
                setHit(getHit()+1);
                System.out.println("You have hit the boundary.");
                return true;
            }else if (getPacmanCol() < getPacmanRow()) {
                setHit(getHit()+1);
                System.out.println("You have hit a wall.");
                return true;
            }
            else{
                setMoveTime(getMoveTime() + 1);
                return false;
            }
        }
        else {
            if (getPacmanCol() == 0 || getPacmanCol() == mazeLength-1 || getPacmanRow() == 0 || getPacmanRow() == mazeWidth-1){
                setHit(getHit()+1);
                System.out.println("You have hit the boundary.");
                return true;
            }
            else if (getPacmanRow() % 2 != 1 && getPacmanCol() != 1 && getPacmanCol() != mazeLength -2) {
                setHit(getHit()+1);
                System.out.println("You have hit a wall.");
                return true;
            }
            else{
                setMoveTime(getMoveTime() + 1);
                return false;
            }
        }


    }

    public void movement(String direction, int mazeLength, int mazeWidth, int mazeType){
        switch (direction.toUpperCase()){
            case ("D"):
                setPacman_col(getPacmanCol() + 1);
                if (hitMaze(mazeLength, mazeWidth, mazeType)){
                    setPacman_col(getPacmanCol() - 1);
                }
                break;
            case ("A"):
                setPacman_col(getPacmanCol() - 1);
                if (hitMaze(mazeLength, mazeWidth, mazeType)){
                    setPacman_col(getPacmanCol() + 1);
                }
                break;
            case ("W"):
                setPacman_row(getPacmanRow() - 1);
                if (hitMaze(mazeLength, mazeWidth, mazeType)){
                    setPacman_row(getPacmanRow() + 1);
                }
                break;
            case ("S"):
                setPacman_row(getPacmanRow() + 1);
                if (hitMaze(mazeLength, mazeWidth, mazeType)){
                    setPacman_row(getPacmanRow() - 1);
                }
                break;
            case ("Q"):
                System.out.println("Your game is paused and saved.");
                break;
            default:
                System.out.println("Invalid Input.");
        }
    }





    //DO NOT MODIFY THIS CODE
    private void generateFood(long seed, int mazeLength, int mazeWidth, int mazeType) {
        FoodGenerator generator = new FoodGenerator(seed);
        while(true) {
            int xFood = generator.generatePosition(1, mazeLength-2);
            int yFood = generator.generatePosition(2, mazeWidth-2);
            if ((mazeType == 1 && xFood <= yFood) || (mazeType ==2 && xFood >=yFood) || (mazeType ==3 && !(yFood%2 == 0 && xFood!= 1 && xFood!= mazeLength-2))){
                this.colPosFood = xFood;
                this.rowPosFood = yFood;
                break;
            }

        }
    }




}

