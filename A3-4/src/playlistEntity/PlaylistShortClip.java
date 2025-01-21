/**
 * Author: Yang, Shu-Han
 * Student Id: 1435627
 * Email: shuhyang3@student.unimelb.edu.au
 */
package playlistEntity;

import exceptionFile.InvalidFormatException;
import exceptionFile.InvalidLineException;
import exceptionFile.MediaNotFoundException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import util.Constants;

/**
 * PlaylistShortClip inherits from Playlist and primarily handles operations specific to ShorClip media types.
 */
public class PlaylistShortClip extends Playlist {

    /**
     * Constructor of PlaylistShortClip with parameters.
     * Some attributes are inheritance from the Playlist, which is the parent class of the PlaylistShortClip.
     * @param name the name of the media
     * @param description the description of the media
     * @param artistName the artist name of the media
     * @param durationInMin the duration minutes of the media
     * @param captionFileName the caption file name of the media
     */
    public PlaylistShortClip(String name, String description, String artistName, int durationInMin, String captionFileName) {
        super(name, description, artistName, durationInMin, captionFileName);
    }

    /**
     * Constructor of PlaylistSong without parameters.
     */
    public PlaylistShortClip(){
        super();
    }


    @Override
    public void handlePlaylist(PlaylistList playlistListItem, String[] newPlayListLine) throws InvalidFormatException, MediaNotFoundException, InvalidLineException {
        if (newPlayListLine.length != Constants.LENGTH_OF_SHORTCLIP){
            throw new InvalidLineException("ShortClip details incomplete. Skipping this line.");
        }
        try{
            Integer.parseInt(newPlayListLine[3]);
        }
        catch (Exception ex){
            throw new InvalidFormatException("Duration in mins not in correct format. Skipping this line.");
        }
        playlistListItem.getPlaylists().add(new PlaylistShortClip(newPlayListLine[0],newPlayListLine[1],newPlayListLine[2],Integer.parseInt(newPlayListLine[3]),newPlayListLine[4]));
        captionFileHandle(newPlayListLine);

    }

    @Override
    public void addPlaylist(PlaylistList playlistListItem, Scanner keyboard, String title, String description, int duringMin, String captionFileName){
        System.out.print("Enter the artist Name: ");
        String hostName = keyboard.nextLine();
        playlistListItem.getPlaylists().add(new PlaylistShortClip(title, description, hostName, duringMin, captionFileName));
    }

    @Override
    public void viewContent(PlaylistList playlistListItem){
        int i=0;
        System.out.printf(Constants.SHORTCLIP_PLAYLIST_HEADER, "Id", "Title", "Artist Name", "Description", "Duration In Mins");
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        for (Playlist playlist: playlistListItem.getPlaylists()){
            i ++;
            System.out.printf(Constants.SHORTCLIP_DATA_FORMATTER, i, playlist.getName(), playlist.getArtistName(), playlist.getDescription(),playlist.getDurationInMin());

        }
    }

    @Override
    public void playContent(Playlist playlistItem) throws FileNotFoundException, MediaNotFoundException {
        System.out.println("Playing short clip: " + playlistItem.getName() + " by " + playlistItem.getArtistName() + " for " + playlistItem.getDurationInMin() + " mins.");
        Scanner scanner = new Scanner(new FileInputStream("data/mediatext/" + playlistItem.getCaptionFileName()));
        if (scanner.hasNextLine()) {
            System.out.println("Here are the contents of the short clip.");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        } else {
            throw new MediaNotFoundException("Cannot show captions for short clip. Media not found.");
        }
    }


    @Override
    public void exitMenu(PrintWriter newWriter, Playlist playlistItem) {
        PlaylistShortClip playlistShortclip = (PlaylistShortClip)playlistItem;
        newWriter.println(playlistShortclip.getName()+","+playlistShortclip.getDescription()+","+playlistShortclip.getArtistName()+","+playlistShortclip.getDurationInMin()+","+playlistShortclip.getCaptionFileName());
    }
}

