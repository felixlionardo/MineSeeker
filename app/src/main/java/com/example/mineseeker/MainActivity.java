package com.example.mineseeker;
// APPLICATION SPLASH SCREEN + ANIMATION

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mineseeker.gameobjects.Options;
import com.tomer.fadingtextview.FadingTextView;


public class MainActivity extends AppCompatActivity {
    private Button testt;
    private FadingTextView fade;
    private ImageView image;
    long animationduration = 4000;
    private Options options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        options = Options.getInstance();
        image = (ImageView) findViewById(R.id.imageView);
        ObjectAnimator animatorx = ObjectAnimator.ofFloat(image,"x",560f);
        animatorx.setDuration(animationduration);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorx);
        animatorSet.start();

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.bitch_lasagna_background);
        mp.start();

        fade = findViewById(R.id.fading);
        testt = (Button) findViewById(R.id.test);
        testt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testGame();
            }
        });
        animationSkip();

    }

    public void animationSkip()
    {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent mainIntent = new Intent(MainActivity.this,activity_mainmenu.class);
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
        }, 6000);

    }

    public void testGame(){
        Intent i = new Intent(this, activity_mainmenu.class);
        startActivity(i);
    }
}