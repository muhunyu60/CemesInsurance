package com.cemesinsurance.cemes_customer;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class Web1920_12 extends AppCompatActivity {

    CircularProgressBar circularProgressBar, circularProgressBar1, circularProgressBar2, circularProgressBar3, circularProgressBar4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web1920_12);


        CircularProgressBar circularProgressBar = (CircularProgressBar) findViewById(R.id.yourCircularProgressbar);
        circularProgressBar.setColor(ContextCompat.getColor(this, R.color.progressBarColor));
        circularProgressBar.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundProgressBarColor));
        circularProgressBar.setProgressBarWidth(getResources().getDimension(R.dimen.progressBarWidth));
        circularProgressBar.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.backgroundProgressBarWidth));
        int animationDuration = 2500; // 2500ms = 2,5s
        circularProgressBar.setProgressWithAnimation(65, animationDuration); // Default duration = 1500ms


        CircularProgressBar circularProgressBar1 = (CircularProgressBar) findViewById(R.id.yourCircularProgressbar1);
        circularProgressBar1.setColor(ContextCompat.getColor(this, R.color.progressBarColor1));
        circularProgressBar1.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundProgressBarColor));
        circularProgressBar1.setProgressBarWidth(getResources().getDimension(R.dimen.progressbarwidth));
        circularProgressBar1.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.backgroundprogressbarwidth));
        circularProgressBar1.setProgressWithAnimation(75, animationDuration); // Default duration = 1500ms


        CircularProgressBar circularProgressBar2 = (CircularProgressBar) findViewById(R.id.yourCircularProgressbar2);
        circularProgressBar2.setColor(ContextCompat.getColor(this, R.color.progressBarColor1));
        circularProgressBar2.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundProgressBarColor));
        circularProgressBar2.setProgressBarWidth(getResources().getDimension(R.dimen.progressbarwidth));
        circularProgressBar2.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.backgroundprogressbarwidth));
        circularProgressBar2.setProgressWithAnimation(50, animationDuration); // Default duration = 1500ms


        CircularProgressBar circularProgressBar3 = (CircularProgressBar) findViewById(R.id.yourCircularProgressbar3);
        circularProgressBar3.setColor(ContextCompat.getColor(this, R.color.progressBarColor1));
        circularProgressBar3.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundProgressBarColor));
        circularProgressBar3.setProgressBarWidth(getResources().getDimension(R.dimen.progressbarwidth));
        circularProgressBar3.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.backgroundprogressbarwidth));
        circularProgressBar3.setProgressWithAnimation(65, animationDuration); // Default duration = 1500ms

        CircularProgressBar circularProgressBar4 = (CircularProgressBar) findViewById(R.id.yourCircularProgressbar4);
        circularProgressBar4.setColor(ContextCompat.getColor(this, R.color.progressBarColor1));
        circularProgressBar4.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundProgressBarColor));
        circularProgressBar4.setProgressBarWidth(getResources().getDimension(R.dimen.progressbarwidth));
        circularProgressBar4.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.backgroundprogressbarwidth));
        circularProgressBar4.setProgressWithAnimation(40, animationDuration); // Default duration = 1500ms


    }
}
