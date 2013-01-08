package com.slamhomer.regiongrowing;

import com.slamhomer.regiongrowing_gameobjects.Gamemanager;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class DisplayTask extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_task);
		
		Bundle extras = getIntent().getExtras();
		int position = extras.getInt("numbers");
		
		final TextView textView1 = (TextView)findViewById(R.id.textView1);
		String Titel = Gamemanager.getTask(position).getTaskName();
	    textView1.setText(Titel);
	    final TextView textView2 = (TextView)findViewById(R.id.textView2);
	    String Beschreibung = Gamemanager.getTask(position).getTaskDesc();
	    textView2.setText(Beschreibung);
	    final TextView textView4 = (TextView)findViewById(R.id.textView4);
	    int Einfluss = Gamemanager.getTask(position).getTaskInf();
	    textView4.setText(String.valueOf(Einfluss));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_task, menu);
		return true;
	}

}
