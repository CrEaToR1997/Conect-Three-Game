package com.example.acer.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0, tapIndex;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningStates = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int coinCount = 0;

    boolean gameIsActive = true;
    boolean nobodyHasOne = false;

    String mess = "No one won the game";

    Button b ;
    GridLayout g ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = findViewById(R.id.button2);
    }

    public void DropIn(View v){
        ImageView i = (ImageView)v;
        tapIndex = Integer.parseInt(v.getTag().toString());
        coinCount++;

        if(coinCount > 9){
            Toast.makeText(this, "Nobody has won", Toast.LENGTH_SHORT).show();
            gameIsActive = false;
            b.setVisibility(View.VISIBLE);
        }

        if(gameIsActive && gameState[tapIndex] == 2) {
            //switching image on each turn
            if (activePlayer == 0) {
                i.setTranslationX(-1000);
                i.setImageResource(R.drawable.green);
                i.animate().translationXBy(1000).setDuration(1000);
                activePlayer = 1;
            } else if (activePlayer == 1) {
                i.setTranslationY(-1000);
                i.setImageResource(R.drawable.red);
                i.animate().translationYBy(1000).setDuration(1000);
                activePlayer = 0;
            }

            gameState[tapIndex] = activePlayer;

            //checking whether the gamestate has same elements in positions given by winning positions
            for (int x = 0; x < 8; x++) {
                int j, k, l;
                String mess = "";
                j = gameState[winningStates[x][0]];
                k = gameState[winningStates[x][1]];
                l = gameState[winningStates[x][2]];
                if (j == k && k == l && j != 2) {
                    Log.i("Game result", "Some one has won");
                    if (activePlayer == 0) {
                        mess = "Red has won";
                    } else if (activePlayer == 1) {
                        mess = "Green has won";
                    }
                    Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                    gameIsActive = false;
                    b.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void Reset(View view){
        g = findViewById(R.id.gd);
        for(int i=0; i<g.getChildCount(); i++) {
           ImageView child = (ImageView) g.getChildAt(i);
           child.setImageDrawable(null);
        }

        for(int x =0; x<8; x++){
            gameState[x] = 2;
        }

        activePlayer = 0;
        gameIsActive = true;
        coinCount = 0;
        b.setVisibility(View.INVISIBLE);
    }
}
