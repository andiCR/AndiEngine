package com.andiEngine.nodes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

public class Scene extends Node {
	@Override
	public void draw(Canvas c) {
        c.drawColor(Color.BLACK);
	}

	public boolean onTouchEvent(MotionEvent event) {
		return true;
	}
}
