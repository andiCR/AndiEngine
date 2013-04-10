package com.andiEngine.ai.behaviorTrees.innerNodes;

import java.util.ArrayList;

import com.andiEngine.ai.behaviorTrees.BTNode;

// Concurrent nodes visit all of their children during each traversal. A pre-specified
// number of children needs to fail to make the concurrent node fail, too. Instead of 
// running its child nodes truly in parallel to each other there might be a specific traversal
// order which can be exploited when adding conditions (see below) to a concurrent node because
// an early failing condition prevents its following concurrent siblings from running.
public class ConcurrentNode extends BTNode {

	public int childrenToFail = 1;
	protected ArrayList<BTNode> _children;
	
	public ConcurrentNode() {
		_children = new ArrayList<BTNode>();
	}
	
	public void addChildNode(BTNode node) {
		_children.add(node);
	}
	
	@Override
	protected int getVisitResult() {
		
		int fails = 0;
		int succeded = 0;
		for (BTNode node: _children)
		{
			int state = node.visit();
			if (state == STATE_FAILED)
				fails++;
			
			if (fails >= childrenToFail)
				return STATE_FAILED;
			
			if (state == STATE_SUCCESS)
				succeded ++;
		}
		
		// Did all children finish (fail/success)?
		if (succeded + fails == _children.size())
			return STATE_SUCCESS;

		return STATE_RUNNING;
	}

	@Override
	public void reset() {
		super.reset();
		for (BTNode child: _children) {
			child.reset();
		}
	}
}
