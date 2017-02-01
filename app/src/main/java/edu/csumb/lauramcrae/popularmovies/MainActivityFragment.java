package edu.csumb.lauramcrae.popularmovies;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivityFragment extends Fragment {

    private MyArrayAdapter arrayAdapter;

    //Instead of hardcoding all the popular movies, get them
    //From the api by using the user votes

    public MainActivityFragment(){

    }

    @Override
    protected View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false)
    }
}
