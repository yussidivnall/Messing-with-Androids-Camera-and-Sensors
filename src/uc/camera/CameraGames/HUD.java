package uc.camera.CameraGames;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class HUD {
	SensorsOutput mSensors;
	EnemyPositions mEnemies;
	
	
	Paint redpaint = new Paint();
	Paint greenpaint = new Paint();
	Paint bluepaint = new Paint();	
	Paint blackpaint = new Paint();
	
	
	Rect radarFrame=new Rect(0,0,100,100);
	
	
	public void debugDraw(Canvas c){

		double rotation_X_axis=mSensors.rotation_x;
		double rotation_Y_axis=mSensors.rotation_y;
		double rotation_Z_axis=mSensors.rotation_z;
		
		
		c.drawText("rotation on X:"+rotation_X_axis, 50, 50, redpaint);
		c.drawText("rotation on Y:"+rotation_Y_axis, 50, 60, greenpaint);
		c.drawText("rotation on Z:"+rotation_Z_axis, 50, 70, bluepaint);
	}
	
	public void enemiesDraw(Canvas c,int cx, int cy,int radius){
		for (Enemy e : mEnemies.getList()){
			Vector3D pos=e.getPosition();
			int radarCenterX=radarFrame.centerX();
			int radarCenterY=radarFrame.centerY();
			
			
			
			//Log.d("HUD","drawing enemy x:"+(pos.X*10)+" y:"+(pos.Z*10));
			
			
			//c.drawCircle((float)(cx+pos.X), (float)(cy+pos.Z), 2, redpaint);
			c.drawCircle((float)(pos.X*10), (float)(cy+pos.Z*10
					), 2, redpaint);
			
			
			c.drawPoint((float)(cx+pos.X),(float)(cy+pos.Z),redpaint);
		}
		
	}
	
	
	
	

	public void arrowDraw(Canvas c,Vector3D vector){
		
		
	}
	
	public void axisDraw(Canvas c){
		c.drawRect(radarFrame, blackpaint);
		
		c.drawLine(radarFrame.left, radarFrame.top, radarFrame.bottom, radarFrame.width(), bluepaint); //Z
		c.drawLine(radarFrame.left, radarFrame.top+radarFrame.height()/2, radarFrame.width(), radarFrame.top+radarFrame.height()/2, redpaint); //X
		c.drawLine(radarFrame.left+radarFrame.width()/2, radarFrame.top ,radarFrame.left+radarFrame.width()/2 , radarFrame.bottom, greenpaint); //Y
		
		
		
		
		
	}
	
	
	//Basic top down radar, (Just angle around y-axis)
	public void radarDraw(Canvas c){
		int cx=30; int cy=30; int radius=30;//circle centre, radius
		double angle=Math.PI*mSensors.rotation_y/180; //(-180,180), NORTH at 0
		int xi=(int) (cx+radius*Math.cos(angle)); //end of central radar ray
		int yi=(int) (cy+radius*Math.sin(angle)); //end of central radar ray
		
		c.drawCircle(cx, cy, radius, bluepaint);
		c.drawLine(cx, cy, xi, yi, greenpaint);
		enemiesDraw(c,cx,cy,radius);
	}
	
	
	
	
	public HUD(SensorsOutput so,EnemyPositions enemies){
		mSensors =so;
		mEnemies = enemies;
		
		redpaint.setColor(Color.RED);greenpaint.setColor(Color.GREEN);bluepaint.setColor(Color.BLUE);

	}
	public void draw(Canvas c){
		debugDraw(c);
		
		//radarDraw(c);
		axisDraw(c);
		
		Vector3D directionCam = new Vector3D(10,10,10);
		int xmin=mSensors.mySensorManager.AXIS_MINUS_X;
		
		
		arrowDraw(c,directionCam);
	}
	
}
