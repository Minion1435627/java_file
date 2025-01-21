package Entities;

/**
 * The Monster class inherit from the Entity.
 */
public class Monster extends Entity{
    public enum Color {R, B, G, Y};

    /**
     * Constructor of Monster Entity
     */
    public Monster(Color color){
        super(color.name().charAt(0));
    }
}
