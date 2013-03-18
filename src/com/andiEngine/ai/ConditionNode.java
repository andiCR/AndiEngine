package com.andiEngine.ai;

public abstract class ConditionNode extends BTNode {

	public abstract boolean examineCondition();
	
	@Override
	protected int getVisitResult() {
		if (examineCondition())
			return STATE_SUCCESS;

		return STATE_FAILED;
	}

}
