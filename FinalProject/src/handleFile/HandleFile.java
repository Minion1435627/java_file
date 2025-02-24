/**
 * Author: Yang, Shu-Han
 * Student Id: 1435627
 * Email: shuhyang3@student.unimelb.edu.au
 */
package handleFile;

import exceptionFile.InvalidFormatException;
import exceptionFile.InvalidLineException;
import exceptionFile.MediaNotFoundException;
import playlistEntity.*;
import util.Constants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import enums.Media;

/**
 * Handle the input file
 */
public class HandleFile {

    /**
     * Default constructor of HandleFile
     */
    public HandleFile() {
    }

    /**
     * Read the PlaylistList file into PlaylistList
     * @param args the playlist file from the command lines
     * @throws FileNotFoundException catch the exception for not found the file in the data folder
     * @throws IndexOutOfBoundsException catch the exception for missing the input file (args[1])
     */
    public  ArrayList<PlaylistList> putPlaylistList(String[] args) throws FileNotFoundException, IndexOutOfBoundsException{
        ArrayList<PlaylistList> playlistList = new ArrayList<>();
        Scanner scanner = new Scanner(new FileInputStream("data/"+ args[1]));
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] newLine = line.split(",");
            try{
                if(newLine[1].equalsIgnoreCase(Media.SONG.name()) || newLine[1].equalsIgnoreCase(Media.PODCAST.name())|| newLine[1].equalsIgnoreCase(Media.SHORTCLIP.name())){
                    ArrayList<Playlist> playlist = new ArrayList<>();
                    Media enumMedia = Media.valueOf(newLine[1].toUpperCase());
                    playlistList.add(new PlaylistList(newLine[0],enumMedia,newLine[2],playlist));
                }
                else{
                    throw new InvalidFormatException("Incorrect Media Type. Skipping this line.");
                }
            }
            catch (InvalidFormatException ex){
                System.out.println(ex.getMessage());
            }
            catch (IndexOutOfBoundsException ex){
                System.out.println("Invalid PlayList data. Skipping this line.");
            }
        }
        return playlistList;
    }

    /**
     * Read the playlist into the playlistList. There is an arrayList attribute called playlist in playlistList.
     */
    public void putPlaylist(ArrayList<PlaylistList> playlistList){
        for (PlaylistList playlistListItem: playlistList){
            try{
                Scanner playlistScanner = new Scanner(new FileInputStream("data/playlist/"+ playlistListItem.getFileName()));
                while (playlistScanner.hasNextLine()) {
                    try {
                        String line = playlistScanner.nextLine();
                        String[] newPlayListLine = line.split(",");
                        if (playlistListItem.getMediaType().equals(Media.SONG)) {
                            PlaylistSong song = new PlaylistSong();
                            song.handlePlaylist(playlistListItem, newPlayListLine);
                        }
                        else if (playlistListItem.getMediaType().equals(Media.PODCAST)) {
                            PlaylistPodcast podcast = new PlaylistPodcast();
                            podcast.handlePlaylist(playlistListItem, newPlayListLine);
                        }
                        else if (playlistListItem.getMediaType().equals(Media.SHORTCLIP)){
                            PlaylistShortClip shortClip = new PlaylistShortClip();
                            shortClip.handlePlaylist(playlistListItem, newPlayListLine);
                        }
                    } catch (InvalidFormatException | MediaNotFoundException | InvalidLineException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
            catch (FileNotFoundException ex){
                System.out.println("Invalid or missing file.");
            }

        }
    }
}