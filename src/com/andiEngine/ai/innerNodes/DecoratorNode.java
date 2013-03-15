package com.andiEngine.ai.innerNodes;

import com.andiEngine.ai.BTNode;

public class DecoratorNode extends BTNode {
	BTNode child;
	public DecoratorNode(BTNode child) {
		this.child = child;
	}
	@Override
	public int visit() {
		// TODO Auto-generated method stub
		return child.visit();
	}
	
	
}
