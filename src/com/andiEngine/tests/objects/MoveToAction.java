package com.andiEngine.tests.objects;

import com.andiEngine.ai.behaviorTrees.ActionNode;
import com.andiEngine.math.Point;
import com.andiEngine.nodes.Node;

public class MoveToAction extends ActionNode {

	Point _targetPoint;
	float _speed;
	Node _node;
	
	// TODO: include easing
	public MoveToAction(String name, Node node, Point p, float speed) {
		super(name);
		_targetPoint = p;
	}
	public MoveToAction(String name, Node node, float x, float y, float speed) {
		super(name);
		_targetPoint = new Point(x, y);
		_speed = speed;
		_node = node;
	}
	
	@Override
	protected int getVisitResult() {
		if (_node.position.distance(_targetPoint) < _speed) {
			_node.position = _targetPoint;
			return STATE_SUCCESS;
		}
		
		// Set position
		Point velocity = Point.subtract(_targetPoint, _node.position);
		velocity.normalize();
		velocity.scale(_speed);
		_node.position.add(velocity);
		
		return STATE_RUNNING;
	}

}
