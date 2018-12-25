package com.rich007.quizzo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2;
    TextView question;
    ProgressBar pgBar;
    TextView score;
    private int progress = 0;
    private int scoreX = 0;
    private int currentID = 0;

    private HashMap<Integer, TrueFalse> data = new HashMap();

    final private TrueFalse[] arrayData = new TrueFalse[]{

            new TrueFalse(R.string.q1, "true"),
            new TrueFalse(R.string.q2, "true"),
            new TrueFalse(R.string.q3, "true"),
            new TrueFalse(R.string.q4, "true"),
            new TrueFalse(R.string.q5, "true"),
            new TrueFalse(R.string.q6, "true"),
            new TrueFalse(R.string.q7, "true"),
            new TrueFalse(R.string.q8, "true"),
            new TrueFalse(R.string.q9, "true"),
            new TrueFalse(R.string.q10, "true")
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            scoreX = savedInstanceState.getInt("score");
            currentID = savedInstanceState.getInt("id");

        } else {

            scoreX = 0;
            currentID = 0;

        }

        btn1 = findViewById(R.id.ans1);
        btn2 = findViewById(R.id.ans2);
        pgBar = findViewById(R.id.pgBar);
        score = findViewById(R.id.score);
        score.setText("Score " + scoreX + " / 10");

        //put it into map
        for (int i = 0; i < 10; i++) {
            data.put(i, arrayData[i]);
        }


        question = findViewById(R.id.question);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                btn1.setBackgroundResource(R.color.a2);
                btn2.setBackgroundResource(R.color.a_color);
                checkSet(v);


            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        btn2.setBackgroundResource(R.color.a2);
                                        btn1.setBackgroundResource(R.color.a_color);
                                        checkSet(v);

                                    }
                                }
        );


    }


    private void checkSet(View v) {

        Button b = (Button) v;
        if (b.getText().toString().toLowerCase().equals(data.get(currentID).getAns())) {
            scoreX += 1;

        }
//                Log.d("score" , b.getText().toString().toLowerCase());
//                Log.d("score1",data.get(currentID).getAns());
        currentID = new Random().nextInt(9) + 1;
        Toast.makeText(getApplicationContext(), "Good Choice!", Toast.LENGTH_SHORT).show();
        question.setText(getResources().getString(data.get(currentID).getqId()));

        progress += (progress == 100 ? -100 : 10);
        if (progress == 0) {
            score.setText(getResources().getString(R.string.score));
            showPopUp();
        } else {
            score.setText("Score " + scoreX + " / 10");
        }
        pgBar.setProgress(progress);

    }

    //create the dialog box
    public void showPopUp() {
        AlertDialog.Builder popUp = new AlertDialog.Builder(this);
        popUp.setTitle("Well Done!");
        popUp.setMessage("Thanks for playing. Leave a feedback so we can improve the user experience");
        popUp.setCancelable(false);

        popUp.setPositiveButton("Result", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });


        popUp.setNegativeButton("Feedback", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        AlertDialog alert = popUp.create();
        alert.show();


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("score", scoreX);
        outState.putInt("id", currentID);
    }
}
