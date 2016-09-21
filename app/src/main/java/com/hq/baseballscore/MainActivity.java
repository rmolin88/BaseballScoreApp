package com.hq.baseballscore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "BaseballScoreApp";
  private static final int NumInnings = 9;

  private static int iBalls = 0;
  private static int iStrikes = 0;
  private static int iOuts = 0;

  // Team scores
  private static int iGuestRuns = 0;
  private static int iGuestHits = 0;
  private static int iGuestErrors = 0;

  private static int iHomeRuns = 0;
  private static int iHomeHits = 0;
  private static int iHomeErrors = 0;

  // Inning Scores
  private static int[] iaGuestInningRuns = new int[NumInnings];
  private static int[] iaHomeInningRuns = new int[NumInnings];

  private TextView tvBalls;
  private TextView tvStrikes;
  private TextView tvOuts;

  private TextView tvGuestRuns;
  private TextView tvGuestHits;
  private TextView tvGuestErrors;

  private TextView tvHomeRuns;
  private TextView tvHomeHits;
  private TextView tvHomeErrors;

  private TextView[] tvaGuestInningsRuns = new TextView[NumInnings];
  private TextView[] tvaHomeInningsRuns = new TextView[NumInnings];

  private class myTouchListener implements View.OnTouchListener {
    public boolean onTouch(View view, MotionEvent motionEvent) {
      if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
        // TODO Convert this down here to a function that decodes who got called
        if (view.getId() == tvBalls.getId()) {
          Log.i(TAG, view.getId() + " - Button Pressed!");
          iBalls++;
          tvBalls.setText("" + iBalls);
        }
      }
      return false;
    }
  }

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.content_scrolling);

    // Initialize all TextViews with their listeners
    tvBalls = (TextView) findViewById(R.id.tvBallsValue);
    tvBalls.setOnTouchListener(new myTouchListener());

    tvStrikes = (TextView) findViewById(R.id.tvStrikesValue);
    tvStrikes.setOnTouchListener(new myTouchListener());

    tvOuts = (TextView) findViewById(R.id.tvOutsValue);
    tvOuts.setOnTouchListener(new myTouchListener());

    tvGuestRuns = (TextView) findViewById(R.id.tvGuestRuns);
    tvGuestRuns.setOnTouchListener(new myTouchListener());

    tvGuestHits = (TextView) findViewById(R.id.tvGuestHits);
    tvGuestHits.setOnTouchListener(new myTouchListener());

    tvGuestErrors = (TextView) findViewById(R.id.tvGuestErrors);
    tvGuestErrors.setOnTouchListener(new myTouchListener());

    tvHomeRuns = (TextView) findViewById(R.id.tvHomeRuns);
    tvHomeRuns.setOnTouchListener(new myTouchListener());

    tvHomeHits = (TextView) findViewById(R.id.tvHomeHits);
    tvHomeHits.setOnTouchListener(new myTouchListener());

    tvHomeErrors = (TextView) findViewById(R.id.tvHomeErrors);
    tvHomeErrors.setOnTouchListener(new myTouchListener());

    {
      int iId;
      String sId;
      for (int k=0; k < NumInnings; k++){
        
      }

    }

  }

  // TODO: Crazy idea Change Guest and Home team names on touch
  // TODO: Give some color to the layout
  // TODO: Help menu
}
