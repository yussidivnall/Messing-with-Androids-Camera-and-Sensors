package uc.camera.CameraGames;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.util.Log;




public class EnemyPositions {

	SensorsOutput mSensors;
	List<Enemy> mEnemies;
	
	//Constructor
	EnemyPositions(SensorsOutput so){
		mSensors=so;
		mEnemies = new ArrayList<Enemy>();
	}
	
	public List<Enemy>  getList(){
		return mEnemies;
	}
	

	
	public void add(Enemy enemy){
		mEnemies.add(enemy);
	}
	
	public void draw(Canvas c){
		double fieldOfView = Math.PI/4;
		
		for (Enemy e : mEnemies){
			//Asume pi/2 viewrange (90 degrees), pi/4(45 deg) to left of center  and pi/4 to right
			//might need to tune this;
			Vector3D ePos=e.getPosition(); //Enemy position
			float enemyYAngle=(float) (Math.atan(ePos.X/ePos.Z)*Math.PI/180);
			
			float playerRotationYAxis = (float) (mSensors.rotation_y*Math.PI/180); //Player rotation (left/right) (-pi,pi)
			float angles_difference = playerRotationYAxis-enemyYAngle;
			Log.d("EnemyPositions draw()", "angles_difference"+angles_difference);
			
			//Get angle from center to enemy
			//subtract yrot
			//check if in -pi/4,pi/4
			
			
			e.draw(c);
			
		}
			
	}
	
	
	
}
