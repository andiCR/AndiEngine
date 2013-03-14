package com.andiEngine.tests;

import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;

import com.andiEngine.math.Point;
import com.andiEngine.nodes.AnimatedSpriteNode;
import com.andiEngine.nodes.Node;
import com.andiEngine.nodes.Scene;
import com.andiEngine.nodes.SpriteNode;
import com.andiEngine.nodes.TextNode;
import com.example.surfacetest.R;

public class MyFirstScene extends Scene {

	//-------------------------------------
	// Variables
	//-------------------------------------
	private AnimatedSpriteNode mainCharacter;
	private Point touchPosition = null;

	//-------------------------------------
	// Public methods
	//-------------------------------------
	public MyFirstScene(Context c) {
		super();
		
		// Create game Layer
		Node gameLayer = new Node();
		addChild(gameLayer);

		// Add background
		SpriteNode background = new SpriteNode(R.drawable.background, c);
		gameLayer.addChild(background);

		// Add main character
		mainCharacter = new AnimatedSpriteNode(R.drawable.yoshi, c, 40, 40);
		mainCharacter.addAnimation("down",  new Point[] {new Point(0,0),new Point(0,1),new Point(0,2),new Point(0,3),new Point(0,4)});
		mainCharacter.addAnimation("right", new Point[] {new Point(1,0),new Point(1,1),new Point(1,2),new Point(1,3),new Point(1,4)});
		mainCharacter.addAnimation("up",  	new Point[] {new Point(2,0),new Point(2,1),new Point(2,2),new Point(2,3),new Point(2,4)});
		mainCharacter.addAnimation("left",  new Point[] {new Point(3,0),new Point(3,1),new Point(3,2),new Point(3,3),new Point(3,4)});
		mainCharacter.setAnimation("down");
		mainCharacter.setAnimationSpeed(0.2f);
		mainCharacter.position.x = 30;
		mainCharacter.position.y = 100;
		mainCharacter.anchor = new Point(0.5f, 0.5f);
		mainCharacter.scale = new Point(1.5f,1.5f);
		gameLayer.addChild(mainCharacter);
		
		// Create the text
		TextNode name = new TextNode();
		name.text = "Andi";
		name.setColor(Color.WHITE);
		name.setTextSize(30);
		name.position.x = 0;
		name.position.y = mainCharacter.getHeight();
		mainCharacter.addChild(name);
		
		// Create UI
		Node uiLayer = new Node();
		addChild(uiLayer);
	}

	//-------------------------------------
	// Override update method to do logic
	//-------------------------------------
	@Override
	public void update() {
		if (touchPosition != null) {
			Point distance = Point.subtract(touchPosition, mainCharacter.position);

			distance.normalize();			// Get a unit vector (0 to 1)
			distance.scale(10);				// Scale this unit vector
			
			mainCharacter.position.add(distance);
		}
		super.update();
	}
	
	void setPosition(float x, float y)
	{
		if (touchPosition == null)
    		touchPosition = new Point(x,y);
		else {
    		touchPosition.x = x;
    		touchPosition.y = y;
		}
		
		// Set animation
		Point distance = Point.subtract(touchPosition, mainCharacter.position);
		if (Math.abs(distance.x) > Math.abs(distance.y)) {
			if (distance.x > 0) mainCharacter.setAnimation("right");
			else mainCharacter.setAnimation("left");
		} else {
			if (distance.y > 0) mainCharacter.setAnimation("down");
			else mainCharacter.setAnimation("up");
		}
	}
	
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	int eventAction = event.getAction();
    	switch (eventAction) {
	    	case MotionEvent.ACTION_DOWN:
	    		setPosition(event.getRawX(), event.getRawY());
	    		break;
	    	case MotionEvent.ACTION_MOVE:
	    		setPosition(event.getRawX(), event.getRawY());
	    		break;
	    	case MotionEvent.ACTION_UP:
	    		touchPosition = null;
	    		break;
    	}
    	return true;
    }
}
