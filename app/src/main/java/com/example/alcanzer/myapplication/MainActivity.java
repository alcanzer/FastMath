package com.example.alcanzer.myapplication;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView sumText, corrext, total, timeLeft;
    Button goBtn, opt1, opt2, opt3, opt4, playAgn;
    GridLayout gridLayout;
    int location, score, numberQ;
    ArrayList<Integer> arrayList;
    RelativeLayout relativeLayout;
    public void start(View v){
        goBtn.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goBtn = (Button) findViewById(R.id.startBtn);
        sumText = (TextView) findViewById(R.id.questionText);
        corrext = (TextView) findViewById(R.id.TextView);
        total = (TextView) findViewById(R.id.scoreText);
        arrayList = new ArrayList<>();
        opt1 = (Button) findViewById(R.id.button1);
        opt2 = (Button) findViewById(R.id.button2);
        opt3 = (Button) findViewById(R.id.button3);
        opt4 = (Button) findViewById(R.id.button4);
        timeLeft = (TextView) findViewById(R.id.TimeText);
        playAgn = (Button) findViewById(R.id.playAgn);
        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        relativeLayout = (RelativeLayout) findViewById(R.id.gameLayout);
        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setVisibility(View.VISIBLE);
                generate();
                playAgain(playAgn);
                goBtn.setVisibility(View.GONE);
            }
        });
    }

    public void correctAnswer(View v){
        if (v.getTag().toString().equals(Integer.toString(location))){
            corrext.setText("Correct!");
            score++;
        }
        else{
            corrext.setText("Wrong!");
        }
        numberQ++;
        total.setText(score+"/"+numberQ);
        generate();
    }
    public void generate(){
        arrayList.clear();
        Random random = new Random();
        int a = random.nextInt(40);
        int b = random.nextInt(44);
        sumText.setText(a+"+"+b);
        location = random.nextInt(4);
        int incorrect;
        for(int i = 0; i<4; i++){
            if(i == location){
                arrayList.add(a+b);
            }
            else {
                incorrect = random.nextInt(85);
                while (incorrect == a+b){
                    incorrect = random.nextInt(85);
                }
                arrayList.add(incorrect);
            }
        }
        opt1.setText(arrayList.get(0)+"");
        opt2.setText(arrayList.get(1)+"");
        opt3.setText(arrayList.get(2)+"");
        opt4.setText(arrayList.get(3)+"");

    }

    public void playAgain(View v){
        score = 0;
        numberQ = 0;
        timeLeft.setText("30s");
        total.setText("0/0");
        playAgn.setVisibility(View.GONE);
        gridLayout.setVisibility(View.VISIBLE);
        new CountDownTimer(31000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft.setText(millisUntilFinished/1000+"s");
            }

            @Override
            public void onFinish() {
                timeLeft.setText("0s");
                corrext.setText("You got a score of "+score+"/"+numberQ);
                playAgn.setVisibility(View.VISIBLE);
                gridLayout.setVisibility(View.GONE);
            }
        }.start();
    }
}
