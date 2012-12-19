package com.slamhomer.regiongrowing;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.slamhomer.regiongrowing_gameobjects.Gamemanager;
import com.slamhomer.regiongrowing_gameobjects.LocalPlayer;
import com.slamhomer.regiongrowing_maps.RegionItemizedOverlay;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;

public class DisplayGame extends MapActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_game);
		MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	    
	    /*
	     * All overlay elements on a map are held by the MapView, 
	     * so when you want to add some, you have to get a list from the getOverlays() method. 
	     * Then instantiate the Drawable used for the map marker, 
	     * which was saved in the res/drawable/ directory. The constructor for HelloItemizedOverlay 
	     * (your custom ItemizedOverlay) takes the Drawable in order to set the 
	     * default marker for all overlay items. 
	     */
	    List<Overlay> mapOverlays = mapView.getOverlays();
	    Drawable drawable = this.getResources().getDrawable(R.drawable.android_marker);
	    RegionItemizedOverlay itemizedoverlay = new RegionItemizedOverlay(drawable, this);
	    
	    //Local Player GeoPoint
	    GeoPoint local_point = new GeoPoint(Gamemanager.getLocalPlayer().convLatitude(),
	    		Gamemanager.getLocalPlayer().convLongitude());
	    OverlayItem overlayitem = new OverlayItem(local_point, 
	    		("Name: "+Gamemanager.getLocalPlayer().getName()), 
	    		("Einfluss: "+String.valueOf(Gamemanager.getLocalPlayer().getInfluence())));
	    
	    //Add Item to collection and then to mapview
	    itemizedoverlay.addOverlay(overlayitem);
	    mapOverlays.add(itemizedoverlay);
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
