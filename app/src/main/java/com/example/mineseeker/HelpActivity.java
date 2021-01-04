package com.example.mineseeker;
// HELP ACTIVITY OF APPLICATION
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        //Hyperlinks
        TextView text = (TextView) findViewById(R.id.textView10);
        text.setMovementMethod(LinkMovementMethod.getInstance());
        TextView text0 = (TextView) findViewById(R.id.textView18);
        text0.setMovementMethod(LinkMovementMethod.getInstance());
        TextView text1 = (TextView) findViewById(R.id.textView21);
        text1.setMovementMethod(LinkMovementMethod.getInstance());
        TextView text2 = (TextView) findViewById(R.id.textView22);
        text2.setMovementMethod(LinkMovementMethod.getInstance());
        TextView text3 = (TextView) findViewById(R.id.textView23);
        text3.setMovementMethod(LinkMovementMethod.getInstance());
        TextView text4 = (TextView) findViewById(R.id.textView24);
        text4.setMovementMethod(LinkMovementMethod.getInstance());
        TextView text5 = (TextView) findViewById(R.id.textView25);
        text5.setMovementMethod(LinkMovementMethod.getInstance());
        TextView text6 = (TextView) findViewById(R.id.textView26);
        text6.setMovementMethod(LinkMovementMethod.getInstance());
        TextView text7 = (TextView) findViewById(R.id.textView27);
        text7.setMovementMethod(LinkMovementMethod.getInstance());
        TextView text8 = (TextView) findViewById(R.id.textView28);
        text8.setMovementMethod(LinkMovementMethod.getInstance());
        TextView text9 = (TextView) findViewById(R.id.textView29);
        text9.setMovementMethod(LinkMovementMethod.getInstance());



        btn = (Button) findViewById(R.id.back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
    }
    public void goBack(){
        Intent i = new Intent(this, activity_mainmenu.class);
        startActivity(i);
    }
}