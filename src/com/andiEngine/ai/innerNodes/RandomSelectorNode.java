package com.andiEngine.ai.innerNodes;

import com.andiEngine.ai.BTNode;

public class RandomSelectorNode extends SelectorNode {

	@Override
	public BTNode selectNextChild() {
		int index = (int)(Math.random() * _children.size());
		
		return _children.get(index);
	}
}
