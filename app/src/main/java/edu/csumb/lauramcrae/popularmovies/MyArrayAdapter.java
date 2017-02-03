package edu.csumb.lauramcrae.popularmovies;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by lauramcrae on 2/1/17.
 */

public class MyArrayAdapter extends ArrayAdapter<MovieItem> {
    private static final String LOG_TAG = MyArrayAdapter.class.getSimpleName();

    //Inflate the layout file, and the List is the data we want to
    //populate the lists

    public MyArrayAdapter(Activity context, List<MovieItem> movieItems){
        //Initialize the ArrayAdapter's internal storage for the context and the list
        //second arg used when the ArrayAdapter is populating a single TextView.
        //Dont use the second argument
        super(context, 0, movieItems);
    }

    /*
    *  Provides a view for an AdapterView (ListView, GridView, etc.)
    *
    *  @param position      The AdapterView position that is requesting a view
    *  @param convertView   The recycled view to populate
    *  @param parent        The parent ViewGroup that is used for inflation.
    *  @return              The View for the position in the AdapterView
    */

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //Gets MovieItem object from the ArrayAdapter at the appropritate position
        MovieItem movieItem = getItem(position);

        //Adapters recycle views to AdapterViews.
        //If this is a new View object we're getting, then inflate the layout.
        //If not, this view already has the layout inflated from a previous call to getView,
        //and we modify the View widgets as usual
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_movie_item, parent, false);
        }

        ImageView iconView = (ImageView) convertView.findViewById(R.id.list_movie_item);
        iconView.setImageResource(movieItem.image);

        TextView titleNameView = (TextView) convertView.findViewById(R.id.list_movie_title);
        titleNameView.setText(movieItem.title);

        return convertView;
    }









































}
