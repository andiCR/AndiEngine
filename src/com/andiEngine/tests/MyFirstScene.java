package com.andiEngine.tests;

import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;

import com.andiEngine.ai.behaviorTrees.AgentManager;
import com.andiEngine.math.Point;
import com.andiEngine.nodes.AnimatedSpriteNode;
import com.andiEngine.nodes.Node;
import com.andiEngine.nodes.Scene;
import com.andiEngine.nodes.SpriteNode;
import com.andiEngine.nodes.TextNode;
import com.andiEngine.tests.objects.Link;
import com.andiEngine.tests.objects.Yoshi;
import com.example.surfacetest.R;

public class MyFirstScene extends Scene {

	//-------------------------------------
	// Variables
	//-------------------------------------
	private AgentManager gameAI;
	private AnimatedSpriteNode mainCharacter;
	private Point touchPosition = null;
	private Yoshi yoshi;
	private Link link;

	//-------------------------------------
	// Public methods
	//-------------------------------------
	public MyFirstScene(Context c) {
		super();
		
		gameAI = new AgentManager();
		
		// Create game Layer
		Node gameLayer = new Node();
		addChild(gameLayer);

		// Add background
		SpriteNode background = new SpriteNode(R.drawable.background, c);
		gameLayer.addChild(background);

		// Add main character
		yoshi = new Yoshi(c);
		yoshi.getSprite().position.x = 30;
		yoshi.getSprite().position.y = 100;
		gameLayer.addChild(yoshi.getSprite());
		gameAI.addAgent(yoshi);
		
		link = new Link(c);
		link.sprite.position.x = 60;
		link.sprite.position.y = 100;
		gameLayer.addChild(link.sprite);

		yoshi.setFoe(link.sprite);
		//mainCharacter= yoshi.sprite;
		mainCharacter= link.sprite;
		
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
		gameAI.update();
		
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
