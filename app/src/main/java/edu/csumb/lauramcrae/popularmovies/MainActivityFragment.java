package edu.csumb.lauramcrae.popularmovies;

import android.content.Intent;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.util.Arrays;

public class MainActivityFragment extends Fragment {

    private MyArrayAdapter arrayAdapter;

    //Instead of hardcoding all the popular movies, get them
    //From the api by using the user votes

    MovieItem[] androidFlavors = {
            new MovieItem("Cupcake", 0),
            new MovieItem("Donut", 0),
            new MovieItem("Eclair", 0),
            new MovieItem("Froyo", 0),
            new MovieItem("GingerBread", 0),
            new MovieItem("Honeycomb", 0),
            new MovieItem("Ice Cream Sandwich", 0),
            new MovieItem("Jelly Bean", 0),
            new MovieItem("KitKat", 0),
            new MovieItem("Lollipop", 0)
    };

    public MainActivityFragment(){

    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        //This is where you get all the stuffs from the API and put it into the
        //arrayAdapter to populate the screen

        arrayAdapter = new MyArrayAdapter(getActivity(), Arrays.asList(androidFlavors));

        //Get a reference to the ListView, and attatch this adapter to it.
        GridView gridView = (GridView) rootView.findViewById(R.id.grid_view_main);
        gridView.setAdapter(arrayAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                MovieItem movieItem = arrayAdapter.getItem(position);
                Intent i = new Intent(getActivity(), DetailActivity.class).putExtra("MovieItem", movieItem.title);
                startActivity(i);
            }
        });

        return rootView;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        //This is where the code will go when you make a menu
        //Notified when an option in the menu was selected

        //inflater.inflate(R.menu.forecastfragment, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        //Handle action bar item clicks here. The action bar will
        //Automatically handle clicks on the home/up button, so long
        //as you specify a parent activity in AndroidManifest.xml
        int id = item.getItemId();
//        if(id == R.id.action_settings){
//            //Create intent to go to the settings page i guess
//        }
        return super.onOptionsItemSelected(item);
    }

    //TODO Complete this when the fragment is complete
    private void updateMovies(){



    }



    public void onStart(){

        super.onStart();
        updateMovies();

    }

    public class FetchMovies extends AsyncTask<String, Void, String[]> {

        //The name of the class, because if oyu rename the class, you
        //always want it to stay the same name everywhere
        private final String LOG_TAG = MainActivityFragment.class.getSimpleName();

        protected String[] doInBackground(String ... params){
//            if(params.length == 0){
//                return null;
//            }
//
//            //These two need to be declared outside the try/catch
//            //so that they can be closed in the finally block
//            HttpURLConnection urlConnection = null;
//            BufferedReader reader = null;
//
//            //Will contain the raw JSON response as a string
//            String movieJsonStr = null;
//
//            //API Usage to be under here
            return null;
        }

        @Override
        protected void onPostExecute(String[] result){

            if(result != null){
                arrayAdapter.clear();
                for(int i = 0; i < androidFlavors.length; i++){
                    arrayAdapter.add(androidFlavors[i]);
                }
            }

        }



    }


}

