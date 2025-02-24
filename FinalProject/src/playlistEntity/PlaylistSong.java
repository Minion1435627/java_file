/**
 * Author: Yang, Shu-Han
 * Student Id: 1435627
 * Email: shuhyang3@student.unimelb.edu.au
 */
package playlistEntity;

import enums.Category;
import enums.Genre;
import exceptionFile.InvalidFormatException;
import exceptionFile.InvalidLineException;
import exceptionFile.MediaNotFoundException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import util.Constants;

/**
 * PlaylistSong inherits from Playlist and primarily handles operations specific to song media types.
 */
public class PlaylistSong extends Playlist{

    private Genre genre;

    /**
     * Constructor of PlaylistSong with parameters.
     * Some attributes are inheritance from the Playlist, which is the parent class of the PlaylistPodcast.
     * @param name the name of the media
     * @param description the description of the media
     * @param artistName the artist name of the media
     * @param genre the genre of the media
     * @param durationInMin the duration minutes of the media
     * @param captionFileName the caption file name of the media
     */
    public PlaylistSong(String name, String description, String artistName,Genre genre,int durationInMin, String captionFileName) {
        super(name, description, artistName, durationInMin, captionFileName);
        this.genre = genre;
    }

    /**
     * Constructor of PlaylistSong without parameters.
     */
    public PlaylistSong(){
        super();
    }

    @Override
    public void handlePlaylist(PlaylistList playlistListItem, String[] newPlayListLine) throws InvalidFormatException, MediaNotFoundException, InvalidLineException {
        if (newPlayListLine.length != Constants.LENGTH_OF_SONG) {
            throw new InvalidLineException("Song details incomplete. Skipping this line.");
        }
        try{
            Integer.parseInt(newPlayListLine[4]);
        }
        catch (Exception ex){
            throw new InvalidFormatException("Duration in mins not in correct format. Skipping this line.");
        }
        if (newPlayListLine[3].equalsIgnoreCase(Genre.POP.name())||newPlayListLine[3].equalsIgnoreCase(Genre.JAZZ.name())||newPlayListLine[3].equalsIgnoreCase(Genre.ROCK.name())){
            Genre convertGenre = Genre.valueOf(newPlayListLine[3].toUpperCase());
            String artistName = newPlayListLine[2];
            String newMultiArtistName = artistName(artistName);
            playlistListItem.getPlaylists().add(new PlaylistSong(newPlayListLine[0], newPlayListLine[1], newMultiArtistName, convertGenre, Integer.parseInt(newPlayListLine[4]), newPlayListLine[5]));
            captionFileHandle(newPlayListLine);
        }
        else {
            throw new InvalidFormatException("Incorrect Genre for Song. Skipping this line.");
        }

    }

    @Override
    public void addPlaylist(PlaylistList playlistListItem, Scanner keyboard, String title, String description, int duringMin, String captionFileName){
        System.out.print("Add the Genre: ");
        String genre = keyboard.nextLine();
        boolean multiHost = true;
        System.out.print("Enter the artist Name or Q to stop entering the artist name: ");
        String hostName = keyboard.nextLine();
        String newHostName = hostName;
        while (multiHost){
            System.out.print("Enter the artist Name or Q to stop entering the artist name: ");
            hostName = keyboard.nextLine();
            if (hostName.equalsIgnoreCase(Constants.STOP_ADDING)){
                multiHost = false;
            }
            else{
                newHostName = newHostName + "," + hostName;
            }
        }
        Genre convertGenre = Genre.valueOf(genre);
        playlistListItem.getPlaylists().add(new PlaylistSong(title, description, newHostName, convertGenre, duringMin, captionFileName));
    }

    @Override
    public void viewContent(PlaylistList playlistListItem){
        int i = 0;
        System.out.printf(Constants.SONG_PLAYLIST_HEADER, "Id", "Title", "Artist Name", "Description", "Genre", "Duration In Mins");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        for (Playlist playlist: playlistListItem.getPlaylists()){
            PlaylistSong playListSong = (PlaylistSong)playlist;
            i ++;
            System.out.printf(Constants.SONG_PLAYLIST_DATA_FORMATTER, i, playlist.getName(), playlist.getArtistName(), playlist.getDescription(), playListSong.getGenre().name().toUpperCase(), playlist.getDurationInMin());

        }
    }

    @Override
    public void playContent(Playlist playlistItem) throws FileNotFoundException,MediaNotFoundException {
        System.out.println("Playing Song: " + playlistItem.getName() + " by " + playlistItem.getArtistName() + " for " + playlistItem.getDurationInMin() + " mins.");
        Scanner scanner = new Scanner(new FileInputStream("data/mediatext/" + playlistItem.getCaptionFileName()));
        if (scanner.hasNextLine()) {
            System.out.println("Here are the lyrics to sing along.");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        } else {
            throw new MediaNotFoundException("Cannot show lyrics. Media not found.");
        }
    }


    @Override
    public void exitMenu(PrintWriter newWriter, Playlist playlistItem) {
        PlaylistSong playListSong = (PlaylistSong)playlistItem;
        String returnArtistName = artistNameReturn(playListSong.getArtistName());
        newWriter.println(playListSong.getName()+","+playListSong.getDescription()+","+returnArtistName+","+playListSong.getGenre()+","+playListSong.getDurationInMin()+","+playListSong.getCaptionFileName());
    }

    /**
     * Get the value of genre, which is the attribute in the PlaylistPodcast class.
     * @return genre
     */
    public Genre getGenre() {
        return genre;
    }
}
