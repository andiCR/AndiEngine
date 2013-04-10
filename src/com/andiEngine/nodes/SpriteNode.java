package com.andiEngine.nodes;

import com.andiEngine.math.Point;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class SpriteNode extends Node {
	//-------------------------------------
	// Variables
	//-------------------------------------
	protected Bitmap bitmap;

	// Anchor is the point where to draw the sprite from. 
	// (0, 0) means top left
	// 0.5, 0.5 means center
	// 1,1 means bottom right
	public Point anchor; 

	//-------------------------------------
	// Public methods
	//-------------------------------------
	public SpriteNode(int resource, Context c)
	{
		super();
		BitmapFactory.Options options = new BitmapFactory.Options();  
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		bitmap = BitmapFactory.decodeResource(c.getResources(), resource);
		
		anchor = new Point(0, 0);
	}
	@Override
	public void update() {
		super.update();
	}
	
	@Override
	public void draw(Canvas c) {
		super.draw(c);
		
		c.save();
		
		c.translate(-anchor.x * bitmap.getWidth(), -anchor.y * bitmap.getHeight());
		c.drawBitmap(bitmap, 0, 0, null);

		c.restore();
	}
	
	public int getWidth() {
		return bitmap.getWidth();
	}
	public int getHeight() {
		return bitmap.getHeight();
	}
}
