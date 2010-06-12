package uc.camera.CameraGames;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;



public class OverlayView extends View {
	private class UpdateTimerTask extends TimerTask{
		@Override
		public void run() {
			update();
			postInvalidate();
		}
		
	}
	
	long step=1;
	GameLogic myGameLogic;
	Timer timer;
	SensorsOutput mySensors;

	public OverlayView(Context c,SensorsOutput so){
		super(c);
		mySensors=so;
		myGameLogic=new GameLogic(mySensors);
		timer = new Timer();
		timer.schedule(new  UpdateTimerTask(), 0, step);
		this.invalidate();	
	}
	
	public void update(){
		myGameLogic.advance(step);
	}
	
	public void setSensorsOutput(SensorsOutput so){
		mySensors=so;
		myGameLogic=new GameLogic(mySensors);
	}
	
	protected void onDraw(Canvas c){
		super.onDraw(c);
		Paint p = new Paint();
		p.setColor(Color.RED);
		c.drawText("Rotation x:"+mySensors.rotation_x+"\t y:"+mySensors.rotation_y+"\t z:"+mySensors.rotation_z, 5, 50, p);
		c.drawText("Acceleration x:"+mySensors.acceleration_x+"\t y:"+mySensors.acceleration_y+"\t z:"+mySensors.acceleration_z, 5, 70, p);
		myGameLogic.draw(c);
	}

}
