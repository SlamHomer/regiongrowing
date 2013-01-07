package com.slamhomer.regiongrowing;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.slamhomer.regiongrowing_gameobjects.Gamemanager;
import com.slamhomer.regiongrowing_maps.UpdateMap;
import com.slamhomer.regiongrowing_network.BackgroundUpdateThread;
import com.slamhomer.regiongrowing_network.UpdateThread;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class DisplayGame extends MapActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_game);
		MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	    
	    UpdateMap updateMap = new UpdateMap(mapView, this);
	    updateMap.update();
	    
	    BackgroundUpdateThread background = new BackgroundUpdateThread(
				updateMap);
	    
	    if (background.isAlive() == false) {
			background.start();
		}
	}
	
	@Override
	public void onResume(){
		super.onResume();
		
		Thread updateThread = new UpdateThread(
				Gamemanager.getLocalPlayer().getName());
		updateThread.start();
		try {
			updateThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		BackgroundUpdateThread.setUpdate(true);
	}
	
	@Override
	public void onPause() {
	    super.onPause();
	    
	    BackgroundUpdateThread.setUpdate(false);
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
	
}
