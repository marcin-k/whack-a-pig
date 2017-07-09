package com.mvk.pig_whack;

import android.graphics.Typeface;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Gameplay2alt extends BaseGameplay {

//    boolean alternativeSizeScreen = true;
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

        nextGameplayScreen = 0;
        onlyOneHeadUp = false;
        pigImageAfterHit=R.drawable.head_dizzy_space;
        pigImageBeforeHit=R.drawable.head_space;
    }
//
}
