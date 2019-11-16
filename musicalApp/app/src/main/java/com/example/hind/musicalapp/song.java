package com.example.hind.musicalapp;

/**
 * Created by hind on 21/05/2018 AD.
 */

public class song {
    // Name of the song
    private String songName;

    // song album
    private String songAlbum;

    // Drawable resource ID
    private int imageResourceId;

    /*
    * Create a new AndroidFlavor object.
    *
    * @param sName is the name of song
    * @param sAlbum is the name of song album
    * @param image is drawable reference ID that corresponds to the song
    * */
    public song(String sName, String sAlbum, int simageResourceId)
    {
        songName = sName;
        songAlbum = sAlbum;
        imageResourceId = simageResourceId;
    }

    /**
     * Get the song name
     */
    public String getSongName() {
        return songName;
    }

    /**
     * Get the song album
     */
    public String getSongAlbum() {
        return songAlbum;
    }

    /**
     * Get the image resource ID
     */
    public int getImageResourceId() {return imageResourceId;
    }

}
