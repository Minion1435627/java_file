/**
 * Author: Yang, Shu-Han
 * Student Id: 1435627
 * Email: shuhyang3@student.unimelb.edu.au
 */
package interfaces;

import playlistEntity.PlaylistList;

import java.util.Scanner;

public interface AddPlaylistSpecific {

    /**
     * Read the specific new details of media and add the all details of media to the playlist ArrayList.
     * @param playlistListItem playlistListItem from the PlaylistList ArrayList.
     * @param keyboard the scanner for the command line input
     * @param title the name of media
     * @param description the description of the media
     * @param duringMin the duration in minutes of the media
     * @param captionFileName the caption file name of the media
     * The above details of the podcast have been read in the MusifyApp class
     */
    public  void addPlaylist(PlaylistList playlistListItem, Scanner keyboard, String title, String description, int duringMin, String captionFileName) ;
}
