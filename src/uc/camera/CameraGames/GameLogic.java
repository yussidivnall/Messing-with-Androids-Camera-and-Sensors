package uc.camera.CameraGames;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class GameLogic {
	public final float VIEWRANGE=45/3f;
	
	SensorsOutput mySensors;
	Vector3D myVector;
	public int CanvasWidth=1,CanvasHeight=1;
	
	GameLogic(SensorsOutput so){
		mySensors=so;
		myVector = new Vector3D(10,10,10);
	}
	public void pannel(Canvas c){
		Paint redpaint = new Paint();redpaint.setColor(Color.RED);
		Paint greenpaint = new Paint();greenpaint.setColor(Color.GREEN);
		Paint bluepaint = new Paint();bluepaint.setColor(Color.BLUE);
		
		double rotation_X_axis=mySensors.rotation_x;
		double rotation_Y_axis=mySensors.rotation_y;
		double rotation_Z_axis=mySensors.rotation_z;
		
		
		c.drawText("rotation on X:"+rotation_X_axis, 50, 50, redpaint);
		c.drawText("rotation on Y:"+rotation_Y_axis, 50, 60, greenpaint);
		c.drawText("rotation on Z:"+rotation_Z_axis, 50, 70, bluepaint);
		
		/*
		double distance=Utils.getMagnitude(myVector);
		double rotation_y=Utils.getYAxisAngle(myVector);
		c.drawText("distance: "+distance, 0, 20, paint);
		c.drawText("y rotation: "+rotation_y, 0, 30, paint);
		
		double difference=mySensors.rotation_y-rotation_y;
		paint.setColor(Color.GREEN);
		c.drawText("difference: "+difference, 0, 40, paint);
		c.drawText("Phone x rotation"+mySensors.rotation_x, 0, 50, paint);
		
		
		
		double pointXAvisAngle=Utils.getXAxisAngle(myVector);
		double XAxisAngleDifference=mySensors.rotation_x-pointXAvisAngle;
		c.drawText("point X axis angle:"+pointXAvisAngle,0,90,paint);
		c.drawText("Difference on the x axis rotation:"+XAxisAngleDifference,0,110,paint);
		*/
		
	}
	
	
	public Vector3D getScreenPosition(Vector3D v){
		//Returns screen coordinates of point with Z being distance
		double distance=Utils.getMagnitude(myVector);
		double rotation_y=Utils.getYAxisAngle(myVector);
		double difference_y=mySensors.rotation_y-rotation_y; //difference in angle on the y axis between the camera and the point's position
		
		double rotation_x=Utils.getXAxisAngle(myVector);
		double difference_x=mySensors.rotation_x-rotation_x;
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
	
	
	public void placement(Canvas c){
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		Vector3D p = getScreenPosition(myVector);
		if(p!=null){
			c.drawCircle((float)p.X, (float)p.Y, (float)p.Z*3, paint);
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
		placement(canvas);
		pannel(canvas);
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
