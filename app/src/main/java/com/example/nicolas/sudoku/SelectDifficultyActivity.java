package com.example.nicolas.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class SelectDifficultyActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_difficulty);

        int[] buttons = {R.id.Niveau1, R.id.Niveau2};

        for (int buttonId : buttons) {
            findViewById(buttonId).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {

        int level;
        switch (view.getId()) {
            case R.id.Niveau1:
                level = 1;
                break;
            case R.id.Niveau2:
                level = 2;
                break;
            default:
                level = 0;
                break;
        }
        Intent intent = new Intent(this, SelectGridActivity.class);
        intent.putExtra("Level", level);
        startActivity(intent);
    }
}
