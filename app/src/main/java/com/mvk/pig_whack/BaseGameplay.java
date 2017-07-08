package com.mvk.pig_whack;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by marcin on 05/07/2017.
 */

public class BaseGameplay extends AppCompatActivity {
    public final int TIME_BETWEEN_POP_UPS = 2000;
    public int durationUpMove = 1000;
    public int durationDownMove = 500;

    long timeToPlay = 30;

    boolean canScoreHole1;
    boolean canScoreHole2;
    boolean canScoreHole3;
    boolean canScoreHole4;
    boolean canScoreHole5;
    boolean canScoreHole6;
    boolean canScoreHole7;

    int userScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userScore=0;

    }
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

        ObjectAnimator moveUp = ObjectAnimator.ofFloat(imageView, "y", imageView.getY(), imageView.getY()-pointToMove);
        ObjectAnimator moveDown = ObjectAnimator.ofFloat(imageView, "y", imageView.getY()-pointToMove, imageView.getY());

        moveUp.setDuration(durationUpMove);
        moveDown.setDuration(durationDownMove);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(moveUp, moveDown);

        animatorSet.start();
    }
}
