package uc.camera.CameraGames;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

public class HUD {
	SensorsOutput mSensors;
	EnemyPositions mEnemies;
	Radar mRadar;
	
	
	class Radar{
		Rect radarFrame=new Rect(200,200,50,50);
		public Radar(int x,int y,int w,int h){
			
		}
		
		
		
		//draw the x,y,z axis
		public void axisDraw(Canvas c){
			c.drawRect(radarFrame, blackpaint);		
//			c.drawLine(radarFrame.left, radarFrame.top, radarFrame.bottom, radarFrame.width(), bluepaint); //Z
			c.drawLine(radarFrame.left, radarFrame.top, radarFrame.bottom, radarFrame.right, bluepaint); //Z
			c.drawLine(radarFrame.left, radarFrame.top+radarFrame.height()/2, radarFrame.right, radarFrame.top+radarFrame.height()/2, redpaint); //X
			c.drawLine(radarFrame.left+radarFrame.width()/2, radarFrame.top ,radarFrame.left+radarFrame.width()/2 , radarFrame.bottom, greenpaint); //Y
		}
		//draw field of vision
		public void fovDraw(Canvas c,Vector3D camRot){
			

			rayDraw(c, new Vector3D(0,-0.5,0) );
			rayDraw(c, new Vector3D(0,0.5,0) );
			
			RectF oval = new RectF(0,0,100,150);
			boolean useCenter=true;
			float startAngle = 10f;
			float sweepAngle=5f;
			c.drawArc(oval, startAngle, sweepAngle, useCenter, new Paint(Color.WHITE));
			
		}
		
		
		//draw a circle in the enemy coord
		public void enemiesDraw(Canvas c,EnemyPositions enemies){
			for (Enemy e : enemies.getList()){
				Vector3D epos=e.getPosition();
				//Log.d("HUD.Radar.enemiesDraw", ""+epos.X+" "+epos.Y+" "+epos.Z);
				int[] pt=mRadar.getXYCoord(epos);
				c.drawCircle(pt[0], pt[1], 2, redpaint);
				gridDraw(c,epos);
			}
		}
		
		//draw a box from axis to point  (asymptotes)
		public void gridDraw(Canvas c,Vector3D point){
			Vector3D l1start=new Vector3D(point.X,0,0);
			Vector3D l1end=  new Vector3D(point.X,0,point.Z);
			c.drawLine(getXYCoord(l1start)[0],getXYCoord(l1start)[1], getXYCoord(l1end)[0], getXYCoord(l1end)[1], graypaint);
			
			Vector3D l2start=new Vector3D(0,0,point.Z);
			Vector3D l2end=new Vector3D(point.X,0,point.Z);
			c.drawLine(getXYCoord(l2start)[0],getXYCoord(l2start)[1], getXYCoord(l2end)[0], getXYCoord(l2end)[1], graypaint);
			
			Vector3D l3start=new Vector3D(point.X,0,point.Z);
			Vector3D l3end=new Vector3D(point.X,point.Y,point.Z);
			c.drawLine(getXYCoord(l3start)[0],getXYCoord(l3start)[1], getXYCoord(l3end)[0], getXYCoord(l3end)[1], graypaint);
			
			
			
		}
		
		
		public void rayDraw(Canvas c,Vector3D rotation){
			//double thetaY= rotation.Y*Math.PI/180;
			//double thetaZ= rotation.Z*Math.PI/180;
			double thetaY= rotation.Y;
			double thetaZ= rotation.Z;
			
			double magnitude=50;
			double x=magnitude * Math.cos(thetaY);
			double z=magnitude * Math.sin(thetaY);
			double y=magnitude * Math.sin(thetaZ);
			Vector3D endpoint = new Vector3D(x,y,z);
			gridDraw(c,endpoint);
			
			int[] start = getXYCenter();
			int[] end = getXYCoord(endpoint);
			c.drawLine(start[0], start[1], end[0], end[1], new Paint(Color.WHITE));
			
		}
		//get radar screen center;
		public int[] getXYCenter(){
			int x=radarFrame.left+radarFrame.width()/2;
			int y=radarFrame.left+radarFrame.height()/2;
			int ret[]={x,y};
			return ret;
		}
		
		public int[] getXYCoord(Vector3D position){
			//Calculate as, (ortho)
			//x=pos.x-(1/2)*pos.z
			//y=pos.y-(1/2)*pos.z
			
			int[] radcenter=getXYCenter();
			
			int x=(int)(radcenter[0]+position.X-(position.Z/2) );
			int y=(int)(radcenter[1]+position.Y-(position.Z/2) );
			//Log.d("HUD.Radar getXYCoord", "returning: x:"+x+" ,y:"+y);
			int ret[] = {x,y};
			return ret;
		}
	}
	
	
	
	
	
	
	
	Paint redpaint = new Paint();
	Paint greenpaint = new Paint();
	Paint bluepaint = new Paint();	
	Paint blackpaint = new Paint();
	Paint graypaint = new Paint();
	Paint whitepaint = new Paint();
	//graypaint.setColor(android.graphics.Color.GRAY);
	
	
	
	
	

	public void debugDraw(Canvas c){

		double rotation_X_axis=mSensors.rotation_x;
		double rotation_Y_axis=mSensors.rotation_y;
		double rotation_Z_axis=mSensors.rotation_z;
		
		
		c.drawText("rotation on X:"+rotation_X_axis, 250, 50, redpaint);
		c.drawText("rotation on Y:"+rotation_Y_axis, 250, 60, greenpaint);
		c.drawText("rotation on Z:"+rotation_Z_axis, 250, 70, bluepaint);
	}
	
	
	
	
	
	

	public void arrowDraw(Canvas c,Vector3D vector){}
	

	
	/*
	//Basic top down radar, (Just angle around y-axis)
	public void radarDraw(Canvas c){
		int cx=30; int cy=30; int radius=30;//circle centre, radius
		double angle=Math.PI*mSensors.rotation_y/180; //(-180,180), NORTH at 0
		int xi=(int) (cx+radius*Math.cos(angle)); //end of central radar ray
		int yi=(int) (cy+radius*Math.sin(angle)); //end of central radar ray
		
		c.drawCircle(cx, cy, radius, bluepaint);
		c.drawLine(cx, cy, xi, yi, greenpaint);
		//enemiesDraw(c,cx,cy,radius);
	}*/
	
	
	
	
	public HUD(SensorsOutput so,EnemyPositions enemies){
		mSensors =so;
		mEnemies = enemies;
		mRadar = new Radar(0,0,200,200);
		
		
		redpaint.setColor(Color.RED);greenpaint.setColor(Color.GREEN);bluepaint.setColor(Color.BLUE);
		graypaint.setColor(Color.GRAY);

	}
	public void draw(Canvas c){
		debugDraw(c);
		
		
		mRadar.axisDraw(c);
		Vector3D camRot = mSensors.getOrientation();
		int xmin=mSensors.mSensorManager.AXIS_MINUS_X;
		
		mRadar.enemiesDraw(c,mEnemies);
		mRadar.rayDraw(c,camRot);
		//mRadar.fovDraw(c,camRot);
		
		//arrowDraw(c,orienationCam);
	}
	
}
