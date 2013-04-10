package com.andiEngine.ai.behaviorTrees.innerNodes;

import java.util.ArrayList;

import com.andiEngine.ai.behaviorTrees.BTNode;

public abstract class SelectorNode extends BTNode {
	protected ArrayList<BTNode> _children;
	protected BTNode _runningChild;

	public abstract BTNode selectNextChild();
	
	public SelectorNode() {
		_children = new ArrayList<BTNode>();
		_runningChild = null;
	}
	
	public void addChildNode(BTNode node) {
		_children.add(node);
	}
	
	@Override
	protected int getVisitResult() {
		// If there's a child running
		if (_runningChild != null) {
			int state = _runningChild.visit();
			
			if (state == STATE_SUCCESS) {
				_runningChild = selectNextChild();
				visit();
			}
		}
		else {
			if (_children.size() > 0)
				_runningChild = selectNextChild();
		}
		
		if (_runningChild == null)
			return STATE_SUCCESS;
		
		return _runningChild.getState();
	}
	
	@Override
	public void reset() {
		super.reset();
		for (BTNode child: _children) {
			child.reset();
		}
		
		if (_state == STATE_READY) {
			_runningChild = null;
		}
	}

}
