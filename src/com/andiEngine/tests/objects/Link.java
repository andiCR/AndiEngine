package com.andiEngine.tests.objects;

import android.content.Context;

import com.andiEngine.math.Point;
import com.andiEngine.nodes.AnimatedSpriteNode;
import com.example.surfacetest.R;

public class Link {
	public AnimatedSpriteNode sprite;
	public Link(Context c) {
		sprite = new AnimatedSpriteNode(R.drawable.link, c, 24, 32);
		sprite.addAnimation("down",  new Point[] {new Point(6,0),new Point(6,1),new Point(6,2)});
		sprite.addAnimation("right", new Point[] {new Point(5,0),new Point(5,1),new Point(5,2)});
		sprite.addAnimation("up",  	new Point[] {new Point(4,0),new Point(4,1),new Point(4,2)});
		sprite.addAnimation("left",  new Point[] {new Point(7,0),new Point(7,1),new Point(7,2)});
		sprite.setAnimation("down");
		sprite.setAnimationSpeed(0.2f);
		sprite.anchor = new Point(0.5f, 0.5f);
		sprite.scale = new Point(1.5f,1.5f);
	}
}
