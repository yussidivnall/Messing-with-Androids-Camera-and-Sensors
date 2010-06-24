package uc.camera.CameraGames;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class GameLogic {
	public final float VIEWRANGE=45/3f;
	
	SensorsOutput mySensors;
	Vector3D myVector;
	
	
	GameLogic(SensorsOutput so){
		mySensors=so;
		myVector = new Vector3D(10,10,10);
	}
	public void pannel(Canvas c){
		Paint paint = new Paint();
		paint.setColor(Color.YELLOW);
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
	}
	
	
	public void placement(Canvas c){
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		
		double distance=Utils.getMagnitude(myVector);
		double rotation_y=Utils.getYAxisAngle(myVector);
		double difference_y=mySensors.rotation_y-rotation_y; //difference in angle on the y axis between the camera and the point's position
		
		double rotation_x=Utils.getXAxisAngle(myVector);
		double difference_x=mySensors.rotation_x-rotation_x;
		
		if(difference_y > -VIEWRANGE && difference_y < VIEWRANGE){
			float unit=c.getWidth()/(2*VIEWRANGE);
			//double position = c.getWidth()/2*difference;
			c.drawCircle((float)(c.getWidth()/2-(unit*difference_y)), c.getHeight()/2, 10, paint);
		}
	}
	
	
	public void drawPoint(Canvas c){
		/*Position pos = new Position(10,10,10);
		//Tan(angle) = Opp/Adj
		double point_angle = Math.atan(pos.Z/pos.X)*180/Math.PI;
		double difference_in_angles = mySensors.rotation_x-point_angle;
		
		Paint paint = new Paint();
		paint.setColor(Color.GRAY);
		c.drawText("difference:"+difference_in_angles , 100, 200, paint);
		//c.drawCircle((float)(c.getWidth()/2+difference_in_angles), 90, 10, paint);
		
		if(difference_in_angles < 45 && difference_in_angles > -45){
		
			if(difference_in_angles > 0){
				c.drawCircle((float)(c.getWidth()/2-difference_in_angles*c.getWidth()/90), c.getHeight()/2, 10, paint);
			}else {
				c.drawCircle((float)(c.getWidth()/2+difference_in_angles*c.getWidth()/90), c.getHeight()/2, 10, paint);
			}
		}
		*/
		
		/*
		long rat=pos.Z/pos.X;
		double a =Math.atan(rat)*180/Math.PI;
		double diff_x
		Paint p = new Paint();
		p.setColor(Color.MAGENTA);
		c.drawText("angle: "+a, 50, 60, p);
		int width = c.getWidth(); int height = c.getHeight();
		//c.drawCircle(width/2, height/2, 10, p);
		if(mySensors.rotation_x+45 >a && mySensors.rotation_x-45 < a){
			if(mySensors.rotation_x >a){ 
				c.drawCircle(width/2+mySensors.rotation_x*7, height/2, 10, p);
			}
			if(mySensors.rotation_x <a){ 
				c.drawCircle(width/2-mySensors.rotation_x*7, height/2, 10, p);
			}			
		}
		*/
		
		
	}
	
	public void draw(Canvas canvas){
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
