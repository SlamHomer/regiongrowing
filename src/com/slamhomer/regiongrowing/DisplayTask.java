package com.slamhomer.regiongrowing;

import com.slamhomer.regiongrowing_gameobjects.Gamemanager;
import com.slamhomer.regiongrowing_network.TurnTaskInThread;
import com.slamhomer.regiongrowing_network.UpdateThread;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class DisplayTask extends Activity {
	private static String titel = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_task);
		
		Bundle extras = getIntent().getExtras();
		int position = extras.getInt("numbers");
		
		final TextView textView1 = (TextView)findViewById(R.id.textView1);
		titel = Gamemanager.getTask(position).getTaskName();
	    textView1.setText(titel);
	    final TextView textView2 = (TextView)findViewById(R.id.textView2);
	    String Beschreibung = Gamemanager.getTask(position).getTaskDesc();
	    textView2.setText(Beschreibung);
	    final TextView textView4 = (TextView)findViewById(R.id.textView4);
	    int Einfluss = Gamemanager.getTask(position).getTaskInf();
	    textView4.setText(String.valueOf(Einfluss));
	    
	    //TODO: Hier muss noch ob wer das schon 
	    //abgeben hat und falls ja darf es keine buttons für abgeben und aufnehmen geben
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_task, menu);
		return true;
	}
	
	public void goTurnIn(View view){
		Thread turnIn = new TurnTaskInThread(titel);
		turnIn.run();
		try {
			turnIn.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread update = new UpdateThread(
				Gamemanager.getLocalPlayer().getName());
		update.run();
		try {
			update.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Messages.alert("Tasks wurde abgegeben.", "Erfolg!", "OK", this);

	}
	
	public void goCamera(View view){
		Intent intent = new Intent(this, DisplayCameraActivity.class);
		startActivity(intent);
	}

}
