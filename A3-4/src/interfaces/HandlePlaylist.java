/**
 * Author: Yang, Shu-Han
 * Student Id: 1435627
 * Email: shuhyang3@student.unimelb.edu.au
 */
package interfaces;

import exceptionFile.InvalidFormatException;
import exceptionFile.InvalidLineException;
import exceptionFile.MediaNotFoundException;
import playlistEntity.PlaylistList;

public interface HandlePlaylist {

    /**
     * Handle the exception for media and add details to the playlist ArrayList.
     * @param playlistListItem the item from the PlaylistList ArrayList.
     * @param newPlayListLine the separate details of the array from the input files.
     * @throws InvalidFormatException Throw an exception if the media details are not in the correct format.
     * @throws MediaNotFoundException Throw an exception if the caption file for the media is not found.
     * The method for handling the caption file is in the parent class and will be inherited by this class.
     * @throws InvalidLineException Throw an exception if the song details are incomplete.
     */
    public void handlePlaylist(PlaylistList playlistListItem, String[] newPlayListLine) throws InvalidFormatException, MediaNotFoundException, InvalidLineException;
}
