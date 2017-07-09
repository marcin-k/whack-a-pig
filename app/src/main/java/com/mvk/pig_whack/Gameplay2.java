package com.mvk.pig_whack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.OnClick;

public class Gameplay2 extends Gameplay2alt {

    @BindView(R.id.head_hole_6)
    ImageView headHole6;
    @BindView(R.id.head_hole_7) ImageView headHole7;

//--------------------OnClick methods for additional 2 holes-------------------------------

    //--------Head - Hole 6---------------
    @OnClick(R.id.head_hole_6)
    public void hitPig6(){
        //prevents from multiply points for same pig
        if(canScoreHole6){
            canScoreHole6=false;
            changePigSpriteAndUpdateScore(headHole6);
        }
    }

    //--------Head - Hole 7---------------
    @OnClick(R.id.head_hole_7)
    public void hitPig7(){
        //prevents from multiply points for same pig
        if(canScoreHole7){
            canScoreHole7=false;
            changePigSpriteAndUpdateScore(headHole7);
        }
    }

    @Override
    public void moveUp(int hole) {
        switch (hole) {
            case 6:
                moveImageViewUpAndDown(6, headHole6, pigImageBeforeHit);
                break;
            case 7:
                moveImageViewUpAndDown(7, headHole7, pigImageBeforeHit);
                break;
            default:
                super.moveUp(hole);
        }
    }
}
