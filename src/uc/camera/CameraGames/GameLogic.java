package uc.camera.CameraGames;

import android.graphics.*;
import android.util.Log;
import android.content.res.Resources;

public class GameLogic {
	//public final float VIEWRANGE=45/3f;
	public final float VIEWRANGE=(float)Math.PI/8;
	
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
	
	
	
	//ERROR HERE
	//THIS IS NOT MODULUS, EI, if 
	public Vector3D getScreenPosition(Vector3D v){
		//Returns screen coordinates of point with Z being distance
		//double distance=Utils.getMagnitude(myVector);
		
		double camThetaY=mSensors.getOrientation().Y;
		
		double distance=Utils.getMagnitude(v);
		double thetaY=Utils.getYAxisAngle(v);
		Log.d("GameLogic.getScreenPosition", "=======");
		Log.d("GameLogic.getScreenPosition", "ThetaY"+thetaY);
		if (camThetaY>=0 &&thetaY < Math.PI/2 ){ 
			Log.d("GameLogic.getScreenPosition", "Q1");
		}//Q1
		else if (camThetaY>=Math.PI/2 &&thetaY < Math.PI ){
			Log.d("GameLogic.getScreenPosition", "Q2");
		} //Q2
		else if (camThetaY>=Math.PI &&thetaY < 3*Math.PI/4 ){
			Log.d("GameLogic.getScreenPosition", "Q3");
		} //Q3
		else if (camThetaY>=3*Math.PI/4 &&thetaY < 2*Math.PI ){
			Log.d("GameLogic.getScreenPosition", "Q4");
		} //Q4
		else if (camThetaY>2*Math.PI){
			Log.d("GameLogic.getScreenPosition", "Bigger then 2PI, needs adjusting errorish");
		}
		
		
		double rotation_y=Utils.getYAxisAngle(myVector);
		if(rotation_y-VIEWRANGE <0){
//			Log.d("GameLogic","rotation y - viewrange <0");
		}
		double rotation_x=Utils.getXAxisAngle(myVector);
		if(rotation_x-VIEWRANGE <0){
//			Log.d("GameLogic","rotation x - viewrange <0");
		}		
		
		double difference_y=mSensors.rotation_y-rotation_y; //difference in angle on the y axis between the camera and the point's position
		if (difference_y <0){
//			Log.d("GameLogic","diff y <0");
			difference_y+=2*Math.PI;
		}
		

		double difference_x=mSensors.rotation_x-rotation_x;
		if (difference_x < 0){
			difference_x+=2*Math.PI;
		}
		
		float center_x=CanvasWidth/2;
		float center_y=CanvasHeight/2;
		
		
		
		//if (rotation_y+VIEWRANGE>360)
		//{
		//	Log.d("GameLogic.getScreenPosition","rotation+VIEWRANGE bigger then 360"+(rotation_y+VIEWRANGE));
		//}
		//Log.d("GameLogic.getScreenPosition", "rotation_y"+rotation_y +"  Viewrange:"+VIEWRANGE);
		
		
		//if (rotation_x+VIEWRANGE < 0) rotation_x+=2*Math.PI;

		//if(difference_y <0){difference_y+= 2*Math.PI;}
		//if(difference_x <0){difference_x+= 2*Math.PI;}
		
		//THIS IS IT!!!\/
		if(difference_y > -VIEWRANGE && difference_y < VIEWRANGE){
			float unit_x=CanvasWidth/(2*VIEWRANGE);
			float unit_y=CanvasHeight/(2*VIEWRANGE);
			
			float x=(float)(center_x-(unit_x*difference_y));
			float y=(float)(center_y-(unit_y*difference_x)); // TODO fix this :-?
			//Vector3D ret = new Vector3D(x,y,distance);
			Vector3D ret = new Vector3D(x,10,distance); //for now doom like ,not quake like :-/, rotate on y only
			return ret;
		}else if(difference_y <0){//HERE, RETURN SOMETHING...
			
			return null;
		}else if (difference_y > Math.PI*2){
			return null;
		}else if (difference_x <0){
			return null;
		}else if (difference_x > Math.PI*2){
			return null;		
		}else{ return null;}
		
	}
	public void placeEnemy(Canvas c,Vector3D camRot){
		double camThetaY=camRot.Y*Math.PI /180;
		double viewRange = Math.PI/2; // This might need tunning
		double leftFieldOfView = camThetaY+viewRange/2;
		double rightFieldOfView = camThetaY-viewRange/2;
		
		//Log.d("GameLogic.placeEnemy","=========================");
		for (Enemy e : mEnemies.mEnemies){
			double enemyThetaY;
			
			
			
			//Avoid division by 0 :-/
			if(e.mPosition.X==0){
				if(e.mPosition.Z <0)enemyThetaY=Math.PI/4;
				else enemyThetaY=3*Math.PI/4;
			}else{
				enemyThetaY=Math.atan(e.mPosition.Y/e.mPosition.X); // angle of the enemy on Y
				//This outputs from -pi to pi, but camera is from 0 to 2pi, adjust
				enemyThetaY+=Math.PI;
			}
			
			if (enemyThetaY > rightFieldOfView && enemyThetaY < leftFieldOfView){ //enemy in view range in thetaY
				//Calculate position on screen
				
			}
			Vector3D enemyScrPos=getScreenPosition(e.getPosition());
			if (enemyScrPos != null){
				try{
					Rect r;
					float x=(float)enemyScrPos.X-e.mImage.getWidth()/2;
					float y=20;
					float enemyDistance=(float)Utils.getMagnitude(e.mPosition);
					Matrix matrix=new Matrix();
					matrix.setTranslate(x, y);
					
					float scale = 1/enemyDistance;
					
					matrix.preScale(scale, scale);
					//matrix.postScale(1,1);
					c.drawBitmap(e.mImage, matrix, null);
					
					
				}catch(Exception excp){
					excp.printStackTrace();
				}
				
				//c.drawBitmap(e.mImage, (float)enemyScrPos.X,(float)enemyScrPos.Y,null);
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
