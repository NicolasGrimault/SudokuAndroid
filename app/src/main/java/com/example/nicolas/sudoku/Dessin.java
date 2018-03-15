package com.example.nicolas.sudoku;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Nicolas on 15/03/2018.
 */

public class Dessin extends View {
    public Dessin(Context context) {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);

        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        int minSize = screenWidth<screenHeight? screenWidth :screenHeight;
        int step = minSize/9;
        paint.setTextSize(step-5);
        for (int i = 0; i < 9; i++) {
            canvas.drawLine(0, i * step, minSize, i*step, paint);
            canvas.drawLine(0, i * step + 1, minSize, i * step + 1, paint);

            canvas.drawLine(i * step, 0, i * step, minSize, paint);
            canvas.drawLine(i * step + 1, 0, i * step + 1, minSize, paint);

        }
        canvas.drawLine(0, 9 * step +1 , minSize, 9 * step +1, paint);

        for (int i = 0; i < 9; i++) {
            canvas.drawLine(i * step + 1, 11 * step +1 , i * step +1, 12 * step +1, paint);
            canvas.drawText(String.valueOf(i+1), i * step + 35, 12 * step -15, paint);
        }
        canvas.drawLine(0, 11 * step + 1 , minSize, 11 * step + 1, paint);
        canvas.drawLine(0, 12 * step + 1 , minSize, 12 * step+1, paint);
    }
}
