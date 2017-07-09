package com.mvk.pig_whack;

import android.graphics.Typeface;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Gameplay0 extends BaseGameplay {

    long timeToPlay = 7;

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

}
