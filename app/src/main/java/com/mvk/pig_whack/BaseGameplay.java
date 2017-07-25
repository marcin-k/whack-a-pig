package com.mvk.pig_whack;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by marcin on 05/07/2017.
 */

public class BaseGameplay extends AppCompatActivity {


    @BindView(R.id.round) TextView round;
    @BindView(R.id.roundNumber) TextView roundNumber;

    public final int TIME_BETWEEN_POP_UPS = Controller.getInstance().getTIME_BETWEEN_POP_UPS();
    public int durationUpMove = Controller.getInstance().getDurationUpMove();
    public int durationDownMove = Controller.getInstance().getDurationDownMove();


    //time of each round defined in seconds
    long timeToPlay = 10;
//    int userScore=0;

    int pigImageBeforeHit = R.drawable.head;
    int pigImageAfterHit = R.drawable.head_dizzy;

    int currentScreen = 0;
    //------Screens:
    //0 - activity_welcome_screen
    //1 - activity_gameplay0
    //2 - activity_gameplay1
    //3 - activity_gameplay1alt
    //4 - activity_gameplay2
    //5 - activity_gameplay2alt
    //99 - activity_end_screen

    //to allow one or two heads up at the same time
    boolean onlyOneHeadUp = true;
    //to track if the screen is big enough to display 7 or 5 holes
    boolean alternativeSizeScreen = true;

    //to prevent user multiply score on same sprite
    boolean canScoreHole1;
    boolean canScoreHole2;
    boolean canScoreHole3;
    boolean canScoreHole4;
    boolean canScoreHole5;
    boolean canScoreHole6;
    boolean canScoreHole7;

    @BindView(R.id.head_hole_1) ImageView headHole1;
    @BindView(R.id.head_hole_2) ImageView headHole2;
    @BindView(R.id.head_hole_3) ImageView headHole3;
    @BindView(R.id.head_hole_4) ImageView headHole4;
    @BindView(R.id.head_hole_5) ImageView headHole5;
    @BindView(R.id.score_label) TextView scoreLabel;
    @BindView(R.id.score) TextView score;
    @BindView(R.id.counter) TextView counter;

    //----------------------------------------------------------------------------
    //----------------------------OnClick Methods---------------------------------
    //----------------------------------------------------------------------------

    //--------Head - Hole 1---------------
    @OnClick(R.id.head_hole_1)
    public void hitPig1(){
        //prevents from multiply points for same pig
        if(canScoreHole1){
            canScoreHole1=false;
            changePigSpriteAndUpdateScore(headHole1);
        }
    }
    //--------Head - Hole 2---------------
    @OnClick(R.id.head_hole_2)
    public void hitPig2(){
        //prevents from multiply points for same pig
        if(canScoreHole2){
            canScoreHole2=false;
            changePigSpriteAndUpdateScore(headHole2);
        }
    }
    //--------Head - Hole 2---------------
    @OnClick(R.id.head_hole_3)
    public void hitPig3(){
        //prevents from multiply points for same pig
        if(canScoreHole3){
            canScoreHole3=false;
            changePigSpriteAndUpdateScore(headHole3);
        }
    }
    //--------Head - Hole 2---------------
    @OnClick(R.id.head_hole_4)
    public void hitPig4(){
        //prevents from multiply points for same pig
        if(canScoreHole4){
            canScoreHole4=false;
            changePigSpriteAndUpdateScore(headHole4);
        }
    }
    //--------Head - Hole 2---------------
    @OnClick(R.id.head_hole_5)
    public void hitPig5(){
        //prevents from multiply points for same pig
        if(canScoreHole5){
            canScoreHole5=false;
            changePigSpriteAndUpdateScore(headHole5);
        }
    }

    //----------------------------------------------------------------------------
    //--------------------------------Runnable------------------------------------
    //----------------------------------------------------------------------------

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

            //if only one head is meant to pop up
            if(!onlyOneHeadUp) {
                int randomDecision = random.nextInt(10 - 2) + 1;
                if (randomDecision > 4) {
                    twoPigsUp = true;
                    secondHole = random.nextInt((max - min) + 1) + min;
                    //if numbers are the same one will be incremented by 1 or decremented by 1
                    if (secondHole == firstHole) {
                        if (firstHole == max) {
                            secondHole = firstHole - 1;
                        } else {
                            secondHole = firstHole + 1;
                        }
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
            else{
                pigsHandler.removeCallbacks(pigMoveRunnable);
                moveToNextGamePlay();
            }
        }
    };

    //----------------------------------------------------------------------------
    //--------------------------------Methods-------------------------------------
    //----------------------------------------------------------------------------

    //changes the sprite image after hit and increment score
    public void changePigSpriteAndUpdateScore(ImageView headHole){
        headHole.setImageResource(pigImageAfterHit);
        Controller.getInstance().increaseScore();
        score.setText(Controller.getInstance().getScore()+"");
    }

    //Method called by runnable in easy to override form, so pig head can
    //be changed without overriding the moveImageViewUpAndDown method
    public void moveUp(int hole){
        switch (hole){
            case 1:
                moveImageViewUpAndDown(1,headHole1, pigImageBeforeHit);
                break;
            case 2:
                moveImageViewUpAndDown(2,headHole2, pigImageBeforeHit);
                break;
            case 3:
                moveImageViewUpAndDown(3,headHole3, pigImageBeforeHit);
                break;
            case 4:
                moveImageViewUpAndDown(4,headHole4, pigImageBeforeHit);
                break;
            case 5:
                moveImageViewUpAndDown(5,headHole5, pigImageBeforeHit);
                break;
            default:
                break;
        }
    }

    //Moves pigs up and down, changing the sprite before the hit
    public void moveImageViewUpAndDown(int hole, ImageView imageView, int imageBeforeHit){

        switch (hole){
            case 1:
                //allow to score
                canScoreHole1 = true;
                imageView.setImageResource(imageBeforeHit);
                break;
            case 2:
                canScoreHole2 = true;
                imageView.setImageResource(imageBeforeHit);
                break;
            case 3:
                canScoreHole3 = true;
                imageView.setImageResource(imageBeforeHit);
                break;
            case 4:
                canScoreHole4 = true;
                imageView.setImageResource(imageBeforeHit);
                break;
            case 5:
                canScoreHole5 = true;
                imageView.setImageResource(imageBeforeHit);
                break;
            case 6:
                canScoreHole6 = true;
                imageView.setImageResource(imageBeforeHit);
                break;
            case 7:
                canScoreHole7 = true;
                imageView.setImageResource(imageBeforeHit);
                break;
            default:
                break;
        }
        //Getting screen DPI to establish amount of pixels to move image up and down
        int screenDpi = getResources().getDisplayMetrics().densityDpi;

        float pointToMove=0;
        switch (screenDpi){
            case 120:
                pointToMove = 75f;
                break;
            case 160:
                pointToMove = 100f;
                break;
            case 240:
                pointToMove = 150f;
                break;
            case 320:
                pointToMove = 200f;
                break;
            case 420:
                pointToMove = 262.5f;
                break;
            case 480:
                pointToMove = 300f;
                break;
            case 560:
                pointToMove = 340f;
                break;
            default:
                pointToMove = 350f;
                break;
        }

        Log.d("Movement",hole+" "+imageView.getDrawable()+" - "+getResources().getDrawable(R.drawable.head_space));

        //reducing by 20% heads movements for pigs on alternative screens pigs from hole 2 and 4
        if((hole==2 || hole==4)&&currentScreen==5){
            pointToMove = 0.8f * pointToMove;
        }

        //reduce by 10% head movements for pigs on gameplay 2 all holes except hole 1
        if((hole!=1 && currentScreen==2)){
            pointToMove = 0.9f * pointToMove;
        }

        //reduce by 10% head movements for pigs on gameplay 3 for holes: 1,2,3,6,7
        if((currentScreen==4 && (hole!=4|| hole!=5))){
            pointToMove = 0.9f * pointToMove;
        }

        //reduce by 15% head movements for pigs on gameplay 3 for hole 3
        if(currentScreen==4 && hole==3){
            pointToMove = 0.85f * pointToMove;
        }

        ObjectAnimator moveUp = ObjectAnimator.ofFloat(imageView, "y", imageView.getY(), imageView.getY()-pointToMove);
        ObjectAnimator moveDown = ObjectAnimator.ofFloat(imageView, "y", imageView.getY()-pointToMove, imageView.getY());

        moveUp.setDuration(durationUpMove);
        moveDown.setDuration(durationDownMove);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(moveUp, moveDown);

        animatorSet.start();
    }


    //Creates intent based on screen dpi and currently displayed screen
    public void moveToNextGamePlay(){

        Intent intent= new Intent();
        //Load correct class based on the screen dpi
        if(getResources().getDisplayMetrics().densityDpi > 410){
            switch (Controller.getInstance().getNextScreen()){
                case 0:
                    intent = new Intent(this, Gameplay0.class);
                    break;
                case 1:
                    intent = new Intent(this, Gameplay1.class);
                    break;
                case 2:
                    intent = new Intent(this, Gameplay2.class);
                    break;

                case 99:
                    intent = new Intent(this, EndScreen.class);
                    break;

                default:
                    break;
            }
        }
        else{
            switch (Controller.getInstance().getNextScreen()){
                case 0:
                    intent = new Intent(this, Gameplay0.class);
                    break;
                case 1:
                    intent = new Intent(this, Gameplay1alt.class);
                    break;
                case 2:
                    intent = new Intent(this, Gameplay2alt.class);
                    break;
                case 99:
                    intent = new Intent(this, EndScreen.class);
                    break;
                default:
                    break;
            }
        }
        startActivity(intent);
    }

    public void animateRoundLabel(){
        Typeface frijole = Typeface.createFromAsset(getAssets(), "fonts/Frijole-Regular.ttf");
        round.setText("round");
        round.setTypeface(frijole);
        Animation animation = new AlphaAnimation(1.0f, 0.0f);
        animation.setDuration(2000);
        animation.setFillAfter(true);
        roundNumber.setText(Controller.getInstance().getRoundNumber()+"");
        roundNumber.setTypeface(frijole);
        Animation animation2 = new AlphaAnimation(1.0f, 0.0f);
        animation2.setDuration(2000);
        animation2.setFillAfter(true);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(animation);
        animationSet.addAnimation(animation2);
        animationSet.setDuration(2000);
        animationSet.setFillAfter(true);
        animationSet.start();
        round.startAnimation(animation);
        roundNumber.startAnimation(animation2);
    }

    @Override
    protected void onPause() {
        super.onPause();
        pigsHandler.removeCallbacks(pigMoveRunnable);
        timeHandler.removeCallbacks(timeRunnable);
    }
    @Override
    protected void onResume() {
        String action = getIntent().getAction();
        // Prevent endless loop by adding a unique action, don't restart if action is present
        if(action == null || !action.equals("Already created")) {
            Intent intent = new Intent(this, WelcomeScreen.class);
            Controller.getInstance().playAgain();
            startActivity(intent);
            finish();
        }
        // Remove the unique action so the next time onResume is called it will restart
        else
            getIntent().setAction(null);
        super.onResume();
    }
}
