package com.andiEngine.nodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.andiEngine.math.Point;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class AnimatedSpriteNode extends SpriteNode {

	//-------------------------------------
	// Variables
	//-------------------------------------
	private Rect imageSize;
	private HashMap<String, List<Point>> animations;
	private List<Point> currentAnimation;
	private String currentAnimationName;
	private float currentFrame;
	private float animationSpeed;
	private boolean animationPaused;

	//-------------------------------------
	// Public methods
	//-------------------------------------
	public AnimatedSpriteNode(int resource, Context c, int imageWidth, int imageHeight) {
		super(resource, c);
		imageSize = new Rect(0,0, imageWidth, imageHeight);
		animationSpeed = 1;
		animations = new HashMap<String, List<Point>>();
	}
	
	public void addAnimation(String animationName, Point[] sprites) {
		animations.put(animationName, new ArrayList<Point>(Arrays.asList(sprites)));
	}
	
	public void removeAnimation(String animationName) {
		animations.remove(animationName);
	}
	
	public void setAnimation(String animationName) {
		if (currentAnimationName != animationName) {
			currentFrame = 0;
			currentAnimation = animations.get(animationName);
			currentAnimationName = animationName;
		}
	}
	
	public void setAnimationSpeed(float speed) {
		animationSpeed = speed;
	}

	public void setPaused(boolean paused) {
		animationPaused = paused;
	}
	
	@Override
	public void update() {
		if (!animationPaused) {
			currentFrame += animationSpeed;
			if (currentFrame >= currentAnimation.size())
				currentFrame = 0;
		}
	}
	
	@Override
	public void draw(Canvas c) {
		if (currentAnimation != null) {
			Point index = currentAnimation.get((int)currentFrame);
			
			Rect src =  new Rect((int)index.y*imageSize.width(),
								 (int)index.x*imageSize.height(),
								 (int)index.y*imageSize.width() +imageSize.width(),
								 (int)index.x*imageSize.height()+imageSize.height());
			//Log.d("Andi", "x:" + src.left + ",y:" + src.top + ",w:" + src.width() + ",h:"+src.height());
			c.translate(-anchor.x * imageSize.width(), -anchor.y * imageSize.height());
			c.drawBitmap(bitmap, src, imageSize, null);
			c.translate(anchor.x * imageSize.width(), anchor.y * imageSize.height());
		} else {
			Log.d("Andi","currentanimation null");
		}
	}
	@Override
	public int getWidth() {
		return imageSize.width();
	}
	@Override
	public int getHeight() {
		return imageSize.height();
	}

}
