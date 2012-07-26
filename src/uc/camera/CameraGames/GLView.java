package uc.camera.CameraGames;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.View;

public class GLView extends GLSurfaceView{
    
	public GLView(Context context){
        super(context);
        
        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(new GLRenderer());
    }
	

}
