package com.andiEngine.ai;

// Behavior Tree implementation
// http://www.altdevblogaday.com/2011/02/24/introduction-to-behavior-trees/
public abstract class BTNode {
	public static final int STATE_READY = 0;
	public static final int STATE_SUCCESS = 1;
	public static final int STATE_RUNNING = 2;
	public static final int STATE_FAILED = 3;
	public static final int STATE_ERROR = 4;
	public static final int STATE_VISITING = 5;
	
	protected int _state = STATE_READY;
	
	public int getState() {
		return _state;
	}
	protected abstract int getVisitResult();
	

	public int visit() {
		_state = STATE_VISITING;
		
		_state = getVisitResult();
		
		return _state;
	}
	
	public void reset() {
		if (_state != STATE_RUNNING)
			_state = STATE_READY;
	}
}
