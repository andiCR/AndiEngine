package com.andiEngine.ai.behaviorTrees;

import android.util.Log;

// Behavior Tree implementation
// http://www.altdevblogaday.com/2011/02/24/introduction-to-behavior-trees/
public abstract class BTNode {
	public static final int STATE_READY = 0;
	public static final int STATE_SUCCESS = 1;
	public static final int STATE_RUNNING = 2;
	public static final int STATE_FAILED = 3;
	public static final int STATE_ERROR = 4;
	public static final int STATE_VISITING = 5;
	
	public String name;
	public boolean debugging;
	
	protected int _state = STATE_READY;
	
	public int getState() {
		return _state;
	}
	protected abstract int getVisitResult();
	
	public BTNode() {
		this.name = "";
	}
	
	public BTNode(String name) {
		this.name = name;
	}
	
	public int visit() {
		_state = STATE_VISITING;

		if (debugging)
			Log.d("AI", "Visiting " + name);
		
		_state = getVisitResult();

		if (debugging)
			printDebugLog();
		
		return _state;
	}
	
	public void printDebugLog() {
		String[] debug = { "STATE_READY", "STATE_SUCCESS", "STATE_RUNNING", "STATE_FAILED", "STATE_ERROR", "STATE_VISITING"};
		Log.d("AI", name + debug[_state]);
	}
	
	public void reset() {
		if (_state != STATE_RUNNING)
			_state = STATE_READY;
	}
}
