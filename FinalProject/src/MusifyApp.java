/**
 * Author: Yang, Shu-Han
 * Student Id: 1435627
 * Email: shuhyang3@student.unimelb.edu.au
 */
import exceptionFile.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import handleFile.HandleFile;
import util.Constants;
import playlistEntity.*;
import enums.Media;


/**
 * The main class to run the program.
 */
public class MusifyApp{
    private ArrayList<PlaylistList> playlistList = new ArrayList<>();
    private String name = "Stranger";

    /**
     * Constructor for the MusifyApp
     */
    public MusifyApp(){

    }


    /**
     * Main method to run the program, which include handling the file, displaying the welcome messages, and running the main menu.
     * @param args the input from the command line
     */
    public static void main(String[] args)  {
        MusifyApp app = new MusifyApp();

        app.handleFiles(args);
        app.displayWelcomeMessage(args);
        app.runMainMenu(args);

    }

    /**
     * Put the PlaylistList file and Playlist file into the PlaylistList ArrayList
     * @param args the playlist file from the command lines
     */
    private void handleFiles(String[] args) {
        try {
            HandleFile handleFile = new HandleFile();
            playlistList = handleFile.putPlaylistList(args);
            handleFile.putPlaylist(playlistList);
        } catch (FileNotFoundException ex) {
            System.out.println("Invalid or missing file.");
        } catch (IndexOutOfBoundsException ex){
            // If there is no PlaylistList file, create a new file in the exit method
        }
    }

    /**
     * Main menu to run the MusifyApp. There are seven options to choose.
     * @param args use a parameter in exit method, to write the playlistList back to the command line input file
     */
    private void runMainMenu(String[] args) {
        boolean exitMusify = false;
        Scanner keyboard = Constants.keyboard;
        while (!exitMusify){
            try{
                printMainMenu();
                String option = keyboard.nextLine();
                switch (option) {
                    case Constants.CREATE_PLAYLIST -> createPlayList(playlistList, keyboard);
                    case Constants.VIEW_ALL_PLAYLIST -> viewPlaylistList(playlistList);
                    case Constants.VIEW_CONTENTS_OF_PLAYLIST -> {
                        emptyPlaylistList();
                        System.out.print("Enter Playlist Name: ");
                        String listName = keyboard.nextLine();
                        viewContentOfPlaylist(playlistList, listName);
                    }
                    case Constants.REMOVING_PLAYLIST -> {
                        emptyPlaylistList();
                        removingPlaylist(playlistList, keyboard);
                    }
                    case Constants.MODIFYING_PLAYLIST -> {
                        emptyPlaylistList();
                        modifyPlaylist(playlistList, keyboard);
                    }
                    case Constants.PLAY_CONTENT_OF_PLAYLIST -> {
                        emptyPlaylistList();
                        playMediaContent(playlistList, keyboard);
                    }
                    case Constants.EXIT -> {
                        exitRunMainMenu(playlistList, args);
                        exitMusify = true;
                    }
                    default -> System.out.println(Constants.INVALID_INPUT);
                }
            } catch (NoPlaylistFoundException ex){
                System.out.println(ex.getMessage());
            }
        }

    }

    /**
     * To check that it is not an empty playlistList in removing playlist, modifying playlist, and play content of playlist.
     * @throws NoPlaylistFoundException If it is an empty playlistList, catch the exception and print: no playlists found.
     */
    private void emptyPlaylistList() throws NoPlaylistFoundException{
        if (playlistList.size()==0){
            throw new NoPlaylistFoundException("No playlists found.");
        }
    }

    /**
     * To add the new created playlistList to the original playlistList.
     * In addition, optional to add the new playlist into the playlistList.
     * @param playlistList playlistList that has been created when we handle the file
     * @param keyboard scanner to scan the input command
     */
    private void createPlayList(ArrayList<PlaylistList> playlistList, Scanner keyboard){
        System.out.print("Enter Playlist Name: ");
        String playlistName = keyboard.nextLine();
        System.out.print("Enter Playlist Type: ");
        String playlistType = keyboard.nextLine().toUpperCase();
        Media enumPlaylistType = Media.valueOf(playlistType);
        System.out.print("Enter a filename to save the playlist: ");
        String playlistFileName = keyboard.nextLine();
        ArrayList<Playlist> playlist = new ArrayList<>();
        playlistList.add(new PlaylistList(playlistName,enumPlaylistType,playlistFileName,playlist));
        System.out.println("Add some "+playlistType+" to your Playlist.");
        boolean checkContinueAdd = true;
        while (checkContinueAdd) {
            System.out.print("Enter A to add a "+ playlistType+ " to the playlist or Q to quit adding: ");
            String continueCreate = keyboard.nextLine();
            if (continueCreate.equalsIgnoreCase(Constants.CONTINUE_ADDING)){
                for (PlaylistList playlistListItem : playlistList){
                    try {
                        if (playlistListItem.getPlaylists().size() == Constants.PLAYLIST_FULL_SIZE){
                            String newPlaylistName = changeToCapital(playlistName);
                            System.out.println(newPlaylistName);
                            throw new PlaylistFullException("Playlist "+ newPlaylistName +"is full. You cannot add new "+ enumPlaylistType.name().toLowerCase() +" to this playlist.");
                        }
                        if (playlistListItem.getPlayListName().equalsIgnoreCase(playlistName)){
                            addPlaylist(playlistListItem, keyboard);
                        }

                    } catch (PlaylistFullException ex){
                        System.out.println(ex.getMessage());
                    }
                }
            }
            else if (continueCreate.equalsIgnoreCase(Constants.STOP_ADDING)){
                checkContinueAdd = false;
            }
            else{
                System.out.println(Constants.INVALID_INPUT);
            }
        }
    }

    /**
     * To capitalize the fist character of each word for the playListName
     * @param playListName playListName from the command line input
     * @return newPlayListName the new playListName
     */
    private String changeToCapital(String playListName){
        String[] wordSplit=playListName.split(" ");
        String newPlayListName = "";
        for (String s : wordSplit) {
            newPlayListName = newPlayListName + s.substring(0, 1).toUpperCase().concat(s.substring(1)).concat(" ");
        }
        return newPlayListName;
    }

    /**
     * add the playlist detail into the playlistListItem
     * @param playlistListItem the single item in the playlistList
     * @param keyboard scanner to scan the input command
     */
    private void addPlaylist(PlaylistList playlistListItem, Scanner keyboard ){
        System.out.print("Enter the title: ");
        String title = keyboard.nextLine();
        System.out.print("Enter the description: ");
        String description = keyboard.nextLine();
        System.out.print("Enter duration in mins: ");
        int duringMin = Integer.parseInt(keyboard.nextLine());
        System.out.print("Enter the filename for the captions or lyrics: ");
        String captionFileName = keyboard.nextLine();
        try {
            Scanner playlistScanner = new Scanner(new FileInputStream("data/mediatext/"+ captionFileName));
        } catch (FileNotFoundException ex) {
            System.out.println("Invalid or missing caption file.");
        } finally{
            if (playlistListItem.getMediaType().equals(Media.PODCAST)) {
                PlaylistPodcast podcast = new PlaylistPodcast();
                podcast.addPlaylist(playlistListItem, keyboard, title, description, duringMin, captionFileName);

            } else if (playlistListItem.getMediaType().equals(Media.SONG)) {
                PlaylistSong song = new PlaylistSong();
                song.addPlaylist(playlistListItem, keyboard, title, description, duringMin, captionFileName);

            } else if (playlistListItem.getMediaType().equals(Media.SHORTCLIP)) {
                PlaylistShortClip shortClip = new PlaylistShortClip();
                shortClip.addPlaylist(playlistListItem, keyboard, title, description, duringMin, captionFileName);

            }
        }
    }

    /**
     * To view all PlaylistList
     * @param playlistList playlistList that has been created when we handle the file
     */
    private void viewPlaylistList(ArrayList<PlaylistList> playlistList){
        try {
            if (playlistList.size() == 0){
                throw new Exception("No playlists found.");
            }
            else{
                System.out.println("Here are your playlists-");
                System.out.printf(Constants.PLAYLIST_HEADER_FORMATTER, "#", "Type", "Playlist Name");
                System.out.println("----------------------------------------------");
                int id = 1;
                for (PlaylistList playlist: playlistList){
                    System.out.printf(Constants.PLAYLIST_FORMATTER, id, playlist.getMediaType(), playlist.getPlayListName());
                    id++;
                }
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    /**
     * To view the content in the single playlist.
     * Search the playlist, which is equal to the listName from the playlistListItem.
     * @param playlistList playlistList that has been created when we handle the file
     * @param listName the playlist name
     */
    private void viewContentOfPlaylist (ArrayList<PlaylistList> playlistList, String listName){
        int countNotMatchPlaylistListItem = 0;
        for (PlaylistList playlistListItem: playlistList){
            if (playlistListItem.getPlayListName().equalsIgnoreCase(listName)){
                if (!mediaEmpty(playlistListItem)){
                    switch (playlistListItem.getMediaType()) {
                        case PODCAST -> {
                            PlaylistPodcast podcast = new PlaylistPodcast();
                            podcast.viewContent(playlistListItem);
                        }
                        case SONG -> {
                            PlaylistSong song = new PlaylistSong();
                            song.viewContent(playlistListItem);
                        }
                        case SHORTCLIP -> {
                            PlaylistShortClip shortClip = new PlaylistShortClip();
                            shortClip.viewContent(playlistListItem);
                        }
                    }
                }
            }
            else{
                countNotMatchPlaylistListItem++;
                if (countNotMatchPlaylistListItem == playlistList.size()){
                    System.out.println("No such playlist found with name: "+listName);
                }
            }

        }


    }

    /**
     * This method is used in the viewContentOfPlaylist. To check there are contents in the playlist.
     * @param playlistListItem single item from playlistList
     * @return Return true means that there is not content in the playlist. Otherwise, continue print out the content.
     */
    private boolean mediaEmpty(PlaylistList playlistListItem){
        if (playlistListItem.getPlaylists().size() == 0) {
            System.out.println("No "+playlistListItem.getMediaType().name().toLowerCase()+" in the playlist to view.");
            return true;
        }
        else{
            return false;
        }

    }

    /**
     * To remove the playlistListItem, which is equal to the listName for the scanner.
     * @param playlistList playlistList that has been created when we handle the file
     * @param keyboard scanner for the input command
     */
    private void removingPlaylist(ArrayList<PlaylistList> playlistList, Scanner keyboard){
        System.out.print("Enter Playlist Name: ");
        String listName = keyboard.nextLine();
        ArrayList<PlaylistList> itemsToRemove = new ArrayList<>();
        int countNotMatchPlaylistListItem = 0;
        for (PlaylistList playlistListItem : playlistList) {
            if (playlistListItem.getPlayListName().equalsIgnoreCase(listName)) {
                itemsToRemove.add(playlistListItem);
                System.out.println("Playlist removed successfully.");
            }
            else{
                countNotMatchPlaylistListItem++;
                if (countNotMatchPlaylistListItem == playlistList.size()){
                    System.out.println("No such playlist found with name: "+listName);
                }
            }
        }
        playlistList.removeAll(itemsToRemove);

    }

    /**
     * To modify the playlistListItem, which has the playlist name equal to the listName from the input command.
     * The Modification includes view_content, add_new_media, and remove_media.
     * And finally, exit the submenu to the main menu.
     * @param playlistList playlistList that has been created when we handle the file
     * @param keyboard scanner for the input command
     */
    private void modifyPlaylist(ArrayList<PlaylistList> playlistList, Scanner keyboard){
        System.out.print("Enter Playlist Name: ");
        String listName = keyboard.nextLine();
        for (PlaylistList playlistListItem : playlistList) {
            if (playlistListItem.getPlayListName().equalsIgnoreCase(listName)) {
                boolean exitMusify = false;
                while (!exitMusify) {
                    System.out.println("Please select one of the options.\n" +
                            "1. View the playlist.\n" +
                            "2. Add a new "+playlistListItem.getMediaType().name().toLowerCase()+".\n" +
                            "3. Remove a "+playlistListItem.getMediaType().name().toLowerCase()+".\n" +
                            "4. Exit and go back to main menu.");
                    String modifyOption = keyboard.nextLine();
                    switch (modifyOption) {
                        case Constants.VIEW_CONTENT -> viewContentOfPlaylist(playlistList, listName);
                        case Constants.ADD_NEW_MEDIA -> {
                            try {
                                if (playlistListItem.getPlaylists().size() == Constants.PLAYLIST_FULL_SIZE) {
                                    String newPlaylistName = changeToCapital(listName);
                                    throw new PlaylistFullException("Playlist " + newPlaylistName + "is full. You cannot add new " + playlistListItem.getMediaType().name().toLowerCase() + " to this playlist.");
                                }
                                addPlaylist(playlistListItem, keyboard);
                            } catch (PlaylistFullException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                        case Constants.REMOVE_MEDIA -> {
                            try {
                                if (playlistListItem.getPlayListName().equalsIgnoreCase(listName)) {
                                    if (playlistListItem.getPlaylists().size() == 0) {
                                        System.out.println("You can not remove media from an empty list.");
                                    } else {
                                        System.out.print("Enter the " + playlistListItem.getMediaType().name().toLowerCase() + " to remove: ");
                                        String removeItem = keyboard.nextLine();
                                        ArrayList<Playlist> itemsToRemove = new ArrayList<>();
                                        int countNotMatchListItem = 0;
                                        for (Playlist playlist : playlistListItem.getPlaylists()) {
                                            if (playlist.getName().equalsIgnoreCase(removeItem)) {
                                                itemsToRemove.add(playlist);
                                                System.out.println("Media removed successfully.");
                                            } else {
                                                countNotMatchListItem++;
                                                if (countNotMatchListItem == playlistListItem.getPlaylists().size()) {
                                                    System.out.println("No such media found with title: " + removeItem);
                                                }
                                            }
                                        }
                                        playlistListItem.getPlaylists().removeAll(itemsToRemove);
                                    }

                                }
                            } catch (Exception ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                        case Constants.EXIT_TO_MENU -> exitMusify = true;
                        default -> System.out.println(Constants.INVALID_INPUT);

                    }

                }
            }



        }


    }

    /**
     * To play the media content in the playlist in the PlaylistListItem, which has the playlist name equal to the listName from the input command.
     *@param playlistList playlistList that has been created when we handle the file
     * @param keyboard scanner for the input command
     */
    private void playMediaContent(ArrayList<PlaylistList> playlistList, Scanner keyboard){
        System.out.print("Enter Playlist Name: ");
        String listName = keyboard.nextLine();
        int countNotMatchPlaylistListItem = 0;
        for (PlaylistList playlistListItem : playlistList) {
            if (playlistListItem.getPlayListName().equalsIgnoreCase(listName)) {
                for (Playlist playlist : playlistListItem.getPlaylists()) {
                    System.out.println("-----------------------------------------------------------------------------------");
                    try {
                        if (playlistListItem.getMediaType().equals(Media.SONG)) {
                            PlaylistSong song = new PlaylistSong();
                            song.playContent(playlist);
                        } else if (playlistListItem.getMediaType().equals(Media.PODCAST)) {
                            PlaylistPodcast podcast = new PlaylistPodcast();
                            podcast.playContent(playlist);

                        } else if (playlistListItem.getMediaType().equals(Media.SHORTCLIP)) {
                            PlaylistShortClip shortClip = new PlaylistShortClip();
                            shortClip.playContent(playlist);
                        }
                        System.out.println("-----------------------------------------------------------------------------------");
                    } catch (FileNotFoundException ex) {
                        System.out.println("Cannot show captions for " + playlistListItem.getMediaType().name().toLowerCase() + ". Media not found.");
                        System.out.println("-----------------------------------------------------------------------------------");
                    } catch (MediaNotFoundException ex) {
                        System.out.println(ex.getMessage());
                        System.out.println("-----------------------------------------------------------------------------------");
                    }
                }
            }
            else{
                countNotMatchPlaylistListItem++;
                if (countNotMatchPlaylistListItem == playlistList.size()){
                    System.out.println("No such playlist found with name: "+listName);
                }
            }
        }

    }

    /**
     * To exit the main menu, and write the playlistList and playlist back to the file.
     * If there is no command line for the file name, create a new file called playlists.txt. And also put the playlistList into the file.
     * @param playlistList playlistList that has been created when we handle the file
     * @param args from the input command at the beginning of the program, to write the playlistList back to the file.
     */
    private void exitRunMainMenu(ArrayList<PlaylistList> playlistList, String[] args){
        if (playlistList.size()==0){
            System.out.println("Exiting Musify. Goodbye, " + name + ".");
        }
        else{
            try {
                String fileName;
                if (args.length!=2){
                    fileName = "playlists.txt";
                }
                else{
                    fileName = args[1];
                }
                PrintWriter writer = new PrintWriter(new FileOutputStream("data/"+fileName),true);
                for (PlaylistList playlistListItem: playlistList){
                    writer.println(playlistListItem.getPlayListName()+","+playlistListItem.getMediaType()+","+playlistListItem.getFileName());
                }
                writer.close();
                for (PlaylistList playlistListItem: playlistList){
                    PrintWriter newWriter = new PrintWriter(new FileOutputStream("data/playlist/"+playlistListItem.getFileName()),true);
                    for(Playlist playlist:playlistListItem.getPlaylists()){
                        if (playlistListItem.getMediaType().equals(Media.SONG)){
                            PlaylistSong song = new PlaylistSong();
                            song.exitMenu(newWriter, playlist);
                        }
                        else if(playlistListItem.getMediaType().equals(Media.PODCAST)){
                            PlaylistPodcast podcast = new PlaylistPodcast();
                            podcast.exitMenu(newWriter, playlist);

                        }
                        else if (playlistListItem.getMediaType().equals(Media.SHORTCLIP)){
                            PlaylistShortClip shortClip = new PlaylistShortClip();
                            shortClip.exitMenu(newWriter, playlist);
                        }

                    }
                    newWriter.close();
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Playlist data saved.");
            System.out.println("Exiting Musify. Goodbye, " + name + ".");
        }
    }

    /**
     *  Print the rules for the main menu.
     */
    private void printMainMenu() {
        System.out.println("Please select one of the options.");
        System.out.println("1. Create a new playlist.");
        System.out.println("2. View all playlist.");
        System.out.println("3. View contents of a playlist.");
        System.out.println("4. Remove a playlist.");
        System.out.println("5. Modify a playlist.");
        System.out.println("6. Play contents of a playlist.");
        System.out.println("7. Exit Musify.");
    }

    /**
     * Welcome message for the player.
     * @param args command line input.
     */
    private void displayWelcomeMessage(String[] args) {
        try{
            name = args[0];
            String fileName = args[1];
            System.out.println("Data loading complete.");
        }
        catch(IndexOutOfBoundsException ex){
            System.out.println("No Playlist data found to load.");
        }
        finally{
            System.out.print("Welcome " + name + ". Choose your music, podcasts or watch short clips.");
        }
    }

}
