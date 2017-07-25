package com.mvk.pig_whack;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.BinderThread;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EndScreen extends AppCompatActivity {

    @BindView(R.id.finalScore) TextView finalScore;
    @BindView(R.id.finalScoreLabel) TextView scoreLabel;
    @BindView(R.id.playAgainButton) ImageView playAgainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        ButterKnife.bind(this);
        finalScore.setText(Controller.getInstance().getScore()+"");

        //Fixing up Fonts
        Typeface frijole = Typeface.createFromAsset(getAssets(), "fonts/Frijole-Regular.ttf");
        finalScore.setTypeface(frijole);
        scoreLabel.setTypeface(frijole);

        getIntent().setAction("Already created");
    }

    @OnClick(R.id.playAgainButton)
    public void playAgain(){
//        Controller.getInstance().playAgain();
//        Intent intent = new Intent();
//        intent = new Intent(this, Gameplay0.class);
//        startActivity(intent);
        Intent intent = new Intent(this, WelcomeScreen.class);
        Controller.getInstance().playAgain();
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        String action = getIntent().getAction();
        // Prevent endless loop by adding a unique action, don't restart if action is present
        if(action == null || !action.equals("Already created")) {
            Intent intent = new Intent(this, WelcomeScreen.class);
            startActivity(intent);
            finish();
        }
        // Remove the unique action so the next time onResume is called it will restart
        else
            getIntent().setAction(null);
        super.onResume();
    }
}
