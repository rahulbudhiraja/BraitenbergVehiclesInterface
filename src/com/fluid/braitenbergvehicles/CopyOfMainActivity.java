package com.fluid.braitenbergvehicles;


import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

public class CopyOfMainActivity extends SherlockActivity {


	Workspace workspace;
	 ToggleButton tButton;
	 RelativeLayout relLayout;
	 ActionBar actionBar;
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		

	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    Button b = new Button(this);
	    b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, 
	                                          LayoutParams.WRAP_CONTENT)); 
	    workspace =new Workspace(this);
	      
	  	Button b2 = new Button(this);
		RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		rl.addRule(RelativeLayout.ALIGN_TOP);
		b2.setLayoutParams(rl);
		
		workspace.addView(b2);
//		setContentView(R.layout.activity_main);
		
//		relLayout=(RelativeLayout)findViewById(R.id
		actionBar=getSupportActionBar();
		
	}


//	public boolean onCreateOptionsMenu(Menu menu) 
//	{
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.layout, menu);
//		return true;
//	}
	
	

}
