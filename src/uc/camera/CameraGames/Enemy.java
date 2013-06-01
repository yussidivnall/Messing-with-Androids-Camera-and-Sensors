package uc.camera.CameraGames;

import android.content.res.Resources;
import android.graphics.*;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
import android.util.Log;
import android.content.Context;
import android.view.View;

public class Enemy {
	Vector3D mPosition;
	int hp;
	Bitmap mImage;
	
	
	Enemy(int x,int y,int z,int hp,Bitmap img){
		try{
			Log.d("Enemy constructor", "Starting");
			mPosition= new Vector3D(x, y,z);
			mImage=img;
			Log.d("Enemy()", "img height:" + img.getHeight() +", width:" +img.getWidth());
			Log.d("Enemy constructor", "positioning x"+x+" y"+y+" z"+z);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	public Vector3D getPosition(){
		return mPosition;
	}
	public void attack(){}
	
	public void draw(Canvas c){
		if (mPosition.Z==0.0){
			attack();
		}else{
			int canvWidth=c.getWidth();
			int canvHeight=c.getHeight();
			
			int center_x=canvWidth/2;
			int center_y=canvHeight/2;
			
			int imgWidth = mImage.getWidth();
			int imgHeight = mImage.getHeight();
			//double draw_ratio=Math.sqrt((float)imgWidth*imgWidth+imgHeight*imgHeight) /mPosition.Z;
			double draw_ratio=1/mPosition.Z;
			
			
			int drawWidth=new Integer((int) (imgWidth*draw_ratio));
			int drawHeight=new Integer((int)(imgHeight*draw_ratio));
			
			//int drawWidth=500;
			//int drawHeight=200;
			
			Log.d("Enemy.draw()", "draw ratio"+draw_ratio+" width:"+drawWidth +" height:"+drawHeight);
			
			Rect rect = new Rect(center_x,center_y,center_x+drawWidth,center_y+drawHeight);
			
			Paint paint = new Paint();
			paint.setColor(Color.RED);
			//c.drawBitmap(mImage, matrix,paint);
			c.drawBitmap(mImage, null, rect, paint);
		}
		
		
		
		
		
		
		
		
	}
	
	
}
