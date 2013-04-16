package com.andiEngine.nodes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class TextNode extends Node {
	private String text = "";
	private Paint paint;
	private Rect bounds = new Rect();

	public TextNode() {
		super();
		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setTextSize(10);
		
		//paint.getTextBounds("",0, 0, bounds);
	}
	public void setColor(int color) {
		paint.setColor(color);
	}
	public void setTextSize(int size) {
		paint.setTextSize(size);
		paint.getTextBounds(text, 0, text.length(), bounds);
	}
	public void setText(String text) {
		this.text = text;
		paint.getTextBounds(text, 0, text.length(), bounds);
	}
	public String getText() {
		return text;
	}

	@Override
	public void draw(Canvas c) {
		c.drawText(text, 0,0, paint);
	}
	@Override
	public int getWidth() {
		return bounds.width();
	}
	@Override
	public int getHeight() {
		return bounds.height();
	}
}
