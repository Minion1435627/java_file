/**
 * Author: Yang, Shu-Han
 * Student Id: 1435627
 * Email: shuhyang3@student.unimelb.edu.au
 */
package exceptionFile;

/**
 * The exception for incorrect format
 */
public class InvalidFormatException extends Exception{

    /**
     * Constructor of InvalidFormatException
     * @param message error message
     */
    public InvalidFormatException(String message){
        super(message);
    }
}