package com.example.mineseeker;
//MAIN SCREEN OF APPLICATION
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mineseeker.gameobjects.Options;

public class activity_mainmenu extends AppCompatActivity {
    private Button play;
    private Button option;
    private Button help;
    private Options options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        options = Options.getInstance();

        play = (Button) findViewById(R.id.playbtn);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame();
            }
        });
        option = (Button) findViewById(R.id.option);
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOption();
            }
        });
        help = (Button) findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHelp();
            }
        });
    }
    public void openHelp(){
        Intent i = new Intent(this, HelpActivity.class);
        startActivity(i);
    }
    public void playGame(){
        Intent i = new Intent(this, PlayActivity.class);
        startActivity(i);
    }
    public void openOption(){
        Intent i = new Intent(this, OptionsActivity.class);
        startActivity(i);
    }
}