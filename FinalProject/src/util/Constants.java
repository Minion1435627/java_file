/**
 * Author: Yang, Shu-Han
 * Student Id: 1435627
 * Email: shuhyang3@student.unimelb.edu.au
 */
package util;

import java.util.Scanner;

/**
 * Constants for program
 */
public class Constants {

    public static final Scanner keyboard = new Scanner(System.in);
    public static final String INVALID_INPUT = "Invalid Input";
    public static final String PLAYLIST_HEADER_FORMATTER = "|%2s|%10s|%30s|%n";
    public static final String PLAYLIST_FORMATTER = "|%2s|%10s|%30s|%n";
    public static final String SONG_PLAYLIST_HEADER = "|%2s|%30s|%30s|%30s|%10s|%16s|%n";
    public static final String SONG_PLAYLIST_DATA_FORMATTER = "|%2d|%30s|%30s|%30s|%10s|%16d|%n";
    public static final String PODCAST_PLAYLIST_HEADER = "|%2s|%30s|%30s|%30s|%15s|%20s|%8s|%16s|%n";
    public static final String PODCAST_DATA_FORMATTER = "|%2d|%30s|%30s|%30s|%15s|%20s|%8d|%16d|%n";
    public static final String SHORTCLIP_PLAYLIST_HEADER = "|%2s|%30s|%30s|%30s|%16s|%n";
    public static final String SHORTCLIP_DATA_FORMATTER = "|%2d|%30s|%30s|%30s|%16d|%n";
   
   /* add more constants here */
    public static final String CREATE_PLAYLIST = "1";
    public static final String VIEW_ALL_PLAYLIST = "2";
    public static final String VIEW_CONTENTS_OF_PLAYLIST = "3";
    public static final String REMOVING_PLAYLIST = "4";
    public static final String MODIFYING_PLAYLIST = "5";
    public static final String PLAY_CONTENT_OF_PLAYLIST= "6";
    public static final String EXIT = "7";

    public static final String VIEW_CONTENT = "1";
    public static final String ADD_NEW_MEDIA = "2";
    public static final String REMOVE_MEDIA = "3";
    public static final String EXIT_TO_MENU = "4";
    public static final int LENGTH_OF_PODCAST = 8;
    public static final int LENGTH_OF_SONG = 6;
    public static final int LENGTH_OF_SHORTCLIP = 5;

    public static final String STOP_ADDING = "q";
    public static final String CONTINUE_ADDING = "a";
    public static final int PLAYLIST_FULL_SIZE = 5;







}

