/**
 * Author: Yang, Shu-Han
 * Student Id: 1435627
 * Email: shuhyang3@student.unimelb.edu.au
 */
package playlistEntity;

import exceptionFile.InvalidFormatException;
import exceptionFile.InvalidLineException;
import exceptionFile.MediaNotFoundException;
import interfaces.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Playlist Class implements different interface, and be the parent class of another playlist type.
 */
public abstract class Playlist implements ViewContent, PlayContent, HandlePlaylist, AddPlaylistSpecific, ExitMenu {
     private String name;
     private String description;
     private String artistName;
     private String captionFileName;
     private int durationInMin;

    /**
     * Constructor of Playlist with parameters
     * @param name the name of the media
     * @param description the description of the media
     * @param artistName the artist name of the media
     * @param durationInMin the duration time of the media
     * @param captionFileName the caption file name of the media
     */
    public Playlist(String name, String description, String artistName, int durationInMin, String captionFileName) {
        this.name = name;
        this.description = description;
        this.artistName = artistName;
        this.durationInMin = durationInMin;
        this.captionFileName = captionFileName;
    }

    /**
     * Constructor without parameters
     */
    public Playlist(){}

    public abstract void handlePlaylist(PlaylistList playlistListItem, String[] newPlayListLine) throws InvalidFormatException, MediaNotFoundException, InvalidLineException;

    /**
     * To check the caption file exist
     * @param newPlayListLine the separate details of the array from the input files.
     * @throws MediaNotFoundException Throw an exception if the caption file for the media is not found.
     */
    public void captionFileHandle(String[] newPlayListLine) throws MediaNotFoundException{
        try {
            String fileName = newPlayListLine[newPlayListLine.length - 1] ;
            Scanner playlistScanner = new Scanner(new FileInputStream("data/mediatext/"+ fileName));
        } catch (FileNotFoundException ex){
            throw new MediaNotFoundException("Invalid or missing caption file.");
        }
    }

    /**
     * Convert the multiple artist names, which are separated by #, to a format separated by commas.
     * @param artistName the artist name from the input read file
     * @return newMultiArtistName
     */
    public String artistName(String artistName){
        String[] multiArtistName = artistName.split("#");
        String newMultiArtistName = "";
        if (multiArtistName.length == 1){
            newMultiArtistName = multiArtistName[0];
        }
        else{
            for (int i = multiArtistName.length-1; i > 0; i--){
                newMultiArtistName = newMultiArtistName + "," + multiArtistName[i];
            }
            newMultiArtistName = multiArtistName[0] + newMultiArtistName;
        }
        return newMultiArtistName;
    }
    public abstract void addPlaylist(PlaylistList playlistListItem, Scanner keyboard, String title, String description, int duringMin, String captionFileName) throws IllegalArgumentException;
    public abstract void viewContent(PlaylistList playlistListItem);
    public abstract void playContent(Playlist playlistItem) throws FileNotFoundException, MediaNotFoundException;
    public abstract void exitMenu(PrintWriter newWriter, Playlist playlistItem);

    /**
     * Convert the multiple artist names, which are separated by commas, back to a format separated by #.
     * This method will help write the correct format back to the file.
     * @param artistName the artist name from the input read file
     * @return newMultiArtistName
     */
    public String  artistNameReturn(String artistName){
        String[] multiArtistName = artistName.split(",");
        String newMultiArtistName = "";
        if (multiArtistName.length == 1){
            newMultiArtistName = multiArtistName[0];
        }
        else{
            for (int i = multiArtistName.length-1; i > 0; i--){
                newMultiArtistName = newMultiArtistName + "#" + multiArtistName[i];
            }
            newMultiArtistName = multiArtistName[0] + newMultiArtistName;
        }
        return newMultiArtistName;

    }

    /**
     * Get the value of name, which is the attribute in the Playlist class.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the value of description, which is the attribute in the Playlist class.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the value of artistName, which is the attribute in the Playlist class.
     * @return artistName
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * Get the value of captionFileName, which is the attribute in the Playlist class.
     * @return captionFileName
     */
    public String getCaptionFileName() {
        return captionFileName;
    }

    /**
     * Get the value of durationInMin, which is the attribute in the Playlist class.
     * @return durationInMin
     */
    public int getDurationInMin() {
        return durationInMin;
    }


}
