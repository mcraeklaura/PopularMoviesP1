package edu.csumb.lauramcrae.popularmovies;

import android.content.Intent;
import android.graphics.Movie;
import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.net.MalformedURLException;
import java.net.URL;

import static android.R.attr.name;

/**
 * Created by lauramcrae on 2/1/17.
 */

public class MovieItem implements Parcelable{
    private String title;
    private URL image;
    private int ID;
    private URL imagePoster;
    private String info;
    private String releaseDate;
    private double voteAverage;

    public MovieItem(String vName, URL image, int ID, URL imagePoster, String info, String releaseDate, double voteAverage)
    {
        this.title = vName;
        this.image = image;
        this.ID = ID;
        this.imagePoster = imagePoster;
        this.info = info;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
    }
    public MovieItem(Parcel source) {
        try {
            title = source.readString();
            image = new URL(source.readString());
            ID = source.readInt();
            imagePoster = new URL(source.readString());
            info = source.readString();
            releaseDate = source.readString();
            voteAverage = source.readDouble();
        } catch(MalformedURLException e){
            Log.e("MovieItem: ", e.getMessage());
        }

    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(image.toString());
        dest.writeInt(ID);
        dest.writeString(imagePoster.toString());
        dest.writeString(info);
        dest.writeString(releaseDate);
        dest.writeDouble(voteAverage);
    }

    public String getTitle(){
        return this.title;
    }

    public URL getImage(){
        return this.image;
    }

    public int getID(){
        return this.ID;
    }

    public URL getImagePoster() { return this.imagePoster; }

    public String getInfo() { return this.info; }

    public String getReleaseDate() { return this.releaseDate; }

    public double getVoteAverage() { return this.voteAverage; }

    public static final Parcelable.Creator<MovieItem> CREATOR = new Creator<MovieItem>() {
            @Override
            public MovieItem[] newArray(int size) {
                return new MovieItem[size];
            }

            @Override
            public MovieItem createFromParcel(Parcel source) {
                return new MovieItem(source);
            }
    };




        }


