package com.andiEngine.ai.behaviorTrees;

public class ActionNode extends BTNode {

	public ActionNode(String name) {
		super(name);
	}
	@Override
	protected int getVisitResult() {
		// TODO Auto-generated method stub
		return STATE_SUCCESS;
	}

}
