package com.andiEngine.ai.behaviorTrees.innerNodes;

import java.util.Random;

import com.andiEngine.ai.behaviorTrees.BTNode;

// Random selectors randomly (hah again) select which of their child nodes to visit.
// A running node is visited again until it finishes.

public class RandomSelectorNode extends SelectorNode {

	Random _random;
	
	public RandomSelectorNode() {
		_random = new Random();
	}
	
	@Override
	public BTNode selectNextChild() {
		
		int index = _random.nextInt(_children.size());
		
		return _children.get(index);
	}
}
