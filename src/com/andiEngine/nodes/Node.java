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
	public String name = "";

	// Anchor is the point where to draw the sprite from. 
	// (0, 0) means top left
	// 0.5, 0.5 means center
	// 1,1 means bottom right
	public Point anchor; 

	//-------------------------------------
	// Public methods
	//-------------------------------------
	public Node() {
		scale = new Point(1,1);
		position = new Point(0,0);
		rotation = 0;
		children = new ArrayList<Node>();
		anchor = new Point(0, 0);
	}
	public void updateLoop() {
		update();
		for (Node n: children)
		{
			n.updateLoop();
		}
	}
	public void drawLoop(Canvas c) {
		c.save();
		c.translate(position.x, position.y);
		c.rotate(rotation);
		c.scale(scale.x, scale.y);

		c.translate(-anchor.x * getWidth(), -anchor.y * getHeight());
		draw(c);
		for (Node n: children)
		{
			n.drawLoop(c);
		}

		c.restore();
	}
	
	public void addChild(Node node) {
		children.add(node);
	}
	
	public void removeChild(Node node) {
		children.remove(node);
	}
	public Node findChildByName(String name) {
		for (Node child: children) {
			if (child.name.equals(name))
				return child;
		}
		return null;
	}
	
	public boolean pointInside(Point p) {
		float l = position.x -anchor.x * getWidth();
		float r = l + getWidth();
		float t = position.y -anchor.y * getHeight();
		float b = t + getHeight();
		return (p.x >= l && p.x <= r && p.y >= t && p.y <= b);
	}
	
	//-------------------------------------
	// Abstract methods. OVERRIDE THESE
	//-------------------------------------
	public void draw(Canvas c) {
	}
	public void update() {
	}
	
	public int getWidth() {
		return 0;
	}

	public int getHeight() {
		return 0;
	}
}
