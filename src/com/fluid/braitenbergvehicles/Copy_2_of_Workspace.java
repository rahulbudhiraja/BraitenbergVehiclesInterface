package com.fluid.braitenbergvehicles;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

public class Copy_2_of_Workspace extends ViewGroup{
	
	 ImageView imageView;
	 Bitmap Vehicle;
	 ToggleButton tButton;

public Copy_2_of_Workspace(Context context) {
		super(context);
	
       
		// TODO Auto-generated constructor stub
		paint =new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.RED);
		paint.setStyle(Style.FILL);
		
//		setBackgroundResource(R.drawable.grey_wall);
		
		//ImageView Setup
        imageView = new ImageView(getContext());
        //setting image resource
        imageView.setImageResource(R.drawable.vehicleaggression);
		Vehicle=BitmapFactory.decodeResource(getResources(), R.drawable.vehicleaggression);
		
		tButton=new ToggleButton(context);
		tButton.setText("fsdfsfs");
		
		setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.FILL_PARENT
		            ,LinearLayout.LayoutParams.FILL_PARENT)); 
		 
		Button b = new Button(context);
		RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		rl.addRule(RelativeLayout.ALIGN_TOP);
		b.setLayoutParams(rl);
		
		this.addView(b);
		
	}
private Paint paint;

 int x=40,y=550,radius=30;
 
    public void onDraw(Canvas canvas) {


        //  canvas.drawColor(Color.BLACK);
    	canvas.drawCircle(10, 20, radius, this.paint);
        imageView.draw(canvas);
        canvas.drawBitmap(Vehicle, 380-Vehicle.getWidth()/2, 360, paint);
        
     
    }
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		
	}
    
}
