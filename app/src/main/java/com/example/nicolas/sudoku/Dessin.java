package com.example.nicolas.sudoku;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

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

    private final int positionOfBar = 10;
    private final int correctionTextX = 35;
    private final int correctionTextY = 140;

    @Override
    public void onDraw(Canvas canvas) {
        Paint defaultPaint = new Paint();
        defaultPaint.setColor(Color.BLACK);
        defaultPaint.setStrokeWidth(3);
        defaultPaint.setTextSize(stepSize - 5);

        Paint bluePaint = new Paint();
        bluePaint.setColor(Color.BLUE);
        bluePaint.setTextSize(stepSize - 5);

        Paint redPaint = new Paint();
        redPaint.setColor(Color.RED);
        redPaint.setTextSize(stepSize - 5);

        Paint strong = new Paint();
        strong.setColor(Color.BLACK);
        strong.setStrokeWidth(10);


        for (int i = 0; i <= 11; i++) {
            Paint paintToUse = i % 3 == 0 ? strong : defaultPaint;
            canvas.drawLine(0, i * stepSize, minSize, i * stepSize, paintToUse);
            if (i < 10)
                canvas.drawLine(i * stepSize, 0, i * stepSize, minSize, paintToUse);

            //Draw grid Number
            canvas.drawLine(i * stepSize, positionOfBar * stepSize, i * stepSize, (positionOfBar + 1) * stepSize, defaultPaint);
            canvas.drawText(String.valueOf(i + 1), i * stepSize + correctionTextX, positionOfBar * stepSize + correctionTextY, bluePaint);
        }

        if (selectedValue != 0)
            canvas.drawText(String.valueOf(selectedValue), coordX, coordY, defaultPaint);

        for (int i = 0; i < 81; i++) {
            int x = i / 9;
            int y = i % 9;
            if (grille.charAt(i) != '0')
                canvas.drawText(String.valueOf(grille.charAt(i)), x * stepSize + correctionTextX, y * stepSize + correctionTextY, defaultPaint);
            else if (grilleAnswer.charAt(i) != '0')
                canvas.drawText(String.valueOf(grilleAnswer.charAt(i)), x * stepSize + correctionTextX, y * stepSize + correctionTextY, isUnique(i) ? bluePaint : redPaint);
        }
    }

    public boolean isUnique(int index) {
        int x = index / 9;
        int y = index % 9;
        int cellX = Math.round(x / 3);
        int cellY = Math.round(y / 3);

        for (int i = 0; i < 9; i++) {
            int indX = x * 9 + i;
            int indY = i * 9 + y;
            if (indX != index && !AreCompatible(index, indX))
                return false;
            if (indY != index && !AreCompatible(index, indY))
                return false;
        }
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++)
            {
            int ind =(cellX*3 + i) * 9 + (cellY*3 +j);
                if (ind != index && !AreCompatible(index, ind))
                    return false;
            }
        }

        return true;
    }

    public boolean AreCompatible(int indexA, int indexB) {
        String a;
        if (grille.charAt(indexA) != '0')
            a = String.valueOf(grille.charAt(indexA));
        else if (grilleAnswer.charAt(indexA) != '0')
            a = String.valueOf(grilleAnswer.charAt(indexA));
        else
            return true;
        String b;
        if (grille.charAt(indexB) != '0')
            b = String.valueOf(grille.charAt(indexB));
        else if (grilleAnswer.charAt(indexB) != '0')
            b = String.valueOf(grilleAnswer.charAt(indexB));
        else
            return true;
        return !a.equals(b);
    }


    boolean firstTouch = false;
    long time = System.currentTimeMillis();

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        coordX = (int) motionEvent.getX();
        coordY = (int) motionEvent.getY();
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (firstTouch && (System.currentTimeMillis() - time) <= 300) {
                    int index = getIndex(coordX, coordY);
                    if (index != -1)
                        grilleAnswer = grilleAnswer.substring(0, index) + "0" + grilleAnswer.substring(index + 1);
                    firstTouch = false;

                } else {
                    firstTouch = true;
                    selectedValue = getSelectedNumber(coordX, coordY);
                    time = System.currentTimeMillis();
                }
                break;
            case MotionEvent.ACTION_UP:
                int index = getIndex(coordX, coordY);
                if (index != -1 && grille.charAt(index) == '0' && selectedValue != 0) {
                    grilleAnswer = grilleAnswer.substring(0, index) + selectedValue + grilleAnswer.substring(index + 1);
                }
                selectedValue = 0;
                break;
        }
        this.invalidate();
        return true;
    }

    private int getIndex(int x, int y) {
        if (y > minSize)
            return -1;
        else
            return ((x / stepSize)) * 9 + (y / stepSize);

    }

    private int getSelectedNumber(int x, int y) {
        if (y < positionOfBar * stepSize + 1 || y > (positionOfBar + 1) * stepSize + 1)
            return 0;
        else
            return (x / stepSize) + 1;
    }
}
