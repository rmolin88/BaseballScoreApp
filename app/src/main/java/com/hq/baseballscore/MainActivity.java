package com.hq.baseballscore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

	private InningStats inningStats;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_scrolling);

		inningStats = new InningStats(this, this);

	}

	// TODO: Crazy idea Change Guest and Home team names on touch
	// TODO: Give some color to the layout
	// TODO: Help menu
	//
}
