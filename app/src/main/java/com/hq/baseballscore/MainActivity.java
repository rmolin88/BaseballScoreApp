package com.hq.baseballscore;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "BaseballScoreApp";
  private static final int NumInnings = 9;
  private static final int NumStats = 3;
  private static final int InningTextSize = 64;
  private static final String[] sInningStatsNames = {"Ball", "Strike", "Out"};

  private static int[] iaGuestInningStats = new int[NumStats];
  private static int[] iaHomeInningStats = new int[NumStats];

  private static int[] iaGuestGameStats = new int[NumStats];
  private static int[] iaHomeGameStats = new int[NumStats];

  private static int[] iaGuestInningRuns = new int[NumInnings];
  private static int[] iaHomeInningRuns = new int[NumInnings];

  private static final float llInningsStatsWeight = 10/6;
  private LinearLayout llInningStats;
  private TextView[] tvaInningsStats = new TextView[NumStats*2];

  private TextView[] tvaGuestGameStats = new TextView[NumInnings];
  private TextView[] tvaHomeGameStats = new TextView[NumInnings];

  private TextView[] tvaGuestInningsRuns = new TextView[NumInnings];
  private TextView[] tvaHomeInningsRuns = new TextView[NumInnings];

  private class myTouchListener implements View.OnTouchListener {
    public boolean onTouch(View view, MotionEvent motionEvent) {
      if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
        // TODO Convert this down here to a function that decodes who got called
        // if (view.getId() == tvBalls.getId()) {
        // Log.i(TAG, view.getId() + " - Button Pressed!");
        // iBalls++;
        // tvBalls.setText("" + iBalls);
        // }
      }
      return false;
    }
  }

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.content_scrolling);

    llInningStats = (LinearLayout) findViewById(R.id.llInningStats);

    { // Set up Inning Stats
      LinearLayout.LayoutParams llparamsInningStats = new LinearLayout.LayoutParams(0,
              LinearLayout.LayoutParams.MATCH_PARENT, llInningsStatsWeight);


      for (int k=0; k < NumStats; k++) {
        tvaInningsStats[k].setLayoutParams(llparamsInningStats);
        tvaInningsStats[k+1].setLayoutParams(llparamsInningStats);

        tvaInningsStats[k].setGravity(Gravity.CENTER);
        tvaInningsStats[k+1].setGravity(Gravity.CENTER);

        tvaInningsStats[k].setText(sInningStatsNames[k]);
        tvaInningsStats[k+1].setText("0");
        tvaInningsStats[k+1].setTextSize(TypedValue.COMPLEX_UNIT_DIP, InningTextSize);

        tvaInningsStats[k].setTextColor(Color.CYAN);
        tvaInningsStats[k+1].setTextColor(Color.BLACK);

        if (Build.VERSION.SDK_INT < 23) {
          tvaInningsStats[k].setTextAppearance(this, android.R.style
                  .TextAppearance_Large);
          tvaInningsStats[k+1].setTextAppearance(this, android.R.style
                  .TextAppearance_Large);
        }
        else {
          tvaInningsStats[k].setTextAppearance(android.R.style
                  .TextAppearance_DeviceDefault_Large);
          tvaInningsStats[k+1].setTextAppearance(android.R.style
                  .TextAppearance_DeviceDefault_Large);
        }

        tvaInningsStats[k].setTypeface(Typeface.DEFAULT_BOLD);
        tvaInningsStats[k+1].setTypeface(Typeface.DEFAULT_BOLD);

        llInningStats.addView(tvaInningsStats[k]);
        llInningStats.addView(tvaInningsStats[k+1]);

      }
    }
  }

  // TODO: Crazy idea Change Guest and Home team names on touch
  // TODO: Give some color to the layout
  // TODO: Help menu
}
