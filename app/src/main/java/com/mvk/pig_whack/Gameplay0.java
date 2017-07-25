package com.mvk.pig_whack;

import android.animation.AnimatorSet;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Gameplay0 extends BaseGameplay {

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

        //nextGameplayScreen = 0;

        //to prevent initial screen when switching activities
        getIntent().setAction("Already created");

        //preserve the score on new gameplay screen
        score.setText(Controller.getInstance().getScore()+"");
        currentScreen=1;

        //--------------------------------------------------------------------------
        animateRoundLabel();
        //--------------------------------------------------------------------------
    }

}
