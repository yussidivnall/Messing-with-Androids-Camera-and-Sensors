package uc.camera.CameraGames;

import android.graphics.*;
import android.util.Log;
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
		//double distance=Utils.getMagnitude(myVector);
		double distance=10;
		
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
	public void placeEnemy(Canvas c,Vector3D camRot){
		double camThetaY=camRot.Y*Math.PI /180;
		double viewRange = Math.PI/2; // This might need tunning
		double leftFieldOfView = camThetaY-viewRange/2;
		double rightFieldOfView = camThetaY+viewRange/2;
		
		
		for (Enemy e : mEnemies.mEnemies){
			double enemyThetaY;
			if(e.mPosition.X==0){
				if(e.mPosition.Z <0)enemyThetaY=Math.PI/4;
				else enemyThetaY=3*Math.PI/4;
			}else{
				enemyThetaY=Math.atan(e.mPosition.Y/e.mPosition.X); // angle of the enemy on Y
			}
			Log.d("GameLogic.placeEnemy","Enemy Theta Y: "+enemyThetaY+" ;CameraThetaY: "+camThetaY);
			
			
			
			Vector3D enemyScrPos=getScreenPosition(e.getPosition());
			if (enemyScrPos != null){
				
				Rect r;
				c.drawBitmap(e.mImage, (float)enemyScrPos.X,(float)enemyScrPos.Y,null);
				c.drawCircle((float)enemyScrPos.X, (float)enemyScrPos.Y, 10, new Paint(Color.CYAN));
			}
		}
	}
	
	

	
	public void updateCanvasSize(Canvas c){
		if(c.getWidth()!=CanvasWidth) CanvasWidth=c.getWidth();
		if(c.getHeight()!=CanvasHeight) CanvasHeight=c.getHeight();
	}
	
	
	public void draw(Canvas canvas){
		updateCanvasSize(canvas);
		
		
		//placement(canvas); //TODO get rid of this...
		
		Vector3D camRot = mSensors.getOrientation();
		placeEnemy(canvas,camRot);
		
		
		//mEnemies.draw(canvas,mSensors);
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
