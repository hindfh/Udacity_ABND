package com.example.hind.musicalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hind on 21/05/2018 AD.
 */

public class nowPlaying extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_view);

        ImageView icon = findViewById(R.id.song_icon);
        TextView song = findViewById(R.id.song);
        TextView album = findViewById(R.id.album);

        //create intent for imageButton to move it from this activity(nowPlaying) to (MainActivity)
        ImageButton home = findViewById(R.id.back);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(nowPlaying.this, MainActivity.class);
                startActivity(homeIntent);
            }
        });

        //create Bundle to carry extra information sent from intent
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            //set views according to the song name sent from intent
            song.setText(bundle.getString("song"));

            if (song.getText().toString().equalsIgnoreCase("Ramadan")) {
                icon.setImageResource(R.drawable.images1);
                song.setText("Ramadan");
                album.setText("2017");
            } else if (song.getText().toString().equalsIgnoreCase("Number One for Me")) {
                icon.setImageResource(R.drawable.numberone);
                song.setText("Number One for Me");
                album.setText("Forgive Me");
            } else if (song.getText().toString().equalsIgnoreCase("Mawlaya")) {
                icon.setImageResource(R.drawable.mawlaya);
                song.setText("Mawlaya");
                album.setText("Forgive Me");
            } else {
                icon.setImageResource(R.drawable.medina);
                song.setText("Medina");
                album.setText("One");
            }

        }

    }
}
