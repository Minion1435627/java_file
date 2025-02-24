/**
 * Author: Yang, Shu-Han
 * Student Id: 1435627
 * Email: shuhyang3@student.unimelb.edu.au
 */
package exceptionFile;

/**
 * The exception for the media loses part of details
 */
public class InvalidLineException extends Exception{

    /**
     * Constructor of InvalidLineException
     * @param message error message
     */
    public InvalidLineException(String message){
        super(message);

    }

}

