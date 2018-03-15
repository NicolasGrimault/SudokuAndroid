package com.example.nicolas.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Random;

public class SelectGridActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_grid);
        Intent intent = this.getIntent();
        int level = intent.getIntExtra("Level", 1);


        ArrayList<vGrille> ListVgrille = new ArrayList<vGrille>();
        for (int i = 0; i < 100; i++){
            Random ran = new Random();
            ListVgrille.add(new vGrille(level,ran.nextInt(100),i));
        }
        vgrilleAdapter adapter = new vgrilleAdapter(this, ListVgrille);
        final ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vGrille selectedItem = (vGrille) parent.getItemAtPosition(position);

                Intent sudoku = new Intent(SelectGridActivity.this,SudokuActivity.class);
                sudoku.putExtra("vGrille", selectedItem.toString());
                startActivity(sudoku);
            }
        });

    }
}
