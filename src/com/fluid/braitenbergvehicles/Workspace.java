package com.fluid.braitenbergvehicles;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;

public class Workspace extends SurfaceView  implements SurfaceHolder.Callback {
	
	  private final SurfaceHolder surfaceHolder;
	  private TutorialThread _thread;

public Workspace(Context context) {
		super(context);
	
        surfaceHolder = getHolder();
        getHolder().addCallback(this);
        _thread = new TutorialThread(getHolder(), this);
        
		// TODO Auto-generated constructor stub
		paint =new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.RED);
		paint.setStyle(Style.FILL);
		
		
	}
private Paint paint;

 int x=40,y=550,radius=30;
    public void onDraw(Canvas canvas) {
//    	  if (surfaceHolder.getSurface().isValid())
//    	  {
//              canvas = surfaceHolder.lockCanvas();
              canvas.drawColor(Color.BLACK);
              canvas.drawCircle(30, 30, radius, this.paint);
//              surfaceHolder.unlockCanvasAndPost(canvas);
//          }
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            if (surfaceHolder.getSurface().isValid()) {
                Canvas canvas = surfaceHolder.lockCanvas();
                canvas.drawColor(Color.BLACK);
                canvas.drawCircle(event.getX(), event.getY(), 50, paint);
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
        return false;
    }
    public void surfaceCreated(SurfaceHolder arg0) {
        _thread.setRunning(true);
        _thread.start();
    }

    public void surfaceDestroyed(SurfaceHolder arg0) {
        boolean retry = true;
        _thread.setRunning(false);
        while (retry) {
            try {
                _thread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }
    
    class TutorialThread extends Thread {
        private SurfaceHolder _surfaceHolder;
        private Workspace _panel;
        private boolean _run = false;

        public TutorialThread(SurfaceHolder surfaceHolder, Workspace panel) {
            _surfaceHolder = surfaceHolder;
            _panel = panel;
        }

        public void setRunning(boolean run) {
            _run = run;
        }

        @SuppressLint("WrongCall")
		@Override
        public void run() {
            Canvas c;
            while (_run) {
                c = null;
                try {
                    c = _surfaceHolder.lockCanvas(null);
                    synchronized (_surfaceHolder) {
                        _panel.onDraw(c);
                    }
                } finally {
                    if (c != null) {
                        _surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }
    }

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}
	public void addView(Button b2) {
		// TODO Auto-generated method stub
		
	}
}
