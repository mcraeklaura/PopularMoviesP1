package edu.csumb.lauramcrae.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

/*
* Usage Example: http://api.themoviedb.org/3/movie/popular?api_key=[YOUR_API_KEY]
*/

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainActivityFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate menu; adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //Action bar item clicks
        //Automatically handle clicks on the Home/up button, so long
        //as you specify a parent activity in AndroidManifest.xml
        int id = item.getItemId();

        if(id == R.id.action_settings){
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
