package uc.camera.CameraGames;

import android.util.Log;

public class Vector3D {
	double X,Y,Z;
	Vector3D(double x, double y,double z){
		X=x;
		Y=y;
		Z=z;
	}
	
	//If X,Y,Z represent a point, than this returns the angle from the centre, looking north
	//TODO this \/
	public Vector3D PositionToAngle(){
		/*  
		   
		    
		.-----
		
		*/
		float angle_x=(float) Math.atan(Z/Y);
		float angle_y=(float) Math.atan(X/Z);
		float angle_z=(float) Math.atan(Y/X);
		
		X=angle_x;
		Y=angle_y;
		Z=angle_z;
		
		return this;
		//Log.d("Position to angle", "Rotation y:"+angle_y);
	}
}


