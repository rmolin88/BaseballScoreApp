package com.hq.baseballscore;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

class CurrInnStats {
  private static final String TAG = "BaseballApp";
  private static final int NUM_ELEMENTS = 6;
  private static final int NUM_VALUES = 3;
  private static final String[] STATS_NAMES = {"Ball", "Strike", "Out"};
  private static final int INDEX_BALLS = 1;
  private static final int INDEX_STRIKES = 3;
  private static final int INDEX_OUTS = 5;

  // Values
  private GenTextView tvBalls;
  private GenTextView tvStrikes;
  private GenTextView tvOuts;

  private TextView tvTeamAtBat;

  private Activity activity;

  // Constructor which initializes all the views
  CurrInnStats(Context context_, Activity activity_) {
    activity = activity_;
    LinearLayout llInningStats = (LinearLayout) activity.findViewById(R.id.llInningStats);

    for (int k = 0, k1 = 0; k < NUM_ELEMENTS; k++) {
      GenTextView tv = new GenTextView(context_);
      tv.setId(k);

      tv.setLayoutParams(
          new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, (float) 1 / 6));

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
          tv.setOnTouchListener(new CustomTouchListener());
          tvBalls = tv;
          tvBalls.setReset(4);
        } else if (k == INDEX_STRIKES) {
          tv.setOnTouchListener(new CustomTouchListener());
          tvStrikes = tv;
          tvStrikes.setReset(3);
        } else {
          tv.setOnTouchListener(new CustomTouchListener());
          tvOuts = tv;
          tvOuts.setReset(3);
        }
      } else {
        // Set appearance
        if (Build.VERSION.SDK_INT < 23) {
          tv.setTextAppearance(context_, R.style.InningStatsText);
        } else {
          tv.setTextAppearance(R.style.InningStatsText);
        }

        tv.setText(STATS_NAMES[k1++]);
      }

      llInningStats.addView(tv);
    }

  tvTeamAtBat = (TextView) activity.findViewById(R.id.tvTeamAtBat);
  }

  private class CustomTouchListener implements View.OnTouchListener {
    public boolean onTouch(View view, MotionEvent motionEvent) {
      if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) { // If user pressed view
        UpdateView((GenTextView) view);
      }
      return false;
    }
  }

  private void Reset() {
    tvBalls.Reset();
    tvStrikes.Reset();
    tvOuts.Reset();
  }

  private void UpdateView(GenTextView view) {
    if (view == tvBalls) {
      view.Update();
    } else if (view == tvStrikes) {
      if (view.Update() == 1) {
        tvBalls.Reset();
        if (tvOuts.Update() == 1) Reset();
      }
    } else {
      tvBalls.Reset();
      tvStrikes.Reset();
      if (view.Update() == 1) {
        view.Reset();
        SwitchTeamAtBat();
      }
    }
  }

  private void SwitchTeamAtBat() {
    if (tvTeamAtBat.getText() == activity.getString(R.string.guest)) {
      tvTeamAtBat.setText(R.string.home);
    } else tvTeamAtBat.setText(R.string.guest);
		// TODO: Notify InningStats that needs to update innings
  }
}

// Note: Always set Reset value
class GenTextView extends TextView {
  private int iValue = 0;
  private int iReset;

  public GenTextView(Context context) {
    super(context);
  }

  public GenTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public GenTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public GenTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  // Returns 0 when there it doesnt resets 1 otherwise
  public int Update() {
    int iRet = 0;
    iValue++;
    if (iValue == iReset) {
      iValue = 0;
      iRet = 1;
    }
    setText(Integer.toString(iValue));
    return iRet;
  }

  public void Reset() {
    iValue = 0;
    setText(Integer.toString(iValue));
  }

  public void setReset(int iReset_) {
    iReset = iReset_;
  }
}
