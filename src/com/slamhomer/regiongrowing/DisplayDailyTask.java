package com.slamhomer.regiongrowing;

import com.slamhomer.regiongrowing_gameobjects.Gamemanager;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class DisplayDailyTask extends ListActivity {
	static final String[] Task_name = new String[] { Gamemanager.getTask(0).getTaskName(), Gamemanager.getTask(1).getTaskName(), 
		Gamemanager.getTask(2).getTaskName(), Gamemanager.getTask(3).getTaskName(), 
		Gamemanager.getTask(4).getTaskName() };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_display_daily_task);
		
		setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_display_daily_task,R.id.textView1,Task_name));
		 
		ListView listView = getListView();
		listView.setTextFilterEnabled(true);
 
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    // When clicked, show a toast with the TextView text
			    Toast.makeText(getApplicationContext(),
				((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_daily_task, menu);
		return true;
	}
	
	@Override
	public void onDestroy() {
	    super.onDestroy();  // Always call the superclass
	    
	    // Stop method tracing that the activity started during onCreate()
	    android.os.Debug.stopMethodTracing();
	}

}
