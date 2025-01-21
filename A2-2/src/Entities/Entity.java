package Entities;

public class Entity {
    private char symbol;
    private int row;
    private int col;

    /**
     * Constructor of Entity
     */
    public Entity(char symbol){
        this.symbol = symbol;
        row = 0;
        col = 0;
    }

    public Entity(){

    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}

