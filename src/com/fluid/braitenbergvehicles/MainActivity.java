package com.fluid.braitenbergvehicles;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Camera.Parameters;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.VideoView;

public class MainActivity extends Activity 
{

	public static boolean bodyButtonselected=false,wiringButtonselected=false,sensorButtonselected=false;
	public static boolean singleBodyselected=false,dualBodyselected=false;
	
	ImageButton bodyButton,sensorButton,wiringButton;
	ImageButton sensorColorButton,sensorEdgeButton,sensorAreaButton;
	ImageButton singleBodyButton,dualBodyButton,connectionButton;
	static ImageButton playButton;
	
	ImageView BodyView;
	public static ImageView leftAnimationCircle,rightAnimationCircle,leftMotorAnimation,rightMotorAnimation;
	
	static ImageView textView;
	Timer timer ;
	MediaPlayer mediaPlayer;
	static boolean completed=false;
	static TextView vehicleType,sensorTextView;
	TextView quotationView,chapterView;
	VideoView view,progressBarView;
	int sensorNum;
	static boolean toggleConnection=false;
	
	
	 public void onCreate(Bundle savedInstanceState) 
	 {
		    super.onCreate(savedInstanceState);
		  
		    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		    
		    setContentView(R.layout.main);		    
		   
		    System.gc();
		    
		    initializeViewsandListeners();	
		    
			sensorEdgeButton.bringToFront();
			sensorEdgeButton.invalidate();
	   
	//bodyButton.bringToFront();

 }


public boolean onTouch(View v, MotionEvent event)
{
	
// 
  
	if(v.getId()==R.id.bodyButton)
		Log.d("Sucessful","yesyes");

   return false;
}

public static void setVehicleType()
{
	MainActivity.vehicleType.setVisibility(View.VISIBLE);
	
	
		if(!toggleConnection)
		{
		if(DrawingView.makeConnection1&&DrawingView.makeConnection4)
			vehicleType.setText("Vehicle: FEAR");
		else if(DrawingView.makeConnection2&&DrawingView.makeConnection3)
			vehicleType.setText("Vehicle: AGGRESSION");
		}
		
		else{
			
		if(DrawingView.makeConnection1&&DrawingView.makeConnection4)
			vehicleType.setText("Vehicle: TRUELOVE");
		else if(DrawingView.makeConnection2&&DrawingView.makeConnection3)
			vehicleType.setText("Vehicle: EXPLORER");
		}
}


public void setSensorType(int choice)
{
	if(choice==1)
		sensorTextView.setText("Sensor: Red");
	else if(choice==2)
		sensorTextView.setText("Sensor: Green");
	else sensorTextView.setText("Sensor: Blue");
	
	sensorNum=choice;
	
	sensorTextView.setVisibility(View.VISIBLE);
}

public static void ActivateSpecificRing(int ringNum)
{
	 if(ringNum==1)
		 leftAnimationCircle.setVisibility(View.VISIBLE);
	 else if(ringNum==2)
		 rightAnimationCircle.setVisibility(View.VISIBLE);
	 else if(ringNum==3)
		 leftMotorAnimation.setVisibility(View.VISIBLE);
	 else if(ringNum==4)
		 rightMotorAnimation.setVisibility(View.VISIBLE);
	 
	 textView.setImageResource(R.drawable.text401);
	 
}
public void initializeViewsandListeners()
{

	bodyButton=(ImageButton) findViewById(R.id.bodyButton);
	sensorButton=(ImageButton) findViewById(R.id.sensorButton);
	wiringButton=(ImageButton) findViewById(R.id.wiringButton);
	
	sensorColorButton=(ImageButton) findViewById(R.id.sensorcolorbutton);
	sensorEdgeButton=(ImageButton) findViewById(R.id.sensorEdgeButton);
	sensorAreaButton=(ImageButton) findViewById(R.id.sensorAreaButton);
	
	singleBodyButton=(ImageButton) findViewById(R.id.singleBody);
	dualBodyButton=(ImageButton) findViewById(R.id.dualBody);
	
	playButton=(ImageButton) findViewById(R.id.playButton);
	
	connectionButton=(ImageButton) findViewById(R.id.connectionButton);
	connectionButton.setImageResource(R.drawable.greenplus); 
	connectionButton.bringToFront();
	
	BodyView=(ImageView) findViewById(R.id.vehicleImageView);
	BodyView.setVisibility(View.INVISIBLE);
	
	leftAnimationCircle = (ImageView)findViewById(R.id.leftAnimationRing);
	leftAnimationCircle.setBackgroundResource(R.drawable.animation1);
	
	rightAnimationCircle = (ImageView)findViewById(R.id.rightAnimationRing);
	rightAnimationCircle.setBackgroundResource(R.drawable.animation2);
	
	leftMotorAnimation = (ImageView)findViewById(R.id.leftmotorAnimation);
	leftMotorAnimation.setBackgroundResource(R.drawable.animation3);
	
	rightMotorAnimation = (ImageView)findViewById(R.id.rightmotorAnimation);
	rightMotorAnimation.setBackgroundResource(R.drawable.animation4);
	
	textView=(ImageView)findViewById(R.id.textview1);
	textView.setImageResource(R.drawable.text101);
	
	vehicleType=(TextView)findViewById(R.id.vehicleType);
	vehicleType.setVisibility(View.INVISIBLE);
	
	sensorTextView=(TextView)findViewById(R.id.sensorTextView);
	sensorTextView.setVisibility(View.INVISIBLE);
	
	connectionButton.setVisibility(View.INVISIBLE);
	
	bodyButton.setOnTouchListener(new MyOnTouchListener());
	sensorButton.setOnTouchListener(new MyOnTouchListener());
	wiringButton.setOnTouchListener(new MyOnTouchListener());
	sensorColorButton.setOnTouchListener(new MyOnTouchListener());
	sensorEdgeButton.setOnTouchListener(new MyOnTouchListener());
	sensorAreaButton.setOnTouchListener(new MyOnTouchListener());
	singleBodyButton.setOnTouchListener(new MyOnTouchListener());
	dualBodyButton.setOnTouchListener(new MyOnTouchListener());
	playButton.setOnTouchListener(new MyOnTouchListener());
	
	connectionButton.setOnTouchListener(new MyOnTouchListener());

}

public static void activateLowerAnimatedRings()
{
	
	leftMotorAnimation.setVisibility(View.VISIBLE);
	rightMotorAnimation.setVisibility(View.VISIBLE);
	
	 AnimationDrawable frameAnimation = (AnimationDrawable) leftMotorAnimation.getBackground();
		// Start the animation (looped playback by default).
		frameAnimation.start();
		frameAnimation = (AnimationDrawable) rightMotorAnimation.getBackground();
		frameAnimation.start();
		
	leftAnimationCircle.setVisibility(View.INVISIBLE);
	rightAnimationCircle.setVisibility(View.INVISIBLE);
		
}

public static void deActivateRings()
{
	leftMotorAnimation.setVisibility(View.INVISIBLE);
	rightMotorAnimation.setVisibility(View.INVISIBLE);
	
}

public static boolean getWiringButtonStatus()
{
	return wiringButtonselected;
}

public static void activateUpperRings()
{
	if(wiringButtonselected)
	{
	leftAnimationCircle.setVisibility(View.VISIBLE);
	rightAnimationCircle.setVisibility(View.VISIBLE);
	}
}

private void play(Context context, String file) {
try {
    AssetFileDescriptor afd = context.getAssets().openFd(file);
    mediaPlayer.setDataSource(
            afd.getFileDescriptor(),
            afd.getStartOffset(),
            afd.getLength()
        );
    afd.close();
    mediaPlayer.prepare();
    
    mediaPlayer.start();
} catch (IllegalArgumentException e) {
    e.printStackTrace();
} catch (IllegalStateException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}
}

public void showStartButton()
{
	
playButton.setVisibility(View.VISIBLE);


}


// this is a canned method that should presumably work
private void writeStringToTextFile(String s, String f){
File sdCard = Environment.getExternalStorageDirectory();
File dir = new File (sdCard.getAbsolutePath());
dir.mkdirs();
File file = new File(dir, f);
try{
   FileOutputStream f1 = new FileOutputStream(file,false); //True = Append to file, false = Overwrite
   PrintStream p = new PrintStream(f1);
   p.print(s);
   p.close();
   f1.close();
} catch (FileNotFoundException e) {
} catch (IOException e) {
}   }

class MyOnTouchListener implements OnTouchListener{

	
	
     public boolean onTouch(View v, MotionEvent event) {
     
    	 
    	 
    	 
    	 
    if(event.getAction() == (MotionEvent.ACTION_UP)){
        //Do whatever you want after press
    	
    }
    
    if(event.getAction()==(MotionEvent.ACTION_DOWN))
    {
    	
    	deselectAll();
    	
    	if(v.getId()==R.id.sensorButton)
    		{
    		
    			Log.d("Sucessfil","Sensor Button");
		
    			if(!completed)
    				{textView.setImageResource(R.drawable.texterror2);
    				playButton.setVisibility(View.INVISIBLE);
    				}
    			else 
    			{
    				hideAnimations();
    			if(!sensorButtonselected)
    				{
    				sensorButton.setImageResource(R.drawable.sensorselected);
    				sensorColorButton.setVisibility(View.VISIBLE);
        			sensorEdgeButton.setVisibility(View.VISIBLE);
        			sensorAreaButton.setVisibility(View.VISIBLE);
        			
        			bodyButtonselected=wiringButtonselected=false;
    				}
    			
    			sensorButtonselected=!sensorButtonselected;
    			}
    		}
    	
    	else if(v.getId()==R.id.bodyButton)
    		{
	    		if(!bodyButtonselected)
				{
    			
	    		Log.d("Sucessfil","Body Button");
    			
    			System.gc();
    			// BUTTON visibility stuff ...
    			bodyButton.setImageResource(R.drawable.bodyselected);
    			singleBodyButton.setVisibility(View.VISIBLE);
    			dualBodyButton.setVisibility(View.VISIBLE);
    			
    			
    			sensorButtonselected=wiringButtonselected=false;
    			
    			
				}
	    		bodyButtonselected=!bodyButtonselected;
     		}
    	else if(v.getId()==R.id.wiringButton)
    		{
    			Log.d("Sucessful","Wiring Button");
    			MainActivity.playButton.setVisibility(View.INVISIBLE);
    			
    			if(!singleBodyselected &&!dualBodyselected)
    				textView.setImageResource(R.drawable.texterror1);
    			
    			else{
    				if(!wiringButtonselected)
    			
    			{
    				wiringButton.setImageResource(R.drawable.wiringselected);
    				sensorButtonselected=bodyButtonselected=false;
    				
    				textView.setImageResource(R.drawable.text301);
    				
    				if(dualBodyselected)
    				{
    				
    				leftAnimationCircle.setVisibility(View.VISIBLE);
    				rightAnimationCircle.setVisibility(View.VISIBLE);
    					
    				AnimationDrawable frameAnimation = (AnimationDrawable) leftAnimationCircle.getBackground();
    				// Start the animation (looped playback by default).
    				frameAnimation.start();
    				frameAnimation = (AnimationDrawable) rightAnimationCircle.getBackground();
    				frameAnimation.start();
    				
    				}
    				
    			}
    				else {
    					hideAnimations();
    				}
    			
    			wiringButtonselected=!wiringButtonselected;
    			}	
    	
    		connectionButton.setVisibility(View.VISIBLE);
    		}
    	else if(v.getId()==R.id.sensorEdgeButton)
    		{
    		
    			
				sensorButtonselected=!sensorButtonselected;
				sensorButton.setImageResource(R.drawable.sensorunselected);
				setSensorType(2);
				showStartButton();
				
				textView.setImageResource(R.drawable.text701);
				
    		}
    	else if(v.getId()==R.id.sensorcolorbutton)
    		{
    			sensorButtonselected=!sensorButtonselected;
    			sensorButton.setImageResource(R.drawable.sensorunselected);
    			setSensorType(1);
    			showStartButton();
    			textView.setImageResource(R.drawable.text701);
    		}
    	else if (v.getId()==R.id.sensorAreaButton)
    	{
	    		sensorButtonselected=!sensorButtonselected;
	    		sensorButton.setImageResource(R.drawable.sensorunselected);
	    		setSensorType(3);
	    		showStartButton();
	    		textView.setImageResource(R.drawable.text701);
    	}
    	else if(v.getId()==R.id.singleBody)
    	{
    			bodyButtonselected=!bodyButtonselected;
    			
    			System.gc();
    			
    			
    			bodyButton.setImageResource(R.drawable.bodyunselected);
    			
    			singleBodyselected=true;
    			dualBodyselected=false;
    			// Put the single body in the image view ..
    			BodyView.destroyDrawingCache();
    			
    			BodyView.setImageResource(R.drawable.unibody);
    			
    			BodyView.setVisibility(View.VISIBLE);
    			BodyView.destroyDrawingCache();
    			
    			textView.setImageResource(R.drawable.text201);
    			
    	}
    	
    	else if(v.getId()==R.id.dualBody)
    	{
    			bodyButtonselected=!bodyButtonselected;
    			System.gc();
    			
    			bodyButton.setImageResource(R.drawable.bodyunselected);
    			
    			dualBodyselected=true;
    			singleBodyselected=false;
    			
    			// Put the Dual body in the image view ..
    			
    			System.gc();
    			BodyView.setImageResource(R.drawable.twobody);
		
    			BodyView.setVisibility(View.VISIBLE);
    			
    			textView.setImageResource(R.drawable.text201);
    	}
    	
    	else if(v.getId()==R.id.connectionButton)
    	{
    		
    		
    		if(!toggleConnection)
    		connectionButton.setImageResource(R.drawable.redminus);
    		else connectionButton.setImageResource(R.drawable.greenplus);
    		
    		if(toggleConnection)
			{
			if(DrawingView.makeConnection1&&DrawingView.makeConnection4)
				vehicleType.setText("Vehicle: FEAR");
			else if(DrawingView.makeConnection2&&DrawingView.makeConnection3)
				vehicleType.setText("Vehicle: AGGRESSION");
			}
			
			else{
				
			if(DrawingView.makeConnection1&&DrawingView.makeConnection4)
				vehicleType.setText("Vehicle: TRUELOVE");
			else if(DrawingView.makeConnection2&&DrawingView.makeConnection3)
				vehicleType.setText("Vehicle: EXPLORER");
			}	
			
    		toggleConnection=!toggleConnection;
    	}
    	else if(v.getId()==R.id.playButton)
    	{
    			setContentView(R.layout.video);
    			
    			quotationView=(TextView)findViewById(R.id.quotationView);
    			view = (VideoView) findViewById (R.id.videoView1);
    			
    			chapterView=(TextView)findViewById(R.id.chapterView);
    			progressBarView=(VideoView)findViewById(R.id.progressbarview);
    			
    			// view.setVisibility(View.INVISIBLE);
    		    // THIS is the Video view Code ..
    			//    
    			//    
    			   view.setVisibility(View.VISIBLE);
    			   String videoName="",quotation="";
    			   
    			    if(vehicleType.getText()=="Vehicle: FEAR")
    			     {videoName= "fear"; 
    			     quotationView.setText(R.string.fear);
    			     chapterView.setText(R.string.fearquote);
    			     }
    			    else if(vehicleType.getText()=="Vehicle: AGGRESSION")
       			     {videoName= "aggression";
       			     quotationView.setText(R.string.aggression);
       			  	 chapterView.setText(R.string.aggressionquote);
       			     }
    			    else if(vehicleType.getText()=="Vehicle: TRUELOVE")
      			     {videoName= "permlove";
      			     quotationView.setText(R.string.permlove);
      			  	 chapterView.setText(R.string.permlovequote);
      			     }
    			    else if(vehicleType.getText()=="Vehicle: EXPLORER")
      			     {videoName= "templove"; 
      			     quotationView.setText(R.string.templove);
      			  	 chapterView.setText(R.string.templovequote);
      			     }
   			    
    			 
    			    
    			    // You build the URI to your video here
    			    StringBuilder uriPathBuilder = new StringBuilder ();
    			    uriPathBuilder.append ("android.resource://");
    			    uriPathBuilder.append (getPackageName());
    			    uriPathBuilder.append (File.separator);
    			    uriPathBuilder.append ("raw");
    			    uriPathBuilder.append (File.separator);
    			    uriPathBuilder.append (videoName);
    			    Uri uri = Uri.parse (uriPathBuilder.toString ());
    			
    			    view.setVideoURI (uri);
    			    view.start ();
    			    
    			    writeStringToTextFile(videoName+","+sensorNum+",	","settings.txt");
    			    
    			    view.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

    			        @Override
    			        public void onCompletion(MediaPlayer mp) {
    			        	Intent i = new Intent();
    	    			    PackageManager manager = getPackageManager();
    	    			    i = manager.getLaunchIntentForPackage("cc.openframeworks.BraitenbergVehicles");
    	    			    i.addCategory(Intent.CATEGORY_LAUNCHER);
    	    			    startActivity(i);
    			        }
    			    });
    			    
    			    //// TextView 
    			
    	}
    }
    return true;
}	 
	 void deselectAll()
	 {
		 
		 sensorColorButton.setVisibility(View.INVISIBLE);
		 sensorEdgeButton.setVisibility(View.INVISIBLE);
		 sensorAreaButton.setVisibility(View.INVISIBLE);
		 
		 singleBodyButton.setVisibility(View.INVISIBLE);
		 dualBodyButton.setVisibility(View.INVISIBLE);
		 
		 System.gc();
		 sensorButton.setImageResource(R.drawable.sensorunselected);
		 wiringButton.setImageResource(R.drawable.wiringunselected);
		 bodyButton.setImageResource(R.drawable.bodyunselected);
		 
	 }
	 
	void hideAnimations()
	{
		leftAnimationCircle.setVisibility(View.INVISIBLE);
		rightAnimationCircle.setVisibility(View.INVISIBLE);
		leftMotorAnimation.setVisibility(View.INVISIBLE);
		rightMotorAnimation.setVisibility(View.INVISIBLE);
	}
	




};

public static class BitmapResizer {

public static Bitmap decodeImage(Resources res, int id ,int requiredSize){
    try {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, id, o);

        //Find the correct scale value. It should be the power of 2.
        final int REQUIRED_SIZE=requiredSize;
        int width_tmp=o.outWidth, height_tmp=o.outHeight;
        int scale=1;
        while(true){
            if(width_tmp/2<REQUIRED_SIZE || height_tmp/2<REQUIRED_SIZE)
                break;
            width_tmp/=2;
            height_tmp/=2;
            scale*=2;
        }

        //decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize=scale;
        return BitmapFactory.decodeResource(res, id, o2);
    } catch (Exception e) {

    }
    return null;
}

}




}