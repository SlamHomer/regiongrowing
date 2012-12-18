package com.slamhomer.regiongrowing;

import com.slamhomer.regiongrowing_network.GPS;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayOptionsActivity extends Activity implements LocationListener{
	  private TextView latituteField;
	  private TextView longitudeField;
	  private LocationManager locationManager;
	  private String provider;
	  private String lalo = null;
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_options);
		
		latituteField = (TextView) findViewById(R.id.editText3);
	    longitudeField = (TextView) findViewById(R.id.editText1);

	    // Get the location manager
	    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    // Define the criteria how to select the locatioin provider -> use
	    // default
	    Criteria criteria = new Criteria();
	    provider = locationManager.getBestProvider(criteria, false);

	    /*	Location location = locationManager.getLastKnownLocation(provider);

	    // Initialize the location fields
	    if (location != null) {
	      System.out.println("Provider " + provider + " has been selected.");
	      onLocationChanged(location);
	    } else {
	      latituteField.setText("Location not available");
	      longitudeField.setText("Location not available");
	    }*/
	    
	    if(GPS.loadHomeLoc(this) == true){
		    latituteField.setText(String.valueOf(GPS.getLatitude()));
		    longitudeField.setText(String.valueOf(GPS.getLongitude()));
	    }
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_options, menu);
		return true;
	}

	  /* Request updates at startup */
	  @Override
	  protected void onResume() {
	    super.onResume();
	    locationManager.requestLocationUpdates(provider, 400, 1, this);
	  }

	  /* Remove the locationlistener updates when Activity is paused */
	  @Override
	  protected void onPause() {
	    super.onPause();
	    locationManager.removeUpdates(this);
	  }

	  public void onStatusChanged(String provider, int status, Bundle extras) {
	    // TODO Auto-generated method stub
	  }

	  public void onProviderEnabled(String provider) {
	    Toast.makeText(this, "Enabled new provider " + provider,
	        Toast.LENGTH_SHORT).show();

	  }

	  public void onProviderDisabled(String provider) {
	    Toast.makeText(this, "Disabled provider " + provider,
	        Toast.LENGTH_SHORT).show();
	  }


	  public void onLocationChanged(Location location) {
		double lat = (double) (location.getLatitude());
	    double lng = (double) (location.getLongitude());
	    	    
	    this.lalo = String.valueOf(lat) +"\n"+String.valueOf(lng);    
	  }
	  

	  public void setLocation(View view){  
		  String fehler = GPS.saveHomeLoc(this.lalo, this);
		  
		  if(!fehler.equals("OK")){
			  //Alert "Fehler beim Speichern der GPS Koordinaten"+fehler
			  AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DisplayOptionsActivity.this);
				alertDialogBuilder.setTitle("Fehler");
				alertDialogBuilder
						.setMessage("Fehler beim Speichern der GPS Koordinaten:"+fehler)
						.setCancelable(false)
						.setNeutralButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {
										return;
									}
								});
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
		  }
		  if(GPS.loadHomeLoc(this) == true){
			    latituteField.setText(String.valueOf(GPS.getLatitude()));
			    longitudeField.setText(String.valueOf(GPS.getLongitude()));
		  }
	  }
}
