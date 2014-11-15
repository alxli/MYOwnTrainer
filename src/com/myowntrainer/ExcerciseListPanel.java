package com.myowntrainer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ExcerciseListPanel extends SurfaceView implements SurfaceHolder.Callback  {
    ExcerciseListActivity context;
    PanelThread _thread;      
    public Paint paint = new Paint();
    public Bitmap burpee = BitmapFactory.decodeResource(getResources(), R.drawable.burpeestile);
    public Bitmap running = BitmapFactory.decodeResource(getResources(), R.drawable.runningtile);
    public Bitmap pushup = BitmapFactory.decodeResource(getResources(), R.drawable.pushuptile);
    public Bitmap jumpingjack = BitmapFactory.decodeResource(getResources(), R.drawable.jumpingjacktile);
    public Bitmap situp = BitmapFactory.decodeResource(getResources(), R.drawable.situptile);
    
    Rect burpeeRect = new Rect(200,75,580,500);
    Rect runningRect =  new Rect(450,400,850,820);
    Rect pushupRect = new Rect(200,720,580,1140);
    Rect jumpingjackRect = new Rect(450,1040,850,1460);
    Rect situpRect = new Rect(200,1360,580,1780);
    
    String burpeeStr = "BURPEE";
    String runningStr = "RUNNING";
    String pushupStr = "PUSH-UP";
    String jumpingStr = "JUMPING";
    String jackStr = "JACK";
    String jumpingjackStr = "JUMPING JACK";
    String situpStr = "SIT-UP";
    
    //Constructors
    public ExcerciseListPanel(Context context) { 
        super(context);
        this.context = (ExcerciseListActivity) context;          
        this.setBackgroundColor(Color.WHITE);
    }

    //Essentially the main method, runs multiple times and is where updating and drawing is done.
    @Override 
    public void onDraw(Canvas canvas) {
        //do drawing stuff here.
        update();
        draw(canvas, paint);            
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }
    
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setWillNotDraw(false); //Allows us to use invalidate() to call onDraw()
        _thread = new PanelThread(getHolder(), this); //Start the thread that
        _thread.setRunning(true);                     //will make calls to 
        _thread.start();                              //onDraw()
    	init();
    }
    
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        try {
        	pushup.recycle();
    		running.recycle();
    		situp.recycle();
    		jumpingjack.recycle();
    		burpee.recycle();
            _thread.setRunning(false);                //Tells thread to stop
            _thread.join();                           //Removes thread from mem.
            
        } catch (InterruptedException e) {}
    }
    

    public void init() {
   
    }   

    public void update() {

    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        switch (event.getAction()) {
          case MotionEvent.ACTION_DOWN:
            screenTouched(eventX, eventY);
            return true;
          case MotionEvent.ACTION_MOVE:
            screenMoved(eventX, eventY);
            break;
          case MotionEvent.ACTION_UP:
            screenReleased(eventX, eventY);
            break;
          default:
            return false;
        }
        return true;
    }
    
    private void screenTouched(float eventX, float eventY) {
        int intX = (int) eventX, intY = (int) eventY;
        if (burpeeRect.contains(intX, intY)) {
			context.Exercise(burpeeStr);
		}
		else if (runningRect.contains(intX, intY)) {
			context.Exercise(runningStr);
		}
		else if (pushupRect.contains(intX, intY)) {
			context.Exercise(pushupStr);
		}
		else if (jumpingjackRect.contains(intX, intY)) {
			context.Exercise(jumpingjackStr);
		}
		else if (situpRect.contains(intX, intY)) {
			context.Exercise(situpStr);
		}
    }
    
    private void screenMoved(float eventX, float eventY) {

    }
    
    private void screenReleased(float eventX, float eventY) {
    	
    }

    public void draw(Canvas canvas, Paint paint) {
    		paint.setTextSize(80);
    		canvas.drawBitmap(burpee, null, burpeeRect, paint);
    		canvas.drawText(burpeeStr, 600, 285, paint);
    		canvas.drawBitmap(running, null, runningRect, paint);
    		canvas.drawText(runningStr, 115, 615, paint);
    		canvas.drawBitmap(pushup, null, pushupRect, paint);
    		canvas.drawText(pushupStr, 600, 955, paint);
    		canvas.drawBitmap(jumpingjack, null, jumpingjackRect, paint);
    		canvas.drawText(jumpingStr, 115, 1250, paint);
    		canvas.drawText(jackStr, 165, 1325, paint);
    		canvas.drawBitmap(situp, null, situpRect, paint);
    		canvas.drawText(situpStr, 600, 1620, paint);
    }
}
