package uc.camera.CameraGames;

public class Utils {
	public static double getMagnitude(Vector3D vector){
		double ret=Math.sqrt(vector.X+vector.Y+vector.Z);
		return ret;
	}
	
	//Returns the X and Y axis angle in respect to point {0,0,0}
	//getYAxisAngle: Y axis, relative to north
	public static double getYAxisAngle(Vector3D vector){
		double ret=Math.atan(vector.Z/vector.X)*180/Math.PI;
		return ret;
	}
	//getXAxisAngle: relative to floor
	public static double getXAxisAngle(Vector3D vector){
		//double ret=0;
		double hyp=getMagnitude(vector);
		double opp=vector.Y;
		double adj=Math.sqrt(hyp*hyp-opp*opp);//b^2=sqrt(a^2-c^2)
		//double ret = Math.acos(adj/hyp)*180/Math.PI;
		double rat=(opp/hyp)*Math.PI/180;
		double ret = Math.asin(rat)*180;
		return ret;
		//return (Math.asin( (Math.sin(90)*opp)/hyp ));
	}
}