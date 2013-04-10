package com.andiEngine.ai.behaviorTrees;

import java.util.ArrayList;

public class AgentManager {
	private ArrayList<IAgent> _agents;

	public AgentManager() {
		_agents = new ArrayList<IAgent>();
	}
	
	public void addAgent(IAgent agent) {
		agent.init();
		_agents.add(agent);
	}
	
	public void update() {
		for (IAgent agent: _agents) {
			agent.tick();
			agent.reset();
		}
	}
}
