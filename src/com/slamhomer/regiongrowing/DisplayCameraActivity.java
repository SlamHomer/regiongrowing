package com.slamhomer.regiongrowing;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class DisplayCameraActivity extends Activity{
	
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private Uri fileUri;
	
	/*
	 * Camera
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_display_camera);

	    // create Intent to take a picture and return control to the calling application
	    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

	    fileUri = getOutputMediaFileUri(); // create a file to save the image
	    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

	    // start the image capture Intent
	    startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}
	
	@Override
	public void onDestroy() {
	    super.onDestroy();  // Always call the superclass
	    
	    // Stop method tracing that the activity started during onCreate()
	    android.os.Debug.stopMethodTracing();
	}
	
	/*
	 * Media Storage
	 */
	
	/** Create a file Uri for saving an image*/
	private static Uri getOutputMediaFileUri(){
	      return Uri.fromFile(getOutputMediaFile());
	}

	/** Create a File for saving an image*/
	private static File getOutputMediaFile(){
	    File mediaFile = null;
	    
	    if (Environment.getExternalStorageState() != null) {
			File mediaStorageDir = new File(
					Environment
							.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
					"RegionGrowing_Pictures");

			// Create the storage directory if it does not exist
			if (!mediaStorageDir.exists()) {
				if (!mediaStorageDir.mkdirs()) {
					Log.d("RegionGrowing_Pictures", "failed to create directory");
					return null;
				}
			}
			// Create a media file name
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
					.format(new Date());
				
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
						+ "IMG_" + "RG_" + timeStamp + ".jpg");
		}
		return mediaFile;
	}
	
	/*
	 * get camera intent results
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (resultCode == RESULT_OK) {
	            // Image captured and saved to fileUri specified in the Intent
	            Toast.makeText(this, "Image saved to:\n" +
	                     data.getData(), Toast.LENGTH_LONG).show();
	        } else if (resultCode == RESULT_CANCELED) {
	            // TODO:User cancelled the image capture
	        	System.out.println("User cancelled the image capture");
	        } else {
	            // TODO:Image capture failed, advise user
	        	System.out.println("Image capture failed");
	        }
	}

}
