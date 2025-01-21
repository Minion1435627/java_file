/**
 * Author: Yang, Shu-Han
 * Student Id: 1435627
 * Email: shuhyang3@student.unimelb.edu.au
 */
package playlistEntity;

import enums.Category;
import exceptionFile.InvalidFormatException;
import exceptionFile.InvalidLineException;
import exceptionFile.MediaNotFoundException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import util.Constants;


/**
 * PlaylistPodcast inherits from Playlist and primarily handles operations specific to podcast media types.
 */
public class PlaylistPodcast extends Playlist{

    private Category category;
    private String seriesName;
    private int episodeNum;

    /**
     * Constructor of PlaylistPodcast with parameters.
     * Some attributes are inheritance from the Playlist, which is the parent class of the PlaylistPodcast.
     * @param name the name of the media
     * @param description the description of the media
     * @param artistName the artist name of the media
     * @param category the category of the podcast. This attribute only belongs to podcast media.
     * @param seriesName the series name of the podcast.
     * @param episodeNum the episode number of the podcast.
     * @param durationInMin the duration minutes of the media
     * @param captionFileName the caption file name of the media
     */
    public PlaylistPodcast(String name, String description, String artistName, Category category, String seriesName, int episodeNum, int durationInMin, String captionFileName) {
        super(name, description, artistName, durationInMin, captionFileName);
        this.category = category;
        this.seriesName = seriesName;
        this.episodeNum = episodeNum;
    }

    /**
     * Constructor of PlaylistPodcast without parameters.
     */
    public PlaylistPodcast() {
        super();
    }


    @Override
    public void handlePlaylist(PlaylistList playlistListItem,  String[] newPlayListLine) throws InvalidFormatException, MediaNotFoundException, InvalidLineException {

        if (newPlayListLine.length != Constants.LENGTH_OF_PODCAST) {
            throw new InvalidLineException("Podcast details incomplete. Skipping this line.");
        }
        try{
            int durationInMin = Integer.parseInt(newPlayListLine[6]);
            if (durationInMin<=0){
                throw new InvalidFormatException("Duration in mins not in correct format. Skipping this line.");
            }
        }
        catch (Exception ex){
            throw new InvalidFormatException("Duration in mins not in correct format. Skipping this line.");
        }
        try{
            int episodeNum = Integer.parseInt(newPlayListLine[5]);
            if (episodeNum<=0){
                throw new InvalidFormatException("Duration in mins not in correct format. Skipping this line.");
            }
        }
        catch (Exception ex){
            throw new InvalidFormatException("Episode not in correct format. Skipping this line.");
        }
        if (newPlayListLine[3].equalsIgnoreCase(Category.HEALTH.name())||newPlayListLine[3].equalsIgnoreCase(Category.EDUCATION.name())||newPlayListLine[3].equalsIgnoreCase(Category.TECHNOLOGY.name())){
            Category convertCategory = Category.valueOf(newPlayListLine[3].toUpperCase());
            String artistName = newPlayListLine[2];
            String newMultiArtistName = artistName(artistName);
            playlistListItem.getPlaylists().add(new PlaylistPodcast(newPlayListLine[0], newPlayListLine[1], newMultiArtistName, convertCategory, newPlayListLine[4], Integer.parseInt(newPlayListLine[5]), Integer.parseInt(newPlayListLine[6]), newPlayListLine[7]));
            captionFileHandle(newPlayListLine);
        }
        else {
            throw new InvalidFormatException("Incorrect Category for Podcast. Skipping this line.");
        }
    }

    @Override
    public void addPlaylist(PlaylistList playlistListItem, Scanner keyboard, String title, String description, int duringMin, String captionFileName){
        System.out.print("Add the category: ");
        String category = keyboard.nextLine();
        boolean multiHost = true;
        System.out.print("Enter the host Name or Q to stop entering the host name: ");
        String hostName = keyboard.nextLine();
        String newHostName = hostName;
        while (multiHost) {
            System.out.print("Enter the host Name or Q to stop entering the host name: ");
            hostName = keyboard.nextLine();
            if (hostName.equalsIgnoreCase(Constants.STOP_ADDING)) {
                multiHost = false;
            } else {
                newHostName = newHostName + "," + hostName;
            }
        }
        System.out.print("Enter the series Name: ");
        String seriesName = keyboard.nextLine();
        System.out.print("Enter the episode Number: ");
        int episodeNum = Integer.parseInt(keyboard.nextLine());
        Category convertCategory = Category.valueOf(category);
        playlistListItem.getPlaylists().add(new PlaylistPodcast(title, description, newHostName, convertCategory, seriesName, episodeNum, duringMin, captionFileName));

    }


    @Override
    public void viewContent(PlaylistList playlistListItem) {
        int i = 0;
        System.out.printf(Constants.PODCAST_PLAYLIST_HEADER, "Id", "Title", "Host Name(s)", "Description", "Category", "Series Name", "Episode#", "Duration In Mins");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Playlist playlistItem : playlistListItem.getPlaylists()) {
            PlaylistPodcast playListPodcast = (PlaylistPodcast) playlistItem;
            i++;
            System.out.printf(Constants.PODCAST_DATA_FORMATTER, i, playListPodcast.getName(), playListPodcast.getArtistName(), playListPodcast.getDescription(), playListPodcast.getCategory(), playListPodcast.getSeriesName(), playListPodcast.getEpisodeNum(), playListPodcast.getDurationInMin());

        }
    }

    @Override
    public void playContent(Playlist playlistItem) throws FileNotFoundException, MediaNotFoundException {
        System.out.println("Playing Podcast: " + playlistItem.getName() + " by " + playlistItem.getArtistName() + " for " + playlistItem.getDurationInMin() + " mins. " +
                "This podcast is about " + playlistItem.getDescription());
        Scanner scanner = new Scanner(new FileInputStream("data/mediatext/" + playlistItem.getCaptionFileName()));
        if (scanner.hasNextLine()) {
            System.out.println("Here are the contents of the podcast.");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        } else {
            throw new MediaNotFoundException("Cannot show captions for podcast. Media not found.");
        }
    }


    @Override
    public void exitMenu(PrintWriter newWriter, Playlist playlistItem) {
        PlaylistPodcast playListPodcast = (PlaylistPodcast)playlistItem;
        String returnArtistName = artistNameReturn(playListPodcast.getArtistName());
        newWriter.println(playListPodcast.getName()+","+playListPodcast.getDescription()+","+returnArtistName+","+playListPodcast.getCategory()+","+playListPodcast.getSeriesName()+","+playListPodcast.getEpisodeNum()+","+playListPodcast.getDurationInMin()+","+playListPodcast.getCaptionFileName());
    }

    /**
     * Get the value of category, which is the attribute in the PlaylistPodcast class.
     * @return category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Get the value of seriesName, which is the attribute in the PlaylistPodcast class.
     * @return seriesName
     */
    public String getSeriesName() {
        return seriesName;
    }

    /**
     * Get the value of episodeNum, which is the attribute in the PlaylistPodcast class.
     * @return episodeNum
     */
    public int getEpisodeNum() {
        return episodeNum;
    }
}
