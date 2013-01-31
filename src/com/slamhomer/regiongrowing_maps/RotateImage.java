package com.slamhomer.regiongrowing_maps;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;

public class RotateImage{

	public static BitmapDrawable rotate180(Bitmap bmp) {
		// Getting width & height of the given image.
		int w = bmp.getWidth();
		int h = bmp.getHeight();
		// Setting post rotate to 90
		Matrix mtx = new Matrix();
		mtx.postRotate(180);
		// Rotating Bitmap
		Bitmap rotatedBMP = Bitmap.createBitmap(bmp, 0, 0, w, h, mtx, true);
		@SuppressWarnings("deprecation")
		BitmapDrawable bmd = new BitmapDrawable(rotatedBMP);
		
		return bmd;
	}
}
