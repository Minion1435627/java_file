/**
 * Author: Yang, Shu-Han
 * Student Id: 1435627
 * Email: shuhyang3@student.unimelb.edu.au
 */
package exceptionFile;

/**
 * The exception for the playlistList size is zero.
 */
public class NoPlaylistFoundException extends Exception{

    /**
     * Constructor of NoPlaylistFoundException
     * @param message error message
     */
    public NoPlaylistFoundException(String message){
        super(message);

    }
}
