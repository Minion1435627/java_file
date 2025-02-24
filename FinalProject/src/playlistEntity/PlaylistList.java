/**
 * Author: Yang, Shu-Han
 * Student Id: 1435627
 * Email: shuhyang3@student.unimelb.edu.au
 */
package playlistEntity;

import java.util.ArrayList;
import enums.Media;

/**
 * PlaylistList Class. Define four attributes, which will be used during adding the new PlaylistList items.
 */
public class PlaylistList{
    private final String playListName;
    private final Media mediaType;
    private final String fileName;
    private final ArrayList<Playlist> playlists;

    /**
     * Constructor of PlaylistList with parameters.
     * @param playListName the name of the playlist
     * @param mediaType the type of the playlist
     * @param fileName the file name of the playlist
     * @param playlists the playlist arrayList, which contain several media
     */
    public PlaylistList(String playListName, Media mediaType, String fileName, ArrayList<Playlist> playlists) {
        this.playListName = playListName;
        this.mediaType = mediaType;
        this.fileName = fileName;
        this.playlists = playlists;
    }

    /**
     * Get the value of playListName, which is the attribute in the PlaylistList class.
     * @return playListName
     */
    public String getPlayListName() {
        return playListName;
    }

    /**
     * Get the value of mediaType, which is the attribute in the PlaylistList class.
     * @return mediaType
     */
    public Media getMediaType() {
        return mediaType;
    }

    /**
     * Get the value of fileName, which is the attribute in the PlaylistList class.
     * @return fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Get the value of playlists, which is the attribute in the PlaylistList class.
     * @return playlists
     */
    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }
}
