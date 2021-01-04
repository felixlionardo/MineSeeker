package com.example.mineseeker;
// THE MAIN GAMEPLAY ACTIVITY OF THE APPLICATION

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.mineseeker.gameobjects.Options;

import java.util.Random;

public class PlayActivity extends AppCompatActivity {
    Options options = Options.getInstance();
    private final int numrow = options.getRow();
    private final int numcol = options.getColumn();
    private final int nummines = options.getNumberOfMines();
    Button buttons[][] = new Button[numrow][numcol];
    private int numberdim[][] = new int[numrow][numcol];
    private int numberdim2[][]= new int[numrow][numcol];
    Random number = new Random();
    private boolean nomorebomb[][] = new boolean[numrow][numcol];
    private int countbomb = 0;
    private int scan = 0;
    private int countmedia = 0;
    MediaPlayer mp;
    TextView text;
    TextView text2;
    TextView text3;
    TextView text4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        text = (TextView) findViewById(R.id.numofscan);
        text2 = (TextView) findViewById(R.id.numofbomb);
        text3 = (TextView) findViewById(R.id.totalbomb);
        text4 = (TextView) findViewById(R.id.minscan);
        if(options.getNumberOfScans() != 0)
            text4.setText(""+options.getNumberOfScans());
        text3.setText(""+nummines);
        populateButton();
    }

    private void populateBomb() {
        for(int z = 0;z<nummines;z++) {
            int num1 = number.nextInt(numrow);
            int num2 = number.nextInt(numcol);
            if (numberdim[num1][num2] == 1000)
                z--;
            numberdim[num1][num2] = 1000;
        }
    }

    private void populateButton() {
        TableLayout table = (TableLayout) findViewById(R.id.tablebutton);
        for(int row = 0;row<numrow;row++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            table.addView(tableRow);

            for(int col = 0;col<numcol;col++){
                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));
                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
        populateBomb();
        for(int i = 0;i<numrow;i++) {
            for(int j = 0;j<numcol;j++) {
                final int num1final = i;
                final int num2final = j;
                if(numberdim[i][j] == 1000) {
                    buttons[i][j].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            gridButton(num1final, num2final);
                        }
                    });
                }
                int count = 0;
                for(int k = 0;k<numcol;k++) {
                    if(numberdim[i][k] == 1000)
                        count++;
                }
                for(int l = 0;l<numrow;l++){
                    if(numberdim[l][j] == 1000)
                        count++;
                }
                final int finalcount = count;
                if(numberdim[i][j] < 1000){
                    buttons[i][j].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            gridButton2(num1final,num2final,finalcount);
                        }
                    });
                }
            }
        }
    }

    private void gridButton2(int final_row, int final_col, int count) {
        Button button = buttons[final_row][final_col];
        mp = MediaPlayer.create(this, R.raw.scan_sound);
        mp.start();
        button.setText("" +count);
        if(numberdim2[final_row][final_col] == 1000)
            scan--;
        numberdim2[final_row][final_col] = 1000;
        scan++;
        text.setText(""+scan);
    }

    private void gridButton(int final_row, int final_col) { //On-click Bomb
        lockButton();
        countbomb++;
        countmedia++;
        if(countmedia != nummines) {
            mp = MediaPlayer.create(this, R.raw.button_hit_sound);
            mp.start();
        }
        scan++;
        Button button = buttons[final_row][final_col];
        button.setBackgroundResource(R.drawable.bombicon);
        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bombicon);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resources = getResources();
        button.setBackground(new BitmapDrawable(resources, scaledBitmap));
        nomorebomb[final_row][final_col] = true;

        for(int i = 0;i<numrow;i++){
            for(int j = 0;j<numcol;j++) {
                int count1 = 0;
                final int num1final = i;
                final int num2final = j;
                for(int k = 0;k<numcol;k++) {
                    if(numberdim[i][k] == 1000) {
                        count1++;
                        if(nomorebomb[i][k] == true)
                            count1--;
                    }
                }
                for(int l = 0;l<numrow;l++){
                    if(numberdim[l][j] == 1000) {
                        count1++;
                        if(nomorebomb[l][j] == true)
                            count1--;
                    }
                }
                if(count1 < 0)
                    count1 = 0;
                final int finalcount = count1;
                if(numberdim[i][j] < 1000){
                    buttons[i][j].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            gridButton2(num1final,num2final,finalcount);

                        }
                    });
                }
                if(numberdim2[i][j] == 1000) {
                    Button buttonss = buttons[i][j];
                    buttonss.setText(""+count1);
                }
            }
        }
        if(numberdim[final_row][final_col] > 1000 || numberdim2[final_row][final_col] > 1000){
            scan--;
            countbomb--;
        }
        numberdim[final_row][final_col]++;
        numberdim2[final_row][final_col]++;
        text.setText(""+scan);
        text2.setText(""+countbomb);
        if(countbomb == nummines){
            mp = MediaPlayer.create(this,R.raw.play_win);
            mp.start();
            if(options.getNumberOfScans() == 0 || options.getNumberOfScans() > scan) {
                text4.setText(""+scan);
                options.setNumberOfScans(scan);
            }
            Congratulation();
        }
    }

    private void Congratulation() {
        FragmentManager manager = getSupportFragmentManager();
        Congrats dialog = new Congrats();
        dialog.show(manager ,"message dialog");
    }

    private void lockButton() {
        for(int row = 0;row<numrow;row++){
            for(int col = 0;col<numcol;col++){
                Button button = buttons[row][col];
                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);
                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }

    }
}