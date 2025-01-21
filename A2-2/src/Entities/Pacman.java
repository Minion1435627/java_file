package Entities;

/**
 * The Pacman class inherit from the Entity.
 */
public class Pacman extends Entity{
    private int hits;
    private int moves;
    private int eats;
    private int monsterKillNo;
    private int superpower;
    public static final char PACMAN_SYMBOL = 'P';

    /**
     * Constructor of Pacman Entity
     */
    public Pacman(){
        super(PACMAN_SYMBOL);
        setCol(1);
        setRow(1);
        hits = 0;
        moves = 0;
        eats = 0;
        monsterKillNo = 0;
        superpower = 0;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public int getEats() {
        return eats;
    }

    public void setEats(int eats) {
        this.eats = eats;
    }

    public int getMonsterKillNo() {
        return monsterKillNo;
    }

    public void setMonsterKillNo(int monsterKillNo) {
        this.monsterKillNo = monsterKillNo;
    }

    public int getSuperpower() {
        return superpower;
    }

    public void setSuperpower(int superpower) {
        this.superpower = superpower;
    }

}
