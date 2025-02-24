/**
 * Author: Yang, Shu-Han
 * Student Id: 1435627
 * Email: shuhyang3@student.unimelb.edu.au
 */
package exceptionFile;

/**
 * The exception class for media, which doesn't have an invalid caption file, or it is empty in the caption file.
 */
public class MediaNotFoundException extends Exception{

    /**
     * Constructor of MediaNotFoundException
     * @param message error message
     */
    public MediaNotFoundException(String message){
        super(message);

    }
}
