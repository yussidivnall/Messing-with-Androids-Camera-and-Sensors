package uc.camera.CameraGames;

//import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorsOutput implements SensorEventListener{
	public SensorManager mSensorManager;
	Sensor orienationSensor;
	
	public float rotation_x,rotation_y,rotation_z;
	float acceleration_x,acceleration_y,acceleration_z;
	//public float x,y,z;
	
	public SensorsOutput(SensorManager sm){
		//super();
		mSensorManager=sm;
		
		//mySensorManager = (SensorManager)super.getSystemService(SENSOR_SERVICE);
		orienationSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        //Sensor aSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mSensorManager.registerListener(this, orienationSensor, SensorManager.SENSOR_DELAY_GAME);
        //mySensorManager.registerListener(this, aSensor, SensorManager.SENSOR_DELAY_NORMAL);
        
	}
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		if(event.sensor.getType()==Sensor.TYPE_ORIENTATION){
			rotation_y=event.values[0];
			rotation_x=event.values[1];
			rotation_z=event.values[2];
		}
		
		if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
			acceleration_x=event.values[0];
			acceleration_y=event.values[1];
			acceleration_z=event.values[2];
		}
	}
	public Vector3D getOrientation(){
		Vector3D ret = new Vector3D(rotation_x,rotation_y,rotation_z);
		//float[] values;
		//float[] R;
		//mSensorManager.getOrientation(R, values);
		return ret;
	}
	
	public void pause(){
		mSensorManager.unregisterListener(this);
	}
	public void resume(){
		mSensorManager.registerListener(this, orienationSensor, SensorManager.SENSOR_DELAY_UI);
	}
	

}
