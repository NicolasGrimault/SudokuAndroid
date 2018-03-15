package com.example.nicolas.sudoku;

import android.app.Activity;
import android.os.Bundle;

public class SudokuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_sudoku);
        setContentView(new Dessin(this));
    }
}
