package com.learn.vgshan;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {
	Integer value;
	List<GraphNode> links;

	public boolean addLink(Integer link) {
		this.links.add(new GraphNode(link));
		return true;
	}

	public GraphNode(Integer value) {
		super();
		this.value = value;
		this.links = new ArrayList<GraphNode>();
	}

}
