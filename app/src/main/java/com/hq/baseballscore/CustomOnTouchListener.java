package com.hq.baseballscore;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Reinaldo on Sun 25 Sep 2016 06:57:14 PM EDT
 */
public class CustomOnTouchListener implements View.OnTouchListener {
  private TextView tv;

  CustomOnTouchListener(TextView tv_) {
    tv = tv_;
  }

  public boolean onTouch(View view, MotionEvent motionEvent) {return false;}
}

// public class BallsTouchListener implements View.OnTouchListener {
  // private TextView tvBalls;
  // private int iBalls = 0;

  // public boolean onTouch(View view, MotionEvent motionEvent) {
    // if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) { // If user pressed view
      // Log.i(TAG, view.getId() + " - Button Pressed!");
      // tvBalls = (TextView) view;
      // if (iBalls == 3) iBalls = 0;
      // else iBalls++;
      // // Get current text, cast to string, convert to int, add 1, convert to string, display
      // tvBalls.setText(Integer.toString(iBalls));
    // }
    // return false;
  // }
// }

// public class StrikesTouchListener implements View.OnTouchListener {
  // // TODO constructor that gets instanceof TextView
  // // This constructor must also get instance of OutsTouchListener so that it
  // // can call OutsTouchListener.Update()
  // private TextView tvStrikes;
  // private int iStrikes = 0;

  // public StrikesTouchListener(TextView tvStrikes_) {
    // tvStrikes = tvStrikes_;
  // }

  // public boolean onTouch(View view, MotionEvent motionEvent) {
    // if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) { // If user pressed view
      // Log.i(TAG, view.getId() + " - Button Pressed!");
      // tvStrikes = (TextView) view;
      // iStrikes++;
      // if (iStrikes == 3) iStrikes = 0;
      // // TODO Update outs with a function
      // // iOuts++;
      // // Get current text, cast to string, convert to int, add 1, convert to string, display
      // tvStrikes.setText(Integer.toString(iStrikes));
    // }
    // return false;
  // }
// }

// public class OutsTouchListener implements View.OnTouchListener {
  // private TextView tvOuts;
  // private int iOuts = 0;

  // public boolean onTouch(View view, MotionEvent motionEvent) {
    // if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) { // If user pressed view
      // Log.i(TAG, view.getId() + " - Button Pressed!");
      // tvOuts = (TextView) view;
      // iOuts++;
      // if (iOuts == 3) iOuts = 0;
      // // Get current text, cast to string, convert to int, add 1, convert to string, display
      // tvOuts.setText(Integer.toString(iOuts));
    // }
    // return false;
  // }
// }

// private class CustomTouchListener implements View.OnTouchListener {
  // public boolean onTouch(View view, MotionEvent motionEvent) {
    // if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) { // If user pressed view
      // // TODO Convert this down here to a function that decodes who got called
      // Log.i(TAG, view.getId() + " - Button Pressed!");
      // TextView tv = (TextView) view;
      // // Get current text, cast to string, convert to int, add 1, convert to string, display
      // tv.setText(Integer.toString(Integer.parseInt((String) tv.getText()) + 1));
    // }
    // return false;
  // }
// }
