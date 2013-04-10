package com.andiEngine.ai.behaviorTrees;

public abstract class ConditionNode extends BTNode {

	public abstract boolean examineCondition();
	
	public ConditionNode(String name) {
		super(name);
	}
	
	@Override
	protected int getVisitResult() {
		if (examineCondition())
			return STATE_SUCCESS;

		return STATE_FAILED;
	}

}
