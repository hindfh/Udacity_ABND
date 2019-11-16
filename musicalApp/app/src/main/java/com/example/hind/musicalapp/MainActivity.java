package com.example.hind.musicalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create an ArrayList of songs objects
        final ArrayList<song> songs = new ArrayList<song>();
        songs.add(new song("Ramadan", "2017", R.drawable.images1));
        songs.add(new song("Number One for Me", "Forgive Me", R.drawable.numberone));
        songs.add(new song("Mawlaya", "Forgive Me", R.drawable.mawlaya));
        songs.add(new song("Medina", "One", R.drawable.medina));

        // Create an {@link AndroidFlavorAdapter}, whose data source is a list of
        // {@link AndroidFlavor}s. The adapter knows how to create list item views for each item
        // in the list.
         songAdapter songAdap = new songAdapter(this, songs);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.listview_song);

        //create onItemClickListener so that when user click on individual item it will create intent move to NowPlayingActivity
        //also put extra info like song name in the intent
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, nowPlaying.class);
                intent.putExtra("song", songs.get(i).getSongName());
                startActivity(intent);
            }
        });

        // Make the {@link ListView} use the {@link SongAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Song} in the list.
        listView.setAdapter(songAdap);
    }

}