package com.hq.baseballscore;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Reinaldo on Sun 25 Sep 2016 06:35:43 PM EDT
 */
class InningStats {
	private static final int NUM_ELEMENTS = 6;
	private static final int NUM_VALUES = 3;
	private static final String[] STATS_NAMES = {"Ball", "Strike", "Out"};
	private static final int INDEX_BALLS = 1;
	private static final int INDEX_STRIKES = 3;
	private static final int INDEX_OUTS = 5;

	// Values
	private int iBalls = 0;
	private int iStrikes = 0;
	private int iOuts = 0;

	private TextView tvBalls;
	private TextView tvStrikes;
	private TextView tvOuts;

	private TextView tvTeamAtBat;

	// Constructor which initializes all the views
	InningStats(Context context, Activity activity) {
		LinearLayout llInningStats = (LinearLayout) activity.findViewById(R.id.llInningStats);

		for (int k = 0, k1 = 0; k < NUM_ELEMENTS; k++) {
			TextView tv = new TextView(context);
			tv.setId(k);

			tv.setLayoutParams(
					new LinearLayout.LayoutParams(
							0, LinearLayout.LayoutParams.MATCH_PARENT, (float) 1 / 6));

			tv.setGravity(Gravity.CENTER);

			if ((k & 1) == 1) { // Odd are values
				// Set appearance
				if (Build.VERSION.SDK_INT < 23) {
					tv.setTextAppearance(activity, R.style.InningStatsValue);
				} else {
					tv.setTextAppearance(R.style.InningStatsValue);
				}
				tv.setText("0");

				// Assign OnTouchListener and TextView
				if (k == INDEX_BALLS) {
					tv.setOnTouchListener(new BallsTouchListener());
					tvBalls = tv;
				} else if (k == INDEX_STRIKES) {
					tv.setOnTouchListener(new StrikesTouchListener());
					tvStrikes = tv;
				} else {
					tv.setOnTouchListener(new OutsTouchListener());
					tvOuts = tv;
				}
			} else {
				// Set appearance
				if (Build.VERSION.SDK_INT < 23) {
					tv.setTextAppearance(context, R.style.InningStatsText);
				} else {
					tv.setTextAppearance(R.style.InningStatsText);
				}

				tv.setText(STATS_NAMES[k1++]);
			}

			llInningStats.addView(tv);
		}

		tvTeamAtBat = (TextView) activity.findViewById(R.id.tvTeamAtBat);
	}

	private class BallsTouchListener implements View.OnTouchListener {
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) { // If user pressed view
				UpdateBalls();
			}
			return false;
		}
	}

	private class StrikesTouchListener implements View.OnTouchListener {
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) { // If user pressed view
				UpdateStrikes();
			}
			return false;
		}
	}

	private class OutsTouchListener implements View.OnTouchListener {
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) { // If user pressed view
				UpdateOuts();
			}
			return false;
		}
	}

	private void UpdateBalls() {
		iBalls++;
		if (iBalls == 4) iBalls = 0;
		UpdateDisplay();
	}

	private void UpdateStrikes() {
		iStrikes++;
		if (iStrikes == 3) {
			// On strike out reset balls and increase outs
			UpdateOuts();
		} else
			UpdateDisplay();
	}

	private void UpdateOuts() {
		iOuts++;
		// On every out reset balls and strikes
		iBalls = 0;
		iStrikes = 0;

		if (iOuts == 3) {
			iOuts = 0;
		}
		UpdateDisplay();
	}

	private void Reset() {
		iBalls = iStrikes = iOuts = 0;
		UpdateDisplay();
		tvTeamAtBat.setText(R.string.guest);
	}

	private void UpdateDisplay() {
		tvBalls.setText(Integer.toString(iBalls));
		tvStrikes.setText(Integer.toString(iStrikes));
		tvOuts.setText(Integer.toString(iOuts));
	}

	private void SwitchTeamAtBat(){
		// TODO fix getString and check this logic
		if (tvTeamAtBat.getText() == getString(R.string.guest)){
			tvTeamAtBat.setText(R.string.home);
		}
		else
			tvTeamAtBat.setText(R.string.guest);
	}
}

