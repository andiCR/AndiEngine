package com.andiEngine.ai.behaviorTrees.innerNodes;

import com.andiEngine.ai.behaviorTrees.BTNode;

// Loops are like sequences but they loop around (hah, who would have thought!) when reaching their
// last child during their traversal instead of returning to their parent node like sequence node do.
public class LoopSelectorNode extends SelectorNode {

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
			return _children.get(0);
		
		// Get the next child
		return _children.get(currentIndex + 1);
	}

}
