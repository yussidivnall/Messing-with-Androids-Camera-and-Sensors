package uc.camera.CameraGames;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
	SurfaceHolder mHolder;
	Camera mCamera;
	
	public CameraPreview(Context context) {
		super(context);
		mHolder=this.getHolder();
		mHolder.addCallback(this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		//mHolder.setType(SurfaceHolder.SURFACE_TYPE_HARDWARE);
		// TODO Auto-generated constructor stub
	}

	

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		mCamera.startPreview();
	}
	
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
		mCamera = Camera.open();
		try{
			mCamera.setPreviewDisplay(holder);
		}catch(IOException ioe){
			mCamera.release();
			mCamera=null;
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		mCamera.stopPreview();
		mCamera.release();
		mCamera=null;
	}

}
