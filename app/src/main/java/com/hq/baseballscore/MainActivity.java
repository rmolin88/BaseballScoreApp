package com.hq.baseballscore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	private CurrInnStats currInnStats;
	private InningStats inningStats;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_scrolling);

		currInnStats = new CurrInnStats();
		inningStats = new InningStats();
	}


	// Note: Always set Reset value
	private class GenTextView extends TextView {
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

		// Constructor which initializes all the views
		CurrInnStats() {
			LinearLayout llInningStats = (LinearLayout) findViewById(R.id.llInningStats);

			for (int k = 0, k1 = 0; k < NUM_ELEMENTS; k++) {

				GenTextView tv = new GenTextView(this);
				tv.setId(k);

				tv.setLayoutParams(
						new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, (float) 1 / 6));

				tv.setGravity(Gravity.CENTER);

				if ((k & 1) == 1) { // Odd are values
					// Set appearance
					if (Build.VERSION.SDK_INT < 23) {
						tv.setTextAppearance(this, R.style.InningStatsValue);
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
						tv.setTextAppearance(this, R.style.InningStatsText);
					} else {
						tv.setTextAppearance(R.style.InningStatsText);
					}

					tv.setText(STATS_NAMES[k1++]);
				}

				llInningStats.addView(tv);
			}

			tvTeamAtBat = (TextView) findViewById(R.id.tvTeamAtBat);
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
			if (tvTeamAtBat.getText() == getString(R.string.guest)) {
				tvTeamAtBat.setText(R.string.home);
			} else tvTeamAtBat.setText(R.string.guest);
			// TODO: Notify InningStats that needs to update innings
		}
	}
	private class GameStats {
		private static final int NUM_ELEMENTS = 3;

		GenTextView tvGuestRuns;
		GenTextView tvHomeRuns;

		GameStats() {
			TableRow llGuestGameStats = (TableRow) findViewById(R.id.trTable1Row1);
			TableRow llHomeGameStats = (TableRow) findViewById(R.id.trTable1Row2);

			for (int k = 0; k < NUM_ELEMENTS; k++) {
				// Set first Guest elements
				GenTextView tvGuest = new GenTextView(this);
				GenTextView tvHome = new GenTextView(this);

				tvGuest.setReset(10000); // Reset value like this so that it never happens
				tvHome.setReset(10000); // Reset value like this so that it never happens

				tvGuest.setGravity(Gravity.CENTER);
				tvHome.setGravity(Gravity.CENTER);

				// Set appearance
				if (Build.VERSION.SDK_INT < 23) {
					tvGuest.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Large);
				} else {
					tvGuest.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Large);
				}
				if (Build.VERSION.SDK_INT < 23) {
					tvHome.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Large);
				} else {
					tvHome.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Large);
				}

				tvGuest.setText("0");
				tvHome.setText("0");

				tvGuest.setTextColor(Color.BLACK);
				tvHome.setTextColor(Color.BLACK);

				tvGuest.setOnTouchListener(new GameStatsTouchListener());
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

	private class InningStats {
		private ArrayList<GenTextView> alGuestInnStats;
		private ArrayList<GenTextView> alHomeInnStats;
		private static final int NUM_ELEMENTS = 9;

		private GenTextView tvCurrInn;

		InningStats() {
			alGuestInnStats = new ArrayList<>(NUM_ELEMENTS);
			alHomeInnStats = new ArrayList<>(NUM_ELEMENTS);

			TableRow llGuestInnStats = (TableRow) findViewById(R.id.trTable0Row1);
			TableRow llHomeInnStats = (TableRow) findViewById(R.id.trTable0Row2);

			for (int k = 0; k < NUM_ELEMENTS; k++) {
				GenTextView tvGuest = new GenTextView(this);
				GenTextView tvHome = new GenTextView(this);

				tvGuest.setReset(10000); // Reset value like this so that it never happens
				tvHome.setReset(10000); // Reset value like this so that it never happens

				tvGuest.setGravity(Gravity.CENTER);
				tvHome.setGravity(Gravity.CENTER);

				// Set appearance
				if (Build.VERSION.SDK_INT < 23) {
					tvGuest.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Large);
				} else {
					tvGuest.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Large);
				}
				if (Build.VERSION.SDK_INT < 23) {
					tvHome.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Large);
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
	}

	// TODO: Crazy idea Change Guest and Home team names on touch
	// TODO: Give some color to the layout
	// TODO: Help menu
	//
}
