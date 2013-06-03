package uc.camera.CameraGames;

//import java.util.TimerTask;

import android.app.Activity;

//import android.graphics.Canvas;
//import android.graphics.Paint;
import android.hardware.Camera;
import android.hardware.SensorManager;
import android.os.Bundle;



//import android.view.View;

import android.util.Log;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
//import android.widget.Toast;
import android.opengl.GLSurfaceView;

public class CameraGames extends Activity {
	Camera mCamera;
	OverlayView overlay;
	GameLogic mGameLogic;
	SensorsOutput mSensors;
	
	//GLSurfaceView mGLSurfaceView;
    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	try{
        CameraPreview preview = new CameraPreview(this); //Grab camera layer
        mSensors = new SensorsOutput((SensorManager)getSystemService(SENSOR_SERVICE)); //Sensors 
        overlay= new OverlayView(this,mSensors);//Grab OverlayView
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(preview);  //Set camera preview as base content
        this.addContentView(overlay, new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)); //add another content
    	}catch (Exception e){
    		Log.d("OnCreate exception", e.toString());
    	}finally{Log.d("Oncreate", "Finally");};
    }
    
    @Override
    protected void onStart(){
    	super.onStart();
    	Log.d("onStart", "Called");
    }
    @Override 
    protected void onPause(){
    	try{
	    	super.onPause();
	    	//sensors.wait();
	    	//mCamera.release();
	    	overlay.pause();
	    	Log.d("onPause", "Called");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    @Override
    protected void onResume(){
    	super.onResume();
    	overlay.resume();
    	mSensors.resume();
    	Log.d("onResume", "Called");
    }
    @Override
   	protected void onRestart(){
    	super.onRestart();
    	onResume();
    	mSensors.resume();
    	Log.d("onRestart", "Called");
    }
    @Override
    protected void onStop(){
    	super.onStop();
    	//onPause();
    	overlay.pause();
    	mSensors.pause();
    	Log.d("onStop", "Called");
    }
    
}