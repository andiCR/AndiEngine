package com.andiEngine;

import com.andiEngine.nodes.Scene;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AndiEngineView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
	
	private Scene scene;
	private Thread drawThread;
	private boolean destroying;
	
	public AndiEngineView(Context context) {

        super(context);
        getHolder().addCallback(this);

        destroying = false;
        this.scene = null;
    }
	
	public void setScene(Scene scene) {
		this.scene = scene;
	}
 
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    	
    }
 
    @Override
    public void surfaceCreated(SurfaceHolder holder) {     
        drawThread = new Thread(this);
        drawThread.start();
    }
 
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    	destroying = true;
    	drawThread.interrupt();
    	drawThread = null;
    }
    
    @Override
    public void onDraw(Canvas canvas) {
    	if (scene != null)
    	{
	    	// Update scene first
	    	scene.updateLoop();
	    	
	    	// Draw scene
	    	scene.drawLoop(canvas);
    	}
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	return scene.onTouchEvent(event);
    }
    
    @Override
    public void run() {

        Canvas c;
        while (!destroying) {
        	SurfaceHolder holder = getHolder();
            c = null;
            try {
                c = holder.lockCanvas(null);
                synchronized (holder) {
                    onDraw(c);
     
                }
            } finally {
                // do this in a finally so that if an exception is thrown
                // during the above, we don't leave the Surface in an
                // inconsistent state
                if (c != null) {
                	holder.unlockCanvasAndPost(c);
                }
            }
        }
     
    }

}
