package com.example.clickergame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class FinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        Button endButton = findViewById(R.id.endButton);
        TextView timeTextView = findViewById(R.id.timeTextView);

        Intent intent = getIntent();
        long time = intent.getLongExtra("time",0);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(time);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(time);

        timeTextView.setText(minutes+" minutes and "+seconds+" seconds");

        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
