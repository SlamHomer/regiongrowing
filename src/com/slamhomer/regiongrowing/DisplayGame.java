package com.slamhomer.regiongrowing;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.slamhomer.regiongrowing_maps.UpdateMap;
import com.slamhomer.regiongrowing_network.BackgroundUpdateThread;

import android.os.Bundle;
import android.view.Menu;

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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_game, menu);
		return true;
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
}
