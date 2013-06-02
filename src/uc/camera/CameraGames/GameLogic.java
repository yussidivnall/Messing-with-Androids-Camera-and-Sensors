package uc.camera.CameraGames;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.*;
import android.content.res.Resources;

public class GameLogic {
	public final float VIEWRANGE=45/3f;
	
	SensorsOutput 	mSensors;
	EnemyPositions 	mEnemies;
	HUD 			mHUD;
	
	BitmapFactory mBitmapFactory;
	
	Vector3D myVector;
	Vector3D redDotVector;
	Vector3D greenDotVector;
	Vector3D blueDotVector;
	
	public int CanvasWidth=1,CanvasHeight=1;
	
	GameLogic(SensorsOutput so,EnemyPositions initialEnemies){
		mBitmapFactory = new BitmapFactory();
		
		mSensors=so;
		//mEnemies= new EnemyPositions(so);
		mEnemies=initialEnemies;
		mHUD = new HUD(mSensors,mEnemies);
		
		
		redDotVector=new Vector3D(-10,10,10);
		greenDotVector=new Vector3D(0,0,0);
		blueDotVector=new Vector3D(-10,10,10);
		redDotVector=new Vector3D(-10,10,10);
		myVector = new Vector3D(10,10,10);
		
		//TODO move all this to some level loader
		//BitmapFactory.decodeResource(getResources(),R.drawable.skull);
		//Enemy enemyA=new Enemy(-10,0,10,999, bmp);
		
	}
	
	
	public Vector3D getScreenPosition(Vector3D v){
		//Returns screen coordinates of point with Z being distance
		double distance=Utils.getMagnitude(myVector);
		double rotation_y=Utils.getYAxisAngle(myVector);
		double difference_y=mSensors.rotation_y-rotation_y; //difference in angle on the y axis between the camera and the point's position
		
		double rotation_x=Utils.getXAxisAngle(myVector);
		double difference_x=mSensors.rotation_x-rotation_x;
		float center_x=CanvasWidth/2;
		float center_y=CanvasHeight/2;
		
		if(difference_y > -VIEWRANGE && difference_y < VIEWRANGE){
			float unit_x=CanvasWidth/(2*VIEWRANGE);
			float unit_y=CanvasHeight/(2*VIEWRANGE);
			
			float x=(float)(center_x-(unit_x*difference_y));
			float y=(float)(center_y-(unit_y*difference_x)); // TODO
			Vector3D ret = new Vector3D(x,y,distance);
			return ret;
		}else{
			return null;
		}
		
	}
	public void placeEnemy(Canvas c){
		for (Enemy e : mEnemies.mEnemies){
			Vector3D enemyScrPos=getScreenPosition(e.getPosition());
			if (enemyScrPos != null){
				
				Rect r;
				c.drawBitmap(e.mImage, (float)enemyScrPos.X,(float)enemyScrPos.Y,null);
			}
		}
	}
	
	
	public void placement(Canvas c){
		Paint red = new Paint();red .setColor(Color.RED);
		Paint green = new Paint();green.setColor(Color.GREEN);
		
		
		
		
		Vector3D gdv=getScreenPosition(greenDotVector);
		Vector3D rdv=getScreenPosition(blueDotVector);
		if(gdv!=null){
			c.drawCircle((float)gdv.X, (float)gdv.Y, (float)gdv.Z*3, green);
		}
		if(rdv!=null){
			c.drawCircle((float)rdv.X, (float)rdv.Y, (float)rdv.Z*3, red);
		}
		
		Vector3D p = getScreenPosition(myVector);
		if(p!=null){
			c.drawCircle((float)p.X, (float)p.Y, (float)p.Z*3, green);
		}
		/*
		double distance=Utils.getMagnitude(myVector);
		double rotation_y=Utils.getYAxisAngle(myVector);
		double difference_y=mySensors.rotation_y-rotation_y; //difference in angle on the y axis between the camera and the point's position
		
		double rotation_x=Utils.getXAxisAngle(myVector);
		double difference_x=mySensors.rotation_x-rotation_x;
		
		if(difference_y > -VIEWRANGE && difference_y < VIEWRANGE){
			float unit=c.getWidth()/(2*VIEWRANGE);
			//double position = c.getWidth()/2*difference;
			float x=(float)(c.getWidth()/2-(unit*difference_y));
			//c.drawCircle((float)(c.getWidth()/2-(unit*difference_y)), c.getHeight()/2, 10, paint);
			c.drawCircle(x, c.getHeight()/2, 10, paint);
		}
		*/
	}
	
	public void updateCanvasSize(Canvas c){
		if(c.getWidth()!=CanvasWidth) CanvasWidth=c.getWidth();
		if(c.getHeight()!=CanvasHeight) CanvasHeight=c.getHeight();
	}
	
	
	public void draw(Canvas canvas){
		updateCanvasSize(canvas);
		
		
		//placement(canvas); //TODO get rid of this...
		placeEnemy(canvas);
		
		
		mEnemies.draw(canvas,mSensors);
		mHUD.draw(canvas);
		
		/*drawPoint(canvas);
		if(mySensors.rotation_x> 180 && mySensors.rotation_x <270){
			Paint p = new Paint();
			p.setColor(Color.BLUE);
			canvas.drawCircle(mySensors.rotation_x, -mySensors.rotation_y, 5+mySensors.acceleration_z*10, p);
		}*/
	}
	
	public void advance(long step){
		
	}
}
