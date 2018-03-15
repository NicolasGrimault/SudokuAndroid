package com.example.nicolas.sudoku;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicolas on 15/03/2018.
 */

public class Dessin extends View implements View.OnTouchListener {
    public Dessin(Context context) {
        super(context);
        this.setOnTouchListener(this);
    }


    private int coordX;
    private int coordY;
    private int selectedValue = 0;

    private final int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private final int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int minSize = screenWidth < screenHeight ? screenWidth : screenHeight;
    private int stepSize = minSize / 9;

    private String grille = "008203500009670408346050702430010059967005001000496203280034067703500904004107020";
    private String grilleAnswer = "000000000000000000000000000000000000000000000000000000000000000000000000000000000";

    @Override
    public void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(stepSize - 5);
        Paint paint2 = new Paint();
        paint2.setColor(Color.BLUE);
        paint2.setTextSize(stepSize - 5);
        for (int i = 0; i < 9; i++) {
            canvas.drawLine(0, i * stepSize, minSize, i * stepSize, paint);
            canvas.drawLine(0, i * stepSize + 1, minSize, i * stepSize + 1, paint);

            canvas.drawLine(i * stepSize, 0, i * stepSize, minSize, paint);
            canvas.drawLine(i * stepSize + 1, 0, i * stepSize + 1, minSize, paint);

        }
        canvas.drawLine(0, 9 * stepSize + 1, minSize, 9 * stepSize + 1, paint);

        for (int i = 0; i < 9; i++) {
            canvas.drawLine(i * stepSize + 1, 11 * stepSize + 1, i * stepSize + 1, 12 * stepSize + 1, paint);
            canvas.drawText(String.valueOf(i + 1), i * stepSize + 35, 12 * stepSize - 15, paint);
        }
        canvas.drawLine(0, 11 * stepSize + 1, minSize, 11 * stepSize + 1, paint);
        canvas.drawLine(0, 12 * stepSize + 1, minSize, 12 * stepSize + 1, paint);

        if (selectedValue != 0)
            canvas.drawText(String.valueOf(selectedValue), coordX, coordY, paint);

        for (int i = 0; i < 81; i++) {
            int x = i / 9;
            int y = i % 9;
            if (grille.charAt(i) != '0')
                canvas.drawText(String.valueOf(grille.charAt(i)), x * stepSize + 35, (y + 1) * stepSize - 15, paint);
            else
                if (grilleAnswer.charAt(i) != '0')
                    canvas.drawText(String.valueOf(grilleAnswer.charAt(i)), x * stepSize + 35, (y + 1) * stepSize - 15, paint2);

        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        coordX = (int) motionEvent.getX();
        coordY = (int) motionEvent.getY();
        Log.d("TAG", "coord: " + coordX + " " + coordY);
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                selectedValue = getSelectedNumber(coordX, coordY);
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if (selectedValue != 0) {
                    int c = getCoord(coordX, coordY);
                    if (c != -1) {
                        grilleAnswer = grilleAnswer.substring(0, c) + selectedValue + grilleAnswer.substring(c+1);
                    }
                }
                selectedValue = 0;
                break;
        }
        this.invalidate();
        return true;
    }

    private int getCoord(int x, int y) {
        if (y > minSize)
            return -1;
        else
            return ((x / stepSize) ) * 9 + (y / stepSize) ;

    }

    private int getSelectedNumber(int x, int y) {
        if (y < 11 * stepSize + 1 || y > 12 * stepSize + 1)
            return 0;
        else
            return (x / stepSize) + 1;
    }
}
