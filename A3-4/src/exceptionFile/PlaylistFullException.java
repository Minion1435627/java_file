/**
 * Author: Yang, Shu-Han
 * Student Id: 1435627
 * Email: shuhyang3@student.unimelb.edu.au
 */
package exceptionFile;

/**
 * The exception for the playlist has more than five media.
 */
public class PlaylistFullException extends Exception{
    /**
     * Constructor of PlaylistFullException
     * @param message error message
     */
    public PlaylistFullException(String message){
        super(message);
    }
}
