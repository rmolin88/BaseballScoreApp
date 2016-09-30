package com.hq.baseballscore;

// File:					GameStats.java
// Description:		Classes to show Runs, Hits and Errors
// Author:				Reinaldo Molina
// Version:				0.0.8
// Date:					Thu Sep 29 2016 09:12

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TableRow;

import java.util.ArrayList;

class GameStats {
	private ArrayList<GenTextView> alGuestStats;
	private ArrayList<GenTextView> alHomeStats;
	private static final int NUM_ELEMENTS = 3;

	private Activity activity;
	// TODO: create reference to Table0 class
	// But load it on its own function.
	// Do the same thing with Table1 class

	GameStats(Activity activity_, Context context_) {
		activity = activity_;

		TableRow llGuestGameStats = (TableRow) activity_.findViewById(R.id.trTable1Row1);
		TableRow llHomeGameStats = (TableRow) activity_.findViewById(R.id.trTable1Row2);

		alGuestStats = new ArrayList<>(NUM_ELEMENTS);
		alHomeStats = new ArrayList<>(NUM_ELEMENTS);

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

			tvGuest.setOnTouchListener(new CustomTouchListener());
			tvHome.setOnTouchListener(new CustomTouchListener());

			alGuestStats.add(tvGuest);
			alHomeStats.add(tvHome);

			llGuestGameStats.addView(tvGuest);
			llHomeGameStats.addView(tvHome);
		}
	}

	private class CustomTouchListener implements View.OnTouchListener {
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) { // If user pressed view
				GenTextView V = (GenTextView) view;
				V.Update();
			}
			return false;
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

			tvGuest.setOnTouchListener(new CustomTouchListener());
			tvHome.setOnTouchListener(new CustomTouchListener());

			// Set text only for the first inning and set as curr inning
			if (k == 1) {
				tvGuest.setText("0");
				tvCurrInn = tvGuest;
			}

		}

	}

	private class CustomTouchListener implements View.OnTouchListener {
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) { // If user pressed view
				GenTextView V = (GenTextView) view;
				V.Update();
			}
			return false;
		}
	}

}
