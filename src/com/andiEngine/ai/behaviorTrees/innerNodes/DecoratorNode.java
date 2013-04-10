package com.andiEngine.ai.behaviorTrees.innerNodes;

import com.andiEngine.ai.behaviorTrees.BTNode;

// Decorator nodes typically have only one child and are used to enforce a
// certain return state or to implement timers to restrict how often the child 
// will run in a given amount of time or how often it can be executed without a pause
public abstract class DecoratorNode extends BTNode {
	protected BTNode _child;
	public DecoratorNode(BTNode child) {
		_child = child;
	}
	
	protected abstract void decorate();
	
	@Override
	protected int getVisitResult() {
		decorate();
		// TODO Auto-generated method stub
		return _child.visit();
	}

	@Override
	public void reset() {
		super.reset();
		_child.reset();
	}
	
}
