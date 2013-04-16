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
	private Rect srcRect;
	private Rect dstRect;
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
		dstRect = new Rect(0,0, imageWidth, imageHeight);
		srcRect = new Rect(0,0, imageWidth, imageHeight);
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
			
			refreshFrame();
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
		if (!animationPaused && currentAnimation != null) {
			int lastFrame = (int)(currentFrame);
			currentFrame += animationSpeed;
			if (currentFrame >= currentAnimation.size())
				currentFrame = 0;
			
			// Did we change frames?
			if (lastFrame != (int)currentFrame) {
				refreshFrame();
			}
		}
	}
	
	private void refreshFrame() {
		Point index = currentAnimation.get((int)currentFrame);
		int w = dstRect.width();
		int h = dstRect.height();
		srcRect.left = (int)index.y * w;
		srcRect.right = srcRect.left + w;
		srcRect.top = (int)index.x * h;
		srcRect.bottom = srcRect.top + h;
	}

	@Override
	public void draw(Canvas c) {
		c.drawBitmap(bitmap, srcRect, dstRect, null);
	}
	@Override
	public int getWidth() {
		return dstRect.width();
	}
	@Override
	public int getHeight() {
		return dstRect.height();
	}

}
