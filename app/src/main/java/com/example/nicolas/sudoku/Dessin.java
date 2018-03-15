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
        int height = minSize/9;
        int width = minSize/9;


        for (int i = 0; i < 9; i++) {
            canvas.drawLine(0, i * height, minSize, i*height, paint);
            canvas.drawLine(0, i * height + 1, minSize, i * height + 1, paint);
            canvas.drawLine(i * width, 0, i * width, minSize, paint);
            canvas.drawLine(i * width + 1, 0, i * width + 1, minSize, paint);
        }
        canvas.drawLine(0, 9 * height + 1, minSize, 9 * height + 1, paint);

    }
}
