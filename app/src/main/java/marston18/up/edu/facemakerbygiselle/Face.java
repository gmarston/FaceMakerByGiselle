package marston18.up.edu.facemakerbygiselle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Face Class to draw face on surface view
 *
 * @author Giselle Marston
 * @version 2/11/16
 *
 */
public class Face extends SurfaceView {

    public int skinColor;
    public int eyeColor;
    public int hairColor;
    public Path[] hairStyles = new Path[3];

    public int hairStyleIndex;
    public int eyeStyle;
    public int noseStyle;

    int skinR;
    int skinG;
    int skinB;
    int eyeR;
    int eyeG;
    int eyeB;
    int hairR;
    int hairG;
    int hairB;

    public Face(Context context) {
        super(context);
    }

    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        randomize();
    }

    public Face(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        randomize();
    }

    public Face(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setWillNotDraw(false);
        randomize();
    }

    /**
     * Generates random values for face
     */
    public void randomize() {
        eyeStyle = (int) (Math.random() * 3);
        noseStyle = (int) (Math.random() * 3);
        hairStyleIndex = (int) (Math.random() * 3);
        skinR = (int) (Math.random() * 255);
        skinG = (int) (Math.random() * 255);
        skinB = (int) (Math.random() * 255);
        eyeR = (int) (Math.random() * 255);
        eyeG = (int) (Math.random() * 255);
        eyeB = (int) (Math.random() * 255);
        hairR = (int) (Math.random() * 255);
        hairG = (int) (Math.random() * 255);
        hairB = (int) (Math.random() * 255);

    }


    /**
     * Draws all facial features
     *
     * @param canvas - canvas face is drawn on
     *
     */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        skinColor = Color.rgb(skinR, skinG, skinB);
        eyeColor = Color.rgb(eyeR, eyeG, eyeB);
        hairColor = Color.rgb(hairR, hairG, hairB);

        //draws face background
        Paint skinPaint = new Paint();
        skinPaint.setColor(skinColor);
        canvas.drawOval(150.0f, 100.0f, 850.0f, 1100.0f, skinPaint);

        //eye options
        Paint eyePaint = new Paint();
        eyePaint.setColor(eyeColor);
        if (eyeStyle == 0) {
            canvas.drawRect(350, 300, 400, 350, eyePaint);
            canvas.drawRect(600, 300, 650, 350, eyePaint);
        } else if (eyeStyle == 1) {
            canvas.drawOval(350, 300, 400, 350, eyePaint);
            canvas.drawOval(600, 300, 650, 350, eyePaint);
        } else if (eyeStyle == 2) {
            canvas.drawArc(350, 300, 400, 350, 0, 180, false, eyePaint);
            canvas.drawArc(600, 300, 650, 350, 0, 180, false, eyePaint);
        }

        //hairstyles
        Paint hairPaint = new Paint();
        hairPaint.setColor(hairColor);
        if (hairStyleIndex == 0) {            //hair gelled up
            hairStyles[0] = new Path();
            hairStyles[0].moveTo(270, 210);
            hairStyles[0].lineTo(200, 50);
            hairStyles[0].lineTo(800, 50);
            hairStyles[0].lineTo(730, 210);
            hairStyles[0].moveTo(270, 210);
        } else if (hairStyleIndex == 1) {     //bowl cut
            hairStyles[1] = new Path();
            hairStyles[1].moveTo(300, 300);
            hairStyles[1].lineTo(700, 300);
            hairStyles[1].addArc(300, 100, 700, 300, 0, -180);
            hairStyles[1].moveTo(300, 300);
        } else if (hairStyleIndex == 2) {     //fohawk
            hairStyles[2] = new Path();
            hairStyles[2].moveTo(275, 200);
            hairStyles[2].lineTo(500, 50);
            hairStyles[2].lineTo(725, 200);
            hairStyles[2].moveTo(275, 200);

        }
        canvas.drawPath(hairStyles[hairStyleIndex], hairPaint); //draw hair

        //draws nose and smile without fill
        Paint blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        blackPaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(250, 500, 750, 900, 0, 180, false, blackPaint); //smile
        if (noseStyle == 0) {
            canvas.drawRect(475, 500, 525, 530, blackPaint);
        } else if (noseStyle == 1) {
            canvas.drawOval(475, 500, 525, 530, blackPaint);
        } else if (noseStyle == 2) {
            canvas.drawArc(475, 500, 525, 530, 0, 180, false, blackPaint);
        }


    }


}
