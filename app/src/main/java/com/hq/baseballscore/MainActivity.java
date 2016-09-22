package com.hq.baseballscore;

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
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "BaseballScoreApp";
  private static final int NumInnings = 9;
  private static final int NumStats = 6;
  private static final int InningTextSize = 64;
  private static final String[] sInningStatsNames = {"Ball", "Strike", "Out"};
  private static final float llInningsStatsWeight = (float) 0.16;

  private static int[] iaGuestInningStats = new int[NumStats];
  private static int[] iaHomeInningStats = new int[NumStats];

  private static int[] iaGuestGameStats = new int[NumStats];
  private static int[] iaHomeGameStats = new int[NumStats];

  private static int[] iaGuestInningRuns = new int[NumInnings];
  private static int[] iaHomeInningRuns = new int[NumInnings];

  private LinearLayout llInningStats;
  private TextView[] tvaInningsStats = new TextView[NumStats];

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

    for (int k = 0, k1 = 0; k < NumStats; k++) {
      tvaInningsStats[k] = new TextView(this);

      // Even are words: balls, str....
      tvaInningsStats[k].setId(k);
      tvaInningsStats[k]
          .setLayoutParams(
              new LinearLayout.LayoutParams(
                  0, LinearLayout.LayoutParams.MATCH_PARENT, llInningsStatsWeight));
      tvaInningsStats[k].setGravity(Gravity.CENTER);
      if (Build.VERSION.SDK_INT < 23) {
        tvaInningsStats[k].setTextAppearance(this, android.R.style.TextAppearance_Large);
      } else {
        tvaInningsStats[k].setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Large);
      }

      if ((k & 1) == 1) { // Odd are values
        tvaInningsStats[k].setText("0");
        tvaInningsStats[k].setTextSize(TypedValue.COMPLEX_UNIT_DIP, InningTextSize);
        tvaInningsStats[k].setTextColor(Color.BLACK);
      } else {
        tvaInningsStats[k].setText(sInningStatsNames[k1++]);
        tvaInningsStats[k].setTypeface(Typeface.DEFAULT_BOLD);
        tvaInningsStats[k].setTextColor(Color.CYAN);
      }

      llInningStats.addView(tvaInningsStats[k]);
    }
  }

  // TODO: Crazy idea Change Guest and Home team names on touch
  // TODO: Give some color to the layout
  // TODO: Help menu
}
