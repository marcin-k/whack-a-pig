package com.mvk.pig_whack;

import android.widget.ImageView;
import butterknife.BindView;
import butterknife.OnClick;

/**************************************************************
 *
 *  Class extends the Gameplay1alt - alternative view designed
 *  for smaller devices (containing only 5 holes)
 *  Gameplay1alt selects a xml to load.
 *  Class adds the view binding for additional ui elements,
 *  onClick methods and overrides the the moveUp method
 *  (to allow heads movements in 2 additional holes)
 *
 *  @author: Marcin Krzeminski
 *           marcin.krze@icloud.com
 *
 **************************************************************/
public class Gameplay1 extends Gameplay1alt {

    @BindView(R.id.g1_head_hole_6) ImageView headHole6;
    @BindView(R.id.g1_head_hole_7) ImageView headHole7;

//--------------------OnClick methods for additional 2 holes-------------------------------

    //--------Head - Hole 6---------------
    @OnClick(R.id.g1_head_hole_6)
    public void hitPig6(){
        headHole6.setImageResource(R.drawable.head_dizzy);
        //prevents from multiply points for same pig
        if(canScoreHole6){
            canScoreHole6=false;
            userScore++;
            score.setText(userScore+"");
        }
    }
    //--------Head - Hole 7---------------
    @OnClick(R.id.g1_head_hole_7)
    public void hitPig7(){
        headHole7.setImageResource(R.drawable.head_dizzy);
        //prevents from multiply points for same pig
        if(canScoreHole7){
            canScoreHole7=false;
            userScore++;
            score.setText(userScore+"");
        }
    }

    @Override
    public void moveUp(int hole) {
            switch (hole) {
                case 1:
                    moveImageViewUpAndDown(1, headHole1, R.drawable.head);
                    break;
                case 2:
                    moveImageViewUpAndDown(2, headHole2, R.drawable.head);
                    break;
                case 3:
                    moveImageViewUpAndDown(3, headHole3, R.drawable.head);
                    break;
                case 4:
                    moveImageViewUpAndDown(4, headHole4, R.drawable.head);
                    break;
                case 5:
                    moveImageViewUpAndDown(5, headHole5, R.drawable.head);
                    break;
                case 6:
                    moveImageViewUpAndDown(6, headHole6, R.drawable.head);
                    break;
                case 7:
                    moveImageViewUpAndDown(7, headHole7, R.drawable.head);
                    break;
                default:
                    break;
            }
    }
}
