package com.example.nicolas.sudoku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nicolas on 14/03/2018.
 */

public class vgrilleAdapter  extends ArrayAdapter<vGrille> {

    public vgrilleAdapter(Context context, ArrayList<vGrille> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        vGrille vgrille = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.vgrille_layout, parent, false);
        }

        TextView Number = (TextView) convertView.findViewById(R.id.Number);
        TextView Done = (TextView) convertView.findViewById(R.id.done);
        TextView Difficulty = (TextView) convertView.findViewById(R.id.difficulty);

        Number.setText(String.valueOf(vgrille.num));
        Done.setText(String.valueOf(vgrille.done) + "%");
        Difficulty.setText("Niveau " +String.valueOf(vgrille.level));

        return convertView;
    }
}

