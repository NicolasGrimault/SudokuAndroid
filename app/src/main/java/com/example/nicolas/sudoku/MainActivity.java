package com.example.nicolas.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] buttons = {R.id.Niveau1, R.id.Niveau2};

        for (int buttonId : buttons) {
            findViewById(buttonId).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this, SelectGridActivity.class);
        startActivity(intent);
    }
}
