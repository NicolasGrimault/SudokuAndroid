package com.example.nicolas.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SudokuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        vGrille level = (vGrille) intent.getParcelableExtra("vGrille");
        setContentView(new Dessin(this));
    }
}
