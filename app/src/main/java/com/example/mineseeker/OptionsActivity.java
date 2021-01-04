package com.example.mineseeker;
// OPTIONS ACTIVITY OF APPLICATION

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.example.mineseeker.gameobjects.Options;


public class OptionsActivity extends AppCompatActivity {
    private Button btn;
    private Options options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        options = Options.getInstance();

        int savedMineValue = options.getNumberOfMines();
        int savedBoardSizeRow = options.getRow();
        int savedBoardSizeColumn = options.getColumn();
        Toast.makeText(this, "Saved Preferences: " + savedMineValue + " Bases, " + savedBoardSizeRow + "x" + savedBoardSizeColumn + " Board Size", Toast.LENGTH_SHORT)
                .show();

        createBoardSizeButtons();
        createNumMinesButtons();
        refreshScreen();

        btn = (Button) findViewById(R.id.options_back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
    }

    private void refreshScreen() {
        TextView boardSize = (TextView) findViewById(R.id.options_board_size);
        String boardSizeText = options.getRow() + "x" + options.getColumn();
        boardSize.setText("" + boardSizeText);
        TextView numMines = (TextView) findViewById(R.id.options_mines);
        String numMinesText = "" + options.getNumberOfMines();
        numMines.setText("" + numMinesText);

    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, OptionsActivity.class);
    }

    private void createBoardSizeButtons() {
        RadioGroup boardGroup = (RadioGroup) findViewById(R.id.radio_group_board_size);

        final String[] num_board_size = getResources().getStringArray(R.array.num_board_size);

        //Create the buttons:
        for (int i = 0; i < num_board_size.length; i++) {
            final String numBoardSize = num_board_size[i];

            RadioButton button = new RadioButton(this);
            button.setText(numBoardSize);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(OptionsActivity.this, numBoardSize + " Board Selected", Toast.LENGTH_SHORT)
                            .show();



                    saveNumBoardSize(numBoardSize);
                }
            });

            //Add to radio group
            boardGroup.addView(button);
            //Default radio button:
            if (numBoardSize == getBoardSize(this)) {
                button.setChecked(true);
            }
        }
    }

    private void saveNumBoardSize(String numBoardSize) {
        SharedPreferences preferences = this.getSharedPreferences("GamePrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Board Size:", numBoardSize);
        editor.apply();
        //Adjust Setting
        if(numBoardSize.equals("4x6")) {
            options.setRow(4);
            options.setColumn(6);
        }
        else if (numBoardSize.equals("5x10")) {
            options.setRow(5);
            options.setColumn(10);
        }
        else if (numBoardSize.equals("6x15")) {
            options.setRow(6);
            options.setColumn(15);
        }
    }

    static public String getBoardSize(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("GamePrefs", MODE_PRIVATE);
        String defaultBoard = context.getResources().getString(R.string.default_board_size);
        return preferences.getString("Board Size:", defaultBoard);

    }

    private void createNumMinesButtons() {
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group_num_mines);
        final String[] num_mines = getResources().getStringArray(R.array.num_mines);

        for (int i = 0; i < num_mines.length; i++) {
            final String numMine = num_mines[i];

            RadioButton button = new RadioButton(this);
            button.setText(numMine);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(OptionsActivity.this, numMine + " Bases Selected", Toast.LENGTH_SHORT)
                            .show();

                    saveNumMines(numMine);
                }
            });


            group.addView(button);

            //Default radio button:
            if (numMine == getNumMine(this)) {
                button.setChecked(true);
            }
        }
    }

    private void saveNumMines(String numMine) {
        SharedPreferences preferences = this.getSharedPreferences("GamePrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Number of Mines:", numMine);
        editor.apply();
        //Adjust Setting
        if (numMine.equals("6")) {
            options.setNumberOfMines(6);
        }
        else if (numMine.equals("10")) {
            options.setNumberOfMines(10);
        }
        else if (numMine.equals("15")) {
            options.setNumberOfMines(15);
        }
        else if (numMine.equals("20")) {
            options.setNumberOfMines(20);
        }
    }

    static public String getNumMine(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("GamePrefs", MODE_PRIVATE);

        String defaultMine = context.getResources().getString(R.string.default_num_mines);
        return preferences.getString("Number of Mines:", defaultMine);

    }

    public void goBack(){
        Intent i = new Intent(this, activity_mainmenu.class);
        startActivity(i);
    }

}