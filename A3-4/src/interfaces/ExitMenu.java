/**
 * Author: Yang, Shu-Han
 * Student Id: 1435627
 * Email: shuhyang3@student.unimelb.edu.au
 */
package interfaces;

import playlistEntity.Playlist;

import java.io.PrintWriter;

public interface ExitMenu {

    /**
     * Write the details of the medai back to file.
     * @param newWriter the writer to write the arrayList back to the file
     * @param playlistItem the playlist item in the Playlist ArrayList
     */
    public void exitMenu(PrintWriter newWriter, Playlist playlistItem);
}
