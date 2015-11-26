package com.kelvinhado.bluetictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

            final TicTacToeFragment ticTacToeFragment = new TicTacToeFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayoutGame, ticTacToeFragment)
                    .addToBackStack(null)
                    .commit();
    }
}
