package com.example.android.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /* counting variable to manage layout views */
    int questionNum = 0;

    /* score variables */
    int answer1store;
    int answer2store;
    int answer3store;
    int answer4store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /* actions for button IDs next and displayScore */
    public void nextButton(View view) {

        /* page layout viewer variable - increase */
        ++questionNum;

        /* reads questionNum variable to determine which page layout to show next.
        *  Checks answers and stores 1 in global variable answerXstore if correct*/
        if (questionNum == 1) {
            setContentView(R.layout.question1);

        } else if (questionNum == 2) {
            RadioButton answer1 = (RadioButton) findViewById(R.id.radio_q1C);
            if (answer1.isChecked()) {
                answer1store = 1;
            } else {
                answer1store = 0;
            }
            setContentView(R.layout.question2);

        } else if (questionNum == 3) {
            RadioButton answer2 = (RadioButton) findViewById(R.id.radio_q2B);
            if (answer2.isChecked()) {
                answer2store = 1;
            } else {
                answer2store = 0;
            }
            setContentView(R.layout.question3);

            /*********************************** empty EditText error here **********************************/
        } else if (questionNum == 4) {
            EditText answer3 = (EditText) findViewById(R.id.q3_input);
            String answer3string = answer3.getText().toString();
            int a3int = Integer.parseInt(answer3string);

            if (answer3string == null || answer3string.equals("")) {
                Toast empty = Toast.makeText(this,"Toast", Toast.LENGTH_SHORT);
                empty.show();
                setContentView(R.layout.question4);
            } else if (a3int == 1) {
                answer3store = 1;
            } else {
                answer3store = 0;
            }
            setContentView(R.layout.question4);

        } else if (questionNum == 5)

        {
            CheckBox answer4A = (CheckBox) findViewById(R.id.checkboxA);
            CheckBox answer4B = (CheckBox) findViewById(R.id.checkboxB);
            CheckBox answer4C = (CheckBox) findViewById(R.id.checkboxC);
            CheckBox answer4D = (CheckBox) findViewById(R.id.checkboxD);
            if (!answer4A.isChecked() && answer4B.isChecked() && answer4C.isChecked() && !answer4D.isChecked()) {
                answer4store = 1;
            } else {
                answer4store = 0;
            }
            setContentView(R.layout.score);
        } else

        {
            /* method to calculate and display score*/
            displayScore();

        }

    }

    /* allows user to view previous questions */

    public void backButton(View view) {

        /* page layout viewer variable - decrease */
        --questionNum;

        /* reads questionNum variable to determine which page layout to show */
        if (questionNum == 0) {
            setContentView(R.layout.activity_main);
        } else if (questionNum == 1) {
            setContentView(R.layout.question1);
        } else if (questionNum == 2) {
            setContentView(R.layout.question2);
        } else if (questionNum == 3) {
            setContentView(R.layout.question3);
        } else if (questionNum == 4) {
            setContentView(R.layout.question4);
        }
    }

    /* calculates and displays score */
    public void displayScore() {

        /* sums answer values */
        int score = answer1store + answer2store + answer3store + answer4store;

        /* calculates percentage correct */
        int percent = 100 * score / 4;

        /* creates output message to display */
        String scoreMessage = score + " out of 4 = " + percent + "%";

        if (score == 0) {
            scoreMessage += "\nDoodoo Brown :(";
        } else if (score == 1) {
            scoreMessage += "\nEnglish Major";
        } else if (score == 2) {
            scoreMessage += "\nPretty Good";
        } else if (score == 3) {
            scoreMessage += "\nSupagood";
        } else {
            scoreMessage += "\nMathematical Genius!";
        }

        /* center Toast Text */

        Spannable centerToastText = new SpannableString(scoreMessage);
        centerToastText.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
                0, centerToastText.length() - 1,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);

         /* display message on device */
        Toast displayScore = Toast.makeText(this, centerToastText, Toast.LENGTH_LONG);
        displayScore.show();

        /* change textview text */
        TextView madeIt = (TextView) findViewById(R.id.scoreHeader);
        String finalScore = "SCORE";
        madeIt.setText(finalScore);
    }



    /* Warnings that I couldn't resolve
    09-14 06:05:46.466 16354-16354/com.example.android.quizapp W/System: ClassLoader referenced unknown path: /data/app/com.example.android.quizapp-1/lib/arm
    09-14 06:05:46.846 16354-16354/com.example.android.quizapp W/art: Before Android 4.1, method android.graphics.PorterDuffColorFilter android.support.graphics.drawable.VectorDrawableCompat.updateTintFilter(android.graphics.PorterDuffColorFilter, android.content.res.ColorStateList, android.graphics.PorterDuff$Mode) would have incorrectly overridden the package-private method in android.graphics.drawable.Drawable
    Unable to open '/system/framework/com.qti.location.sdk.jar': No such file or directory
    09-14 06:05:40.096 16354-16354/com.example.android.quizapp W/art: Failed to open zip archive '/system/framework/com.qti.location.sdk.jar': I/O Error
     */
}