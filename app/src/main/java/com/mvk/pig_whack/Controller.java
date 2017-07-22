package com.mvk.pig_whack;

/**
 * Created by marcin on 21/07/2017.
 */

class Controller {
    private static Controller getInstance=null;

    static Controller getInstance() {
        if(getInstance==null){
            getInstance = new Controller();
        }
        return getInstance;
    }
//------------------------------------------------------------------------------------
    private int nextScreen;
    private int TIME_BETWEEN_POP_UPS;
    private int durationUpMove;
    private int durationDownMove;
    private int score;
    private int scorePoint;

    //Constructor
    private Controller() {
        score=0;
        scorePoint=10;
        nextScreen = 0;
        TIME_BETWEEN_POP_UPS = 2000;
        durationUpMove = 1000;
        durationDownMove = 500;
    }

    public int getNextScreen(){
        //so the initial transition is to gameplay 1
        nextScreen++;

        //for a second and third iteration increase the speed and scorePoint
        if(nextScreen%3==0) {
            TIME_BETWEEN_POP_UPS -= 225;
            durationUpMove -= 150;
            durationDownMove -= 75;
            scorePoint+=5;
        }

        if(nextScreen==9){
            return 99;
        }
        else{
            if(nextScreen<3){
                return nextScreen;
            }
            else{
                return nextScreen%3;
            }
        }
    }

    public int getTIME_BETWEEN_POP_UPS() {
        return TIME_BETWEEN_POP_UPS;
    }

    public int getDurationUpMove() {
        return durationUpMove;
    }

    public int getDurationDownMove() {
        return durationDownMove;
    }
    public void increaseScore(){
        score=score+scorePoint;
    }
    public void playAgain(){
        score=0;
        scorePoint=10;
        nextScreen = 0;
        TIME_BETWEEN_POP_UPS = 2000;
        durationUpMove = 1000;
        durationDownMove = 500;
    }
    public int getScore(){
        return score;
    }
}
