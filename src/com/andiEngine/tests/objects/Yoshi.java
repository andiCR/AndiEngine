package com.andiEngine.tests.objects;

import android.content.Context;

import com.andiEngine.math.Point;
import com.andiEngine.nodes.AnimatedSpriteNode;
import com.example.surfacetest.R;

public class Yoshi {
	public AnimatedSpriteNode sprite;
	public Yoshi(Context c) {
		sprite = new AnimatedSpriteNode(R.drawable.yoshi, c, 40, 40);
		sprite.addAnimation("down",  new Point[] {new Point(0,0),new Point(0,1),new Point(0,2),new Point(0,3),new Point(0,4)});
		sprite.addAnimation("right", new Point[] {new Point(1,0),new Point(1,1),new Point(1,2),new Point(1,3),new Point(1,4)});
		sprite.addAnimation("up",  	new Point[] {new Point(2,0),new Point(2,1),new Point(2,2),new Point(2,3),new Point(2,4)});
		sprite.addAnimation("left",  new Point[] {new Point(3,0),new Point(3,1),new Point(3,2),new Point(3,3),new Point(3,4)});
		sprite.setAnimation("down");
		sprite.setAnimationSpeed(0.2f);
		sprite.anchor = new Point(0.5f, 0.5f);
		sprite.scale = new Point(1.5f,1.5f);
	}
}
