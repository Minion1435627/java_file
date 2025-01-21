/**
 * Author: Yang, Shu-Han
 * Student Id: 1435627
 * Email: shuhyang3@student.unimelb.edu.au
 */
package interfaces;

import exceptionFile.MediaNotFoundException;
import playlistEntity.Playlist;

import java.io.FileNotFoundException;

public interface PlayContent {
    /**
     * Play the content of the Podcast media.
     * @param playlistItem the playlist item in the Playlist ArrayList
     * @throws FileNotFoundException Throw an exception if there is no media file
     * @throws MediaNotFoundException Throw an exception if there is no text in the media file
     */
    public void playContent(Playlist playlistItem) throws FileNotFoundException, MediaNotFoundException;
}
