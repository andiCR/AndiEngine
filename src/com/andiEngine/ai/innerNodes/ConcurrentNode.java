package com.andiEngine.ai.innerNodes;

import java.util.ArrayList;

import com.andiEngine.ai.BTNode;

public class ConcurrentNode extends BTNode {

	public int childrenToFail = 10;
	protected ArrayList<BTNode> _children;
	
	public ConcurrentNode() {
		_children = new ArrayList<BTNode>();
	}
	
	public void addChildNode(BTNode node) {
		_children.add(node);
	}
	
	@Override
	public int visit() {
		
		int fails = 0;
		int succeded = 0;
		for (BTNode node: _children)
		{
			int state = node.visit();
			if (state == STATE_FAILED)
				fails++;
			else if (state == STATE_SUCCESS)
				succeded++;
		}
		
		if (fails >= childrenToFail)
			return STATE_FAILED;
		
		// Did all children finish (fail/success)?
		if (succeded + fails == _children.size())
			return STATE_SUCCESS;

		return STATE_RUNNING;
	}

}
