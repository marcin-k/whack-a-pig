package com.mvk.pig_whack;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Gameplay0 extends BaseGameplay {

    long timeToPlay = 27;


    @BindView(R.id.g0_head_hole_1) ImageView headHole1;
    @BindView(R.id.g0_head_hole_2) ImageView headHole2;
    @BindView(R.id.g0_head_hole_3) ImageView headHole3;
    @BindView(R.id.g0_head_hole_4) ImageView headHole4;
    @BindView(R.id.g0_head_hole_5) ImageView headHole5;
    @BindView(R.id.g0_score_label) TextView scoreLabel;
    @BindView(R.id.g0_score) TextView score;
    @BindView(R.id.g0_counter) TextView counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay0);

        ButterKnife.bind(this);
        //Fixing up Fonts
        Typeface frijole = Typeface.createFromAsset(getAssets(), "fonts/Frijole-Regular.ttf");
        scoreLabel.setTypeface(frijole);
        score.setTypeface(frijole);
        counter.setTypeface(frijole);

        //--------------Runnable for time and head movements------------------
        timeHandler.postDelayed(timeRunnable, 0);
        pigsHandler.postDelayed(pigMoveRunnable, 0);
    }

    //On hit methods for each hole
    //--------Head - Hole 1---------------
    @OnClick(R.id.g0_head_hole_1)
    public void hitPig1(){
        headHole1.setImageResource(R.drawable.head_dizzy);
        //prevents from multiply points for same pig
        if(canScoreHole1){
            canScoreHole1=false;
            userScore++;
            score.setText(userScore+"");
        }
    }
    //--------Head - Hole 2---------------
    @OnClick(R.id.g0_head_hole_2)
    public void hitPig2(){
        headHole2.setImageResource(R.drawable.head_dizzy);
        //prevents from multiply points for same pig
        if(canScoreHole2){
            canScoreHole2=false;
            userScore++;
            score.setText(userScore+"");
        }
    }
    //--------Head - Hole 2---------------
    @OnClick(R.id.g0_head_hole_3)
    public void hitPig3(){
        headHole3.setImageResource(R.drawable.head_dizzy);
        //prevents from multiply points for same pig
        if(canScoreHole3){
            canScoreHole3=false;
            userScore++;
            score.setText(userScore+"");
        }
    }
    //--------Head - Hole 2---------------
    @OnClick(R.id.g0_head_hole_4)
    public void hitPig4(){
        headHole4.setImageResource(R.drawable.head_dizzy);
        //prevents from multiply points for same pig
        if(canScoreHole4){
            canScoreHole4=false;
            userScore++;
            score.setText(userScore+"");
        }
    }
    //--------Head - Hole 2---------------
    @OnClick(R.id.g0_head_hole_5)
    public void hitPig5(){
        headHole5.setImageResource(R.drawable.head_dizzy);
        //prevents from multiply points for same pig
        if(canScoreHole5){
            canScoreHole5=false;
            userScore++;
            score.setText(userScore+"");
        }
    }

    //TODO: fix that
    //on pause stop game go to welcome screen
    @Override
    public void onPause() {
        pigsHandler.removeCallbacks(pigMoveRunnable);
        timeHandler.removeCallbacks(timeRunnable);
//        Intent intent = new Intent(this, WelcomeScreen.class);
//        startActivity(intent);
        super.onPause();
    }

    //--------------------------------Runnable-------------------------------------

    //Time counter
    Handler timeHandler = new Handler();
    Runnable timeRunnable = new Runnable() {
        @Override
        public void run() {
            timeToPlay--;
            counter.setText(timeToPlay+"");
            //If there is less than 3 seconds display counter red
            if(timeToPlay<4){
                counter.setTextColor(Color.RED);
            }
            else {
                counter.setTextColor(Color.WHITE);
            }
            //Repeat itself only if time is grater than 0
            if(timeToPlay!=0){
                timeHandler.postDelayed(this, 1000);
            }
            if(timeToPlay==0){
                //TODO:try to add delay move to correct screen
                //wait 1.5 sec and move to next level
                
                moveToGameplay1();
            }
        }
    };

    //Runnable to repeat pigs popping up and down
    Handler pigsHandler = new Handler();
    Runnable pigMoveRunnable = new Runnable() {

        @Override
        public void run() {
            //random number between one and 5
            Random random = new Random();
            //int randomNumber = random.nextInt(max - min + 1) + min;
            int randomNumber = random.nextInt(6 - 1) + 1;

            switch (randomNumber){
                case 1:
                    moveImageViewUpAndDown(1,headHole1, R.drawable.head);
                    break;
                case 2:
                    moveImageViewUpAndDown(2,headHole2, R.drawable.head);
                    break;
                case 3:
                    moveImageViewUpAndDown(3,headHole3, R.drawable.head);
                    break;
                case 4:
                    moveImageViewUpAndDown(4,headHole4, R.drawable.head);
                    break;
                case 5:
                    moveImageViewUpAndDown(5,headHole5, R.drawable.head);
                    break;
                default:
                    break;
            }
            if(timeToPlay>0){
                pigsHandler.postDelayed(this, TIME_BETWEEN_POP_UPS);
            }
        }
    };

    private void moveToGameplay1(){
        Intent intent;
        //Load correct class based on the screen dpi
        if(getResources().getDisplayMetrics().densityDpi > 410){
            intent = new Intent(this, Gameplay1.class);
        }
        else{
            intent = new Intent(this, Gameplay1alt.class);
        }
        startActivity(intent);
    }

}
