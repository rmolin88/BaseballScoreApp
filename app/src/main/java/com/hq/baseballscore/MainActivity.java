package com.hq.baseballscore;

import android.app.Activity;
import android.content.Context;
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
import android.widget.TableRow;
import android.widget.TextView;

import static com.hq.baseballscore.AppGlobals.COLOR_BLUE;
import static com.hq.baseballscore.AppGlobals.INN_STATS_NAMES;
import static com.hq.baseballscore.AppGlobals.INN_TEXT_SIZE;
import static com.hq.baseballscore.AppGlobals.NUM_GAME_STATS;
import static com.hq.baseballscore.AppGlobals.NUM_INNINGS;
import static com.hq.baseballscore.AppGlobals.NUM_STATS;


public class MainActivity extends AppCompatActivity {

  // tvaInningsStats array index

  // tvaHome/GuestGameStats array index
  private static final int INDEX_RUNS = 0;
  private static final int INDEX_HITS = 1;
  private static final int INDEX_ERRORS = 2;

  private static final float llInningsStatsWeight = (float) 1 / 6;
  // private static int iBalls = 0;
  // private static int iStrikes = 0;
  // private static int iOuts = 0;

  // Runs, Hits, Errors
  private static int[] iaGuestGameStats = new int[NUM_STATS];
  private static int[] iaHomeGameStats = new int[NUM_STATS];

  // Each inning num. of runs
  private static int[] iaGuestInningRuns = new int[NUM_INNINGS];
  private static int[] iaHomeInningRuns = new int[NUM_INNINGS];

  private TextView[] tvaInningsStats = new TextView[NUM_STATS];

  private TextView[] tvaGuestGameStats = new TextView[NUM_INNINGS];
  private TextView[] tvaHomeGameStats = new TextView[NUM_INNINGS];

  private TextView[] tvaGuestInningsRuns = new TextView[NUM_INNINGS];
  private TextView[] tvaHomeInningsRuns = new TextView[NUM_INNINGS];

  private InningStats inningStats;

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.content_scrolling);

    inningStats = new InningStats(this, this);


//    LinearLayout llInningStats = (LinearLayout) findViewById(R.id.llInningStats);
//    TableRow trTable1Row1 = (TableRow) findViewById(R.id.trTable1Row1);
//    TableRow trTable1Row2 = (TableRow) findViewById(R.id.trTable1Row2);
//    TableRow trTable0Row1 = (TableRow) findViewById(R.id.trTable0Row1);
//    TableRow trTable0Row2 = (TableRow) findViewById(R.id.trTable0Row2);

//    View.OnTouchListener otlBalls = new BallsTouchListener();
    //    View.OnTouchListener otlStrikes = new StrikesTouchListener();
//    View.OnTouchListener otlOuts = new OutsTouchListener();

//    for (int
//            k = 0, k1 = 0, k2 = NUM_INNINGS, k3 = NUM_INNINGS * 2, k4 = NUM_INNINGS * 3, k5 =
//                NUM_INNINGS * 4;
//        k < NUM_INNINGS;
//        k++) {
//      // Setting up division 1
//      // TODO: substitute all this with just instantiation of InningStats class
//      // Move all the shared properties to style
//      // Move all the creation of the views to InningStats constructor
//      if (k < NUM_STATS) {
//        tvaInningsStats[k] = new TextView(this);
//
//        // Even are words: balls, str....
//        tvaInningsStats[k].setId(k);
//        tvaInningsStats[k]
//            .setLayoutParams(
//                new LinearLayout.LayoutParams(
//                    0, LinearLayout.LayoutParams.MATCH_PARENT, llInningsStatsWeight));
//        tvaInningsStats[k].setGravity(Gravity.CENTER);
//        if (Build.VERSION.SDK_INT < 23) {
//          tvaInningsStats[k].setTextAppearance(this, android.R.style.TextAppearance_Large);
//        } else {
//          tvaInningsStats[k].setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Large);
//        }
//
//        if ((k & 1) == 1) { // Odd are values
//          tvaInningsStats[k].setText("0");
//          tvaInningsStats[k].setTextSize(TypedValue.COMPLEX_UNIT_DIP, INN_TEXT_SIZE);
//          tvaInningsStats[k].setTextColor(Color.BLACK);
////          if (k == INDEX_BALLS) tvaInningsStats[k].setOnTouchListener(new BallsTouchListener());
//          // Todo fix TouchListener here when you create the strikes and outs
////          else if (k == INDEX_STRIKES)
////            tvaInningsStats[k].setOnTouchListener(new StrikesTouchListener(tvaInningsStats[k]));
////          else tvaInningsStats[k].setOnTouchListener(new OutsTouchListener());
//        } else {
//          tvaInningsStats[k].setText(INN_STATS_NAMES[k1++]);
//          tvaInningsStats[k].setTypeface(Typeface.DEFAULT_BOLD);
//          tvaInningsStats[k].setTextColor(Color.parseColor(COLOR_BLUE));
//        }
//        llInningStats.addView(tvaInningsStats[k]);
//      }
//
//      if (k < NUM_GAME_STATS) {
//        // Setting up Table1.Row1
//        tvaGuestGameStats[k] = new TextView(this);
//
//        tvaGuestGameStats[k].setId(k2++);
//        tvaGuestGameStats[k].setGravity(Gravity.CENTER);
//        if (Build.VERSION.SDK_INT < 23) {
//          tvaGuestGameStats[k].setTextAppearance(this, android.R.style.TextAppearance_Large);
//        } else {
//          tvaGuestGameStats[k]
//              .setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Large);
//        }
//        tvaGuestGameStats[k].setText("0");
//        tvaGuestGameStats[k].setTypeface(Typeface.DEFAULT_BOLD);
//        tvaGuestGameStats[k].setTextColor(Color.BLACK);
//
//        trTable1Row1.addView(tvaGuestGameStats[k]);
//
//        // Setting up Table1.Row2
//        tvaHomeGameStats[k] = new TextView(this);
//
//        tvaHomeGameStats[k].setId(k3++);
//        tvaHomeGameStats[k].setGravity(Gravity.CENTER);
//        if (Build.VERSION.SDK_INT < 23) {
//          tvaHomeGameStats[k].setTextAppearance(this, android.R.style.TextAppearance_Large);
//        } else {
//          tvaHomeGameStats[k].setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Large);
//        }
//        tvaHomeGameStats[k].setText("0");
//        tvaHomeGameStats[k].setTypeface(Typeface.DEFAULT_BOLD);
//        tvaHomeGameStats[k].setTextColor(Color.BLACK);
//
//        trTable1Row2.addView(tvaHomeGameStats[k]);
//      }
//
//      // Setting up Table0.Row2
//      tvaGuestInningsRuns[k] = new TextView(this);
//
//      tvaGuestInningsRuns[k].setId(k4++);
//      tvaGuestInningsRuns[k].setGravity(Gravity.CENTER);
//      if (Build.VERSION.SDK_INT < 23) {
//        tvaGuestInningsRuns[k].setTextAppearance(this, android.R.style.TextAppearance_Large);
//      } else {
//        tvaGuestInningsRuns[k]
//            .setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Large);
//      }
//      if (k == 0) tvaGuestInningsRuns[k].setText("0");
//      tvaGuestInningsRuns[k].setTypeface(Typeface.DEFAULT_BOLD);
//      tvaGuestInningsRuns[k].setTextColor(Color.BLACK);
//      trTable0Row1.addView(tvaGuestInningsRuns[k]);
//
//      // Setting up Table0.Row1
//      tvaHomeInningsRuns[k] = new TextView(this);
//
//      tvaHomeInningsRuns[k].setId(k5++);
//      tvaHomeInningsRuns[k].setGravity(Gravity.CENTER);
//      if (Build.VERSION.SDK_INT < 23) {
//        tvaHomeInningsRuns[k].setTextAppearance(this, android.R.style.TextAppearance_Large);
//      } else {
//        tvaHomeInningsRuns[k].setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Large);
//      }
//      //      tvaHomeInningsRuns[k].setText("0");
//      tvaHomeInningsRuns[k].setTypeface(Typeface.DEFAULT_BOLD);
//      tvaHomeInningsRuns[k].setTextColor(Color.BLACK);
//      trTable0Row2.addView(tvaHomeInningsRuns[k]);
////    }
  }

  // TODO: Crazy idea Change Guest and Home team names on touch
  // TODO: Give some color to the layout
  // TODO: Help menu
  //
}
