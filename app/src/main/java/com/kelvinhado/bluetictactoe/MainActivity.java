package com.kelvinhado.bluetictactoe;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    // TODO change the first player
    private static final int firstGamer = 1;
    private Game game;

    // views
    private Button button0,button1,button2,button3,button4,button5,button6,button7,button8;
    private HashMap<Integer, Button> buttonMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initializeView();
                startNewGame();
                Snackbar.make(view, "New game started, player : " + firstGamer, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initializeView();
        startNewGame();
    }

    private void initializeView() {
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);

        buttonMap = new HashMap<>();
        buttonMap.put(0,button0);
        buttonMap.put(1,button1);
        buttonMap.put(2,button2);
        buttonMap.put(3,button3);
        buttonMap.put(4,button4);
        buttonMap.put(5,button5);
        buttonMap.put(6,button6);
        buttonMap.put(7,button7);
        buttonMap.put(8,button8);

        // initializing button text
        for(Button button : buttonMap.values()) {
            button.setText("*_*");
        }
    }

    // update the text in the button when a player mark a cell
    private void updateView(int player, int location) {
        if(location < buttonMap.size() ) {
            (buttonMap.get(location)).setText(Integer.toString(player));
        }
        else {
            Log.d("Debug#44", "could not update the view #" + location);
        }

    }


    // catch event from the layout
    public void buttonTapped(View view) {
        switch(view.getId()) {
            case R.id.button0:  play(0); break;
            case R.id.button1:  play(1); break;
            case R.id.button2:  play(2); break;
            case R.id.button3:  play(3); break;
            case R.id.button4:  play(4); break;
            case R.id.button5:  play(5); break;
            case R.id.button6:  play(6); break;
            case R.id.button7:  play(7); break;
            case R.id.button8:  play(8); break;
            default:
                Log.d("Debug#Control", "wrong button pressed");
                break;
        }

    }

    private void startNewGame() {
        game = new Game(firstGamer);
    }


    private void play(int location) {
        boolean cellMarked = game.markACell(location);
        boolean gameWon = game.checkForWin();

        if(cellMarked) {
            if(gameWon) {
                initializeView();
                startNewGame();

                Snackbar.make(button0, "**** PLAYER " + game.getCurrentPlayer() + "WON ! ****", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
            else{
                updateView(game.getCurrentPlayer(), location);
                int newGamer = game.changePlayer();
                Snackbar.make(button0, "NOW GAMER " + newGamer, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
        else {
            Snackbar.make(button0, "select another cell", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}