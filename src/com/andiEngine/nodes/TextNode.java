package com.andiEngine.nodes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class TextNode extends Node {
	public String text = "";
	private Paint paint;

	public TextNode() {
		super();
		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setTextSize(10);
	}
	public void setColor(int color) {
		paint.setColor(color);
	}
	public void setTextSize(int size) {
		paint.setTextSize(size);
	}

	@Override
	public void draw(Canvas c) {
		c.drawText(text, 0,0, paint);
	}
}
