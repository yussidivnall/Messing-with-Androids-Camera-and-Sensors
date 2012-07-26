package uc.camera.CameraGames;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;

public class GLRenderer implements GLSurfaceView.Renderer{

	@Override
	public void onDrawFrame(GL10 arg0) {
		// TODO Auto-generated method stub
		//gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		gl.glViewport(0, 0, width, height);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
		
	}

}
