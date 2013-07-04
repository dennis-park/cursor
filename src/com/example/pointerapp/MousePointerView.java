package com.example.pointerapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;

public class MousePointerView extends ImageView{
	private final Bitmap bitmap;
	private float posX = 0, posY = 0;
	
	public MousePointerView(Context context, Bitmap bitmap) {
		super(context);
		this.bitmap = bitmap;
		// TODO Auto-generated constructor stub
	}
	
//	private void updateView(View view, float x, float y) {
//		LinearLayout.LayoutParams params =
//				new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
//		params.leftMargin = Math.round(x);
//		params.rightMargin = Math.round(y);
//		view.setLayoutParams(params);
//	}
	private boolean FLAG_INIT = true;
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (FLAG_INIT) {
			posX = (float) ((this.getRight()-this.getLeft())/2.5);
			posY = (float) ((this.getBottom()-this.getTop())/2.5);
			FLAG_INIT = false;
		}
		canvas.drawBitmap(bitmap, posX, posY, null);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_UP:
			posX = event.getX();
			posY = event.getY();
			invalidate();
			//maintain
			break;
		case MotionEvent.ACTION_MOVE:
			posX = event.getX();
			posY = event.getY();
			invalidate();
			//update position
			break;
		case MotionEvent.ACTION_OUTSIDE:
			break;
		}
		return true;
	}
	
}
