package com.tictaktoeapm.tictactoeapm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    boolean gameIsActive=true;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
   int[][] winningPositions ={ {0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn (View view){
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter] == 2&& gameIsActive) {

            gameState[tappedCounter] = activePlayer;


            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.eii);
                activePlayer = 1;
            } else {

                counter.setImageResource(R.drawable.err);
                activePlayer = 0;
            }


            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]]!=2 ) {
                    //someone won the game
                    gameIsActive=false;
                    String winner = "Grean";

                    if(gameState[winningPosition[0]]==0){
                        winner= "Red";


                    }
                    TextView winnerText = (TextView) findViewById(R.id.winnerText);
                    winnerText.setText(winner+ "\thas Won!");

                    LinearLayout playAgainLinearLayout = (LinearLayout) findViewById(R.id.playAgainLinearLayout);
                    playAgainLinearLayout.setVisibility(View.VISIBLE);


                }else{
                    boolean gameIsOver = true;
                    for(int counterState : gameState) {
                        if (counterState == 2) gameIsOver = false;
                    }
                    if(gameIsOver){

                        TextView winnerText = (TextView) findViewById(R.id.winnerText);
                        winnerText.setText( "It's A Draw!");

                        LinearLayout playAgainLinearLayout = (LinearLayout) findViewById(R.id.playAgainLinearLayout);
                        playAgainLinearLayout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }


            }

            public void playAgain(View view){

        gameIsActive = true;
        LinearLayout playAgainLinearLayout = (LinearLayout) findViewById(R.id.playAgainLinearLayout);
        playAgainLinearLayout.setVisibility(View.INVISIBLE);

        activePlayer=0;
        for(int i=0; i<gameState.length;i++ ){
            gameState[i]=2;
        }
                GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i=0; i<gridLayout.getChildCount();i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }



    }









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
