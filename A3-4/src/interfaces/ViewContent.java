/**
 * Author: Yang, Shu-Han
 * Student Id: 1435627
 * Email: shuhyang3@student.unimelb.edu.au
 */
package interfaces;
import playlistEntity.PlaylistList;

public interface ViewContent {

    /**
     * View the content of the media and print the header of the media.
     * @param playlistListItem the item from the PlaylistList ArrayList.
     */
    public void viewContent(PlaylistList playlistListItem);
}
