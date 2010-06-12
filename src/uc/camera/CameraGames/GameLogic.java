package uc.camera.CameraGames;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class GameLogic {
	SensorsOutput mySensors;
	GameLogic(SensorsOutput so){
		mySensors=so;
	}
	public void draw(Canvas canvas){
		if(mySensors.rotation_x> 180 && mySensors.rotation_x <270){
			Paint p = new Paint();
			p.setColor(Color.BLUE);
			canvas.drawCircle(mySensors.rotation_x, -mySensors.rotation_y, 5+mySensors.acceleration_z*10, p);
		}
	}
	
	public void advance(long step){
		
	}
}
