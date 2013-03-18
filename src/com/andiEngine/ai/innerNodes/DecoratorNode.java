package com.andiEngine.ai.innerNodes;

import com.andiEngine.ai.BTNode;

// Decorator nodes typically have only one child and are used to enforce a
// certain return state or to implement timers to restrict how often the child 
// will run in a given amount of time or how often it can be executed without a pause
public class DecoratorNode extends BTNode {
	BTNode _child;
	public DecoratorNode(BTNode child) {
		_child = child;
	}
	@Override
	protected int getVisitResult() {
		// TODO Auto-generated method stub
		return _child.visit();
	}

	@Override
	public void reset() {
		super.reset();
		_child.reset();
	}
	
}
