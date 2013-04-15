package uc.camera.CameraGames;

import android.content.res.Resources;
import android.graphics.*;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
import android.util.Log;


public class Enemy {
	Vector3D mPosition;
	int hp;
	Bitmap mImage;
	
	Enemy(){
		//BitmapFactory.decodeResource(getResources(),R.drawable.skull); 
	}
	Enemy(int x,int y,int z,int hp,Bitmap img){
		Log.d("Enemy constructor", "Starting");
		mPosition= new Vector3D(x, y,z);
		Log.d("Enemy constructor", "positioning x"+x+" y"+y+" z"+z);
		mImage=img;
		
	}
	public Vector3D getPosition(){
		return mPosition;
	}
	public void draw(Canvas c){}
	
	
}
