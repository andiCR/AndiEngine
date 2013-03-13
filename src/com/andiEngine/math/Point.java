package com.andiEngine.math;

import android.graphics.PointF;


public class Point extends PointF {
	public Point(float x, float y) {
		super(x, y);
	}

	//------------------------------
	// Static methods
	//------------------------------
	public static Point subtract(Point p1, Point p2){
		Point p = new Point(p1.x, p1.y);
		p.subtract(p2);
		return p;
	}
	public static Point add(Point p1, Point p2){
		Point p = new Point(p1.x, p1.y);
		p.add(p2);
		return p;
	}
	public static Point scale(Point p1, Point p2){
		Point p = new Point(p1.x, p1.y);
		p.scale(p2);
		return p;
	}
	public static Point scale(Point p1, float scale){
		Point p = new Point(p1.x, p1.y);
		p.scale(scale);
		return p;
	}

	//------------------------------
	// Public methods
	//------------------------------
	public void subtract(Point p2) {
		x -= p2.x;
		y -= p2.y;
	}
	public void add(Point p2) {
		x += p2.x;
		y += p2.y;
	}
	public void scale(Point p2) {
		x *= p2.x;
		y *= p2.y;
	}
	public void scale(float scale) {
		x *= scale;
		y *= scale;
	}
	public void normalize() {
		float length = length();
		x /= length;
		y /= length;
	}
	
	@Override
	public String toString() {
		return "x: " + x + ". y:" + y;
	}

}
