package com.example.clickergame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

public class ThirdActivity extends AppCompatActivity {

    private Button upgrade1Button;
    private Button upgrade2Button;
    private Button upgrade3Button;
    private Button upgrade4Button;
    private Button upgrade5Button;
    private Button upgrade6Button;
    private Button backButton;
    private TextView cost1TextView;
    private TextView cost2TextView;
    private  TextView cost3TextView;
    private TextView cost4TextView;
    private TextView cost5TextView;
    private TextView cost6TextView;
    private TextView scoreTextView;
    private String score;
    //upgrades
    private int finalScore;
    private int tapValue;
    private int cookieSize;
    private int miliSeconds;
    private boolean isThreadStarted;
    private boolean visibleNinja;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);



        upgrade1Button = findViewById(R.id.upgrade1Button);
        upgrade2Button = findViewById(R.id.upgrade2Button);
        upgrade3Button = findViewById(R.id.upgrade3Button);
        upgrade4Button = findViewById(R.id.upgrade4Button);
        upgrade5Button = findViewById(R.id.upgrade5Button);
        upgrade6Button = findViewById(R.id.upgrade6Button);
        backButton = findViewById(R.id.backButton);
        cost1TextView = findViewById(R.id.cost1TextView);
        cost2TextView = findViewById(R.id.cost2TextView);
        cost3TextView = findViewById(R.id.cost3TextView);
        cost4TextView = findViewById(R.id.cost4TextView);
        cost5TextView = findViewById(R.id.cost5TextView);
        cost6TextView = findViewById(R.id.cost6TextView);
        scoreTextView = findViewById(R.id.scoreTextView);

        Intent intent = getIntent();
        score = intent.getStringExtra("score");
        scoreTextView.setText("Your score: "+score);
        cost1TextView.setText(intent.getIntExtra("cost1", 0)+"");
        cost2TextView.setText(intent.getIntExtra("cost2", 0)+"");
        cost3TextView.setText(intent.getIntExtra("cost3", 0)+"");
        cost4TextView.setText(intent.getIntExtra("cost4", 0)+"");
        cost5TextView.setText(intent.getIntExtra("cost5", 0)+"");
        cost6TextView.setText(intent.getIntExtra("cost6", 0)+"");
        //upgrades
        tapValue=intent.getIntExtra("tapValue",1);
        finalScore=intent.getIntExtra("finalScore",1000);
        cookieSize=intent.getIntExtra("cookieSize",100);
        miliSeconds=intent.getIntExtra("miliSeconds",1000);
        isThreadStarted=intent.getBooleanExtra("thread",false);
        visibleNinja=intent.getBooleanExtra("visibleNinja",true);

        upgrade1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scoreIsBiggerThan(cost1TextView)){
                    if(cost1TextView.getText().toString().equals("200")){
                        cost1TextView.setText("300");
                        setScore(200);
                        cookieSize=500;
                    }else if(cost1TextView.getText().toString().equals("300")){
                        cost1TextView.setText("-1");
                        setScore(300);
                        cookieSize=700;
                    }else{

                    }
                }
            }
        });

        upgrade2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scoreIsBiggerThan(cost2TextView)){
                    if(cost2TextView.getText().toString().equals("100")){
                        cost2TextView.setText("200");
                        setScore(100);
                        tapValue=2;
                    }else if(cost2TextView.getText().toString().equals("200")){
                        cost2TextView.setText("300");
                        setScore(200);
                        tapValue=4;
                    }else if(cost2TextView.getText().toString().equals("300")){
                        cost2TextView.setText("-1");
                        setScore(300);
                        tapValue=6;
                    }

                }

            }
        });

        upgrade3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scoreIsBiggerThan(cost3TextView)){
                    if(cost3TextView.getText().toString().equals("50")){
                        cost3TextView.setText("200");
                        setScore(50);
                        miliSeconds=1000;
                        if(!isThreadStarted) {
                            isThreadStarted = true;
                        }
                    }else if(cost3TextView.getText().toString().equals("200")){
                        cost3TextView.setText("400");
                        setScore(200);
                        miliSeconds=500;
                    }else if(cost3TextView.getText().toString().equals("400")){
                        cost3TextView.setText("-1");
                        setScore(400);
                        miliSeconds=250;
                    }

                }
            }
        });

        upgrade4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scoreIsBiggerThan(cost4TextView)){
                    if(cost4TextView.getText().toString().equals("50")){
                        cost4TextView.setText("150");
                        setScore(-50);
                    }else if(cost4TextView.getText().toString().equals("150")){
                        cost4TextView.setText("300");
                        setScore(-150);
                    }else if(cost4TextView.getText().toString().equals("300")){
                        cost4TextView.setText("-1");
                        setScore(-300);
                    }

                }
            }
        });

        upgrade5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scoreIsBiggerThan(cost5TextView)){
                    if(cost5TextView.getText().toString().equals("500")) {
                        setScore(500);
                        cost5TextView.setText("-1");
                        visibleNinja=false;
                    }
                }
            }
        });

        upgrade6Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scoreIsBiggerThan(cost6TextView)){
                    if(cost6TextView.getText().toString().equals("300")){
                        cost6TextView.setText("400");
                        setScore(300);
                        finalScore =850;
                    }else if(cost6TextView.getText().toString().equals("400")){
                        cost6TextView.setText("500");
                        setScore(400);
                        finalScore =700;
                    }else if(cost6TextView.getText().toString().equals("500")){
                        cost6TextView.setText("-1");
                        setScore(500);
                        finalScore =500;
                    }

                }
            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("score", score);
                resultIntent.putExtra("cost1", Integer.parseInt(cost1TextView.getText().toString()));
                resultIntent.putExtra("cost2", Integer.parseInt(cost2TextView.getText().toString()));
                resultIntent.putExtra("cost3", Integer.parseInt(cost3TextView.getText().toString()));
                resultIntent.putExtra("cost4", Integer.parseInt(cost4TextView.getText().toString()));
                resultIntent.putExtra("cost5", Integer.parseInt(cost5TextView.getText().toString()));
                resultIntent.putExtra("cost6", Integer.parseInt(cost6TextView.getText().toString()));
                //upgrades
                resultIntent.putExtra("tapValue",tapValue);
                resultIntent.putExtra("cookieSize",cookieSize);
                resultIntent.putExtra("finalScore",finalScore);
                resultIntent.putExtra("miliSeconds",miliSeconds);
                resultIntent.putExtra("thread",isThreadStarted);
                resultIntent.putExtra("visibleNinja",visibleNinja);

                setResult(RESULT_OK, resultIntent);
                finish();

            }
        });


    }

    public boolean scoreIsBiggerThan(TextView text){
        if(Integer.parseInt(score)>=Integer.parseInt(text.getText().toString())){
            return true;
        }else{
            return false;
        }
    }

    public void setScore(int value){
        score=String.valueOf(Integer.parseInt(score)-value);
        scoreTextView.setText("Your score: "+score);
    }
}
