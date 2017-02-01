package edu.csumb.lauramcrae.popularmovies;

import android.media.Image;
import android.widget.ImageButton;

/**
 * Created by lauramcrae on 2/1/17.
 */

public class MovieItem {
    String title;
    int image; // drawable reference id

    public MovieItem(String vName, int image)
    {
        this.title = vName;
        this.image = image;
    }

}