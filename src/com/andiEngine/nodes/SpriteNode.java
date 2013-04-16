package com.andiEngine.nodes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class SpriteNode extends Node {
	//-------------------------------------
	// Variables
	//-------------------------------------
	protected Bitmap bitmap;

	//-------------------------------------
	// Public methods
	//-------------------------------------
	public SpriteNode(int resource, Context c)
	{
		super();
		BitmapFactory.Options options = new BitmapFactory.Options();  
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		bitmap = BitmapFactory.decodeResource(c.getResources(), resource);
	}
	public SpriteNode(Bitmap bmp) {
		super();
		bitmap = bmp;
	}
	@Override
	public void update() {
		super.update();
	}
	
	@Override
	public void draw(Canvas c) {
		c.drawBitmap(bitmap, 0, 0, null);
	}
	
	public int getWidth() {
		return bitmap.getWidth();
	}
	public int getHeight() {
		return bitmap.getHeight();
	}
}
