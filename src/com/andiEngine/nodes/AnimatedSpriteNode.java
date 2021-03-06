package com.andiEngine.nodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.andiEngine.math.Point;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;

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
	private int width;
	private int height;

	//-------------------------------------
	// Public methods
	//-------------------------------------
	public AnimatedSpriteNode(int resource, Context c, int imageWidth, int imageHeight) {
		super(resource, c);
		dstRect = new Rect(0,0, imageWidth, imageHeight);
		srcRect = new Rect(0,0, imageWidth, imageHeight);
		animationSpeed = 1;
		animations = new HashMap<String, List<Point>>();
		width = imageWidth;
		height = imageHeight;
	}
	
	public void addAnimation(String animationName, Point[] sprites) {
		animations.put(animationName, new ArrayList<Point>(Arrays.asList(sprites)));
	}
	
	public void removeAnimation(String animationName) {
		animations.remove(animationName);
	}
	
	public void setAnimation(String animationName) {
		if (currentAnimationName != animationName) {
			if (animations.containsKey(animationName)) {
				currentFrame = 0;
				currentAnimation = animations.get(animationName);
				currentAnimationName = animationName;
				
				refreshFrame();
			}
		}
	}
	
	public boolean containsAnimation(String animationName) {
		return animations.containsKey(animationName);
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
		srcRect.left = (int)index.y * width;
		srcRect.right = srcRect.left + width;
		srcRect.top = (int)index.x * height;
		srcRect.bottom = srcRect.top + height;
	}

	@Override
	public void draw(Canvas c) {
		c.drawBitmap(bitmap, srcRect, dstRect, null);
	}
	@Override
	public int getWidth() {
		return width;
	}
	@Override
	public int getHeight() {
		return height;
	}

}
