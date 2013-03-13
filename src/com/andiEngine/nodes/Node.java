package com.andiEngine.nodes;

import java.util.ArrayList;

import com.andiEngine.math.Point;

import android.graphics.Canvas;

public class Node {

	//-------------------------------------
	// Variables
	//-------------------------------------
	public Point position;
	public float rotation;
	public Point scale;
	private ArrayList<Node> children;

	//-------------------------------------
	// Public methods
	//-------------------------------------
	public Node() {
		scale = new Point(1,1);
		position = new Point(0,0);
		rotation = 0;
		children = new ArrayList<Node>();
	}
	public void updateLoop() {
		update();
		for (Node n: children)
		{
			n.updateLoop();
		}
	}
	public void drawLoop(Canvas c) {
		c.translate(position.x, position.y);
		c.rotate(rotation);
		c.scale(scale.x, scale.y);
		
		draw(c);
		for (Node n: children)
		{
			n.drawLoop(c);
		}

		c.scale(1/scale.x, 1/scale.y);
		c.rotate(-rotation);
		c.translate(-position.x, -position.y);
	}
	
	public void addChild(Node node) {
		children.add(node);
	}
	
	public void removeChild(Node node) {
		children.remove(node);
	}
	//-------------------------------------
	// Abstract methods. OVERRIDE THESE
	//-------------------------------------
	public void draw(Canvas c) {
	}
	public void update() {
	}
}
