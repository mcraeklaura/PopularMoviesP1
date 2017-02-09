package edu.csumb.lauramcrae.popularmovies;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {


    private TextView title;
    private ImageView poster;
    private TextView info;
    private TextView release_date;
    private TextView vote_avg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MovieItem movieItem = (getIntent().getParcelableExtra("myData"));
        setContentView(R.layout.activity_detail);

        TextView title = (TextView) findViewById(R.id.movie_title);
        title.setText(movieItem.getTitle());

        ImageView poster = (ImageView) findViewById(R.id.movie_poster);
        Picasso.with(getApplicationContext()).load(movieItem.getImagePoster().toString()).into(poster);

        TextView info = (TextView) findViewById(R.id.movie_info);
        info.setText(movieItem.getInfo());

        TextView release_date = (TextView) findViewById(R.id.movie_release_date);
        release_date.setText("Release Date: " + movieItem.getReleaseDate());

        TextView vote_avg = (TextView) findViewById(R.id.movie_vote_avg);
        vote_avg.setText("Vote Average: " + Double.toString(movieItem.getVoteAverage()));
    }
}
