package com.example.ttt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class GetStart extends AppCompatActivity {

    public boolean active=true;
    public int activePlayer=0;
    public static String winnerStr;
    Handler handler =new Handler();
    Timer timer;
    boolean isRemain=true;
    public static int i ;

    int[] gameState = {2, 2 , 2, 2, 2, 2, 2, 2, 2};
    //    State meanings:
    //    0 - X
    //    1 - O
    //    2 - Null
    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_start);
    }
    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!active){
            reset(view);
        }
        if(gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                Toast.makeText(this,"O's turn", Toast.LENGTH_SHORT).show();
            } else {
                img.setImageResource(R.drawable.y);
                activePlayer = 0;
                Toast.makeText(this,"X's turn", Toast.LENGTH_SHORT).show();
            }
            img.animate().translationYBy(1000f).setDuration(100);
        }
        // Check if any player has won
        for(int[] winPosition: winPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]]!=2) {
                // Somebody has won! - Find out who!
//                String winnerStr;
                active = false;
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "X has won";
                    i=1;
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Intent i = new Intent(GetStart.this, Won.class);
                            startActivity(i);
                            finish();
                        }
                    }, 400);
                } else {
                    winnerStr = "O has won";
                    i=0;
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Intent i = new Intent(GetStart.this, Won.class);
                            startActivity(i);
                            finish();
                        }
                    }, 400);

                }
                // Update the status bar for winner announcement

            }
        }
        //to reset the game

        for(int i=0; i<gameState.length;i++){
            if(gameState[i]!=2){
                isRemain=false;
            }
            else{
                isRemain=true;
                break;
            }
        }
        if(!isRemain){
            reset(view);

        }


    }

    public void reset(View view) {
        active = true;
        activePlayer = 0;
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.x0)).setImageResource(0);
        ((ImageView)findViewById(R.id.x1)).setImageResource(0);
        ((ImageView)findViewById(R.id.x2)).setImageResource(0);
        ((ImageView)findViewById(R.id.x3)).setImageResource(0);
        ((ImageView)findViewById(R.id.x4)).setImageResource(0);
        ((ImageView)findViewById(R.id.x5)).setImageResource(0);
        ((ImageView)findViewById(R.id.x6)).setImageResource(0);
        ((ImageView)findViewById(R.id.x7)).setImageResource(0);
        ((ImageView)findViewById(R.id.x8)).setImageResource(0);
        Toast.makeText(this,"X's turn", Toast.LENGTH_LONG).show();
    }
    public static String getWinnerStr(){
        return winnerStr;
    }
    public static int getI(){
        return i;
    }
}