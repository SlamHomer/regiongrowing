package com.slamhomer.regiongrowing_maps;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.slamhomer.regiongrowing.R;
import com.slamhomer.regiongrowing_gameobjects.Gamemanager;

public class UpdateMap {
	private List<Overlay> mapOverlays;
    private Drawable drawable;
    private RegionItemizedOverlay itemizedoverlay;
    
    private Circle local_circle;
    private Circle enemy_circle;
    
    private OverlayItem overlayitem;
    
    private MapView mapView;
    private Context context;
	
	public UpdateMap(MapView mapView, Context context){
		this.mapView = mapView;
		this.context = context;
	}
    /*
     * All overlay elements on a map are held by the MapView, 
     * so when you want to add some, you have to get a list from the getOverlays() method. 
     * Then instantiate the Drawable used for the map marker, 
     * which was saved in the res/drawable/ directory. The constructor for HelloItemizedOverlay 
     * (your custom ItemizedOverlay) takes the Drawable in order to set the 
     * default marker for all overlay items. 
     */
	public void update(){
	    
		// Local Player	    
	    if (Gamemanager.getLocalPlayer().getName() != null) {    	
			mapOverlays = mapView.getOverlays();
			drawable = context.getResources().getDrawable(R.drawable.local);
			itemizedoverlay = new RegionItemizedOverlay(drawable, context);
			
	    	//clear map overlays
			mapOverlays.clear();
			
			//Local Player GeoPoint
			GeoPoint local_point = new GeoPoint(Gamemanager.getLocalPlayer()
					.convLatitude(), Gamemanager.getLocalPlayer()
					.convLongitude());
			overlayitem = new OverlayItem(local_point, ("Name: " + Gamemanager
					.getLocalPlayer().getName()),
					("Einfluss: " + String.valueOf(Gamemanager.getLocalPlayer()
							.getInfluence())));
			//Add Item to collection and then to mapview
			itemizedoverlay.addOverlay(overlayitem);
			mapOverlays.add(itemizedoverlay);
			
			//add circle
			local_circle = new Circle(local_point, Gamemanager.getLocalPlayer().getInfluence());
			mapOverlays.add(local_circle);
		for(int i = 0; i < Gamemanager.getEnemyPlayerArray().length; i++){
			if (Gamemanager.getEnemyPlayer(i).getName() != null) {
				// Local Player
				mapOverlays = mapView.getOverlays();
				drawable = context.getResources().getDrawable(R.drawable.enemy);
				itemizedoverlay = new RegionItemizedOverlay(drawable, context);
				//Local Player GeoPoint
				GeoPoint enemy_point = new GeoPoint(Gamemanager.getEnemyPlayer(
						i).convLatitude(), Gamemanager.getEnemyPlayer(i)
						.convLongitude());
				overlayitem = new OverlayItem(enemy_point,
						("Gegner-Name: " + Gamemanager.getEnemyPlayer(i)
								.getName()),
						("Einfluss: " + String.valueOf(Gamemanager
								.getEnemyPlayer(i).getInfluence())));
				//Add Item to collection and then to mapview
				itemizedoverlay.addOverlay(overlayitem);
				mapOverlays.add(itemizedoverlay);
				
				//add circle
				enemy_circle = new Circle(enemy_point, Gamemanager.getEnemyPlayer(i).getInfluence());
				mapOverlays.add(enemy_circle);
			}
	    }
	}
	}
}
