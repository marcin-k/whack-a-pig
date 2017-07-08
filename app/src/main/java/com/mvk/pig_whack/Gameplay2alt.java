package com.mvk.pig_whack;

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

public class Gameplay2alt extends BaseGameplay {

    @BindView(R.id.g1alt_head_hole_1)
    ImageView headHole1;
    @BindView(R.id.g1alt_head_hole_2) ImageView headHole2;
    @BindView(R.id.g1alt_head_hole_3) ImageView headHole3;
    @BindView(R.id.g1alt_head_hole_4) ImageView headHole4;
    @BindView(R.id.g1alt_head_hole_5) ImageView headHole5;

    @BindView(R.id.g1alt_score_label)
    TextView scoreLabel;
    @BindView(R.id.g1alt_score) TextView score;
    @BindView(R.id.g1alt_counter) TextView counter;

    boolean alternativeSizeScreen = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getDisplayMetrics().densityDpi > 410){
            setContentView(R.layout.activity_gameplay2);
            alternativeSizeScreen = false;
        }
        else{
            setContentView(R.layout.activity_gameplay2alt);
        }

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
    //--------------------------Alternative Layout Methods-------------------------------------
    //On hit methods for each hole
    //--------Head - Hole 1---------------
    @OnClick(R.id.g1alt_head_hole_1)
    public void hitPig1(){
        headHole1.setImageResource(R.drawable.head_dizzy_space);
        //prevents from multiply points for same pig
        if(canScoreHole1){
            canScoreHole1=false;
            userScore++;
            score.setText(userScore+"");
        }
    }
    //--------Head - Hole 2---------------
    @OnClick(R.id.g1alt_head_hole_2)
    public void hitPig2(){
        headHole2.setImageResource(R.drawable.head_dizzy_space);
        //prevents from multiply points for same pig
        if(canScoreHole2){
            canScoreHole2=false;
            userScore++;
            score.setText(userScore+"");
        }
    }
    //--------Head - Hole 3---------------
    @OnClick(R.id.g1alt_head_hole_3)
    public void hitPig3(){
        headHole3.setImageResource(R.drawable.head_dizzy_space);
        //prevents from multiply points for same pig
        if(canScoreHole3){
            canScoreHole3=false;
            userScore++;
            score.setText(userScore+"");
        }
    }
    //--------Head - Hole 4---------------
    @OnClick(R.id.g1alt_head_hole_4)
    public void hitPig4(){
        headHole4.setImageResource(R.drawable.head_dizzy_space);
        //prevents from multiply points for same pig
        if(canScoreHole4){
            canScoreHole4=false;
            userScore++;
            score.setText(userScore+"");
        }
    }
    //--------Head - Hole 5---------------
    @OnClick(R.id.g1alt_head_hole_5)
    public void hitPig5(){
        headHole5.setImageResource(R.drawable.head_dizzy_space);
        //prevents from multiply points for same pig
        if(canScoreHole5){
            canScoreHole5=false;
            userScore++;
            score.setText(userScore+"");
        }
    }

//-----------------------------------------------------------------------------------------

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
                //TODO:Finish game screen
                //wait 1.5 sec and move to next level

            }
        }
    };

    //Runnable to repeat pigs popping up and down
    Handler pigsHandler = new Handler();
    Runnable pigMoveRunnable = new Runnable() {

        @Override
        public void run() {
            Random random = new Random();

            int min=1;
            int max=5;
            if(!alternativeSizeScreen){
                max=7;
            }

            int firstHole = random.nextInt((max - min) + 1) + min;
            //decide how many pigs will show up
            //random between 1 and 10 if number is bigger than 5 we show 2 pigs
            boolean twoPigsUp = false;
            int secondHole = 0;
            int randomDecision = random.nextInt(10 - 2) +1;
            if(randomDecision>4){
                twoPigsUp = true;
                secondHole=random.nextInt((max - min) + 1) + min;
                //if numbers are the same one will be incremented by 1 or decremented by 1
                if (secondHole==firstHole){
                    if(firstHole==max){
                        secondHole=firstHole-1;
                    }
                    else{
                        secondHole=firstHole+1;
                    }
                }
            }

            moveUp(firstHole);
            //if two pigs going to show up
            if(twoPigsUp) {
                moveUp(secondHole);
            }

            if(timeToPlay>0){
                pigsHandler.postDelayed(this, TIME_BETWEEN_POP_UPS);
            }
        }
    };

    public void moveUp(int hole){
        switch (hole){
            case 1:
                moveImageViewUpAndDown(1,headHole1, R.drawable.head_space);
                break;
            case 2:
                moveImageViewUpAndDown(2,headHole2, R.drawable.head_space);
                break;
            case 3:
                moveImageViewUpAndDown(3,headHole3, R.drawable.head_space);
                break;
            case 4:
                moveImageViewUpAndDown(4,headHole4, R.drawable.head_space);
                break;
            case 5:
                moveImageViewUpAndDown(5,headHole5, R.drawable.head_space);
                break;
            default:
                break;
        }
    }
}
