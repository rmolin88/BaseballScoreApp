package com.hq.baseballscore;

// File:					GameStats.java
// Description:		Classes to show Runs, Hits and Errors
// Author:				Reinaldo Molina
// Version:				0.1.8
// Date:					Thu Sep 29 2016 09:12

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TableRow;

import java.util.ArrayList;

class GameStatsWrapper {
  private GameStats gameStats;
  private InningStats inningStats;

  GameStatsWrapper(Activity activity_, Context context_) {
    gameStats = new GameStats(activity_, context_);
    inningStats = new InningStats(activity_, context_);
  }

  void UpdateFromInnings(GenTextView view) {
    // if (iWho == 1) gameStats.tvHomeRuns.Update();
    // else if (iWho == 0) {
      // gameStats.tvGuestRuns.Update();
    // } else throw new RuntimeException("Passed unrecognized value to function");
  }

  void UpdateFromGameStats(GenTextView view) {
    // TODO: Figure out how to determine if Correct team is at bat

  }

  private class InningsTouchListener implements View.OnTouchListener {
    public boolean onTouch(View view, MotionEvent motionEvent) {
      if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) { // If user pressed view
        UpdateFromInnings((GenTextView) view);
      }
      return false;
    }
  }

  private class GameStatsTouchListener implements View.OnTouchListener {
    public boolean onTouch(View view, MotionEvent motionEvent) {
      if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) { // If user pressed view
        UpdateFromGameStats((GenTextView) view);
      }
      return false;
    }
  }
}

class GameStats {
  private static final int NUM_ELEMENTS = 3;

  GenTextView tvGuestRuns;
  GenTextView tvHomeRuns;

  private Activity activity;

  GameStats(Activity activity_, Context context_) {
    activity = activity_;

    TableRow llGuestGameStats = (TableRow) activity_.findViewById(R.id.trTable1Row1);
    TableRow llHomeGameStats = (TableRow) activity_.findViewById(R.id.trTable1Row2);

    for (int k = 0; k < NUM_ELEMENTS; k++) {
      // Set first Guest elements
      GenTextView tvGuest = new GenTextView(context_);
      GenTextView tvHome = new GenTextView(context_);

      tvGuest.setReset(10000); // Reset value like this so that it never happens
      tvHome.setReset(10000); // Reset value like this so that it never happens

      tvGuest.setGravity(Gravity.CENTER);
      tvHome.setGravity(Gravity.CENTER);

      // Set appearance
      if (Build.VERSION.SDK_INT < 23) {
        tvGuest.setTextAppearance(activity, android.R.style.TextAppearance_DeviceDefault_Large);
      } else {
        tvGuest.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Large);
      }
      if (Build.VERSION.SDK_INT < 23) {
        tvHome.setTextAppearance(activity, android.R.style.TextAppearance_DeviceDefault_Large);
      } else {
        tvHome.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Large);
      }

      tvGuest.setText("0");
      tvHome.setText("0");

      tvGuest.setTextColor(Color.BLACK);
      tvHome.setTextColor(Color.BLACK);

      // TODO: how to create outter class OnTouchListener from here
      tvGuest.setOnTouchListener(new GameStatsWrapper.
      tvHome.setOnTouchListener(new GameStatsTouchListener());

      if (k == 0) { // Set pointers to important TextViews
        tvGuestRuns = tvGuest;
        tvHomeRuns = tvHome;
      }

      llGuestGameStats.addView(tvGuest);
      llHomeGameStats.addView(tvHome);
    }
  }
}

class InningStats {
  private ArrayList<GenTextView> alGuestInnStats;
  private ArrayList<GenTextView> alHomeInnStats;
  private static final int NUM_ELEMENTS = 9;

  private Activity activity;

  private GenTextView tvCurrInn;

  InningStats(Activity activity_, Context context_) {
    activity = activity_;

    alGuestInnStats = new ArrayList<>(NUM_ELEMENTS);
    alHomeInnStats = new ArrayList<>(NUM_ELEMENTS);

    TableRow llGuestInnStats = (TableRow) activity_.findViewById(R.id.trTable0Row1);
    TableRow llHomeInnStats = (TableRow) activity_.findViewById(R.id.trTable0Row2);

    for (int k = 0; k < NUM_ELEMENTS; k++) {
      GenTextView tvGuest = new GenTextView(context_);
      GenTextView tvHome = new GenTextView(context_);

      tvGuest.setReset(10000); // Reset value like this so that it never happens
      tvHome.setReset(10000); // Reset value like this so that it never happens

      tvGuest.setGravity(Gravity.CENTER);
      tvHome.setGravity(Gravity.CENTER);

      // Set appearance
      if (Build.VERSION.SDK_INT < 23) {
        tvGuest.setTextAppearance(activity, android.R.style.TextAppearance_DeviceDefault_Large);
      } else {
        tvGuest.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Large);
      }
      if (Build.VERSION.SDK_INT < 23) {
        tvHome.setTextAppearance(activity, android.R.style.TextAppearance_DeviceDefault_Large);
      } else {
        tvHome.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Large);
      }

      tvGuest.setTextColor(Color.BLACK);
      tvHome.setTextColor(Color.BLACK);

      tvGuest.setOnTouchListener(new InningsTouchListener());
      tvHome.setOnTouchListener(new InningsTouchListener());

      // Set text only for the first inning and set as curr inning
      if (k == 0) {
        tvGuest.setText("0");
        tvCurrInn = tvGuest;
      }

      alGuestInnStats.add(tvGuest);
      alHomeInnStats.add(tvHome);

      llGuestInnStats.addView(tvGuest);
      llHomeInnStats.addView(tvHome);
    }
  }

  private void UpdateView(GenTextView view) {
    if (view == tvCurrInn) {
      view.Update();
      // Update Runs on Table1 as well
      if (alGuestInnStats.contains(view)) super.UpdateFromInnings(0);
      else UpdateFromInnings(1);
    }
  }
}
