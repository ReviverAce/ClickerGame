package com.example.clickergame;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class SecondActivity extends AppCompatActivity {

    private TextView scoreTextView;
    private TextView finalScoreTextView;
    private Button upgradesButton;
    private ImageButton cookieButton;
    private ImageView ninjaImageView;
    private int cost1;
    private int cost2;
    private int cost3;
    private int cost4;
    private int cost5;
    private int cost6;
    private int score;
    //upgrades
    private int tapValue;
    private int finalScore;
    private int cookieSize;
    private double upgradeRatio;
    private int miliSeconds;
    private boolean isThreadStarted;
    private boolean onlyOnce;
    private boolean visibleNinja;

    //time
    private long startTime;
    private long endTime;
    //disable button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        upgradesButton = findViewById(R.id.upgradesButton);
        cookieButton = findViewById(R.id.cookieButton);
        scoreTextView = findViewById(R.id.scoreTextView);
        finalScoreTextView = findViewById(R.id.finalScoreTextView);
        ninjaImageView = findViewById(R.id.ninjaImageView);

        cost1=200;
        cost2=100;
        cost3=50;
        cost4=50;
        cost5=500;
        cost6=300;

        //upgrades
        tapValue=1;
        finalScore=1000;
        cookieSize=300;
        upgradeRatio=1;
        miliSeconds=-1;
        isThreadStarted=false;
        onlyOnce=false;
        visibleNinja=true;

        //time
        startTime=System.currentTimeMillis();

        //animate ninja
        Display display= getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        long width=size.x;
        int val=350;
        ObjectAnimator animationRight1 = ObjectAnimator.ofFloat(ninjaImageView,"x",width/2-val,width-val);
        ObjectAnimator animationLeft1 = ObjectAnimator.ofFloat(ninjaImageView,"x",width-val,width/2-val);
        ObjectAnimator animationLeft2 = ObjectAnimator.ofFloat(ninjaImageView,"x",width/2-val,0-val);
        ObjectAnimator animationRight2 = ObjectAnimator.ofFloat(ninjaImageView,"x",0-val,width/2-val);

        animationLeft1.setDuration(2000);
        animationRight1.setDuration(2000);
        animationLeft2.setDuration(2000);
        animationRight2.setDuration(2000);

        AnimatorSet bouncer = new AnimatorSet();
        bouncer.play(animationLeft1).after(animationRight1);
        bouncer.play(animationLeft2).after(animationLeft1);
        bouncer.play(animationRight2).after(animationLeft2);

        bouncer.start();

        bouncer.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animation.start();
            }
        });

        //buttons
        cookieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(score>=finalScore){
                    endTime=System.currentTimeMillis();
                    long time=endTime-startTime;

                    Intent intent = new Intent(v.getContext(),FinishActivity.class);
                    intent.putExtra("time",time);
                    finish();
                    startActivity(intent);
                }else{
                    score+=tapValue;
                    scoreTextView.setText(score+"");
                }
            }
        });

        upgradesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String score = scoreTextView.getText().toString();
                Intent intent = new Intent(v.getContext(), ThirdActivity.class);
                intent.putExtra("score", score);
                intent.putExtra("cost1",cost1);
                intent.putExtra("cost2",cost2);
                intent.putExtra("cost3",cost3);
                intent.putExtra("cost4",cost4);
                intent.putExtra("cost5",cost5);
                intent.putExtra("cost6",cost6);
                //upgrades
                intent.putExtra("tapValue",tapValue);
                intent.putExtra("finalScore",finalScore);
                intent.putExtra("cookieSize",cookieSize);
                intent.putExtra("upgradeRatio",upgradeRatio);
                intent.putExtra("miliSeconds",miliSeconds);
                intent.putExtra("thread",isThreadStarted);
                intent.putExtra("visibleNinja",visibleNinja);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1) {
            if (resultCode == RESULT_OK) {
                cost1 = data.getIntExtra("cost1",200);
                cost2 = data.getIntExtra("cost2",100);
                cost3 = data.getIntExtra("cost3",50);
                cost4 = data.getIntExtra("cost4",50);
                cost5 = data.getIntExtra("cost5",500);
                cost6 = data.getIntExtra("cost6",300);

                score=Integer.parseInt(data.getStringExtra("score"));
                scoreTextView.setText(data.getStringExtra("score"));

                //upgrades
                //1
                android.view.ViewGroup.LayoutParams params = cookieButton.getLayoutParams();
                cookieSize=data.getIntExtra("cookieSize",100);
                params.height=data.getIntExtra("cookieSize",100);
                params.width=data.getIntExtra("cookieSize",100);
                cookieButton.setLayoutParams(params);
                //2
                tapValue=data.getIntExtra("tapValue",1);
                //3
                miliSeconds=data.getIntExtra("miliSeconds",1000);
                isThreadStarted=data.getBooleanExtra("thread",false);
                if(isThreadStarted){
                    t.start();
                    isThreadStarted=false;
                }
                //4
                upgradeRatio=data.getDoubleExtra("upgradeRatio",1);
                //5
                visibleNinja=data.getBooleanExtra("visibleNinja",true);
                if(!visibleNinja){
                    ninjaImageView.setVisibility(View.GONE);
                }
                //6
                finalScore= data.getIntExtra("finalScore", 1000);
                finalScoreTextView.setText("Score over " + finalScore + " cookies to win!");




            }
        }
    }

    //thread
    Thread t = new Thread(){
        @Override
        public void run(){
            while(!isInterrupted()){
                try{
                    Thread.sleep(miliSeconds);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            score++;
                            scoreTextView.setText(score+"");
                        }
                    });
                }catch (InterruptedException ex){

                }
            }
        }
    };


}
