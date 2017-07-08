package com.mvk.pig_whack;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeScreen extends AppCompatActivity {
    @BindView(R.id.playText) TextView playText;
    @BindView(R.id.pigWhack) TextView pigWhack;
    @BindView(R.id.playButton) ImageView playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        ButterKnife.bind(this);

        //Referencing custom fonts
        Typeface luminari = Typeface.createFromAsset(getAssets(), "fonts/Luminari-Regular.ttf");
        Typeface papyrus = Typeface.createFromAsset(getAssets(), "fonts/PAPYRUS.TTF");

        //sets fonts
        playText.setTypeface(papyrus);
        pigWhack.setTypeface(luminari);

    }
    //On play button clicked
    @OnClick(R.id.playButton)
    public void goToGameplay1() {
        animateButtonClick();
//        Intent intent = new Intent(this, GameplayTest.class);
        Intent intent = new Intent(this, Gameplay0.class);
        //activity transition  - requires min api 23
        //ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(WelcomeScreen.this);
        startActivity(intent);
    }

    private void animateButtonClick(){
        ObjectAnimator buttonXDown = ObjectAnimator.ofFloat(playButton, "scaleX", 1, 0.7f);
        ObjectAnimator buttonYDown = ObjectAnimator.ofFloat(playButton, "scaleY", 1, 0.7f);
        AnimatorSet scalePlayButtonDown = new AnimatorSet();
        scalePlayButtonDown.playTogether(buttonXDown, buttonYDown);

        ObjectAnimator buttonXUp = ObjectAnimator.ofFloat(playButton, "scaleX", 0.7f, 1);
        ObjectAnimator buttonYUp = ObjectAnimator.ofFloat(playButton, "scaleY", 0.7f, 1);
        AnimatorSet scalePlayButtonUp = new AnimatorSet();
        scalePlayButtonDown.playTogether(buttonXUp, buttonYUp);

        AnimatorSet buttonClick = new AnimatorSet();
        buttonClick.playSequentially(scalePlayButtonDown, scalePlayButtonUp);
        buttonClick.start();

    }
}
