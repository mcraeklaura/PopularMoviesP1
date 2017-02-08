package edu.csumb.lauramcrae.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Movie;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivityFragment extends Fragment {

    private String API_KEY = "";
    private MyArrayAdapter arrayAdapter;
    private Context context;

    //Instead of hardcoding all the popular movies, get them
    //From the api by using the user votes

    public MainActivityFragment(){

    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        context = getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        //This is where you get all the stuffs from the API and put it into the
        //arrayAdapter to populate the screen

        arrayAdapter = new MyArrayAdapter(
                getActivity(),
                new ArrayList<MovieItem>()
        );

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

        FetchMovieTask movieTask = new FetchMovieTask();
        movieTask.execute();

    }



    public void onStart(){

        super.onStart();
        updateMovies();
        System.err.println("Done with the updateMovies function");

    }

        public class FetchMovieTask extends AsyncTask<MovieItem, Void, MovieItem[]>{

            private final String LOG_TAG = MainActivityFragment.class.getSimpleName();

            protected MovieItem[] doInBackground(MovieItem ... params){
                if(params.length == 0){
                    System.err.println("Inside where the API is called");

                }

                //These two need to be declared outside the try/catch
                //so that they can be closed in the finally block
                HttpURLConnection urlConnection = null;
                BufferedReader reader = null;

                // Will contain the raw JSON response as a string
                String movieJsonStr = null;
                try{
                    String POP_MOVIE_URL = "https://api.themoviedb.org/3/movie/top_rated?api_key=" + API_KEY;
                    Uri builtUri = Uri.parse(POP_MOVIE_URL);
                    URL url = new URL(builtUri.toString());

                    Log.v(LOG_TAG, "Built URI " + builtUri.toString());

                    //Create the request to the Movie Db, and open the connection
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.connect();

                    // Read the input stream into a String
                    InputStream inputStream = urlConnection.getInputStream();
                    StringBuffer buffer = new StringBuffer();
                    if(inputStream == null){
                        //Nothing to do
                        return null;
                    }
                    reader = new BufferedReader(new InputStreamReader(inputStream));

                    String line;
                    while((line = reader.readLine()) != null){
                        //Since it's JSON, adding a new line isn't necessary (it won't affect parsing)
                        //But it does make debugging a lot easier if you print out the completed
                        //buffer for debugging
                        buffer.append(line + "\n");
                    }

                    if(buffer.length() == 0){
                        // Stream was empty. No point in parsing.
                        return null;
                    }
                    movieJsonStr = buffer.toString();

                } catch(IOException e){
                    Log.e("FetchMovieTask", "Error", e);
                    // If the code didn't successfully get the weather data, there
                    // is no point in attempting to parse it
                    return null;
                } finally {

                    if(urlConnection != null){
                        urlConnection.disconnect();
                    }
                    if(reader != null){
                        try{
                            reader.close();
                        } catch (final IOException e) {
                            Log.e(LOG_TAG, "Error closing stream", e);
                        }
                    }

                }

                try{
                    return getMovieDataFromJson(movieJsonStr);
                } catch (JSONException e) {
                    Log.e(LOG_TAG, e.getMessage(), e);
                }

                return null;
            }

            private MovieItem[] getMovieDataFromJson(String movieJsonStr) throws JSONException {

                final String MDB_RESULTS = "results";
                final String MDB_TITLE = "title";
                final String MDB_IMAGE_URL = "poster_path";
                final String MDB_ID = "id";

                JSONObject movieJson = new JSONObject(movieJsonStr);
                JSONArray movieArray = movieJson.getJSONArray(MDB_RESULTS);

                ArrayList<MovieItem> results = new ArrayList<>();
                System.out.println("Movie array length" + movieArray.length());
                for(int i = 0; i < movieArray.length(); i++){
                    try{
                        //Getting the title
                        JSONObject rec = movieArray.getJSONObject(i);
                        Log.e(LOG_TAG, rec.getString(MDB_TITLE) + " " + i);

                        //Getting the image from URL


                        MovieItem m = new MovieItem(
                                rec.getString(MDB_TITLE),
                                new URL("https://image.tmdb.org/t/p/w500/" + rec.getString(MDB_IMAGE_URL))
                        );
                        results.add(m);
                    } catch( MalformedURLException e) {
                        Log.e(LOG_TAG, e.getMessage() + " Movie: " + movieJson.getString(MDB_TITLE), e);
                        results.clear();
                        return null;
                    }
                }
                MovieItem[] f= new MovieItem[results.size()];
                for(int i = 0; i < results.size(); i++){
                    f[i] = results.get(i);
                }
                return f;

            }
            @Override
            protected void onPostExecute(MovieItem[] result){
                if(result != null) {
                    arrayAdapter.clear();
                    for(MovieItem movieStr : result){
                        arrayAdapter.add(movieStr);
                    }
                }

            }

        }



    }
