package marston18.up.edu.facemakerbygiselle;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import marston18.up.edu.facemakerbygiselle.R;


/**
 * Main Activity Class to direct changes to face
 *
 * @author Giselle Marston
 * @version 2/11/16
 */
public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    Face face;
    protected TextView redText;
    protected SeekBar redSeek;
    protected TextView greenText;
    protected SeekBar greenSeek;
    protected TextView blueText;
    protected SeekBar blueSeek;
    protected RadioButton hairRadioB;
    protected RadioButton eyesRadioB;
    protected RadioButton skinRadioB;

    protected Spinner spinnerHair;
    protected String[] hairStyleOpt = {"Hair Gelled Up", "Bowl Cut", "Fohawk"};

    protected Spinner spinnerEyes;
    protected String[] eyeStyleOpt = {"Squares", "Circles", "Arcs"};

    protected Spinner spinnerNose;
    protected String[] noseStyleOpt = {"Rectangle", "Oval", "Arc"};

    protected Button randomButton;
    protected SurfaceView newFace;

    protected boolean isHairClicked;
    protected boolean isEyesClicked;
    protected boolean isSkinClicked;

    int r, g, b;


    /**
     * Sets up components of Face Maker and listeners
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        face = (Face) findViewById(R.id.surfaceView);
        redText = (TextView) findViewById(R.id.textViewRed);
        redSeek = (SeekBar) findViewById(R.id.seekBarRed);
        redSeek.setOnSeekBarChangeListener(this);
        redSeek.setMax(255); //seek bars go from 0-255
        greenText = (TextView) findViewById(R.id.textViewGreen);
        greenSeek = (SeekBar) findViewById(R.id.seekBarGreen);
        greenSeek.setOnSeekBarChangeListener(this);
        greenSeek.setMax(255);
        blueText = (TextView) findViewById(R.id.textViewBlue);
        blueSeek = (SeekBar) findViewById(R.id.seekBarBlue);
        blueSeek.setOnSeekBarChangeListener(this);
        blueSeek.setMax(255);

        hairRadioB = (RadioButton) findViewById(R.id.radioButtonHair);
        eyesRadioB = (RadioButton) findViewById(R.id.radioButtonEyes);
        skinRadioB = (RadioButton) findViewById(R.id.radioButtonSkin);


        /**
         External Citation
         Date: 11 February 2016
         Problem: Could not get the selection from spinners to change face
         Resource:
         http://stackoverflow.com/questions/1337424/android-
         spinner-get-the-selected-item-change-event
         Solution: I used the example code from this post.
         */

        spinnerHair = (Spinner) findViewById(R.id.spinnerHair);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, hairStyleOpt);
        spinnerHair.setAdapter(stringArrayAdapter);

        spinnerHair.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                       int position, long id) {
                face.hairStyleIndex = spinnerHair.getSelectedItemPosition();
                face.invalidate(); //make changes to face
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        spinnerEyes = (Spinner) findViewById(R.id.spinnerEyes);
        ArrayAdapter<String> stringArrayAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, eyeStyleOpt);
        spinnerEyes.setAdapter(stringArrayAdapter2);

        spinnerEyes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                       int position, long id) {
                face.eyeStyle = spinnerEyes.getSelectedItemPosition();
                face.invalidate(); //make changes to face
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });


        spinnerNose = (Spinner) findViewById(R.id.spinnerNose);
        ArrayAdapter<String> stringArrayAdapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, noseStyleOpt);
        spinnerNose.setAdapter(stringArrayAdapter3);

        spinnerNose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                       int position, long id) {
                face.noseStyle = spinnerNose.getSelectedItemPosition();
                face.invalidate(); //make changes to face
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        randomButton = (Button) findViewById(R.id.buttonRandom);
        randomButton.setOnClickListener(this);
        newFace = (SurfaceView) findViewById(R.id.surfaceView);

    }

    /**
     * Sets progress according to color of skin, hair, or eyes
     * based on which radio button is clicked.
     * Separates color into RGB values.
     *
     * @param v - view depending on which radio button has been clicked
     */
    public void onRadioButtonClick(View v) {

        if (v.getId() == R.id.radioButtonHair) {
            isHairClicked = true;
            isEyesClicked = false;
            isSkinClicked = false;
            r = Color.red(face.hairColor);
            g = Color.green(face.hairColor);
            b = Color.blue(face.hairColor);
        } else if (v.getId() == R.id.radioButtonEyes) {
            isHairClicked = false;
            isEyesClicked = true;
            isSkinClicked = false;
            r = Color.red(face.eyeColor);
            g = Color.green(face.eyeColor);
            b = Color.blue(face.eyeColor);
        } else if (v.getId() == R.id.radioButtonSkin) {
            isHairClicked = false;
            isEyesClicked = false;
            isSkinClicked = true;
            r = Color.red(face.skinColor);
            g = Color.green(face.skinColor);
            b = Color.blue(face.skinColor);
        }
        redSeek.setProgress(r);
        greenSeek.setProgress(g);
        blueSeek.setProgress(b);
    }


    /**
     * Randomizes facial features and colors
     *
     * @param v - view to check for Random button
     *          being clicked
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonRandom) {
            face.randomize();
            face.invalidate();//make changes to face
        }
    }


    /**
     * Displays progress from seek bar and
     * sets color of skin, hair, or eyes based on progress
     *
     * @param seekBar  - seek bar whose progress has changed
     * @param progress number between 0-255 for RGB values
     * @param fromUser - not used
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


        if (isHairClicked) {
            if (seekBar == redSeek) {
                redText.setText("" + progress);
                face.hairR = progress;
            } else if (seekBar == greenSeek) {
                greenText.setText("" + progress);
                face.hairG = progress;
            } else if (seekBar == blueSeek) {
                blueText.setText("" + progress);
                face.hairB = progress;
            }

        } else if (isSkinClicked) {
            if (seekBar == redSeek) {
                redText.setText("" + progress);
                face.skinR = progress;
            } else if (seekBar == greenSeek) {
                greenText.setText("" + progress);
                face.skinG = progress;
            } else if (seekBar == blueSeek) {
                blueText.setText("" + progress);
                face.skinB = progress;
            }

        } else if (isEyesClicked) {
            if (seekBar == redSeek) {
                redText.setText("" + progress);
                face.eyeR = progress;
            } else if (seekBar == greenSeek) {
                greenText.setText("" + progress);
                face.eyeG = progress;
            } else if (seekBar == blueSeek) {
                blueText.setText("" + progress);
                face.eyeB = progress;
            }
        }

        face.invalidate(); //make changes to face


    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
            //not in use
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
            //not in use
    }

}

