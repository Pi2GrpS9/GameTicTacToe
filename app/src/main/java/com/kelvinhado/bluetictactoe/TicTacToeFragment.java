package com.kelvinhado.bluetictactoe;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class TicTacToeFragment extends Fragment {


    // TODO change the first player
    private static final int firstGamer = 1;
    private static final int me = 1;
    private Game game;

    // views
    private ImageButton button0,button1,button2,button3,button4,button5,button6,button7,button8;
    private HashMap<Integer, ImageButton> buttonMap;





    public TicTacToeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);
        button0 = (ImageButton) view.findViewById(R.id.button0);
        button1 = (ImageButton) view.findViewById(R.id.button1);
        button2 = (ImageButton) view.findViewById(R.id.button2);
        button3 = (ImageButton) view.findViewById(R.id.button3);
        button4 = (ImageButton) view.findViewById(R.id.button4);
        button5 = (ImageButton) view.findViewById(R.id.button5);
        button6 = (ImageButton) view.findViewById(R.id.button6);
        button7 = (ImageButton) view.findViewById(R.id.button7);
        button8 = (ImageButton) view.findViewById(R.id.button8);

        buttonMap = new HashMap<>();
        buttonMap.put(0,button0);
        buttonMap.put(1,button1);
        buttonMap.put(2,button2);
        buttonMap.put(3,button3);
        buttonMap.put(4,button4);
        buttonMap.put(5,button5);
        buttonMap.put(6,button6);
        buttonMap.put(7,button7);
        buttonMap.put(8, button8);

        for(ImageButton button : buttonMap.values()) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bTapped(v);
                }
            });
        }


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();

        startNewGame();
    }



    private void startNewGame() {
        game = new Game(firstGamer);

        // initializing button text
        for(ImageButton button : buttonMap.values()) {
            button.setImageResource(R.drawable.boost);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                button.setBackground(null);
            }
        }

        Snackbar.make(this.button0, "New game started, player : " + firstGamer, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }

    // update the text in the button when a player mark a cell
    private void updateView(int player, int location) {

        /**
         * First player : O
         * Second player : X
         */

        if(location < buttonMap.size() ) {
            switch (player) {
                case 1:
                    (buttonMap.get(location)).setImageResource(R.drawable.circle);
                    break;
                case 2:
                    (buttonMap.get(location)).setImageResource(R.drawable.croix);
                    break;
            }
        }
        else {
            Log.d("Debug#44", "could not update the view #" + location);
        }

    }

    // catch event from the layout
    public void bTapped(View view) {
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

    //need to be called at each row
    private void play(int location) {
        boolean cellMarked = game.markACell(location);
        boolean gameWon = game.checkForWin();

        if(cellMarked) {
            if(gameWon) {
                Snackbar.make(button0, "**** PLAYER " + game.getCurrentPlayer() + " WON ! ****", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                updateView(game.getCurrentPlayer(), location);

                // display dialog
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_game_won);
                ImageView img = (ImageView) dialog.findViewById(R.id.imageView);
                if(game.getCurrentPlayer() != me)
                    img.setImageResource(R.drawable.loser2);
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        startNewGame();
                    }
                });
                dialog.show();

            }
            else{
                updateView(game.getCurrentPlayer(), location);
                int newGamer = game.changePlayer();
//                Snackbar.make(button0, "NOW GAMER " + newGamer, Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        }
        else {
//            Snackbar.make(button0, "select another cell", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
        }
    }

}
