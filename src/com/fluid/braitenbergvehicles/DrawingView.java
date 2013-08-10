package com.fluid.braitenbergvehicles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class DrawingView extends View{

	private Paint paint;
	Color customGray;
	float touchPosX,touchPosY;
	
	int highlighted=0,dragged=0;
	static boolean makeConnection1=false;
	static boolean makeConnection2=false;
	static boolean makeConnection3=false;
	static boolean makeConnection4=false;
	
	int []leftring_top={145,160,200,230};int []rightring_top={290,160,340,230};
	int []leftMotor={125,460,165,560};int []rightMotor={345,460,395,560};

	boolean leftSensorConnected=false,rightSensorConnected=false,leftMotorConnected=false,rightMotorConnected=false;
	
	int contextNum=0;
	public DrawingView(Context context, AttributeSet attributeSet)
	{
	    super(context, attributeSet);

	    paint =new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.GRAY);
		paint.setStyle(Style.FILL_AND_STROKE);
		
	}
	
	 protected void onDraw(Canvas canvas) 
	 {
		 
		 
		 
		// canvas.drawCircle(10, 20, 30, this.paint);
	    // your painting stuff
		 if(highlighted==1)
			 canvas.drawCircle(leftring_top[0]+30,leftring_top[1]+60,15,this.paint);
		 if(highlighted==2)
			 canvas.drawCircle(rightring_top[0]+20,rightring_top[1]+60,15,this.paint);
		 
		 if(dragged==1)
		 {
			 paint.setStrokeWidth(11);
			 canvas.drawLine(leftring_top[0]+30,leftring_top[1]+60, touchPosX,touchPosY,this.paint);
		 }
		 
		 if(dragged==2)
		 {
			 paint.setStrokeWidth(11);
			 canvas.drawLine(rightring_top[0]+20,rightring_top[1]+60, touchPosX,touchPosY,this.paint);
		 }
		 
		 if(makeConnection1)
		 {
			 canvas.drawCircle(leftring_top[0]+30,leftring_top[1]+60,15,this.paint);
			 
			 canvas.drawCircle(leftMotor[0]+20,leftMotor[1]-5,15,this.paint);
			 
			 canvas.drawLine(leftring_top[0]+30,leftring_top[1]+60,leftring_top[0]+30,leftMotor[1],this.paint);
			 canvas.drawLine(leftMotor[0]+20,leftMotor[1]-5,leftring_top[0]+30,leftMotor[1]-5,this.paint);
			 
		 }
		 if(makeConnection2)
		 {
			 canvas.drawCircle(leftring_top[0]+30,leftring_top[1]+60,15,this.paint);
			 canvas.drawCircle(rightMotor[0]-10,rightMotor[1]-5,15,this.paint);
			 
			 //canvas.drawLine(leftring_top[0]+30,leftring_top[1]+60,leftring_top[0]+30,rightMotor[1],this.paint);
			 canvas.drawLine(rightMotor[0]-10,rightMotor[1]-5,leftring_top[0]+30,leftring_top[1]+60,this.paint);
		 }
		 
		 if(makeConnection3)
		 {
			 canvas.drawCircle(rightring_top[0]+20,rightring_top[1]+60,15,this.paint);
			 canvas.drawCircle(leftMotor[0]+20,leftMotor[1]-5,15,this.paint);
			 
			 canvas.drawLine(rightring_top[0]+20,rightring_top[1]+60,leftMotor[0]+20,leftMotor[1]-5,this.paint);
		 }
		 if(makeConnection4)
		 {
			 canvas.drawCircle(rightring_top[0]+20,rightring_top[1]+60,15,this.paint);
			 canvas.drawCircle(rightMotor[0]-10,rightMotor[1]-5,15,this.paint);
			 
			 canvas.drawLine(rightring_top[0]+20,rightring_top[1]+60,rightring_top[0]+20,rightMotor[1],this.paint);
			 canvas.drawLine(rightring_top[0]+15,rightMotor[1]-5,rightring_top[0]+30,rightMotor[1]-5,this.paint);

		 }
	 }


	// events when touching the screen
      public boolean onTouchEvent(MotionEvent event) {
    	  	  Log.d("Touch position"," X: "+event.getX() + "  Y: "+event.getY());
    	  	  
    	  	  touchPosX=event.getX();touchPosY=event.getY();
    	  	  
    	  	  // 175,170
    	  	 
    	  
    	  	 if(event.getAction() == (MotionEvent.ACTION_DOWN)&&MainActivity.getWiringButtonStatus())
    	  	 {
    	  		 
    	  	  if(touchPosX>leftring_top[0]&&touchPosX<leftring_top[2]&&touchPosY>leftring_top[1]&&touchPosY<leftring_top[3])
    	  		  highlighted=1; // Highlighted = 1 means the left one is connected ...
    	  	  else if(touchPosX>rightring_top[0]&&touchPosX<rightring_top[2]&&touchPosY>rightring_top[1]&&touchPosY<rightring_top[3])
    	  		  highlighted=2;
  	  	  
    	  	  else highlighted=0;	
    	  	  
    	  	  }
    	  	 
    	  	 else if(event.getAction() == MotionEvent.ACTION_MOVE &&highlighted==1&&MainActivity.getWiringButtonStatus())
    	  	  {
    	  	   
    	  	   dragged=1;
    	  	   MainActivity.activateLowerAnimatedRings();
    	  	 MainActivity.textView.setImageResource(R.drawable.text4401);
   	  	   
//	    	  if(touchPosX>leftMotor[0]&&touchPosX<leftMotor[2]&&touchPosY>leftMotor[1]&&touchPosY<leftMotor[3])
//	   	  	   {
//	   	  		makeConnection1=true; makeConnection3=false;makeConnection2=false;//rightSensorConnected=false;
//	   	  		leftSensorConnected=true;leftMotorConnected=true;
//	   	  	   }
//	    	  
//	    	  else if(touchPosX>rightMotor[0]&&touchPosX<rightMotor[2]&&touchPosY>rightMotor[1]&&touchPosY<rightMotor[3])
//	   	  	   {
//	   	  		makeConnection2=true;makeConnection4=false;makeConnection1=false;//rightSensorConnected=false;
//	   	  		leftSensorConnected=true;rightMotorConnected=true;
//	   	  	   }
//  	  		
    	  	   
    	  	  }
    	  	 
    	  	 else if(event.getAction() == MotionEvent.ACTION_MOVE &&highlighted==2&&MainActivity.getWiringButtonStatus())
   	  	  {
    	  		 
    	  		dragged=2;
     	  	   MainActivity.activateLowerAnimatedRings();
     	  	MainActivity.textView.setImageResource(R.drawable.text4401);
     	  	   
// 	    	  if(touchPosX>leftMotor[0]&&touchPosX<leftMotor[2]&&touchPosY>leftMotor[1]&&touchPosY<leftMotor[3])
// 	   	  	   {
// 	   	  		makeConnection3=true; makeConnection1=false;makeConnection4=false;
// 	   	  		rightSensorConnected=true;leftMotorConnected=true;
// 	   	  	   }
// 	    	  
// 	    	  else if(touchPosX>rightMotor[0]&&touchPosX<rightMotor[2]&&touchPosY>rightMotor[1]&&touchPosY<rightMotor[3])
// 	   	  	   {
// 	   	  		makeConnection4=true; makeConnection2=false;makeConnection3=false;//leftSensorConnected=false;
// 	   	  		rightSensorConnected=true;rightMotorConnected=true;
// 	   	  	   }
//   	  		
     	  	   
     	  }	    
    	  	 
    	  	 else if(event.getAction() == MotionEvent.ACTION_UP)
    	  		 {	
    	  		 
    	  		 
    	  		 if(highlighted==1&&MainActivity.getWiringButtonStatus())
    	  		 {
    	  			  if(touchPosX>leftMotor[0]-30&&touchPosX<leftMotor[2]+10&&touchPosY>leftMotor[1]&&touchPosY<leftMotor[3])
    		   	  	   {
    		   	  		makeConnection1=true; makeConnection3=false;makeConnection2=false;//rightSensorConnected=false;
    		   	  		leftSensorConnected=true;leftMotorConnected=true;
    		   	  	   }
    		    	  
    		    	  else if(touchPosX>rightMotor[0]&&touchPosX<rightMotor[2]&&touchPosY>rightMotor[1]&&touchPosY<rightMotor[3])
    		   	  	   {
    		   	  		makeConnection2=true;makeConnection4=false;makeConnection1=false;//rightSensorConnected=false;
    		   	  		leftSensorConnected=true;rightMotorConnected=true;
    		   	  	   }
    	  		 }
    	  		 
    	  		 else if(highlighted==2&&MainActivity.getWiringButtonStatus())
    	  		 {
    	  			 if(touchPosX>leftMotor[0]-30&&touchPosX<leftMotor[2]+10&&touchPosY>leftMotor[1]&&touchPosY<leftMotor[3])
    	 	   	  	   {
    	 	   	  		makeConnection3=true; makeConnection1=false;makeConnection4=false;
    	 	   	  		rightSensorConnected=true;leftMotorConnected=true;
    	 	   	  	   }
    	 	    	  
    	 	    	  else if(touchPosX>rightMotor[0]&&touchPosX<rightMotor[2]&&touchPosY>rightMotor[1]&&touchPosY<rightMotor[3])
    	 	   	  	   {
    	 	   	  		makeConnection4=true; makeConnection2=false;makeConnection3=false;//leftSensorConnected=false;
    	 	   	  		rightSensorConnected=true;rightMotorConnected=true;
    	 	   	  	   }
    	   	  		
    	  		 }
    	  		
    	  		 highlighted=0;
    	  		 dragged=0;
    	  		 MainActivity.deActivateRings();
    	  		 
    	  		 
    	  		 
    	  		 if(!makeConnection1&&!makeConnection3&&!makeConnection2&&!makeConnection4)
    	  			MainActivity.activateUpperRings();
    	  		 if((makeConnection1||makeConnection2)&&(makeConnection3||makeConnection4))
    	  		 	{
    	  			 MainActivity.activateUpperRings();
    	  		 	
    	  		 	// Both Connections are Done ...
    	  			 
    	  			MainActivity.textView.setImageResource(R.drawable.text601);
    	  		 	
    	  			if(contextNum<1)
    	  			Toast.makeText(getContext(), "You can still change connections if you want!", Toast.LENGTH_LONG).show();
    	  			
    	  			MainActivity.completed=true;// This is used for the SensorButton and to make it clickable ..... 
    	  			contextNum++;
    	  			
    	  			MainActivity.setVehicleType();
    	  			
    	  		 	
    	  		 	}
    	  		 	
    	  		 if((makeConnection1||makeConnection2)&&(!makeConnection3&&!makeConnection4))
    	  			{MainActivity.ActivateSpecificRing(2);MainActivity.vehicleType.setVisibility(View.INVISIBLE);
    	  			MainActivity.sensorTextView.setVisibility(View.INVISIBLE);
    	  			MainActivity.playButton.setVisibility(View.INVISIBLE);
    	  			MainActivity.completed=false;  // This is used for the SensorButton ..... User must make the other connection before pressing the Sensor Button .....
    	  			}
    	  		else if((!makeConnection1&&!makeConnection2)&&(makeConnection3||makeConnection4))
   	  			 	{MainActivity.ActivateSpecificRing(1);	 MainActivity.vehicleType.setVisibility(View.INVISIBLE);
   	  			 	MainActivity.sensorTextView.setVisibility(View.INVISIBLE);
   	  			 	MainActivity.completed=false;  // This is used for the SensorButton ..... User must make the other connection before pressing the Sensor Button ..... 
   	  			 	MainActivity.playButton.setVisibility(View.INVISIBLE);
   	  			 	}
    	  		 
    	  		 }
    	  	 
    	  		 invalidate();
    	  	  
    	  	  return true;
      }
      
     public static String getState()
     {
    	 if(!makeConnection1&&!makeConnection2&&!makeConnection3&&!makeConnection4)
    	 	 return "No Connections";
    	 else if(makeConnection1||makeConnection2)
    		 return "Only Left";
    	 else if(makeConnection3||makeConnection4)
    		 return "Only Right";
    	 
    	 return null;
    	 
     }
      
      
}

