package uc.camera.CameraGames;

//import java.util.TimerTask;

import android.app.Activity;
//import android.graphics.Canvas;
//import android.graphics.Paint;
import android.hardware.Camera;
import android.hardware.SensorManager;
import android.os.Bundle;
//import android.view.View;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
//import android.widget.Toast;

public class CameraGames extends Activity {
	Camera mCamera;
	OverlayView overlay;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
        CameraPreview preview = new CameraPreview(this);
        
        SensorsOutput sensors = new SensorsOutput((SensorManager)getSystemService(SENSOR_SERVICE));
        overlay= new OverlayView(this,sensors);
        overlay.setSensorsOutput(sensors);

      //this.setContentView(overlay);
      //this.addContentView(preview, new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        
        
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(preview);
        //Toast.makeText(this, "Hey ", 100000);
        this.addContentView(overlay, new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));   
    }
}