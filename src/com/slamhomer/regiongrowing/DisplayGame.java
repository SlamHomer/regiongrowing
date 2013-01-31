package com.slamhomer.regiongrowing;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.slamhomer.regiongrowing_gameobjects.Gamemanager;
import com.slamhomer.regiongrowing_maps.UpdateMap;
import com.slamhomer.regiongrowing_network.LeaveGameThread;
import com.slamhomer.regiongrowing_network.UpdateThread;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;

public class DisplayGame extends MapActivity {
	
	private MapView mapView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/*
		 * Nur ein Hotfix!
		 * TODO: Richtig machen. Maybe AsyncThread?
		 * ################################################## 
		 */
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy); 
		//###################################################
		
		setContentView(R.layout.activity_display_game);
		mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	    
	    UpdateMap updateMap = new UpdateMap(mapView, this);
	    updateMap.update();
	}
	
	@Override
	public void onResume(){
		super.onResume();
		
		UpdateMap updateMap = new UpdateMap(mapView, this);
	    updateMap.update();
	}
	
	@Override
	public void onPause() {
	    super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_game, menu);
		return true;
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	@Override
	public void onDestroy() {
	    super.onDestroy();  // Always call the superclass
	    
	    // Stop method tracing that the activity started during onCreate()
	    android.os.Debug.stopMethodTracing();
	}
	
	/** Called when the user clicks the Neues Spiel button */
    public void goDailyTask(View view) {
		Intent intent = new Intent(this, DisplayDailyTask.class);
		startActivity(intent);
	}
    
    /** Called when the user clicks the Verlassen button */
    public void goLeave(View view) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Achtung!");
		alertDialogBuilder
				.setMessage("Sie verlieren und verlassen das aktuelle Spiel. Ist das okay?")
				.setCancelable(false)
				.setPositiveButton("Ja",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
						    	Thread lg = new LeaveGameThread();
								lg.start();
								try {
									lg.join();
									finish();
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								Thread updateThread = new UpdateThread(Gamemanager.getLocalPlayer().getName());
								updateThread.start();
								try {
									updateThread.join();
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						})
				.setNegativeButton("Nein",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							return;
						}
					});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();	
	}
    
    public void goUpdate(View view){
		UpdateMap updateMap = new UpdateMap(mapView, this);
	    updateMap.update();
    }

}
