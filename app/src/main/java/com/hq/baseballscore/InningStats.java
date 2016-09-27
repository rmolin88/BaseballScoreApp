package com.hq.baseballscore;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Reinaldo on Sun 25 Sep 2016 06:35:43 PM EDT
 */
public class InningStats {
  // Note: The idea here is to have a custom TextView class.
  // And that class will have and update function which will update the value of
  // its text. and do all the pertinent calculations before hand
  // TODO get the views to show
  private static final int NUM_ELEMENTS = 6;
  private static final String[] STATS_NAMES = {"Ball", "Strike", "Out"};
  private static final int INDEX_BALLS = 1;
  private static final int INDEX_STRIKES = 3;
  private static final int INDEX_OUTS = 5;

  // Values
  private int iBalls = 0;
  private int iStrikes = 0;
  private int iOuts = 0;

  private static ArrayList<TextView> TextViews = new ArrayList<>(NUM_ELEMENTS);

  InningStats(Context context, Activity activity) {
    // TODO: Figure out how are you going to add the views
    LinearLayout llInningStats = (LinearLayout) activity.findViewById(R.id.llInningStats);
//    TableRow trTable1Row1 = (TableRow) findViewById(R.id.trTable1Row1);
//    TableRow trTable1Row2 = (TableRow) findViewById(R.id.trTable1Row2);
//    TableRow trTable0Row1 = (TableRow) findViewById(R.id.trTable0Row1);
//    TableRow trTable0Row2 = (TableRow) findViewById(R.id.trTable0Row2);

    for (int k = 0, k1=0; k < NUM_ELEMENTS; k++) {
      TextView tv = new TextView(context);
      tv.setId(k);

			tv.setLayoutParams(
							new LinearLayout.LayoutParams(
									0, LinearLayout.LayoutParams.MATCH_PARENT, (float) 1/6));

	    tv.setGravity(Gravity.CENTER);

			if ((k & 1) == 1) { // Odd are values
        // Set appearance
        if (Build.VERSION.SDK_INT < 23) {
          tv.setTextAppearance(activity, R.style.InningStatsValue);
        } else {
          tv.setTextAppearance(R.style.InningStatsValue);
        }
				tv.setText("0");

				if (k == INDEX_BALLS) tv.setOnTouchListener(new BallsTouchListener());
				// Todo fix TouchListener here when you create the strikes and outs
				else if (k == INDEX_STRIKES)
					tv.setOnTouchListener(new StrikesTouchListener());
				else tv.setOnTouchListener(new OutsTouchListener());
      }
      else {
        // Set appearance
        if (Build.VERSION.SDK_INT < 23) {
          tv.setTextAppearance(context, R.style.InningStatsText);
        } else {
          tv.setTextAppearance(R.style.InningStatsText);
        }

				tv.setText(STATS_NAMES[k1++]);
      }

      TextViews.add(tv);
      llInningStats.addView(tv);
    }
  }
 private class BallsTouchListener implements View.OnTouchListener {
   public boolean onTouch(View view, MotionEvent motionEvent) {
		 if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) { // If user pressed view
       // TODO
//			 Log.i(TAG, view.getId() + " - Button Pressed!");
//			 tvBalls = (TextView) view;
//			 if (iBalls == 3) iBalls = 0;
//			 else iBalls++;
//			  Get current text, cast to string, convert to int, add 1, convert to string, display
//			 tvBalls.setText(Integer.toString(iBalls));
		 }
		 return false;
   }
 }

 private class StrikesTouchListener implements View.OnTouchListener {
   // TODO constructor that gets instanceof TextView
   // This constructor must also get instance of OutsTouchListener so that it
   // can call OutsTouchListener.Update()
//   private TextView tvStrikes;
//   private int iStrikes = 0;

//   public StrikesTouchListener(TextView tvStrikes_) {
//   tvStrikes = tvStrikes_;
//   }

   public boolean onTouch(View view, MotionEvent motionEvent) {
   if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) { // If user pressed view
//   Log.i(TAG, view.getId() + " - Button Pressed!");
//   tvStrikes = (TextView) view;
//   iStrikes++;
//   if (iStrikes == 3) iStrikes = 0;
   // TODO Update outs with a function
   // iOuts++;
   // Get current text, cast to string, convert to int, add 1, convert to string, display
//   tvStrikes.setText(Integer.toString(iStrikes));
   }
   return false;
   }
 }

private class OutsTouchListener implements View.OnTouchListener {
   public boolean onTouch(View view, MotionEvent motionEvent) {
     if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) { // If user pressed view
//       Log.i(TAG, view.getId() + " - Button Pressed!");
//			 tvOuts = (TextView) view;
//			 iOuts++;
//			 if (iOuts == 3) iOuts = 0;
			 // Get current text, cast to string, convert to int, add 1, convert to string, display
//			 tvOuts.setText(Integer.toString(iOuts));
		 }
		 return false;
   }
 }
}

