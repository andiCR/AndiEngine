package com.andiEngine.ai.behaviorTrees.innerNodes;

import java.util.Collections;
import java.util.Comparator;

import com.andiEngine.ai.behaviorTrees.BTNode;

// On each traversal priority selectors check which child to run in priority order until
// the first one succeeds or returns that it is running. One option is to call the last 
// still running node again during the next behavior tree update. The other option is to 
// always restart traversal from the highest priority child and implicitly cancel the last
// running child behavior if it isn’t chosen immediately again.
public class PrioritySelectorNode extends SelectorNode {
	/*
	//===============================================
	// Private Classes
	//===============================================
	class PrioritySelectorChildNode extends BTNode {
		
		public BTNode node;
		public float priority;

		public PrioritySelectorChildNode(BTNode node,float priority) {
			this.node = node;
			this.priority = priority;
		}
		@Override
		protected int getVisitResult() {
			return node.visit();
		}
	}
	class CustomComparator implements Comparator<BTNode> {
	    @Override
	    public int compare(BTNode o1, BTNode o2) {
	        return Float.compare(((PrioritySelectorChildNode)o1).priority,  ((PrioritySelectorChildNode)o2).priority);
	    }
	}
	//===============================================
	// Private functions
	//===============================================
	private PrioritySelectorChildNode getChildNode(BTNode node) {
		for (BTNode child: _children) {
			PrioritySelectorChildNode psNode = (PrioritySelectorChildNode)child;
			if (psNode.node == node) {
				return psNode;
			}
		}
		return null;
	}
	
	//===============================================
	// Public functions
	//===============================================
	public float getPriority(BTNode node) {
		PrioritySelectorChildNode psNode = getChildNode(node);
		return (psNode != null)? psNode.priority : 0;
	}
	
	public void setPriorityToChild(BTNode node, int priority) {
		PrioritySelectorChildNode psNode = getChildNode(node);
		if (psNode != null)
			psNode.priority = priority;
		
		Collections.sort(_children, new CustomComparator());
	}
	
	@Override
	public void addChildNode(BTNode node) {
		addChildNode(node, 0);
	}
	
	// Add child with a priority
	public void addChildNode(BTNode node, float priority) {
		PrioritySelectorChildNode psNode = new PrioritySelectorChildNode(node, priority);
		super.addChildNode(psNode);
		
		Collections.sort(_children, new CustomComparator());
	}
	*/
	@Override
	public BTNode selectNextChild() {
		// Return the first child
		if (_runningChild == null) {
			return _children.get(0);
		}
		
		int currentIndex = _children.indexOf(_runningChild);
		
		// Last child? return null
		if (currentIndex + 1 == _children.size())
			return null;
		
		// Get the next child
		return _children.get(currentIndex + 1);
	}
	
	@Override
	protected int getVisitResult() {
		if (_runningChild == null) {
			if (_children.size() > 0)
				_runningChild = selectNextChild();
		}
		
		// Visit children until one succeeds
		int state = STATE_FAILED;
		while (_runningChild != null) {
			state = _runningChild.visit();
			
			if (state == STATE_FAILED)
				_runningChild = selectNextChild();
			else 
				break;
		}
		
		return state;
	}
}
