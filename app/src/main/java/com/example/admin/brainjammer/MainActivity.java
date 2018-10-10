package com.example.admin.brainjammer;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button enterButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    TextView resultTextView;
    TextView pointsTextView;
    TextView sumtextView;
    TextView timerTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    ConstraintLayout gameConstraintLayout;
    ConstraintLayout startConstraintLayout;
   // GridLayout buttonGridLayout;


       public void playAgain(View view){

           score = 0;
           numberOfQuestions = 0;
           timerTextView.setText("30s");
           pointsTextView.setText("0/00");
           resultTextView.setText("");
           playAgainButton.setVisibility(View.INVISIBLE);
           button0.setEnabled(true);
           button1.setEnabled(true);
           button2.setEnabled(true);
           button3.setEnabled(true);
          // buttonGridLayout.setEnabled(true);
           generateQuestion();



           new CountDownTimer(30000+100, 1000) {
               @Override
               public void onTick(long millisUntilFinished) {


                   timerTextView.setText(String.valueOf(millisUntilFinished/1000 + "s"));
               }

               @Override
               public void onFinish() {
                   timerTextView.setText("0s");
                   resultTextView.setTextColor(Color.BLACK);
                   resultTextView.setText("Your Score is :" + Integer.toString(score));
                   Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                   button0.setEnabled(false);
                   button1.setEnabled(false);
                   button2.setEnabled(false);
                   button3.setEnabled(false);
                   // buttonGridLayout.setEnabled(false);
                   playAgainButton.setVisibility(View.VISIBLE);



               }
           }.start();

       }

    public void generateQuestion(){

        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        sumtextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationOfCorrectAnswer = random.nextInt(4);
           answers.clear();

        int incorrectAnswer;
        for(int i=0;i<4;i++) {
            if (locationOfCorrectAnswer == i) {
                answers.add(a + b);
            } else {

                incorrectAnswer = random.nextInt(41);

                while (incorrectAnswer == a + b) {

                    incorrectAnswer = random.nextInt(41);
                }

                answers.add(incorrectAnswer);


            }

        }
            button0.setText(Integer.toString(answers.get(0)));
            button1.setText(Integer.toString(answers.get(1)));
            button2.setText(Integer.toString(answers.get(2)));
            button3.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view){

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){

            score++;

            resultTextView.setTextColor(Color.GREEN);
            resultTextView.setText("Correct");

            Log.i("Correct" , "Correct");

        }else{
            resultTextView.setTextColor(Color.RED);
            resultTextView.setText("Wrong..!!!");
            Log.i("Wrong", "Wrong");
        }
          numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
          generateQuestion();

    }


    public void enterClicked(View view) {



        enterButton.setVisibility(View.INVISIBLE);
        gameConstraintLayout.setVisibility(ConstraintLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButtonId));


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterButton = (Button) findViewById(R.id.enterButtonId);
        sumtextView = (TextView) findViewById(R.id.sumId);
        resultTextView = (TextView) findViewById(R.id.correctTextViewId);
        pointsTextView = (TextView) findViewById(R.id.scoreTextViewId);
        timerTextView = (TextView) findViewById(R.id.timerTextViewId);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        playAgainButton = (Button) findViewById(R.id.playAgainButtonId) ;
        gameConstraintLayout = (ConstraintLayout) findViewById(R.id.gameConstraintLayout);
        startConstraintLayout = (ConstraintLayout) findViewById(R.id.startConstraintLayoutId);
       // buttonGridLayout = (GridLayout) findViewById(R.id.gridLayout1);


    }
}
