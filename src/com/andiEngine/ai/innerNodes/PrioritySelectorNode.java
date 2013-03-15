package com.andiEngine.ai.innerNodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import com.andiEngine.ai.BTNode;

public class PrioritySelectorNode extends SelectorNode {
	
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
		public int visit() {
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
	
	public void addChildNode(BTNode node, float priority) {
		PrioritySelectorChildNode psNode = new PrioritySelectorChildNode(node, priority);
		super.addChildNode(psNode);
		
		Collections.sort(_children, new CustomComparator());
	}
	
	@Override
	public BTNode selectNextChild() {
		// Return the first child
		if (_runningChild == null) {
			return _children.get(0);
		}
		
		// Return next child
		int currentIndex = _children.indexOf(_runningChild);
		
		// Last child? return null
		if (currentIndex + 1 == _children.size())
			return null;
		
		// Get the next child
		return _children.get(currentIndex + 1);
	}
}
