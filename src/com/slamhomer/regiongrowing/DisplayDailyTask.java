package com.slamhomer.regiongrowing;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DisplayDailyTask extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_daily_task);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_daily_task, menu);
		return true;
	}

}
