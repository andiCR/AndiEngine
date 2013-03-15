package com.andiEngine.ai;

public abstract class BTNode {
	public static final int STATE_READY = 0;
	public static final int STATE_SUCCESS = 1;
	public static final int STATE_RUNNING = 2;
	public static final int STATE_FAILED = 3;
	public static final int STATE_ERROR = 4;
	
	protected int _state = STATE_READY;
	
	public int getState() {
		return _state;
	}
	public abstract int visit();
}
