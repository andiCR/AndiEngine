package com.andiEngine.ai.innerNodes;

import com.andiEngine.ai.BTNode;

public class SequenceSelectorNode extends SelectorNode {

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
