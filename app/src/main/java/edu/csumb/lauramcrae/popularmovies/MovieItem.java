package edu.csumb.lauramcrae.popularmovies;

import android.media.Image;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.net.URL;

/**
 * Created by lauramcrae on 2/1/17.
 */

public class MovieItem {
    String title;
    URL image; // drawable reference id

    public MovieItem(String vName, URL image)
    {
        this.title = vName;
        this.image = image;
    }

}