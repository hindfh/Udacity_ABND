package com.example.hind.tourguide;

/**
 * Created by hind on 23/05/2018 AD.
 */

public class Place {

    private int placeName;

    private int placeDesc;

    private int imageResourceId = NO_IMAGE_PROVIDED;

    /** Constant value that represents no image was provided for this word */
    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Create a new Place object.
     *
     * @param pplaceDescription is the string resource ID for the place description
     * @param pplaceName        is the string resource Id for the name of the place
     */
    public Place(int pplaceName, int pplaceDescription) {
        placeName = pplaceName;
        placeDesc = pplaceDescription;
    }

    /**
     * Create a new Place object.
     *
     * @param pplaceDescription is the string resource ID for the place description
     * @param pplaceName        is the string resource Id for the name of the place
     * @param pimageResourseId is the drawable resource ID for the image associated with the place
     */
    public Place( int pplaceName, int pplaceDescription,int pimageResourseId) {
        placeName = pplaceName;
        placeDesc = pplaceDescription;
        imageResourceId = pimageResourseId;
    }

    public int getPlaceName(){return placeName;}

    public int getPlaceDesc(){return placeDesc;}

    public int getImageResourceId(){return imageResourceId;}

    public boolean hasImage(){return imageResourceId != NO_IMAGE_PROVIDED;}
}
