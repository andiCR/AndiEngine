package com.andiEngine.ai;

public class BTActionNode {

	public BTActionNode(ActionData data)
	{
		
	}
	// Calls a certain member function of actor.
	public BehaviorState update();
 
	// Does nothing.
	virtual void resetState();
 
private:
	ActionData* data;
}
