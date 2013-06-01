package uc.camera.CameraGames;

import java.io.IOException;
//import java.util.List;

import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
//import android.hardware.Camera.Size;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
//import android.view.SurfaceHolder.Callback;
import android.widget.Toast;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
	SurfaceHolder mHolder;
	Camera mCamera;
	
	public CameraPreview(Context context) {
		super(context);
		mHolder=this.getHolder();
		
		mHolder.addCallback(this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		Parameters params = mCamera.getParameters();
		//params.setPreviewSize(w, h);
		//mCamera.setParameters(params);
		mCamera.startPreview();
	}
	
	public void surfaceCreated(SurfaceHolder holder) {	
		mCamera = Camera.open();
		try{
			mCamera.setPreviewDisplay(holder);
		}catch(IOException ioe){
			mCamera.release();
			mCamera=null;
			Toast.makeText(this.getContext(), "Hey", 1000);
			Log.w("", "Camera failed to set preview");
		}
		
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		
		// TODO Auto-generated method stub
		mCamera.stopPreview();
		mCamera.release();
		mCamera=null;
		
	}

}
