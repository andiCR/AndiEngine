package com.andiEngine.tests.objects;

import java.util.Random;

import android.content.Context;

import com.andiEngine.ai.behaviorTrees.ActionNode;
import com.andiEngine.ai.behaviorTrees.BTNode;
import com.andiEngine.ai.behaviorTrees.ConditionNode;
import com.andiEngine.ai.behaviorTrees.IAgent;
import com.andiEngine.ai.behaviorTrees.innerNodes.LoopSelectorNode;
import com.andiEngine.ai.behaviorTrees.innerNodes.PrioritySelectorNode;
import com.andiEngine.math.Point;
import com.andiEngine.nodes.AnimatedSpriteNode;
import com.andiEngine.nodes.Node;
import com.andiEngine.nodes.SpriteNode;
import com.example.surfacetest.R;

public class Yoshi implements IAgent{
	private AnimatedSpriteNode _sprite;
	private BTNode _ai;	
	private Point _target;
	private Random _random = new Random();
	private Node _foe;
	
	public Yoshi(Context c) {
		_sprite = new AnimatedSpriteNode(R.drawable.yoshi, c, 40, 40);
		_sprite.addAnimation("down",  new Point[] {new Point(0,0),new Point(0,1),new Point(0,2),new Point(0,3),new Point(0,4)});
		_sprite.addAnimation("right", new Point[] {new Point(1,0),new Point(1,1),new Point(1,2),new Point(1,3),new Point(1,4)});
		_sprite.addAnimation("up",  	new Point[] {new Point(2,0),new Point(2,1),new Point(2,2),new Point(2,3),new Point(2,4)});
		_sprite.addAnimation("left",  new Point[] {new Point(3,0),new Point(3,1),new Point(3,2),new Point(3,3),new Point(3,4)});
		_sprite.setAnimation("down");
		_sprite.setAnimationSpeed(0.2f);
		_sprite.anchor = new Point(0.5f, 0.5f);
		_sprite.scale = new Point(1.5f,1.5f);
	}
	
	public SpriteNode getSprite() {
		return _sprite;
	}
	
	public void setFoe(Node node) {
		_foe = node;
	}
	public boolean isNearFoe() {
		return _foe.position.distance(_sprite.position) < 10;
	}
	
	@Override
	public void init() {
		PrioritySelectorNode root = new PrioritySelectorNode();
		root.name = "Root node";
		
		// Afraid sequence
		LoopSelectorNode afraid = new LoopSelectorNode();
		afraid.name = "Afraid Sequence";
		afraid.addChildNode(new ConditionNode("Afraid?") {
			@Override
			public boolean examineCondition() {
				return isNearFoe();
			}
		});
		afraid.addChildNode(new ActionNode("SetTargetPoint!") {
			@Override
			protected int getVisitResult() {
				_target = new Point(_random.nextInt(400), _random.nextInt(200));
				return STATE_SUCCESS;
			}
		});
		afraid.addChildNode(new MoveToAction("MoveTo!", _sprite, _target, 0.5f));
		
		root.addChildNode(afraid);
		
		// Idle
		root.addChildNode(new ActionNode("Idle"));
		
		_ai = root;
	}
	@Override
	public void tick() {
		_ai.visit();		
	}
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		_ai.reset();
	}
}
