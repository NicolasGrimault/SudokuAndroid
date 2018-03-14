package com.example.nicolas.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SelectGridActivity extends Activity {

    public class vgrille{
        int level;
        int done;
        int num;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_grid);
        Intent intent = this.getIntent();
        int level = intent.getIntExtra("Level",0);
    }
}
