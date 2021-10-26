package com.learn.vgshan;

public class Graph {
	GraphNode root = null;

	boolean add(Integer a, Integer b) {
		GraphNode aNode = search(root, a);
		if (aNode == null) {
			root = new GraphNode(a);
			aNode = root;
		}
		GraphNode bNode = search(root, b);
		if (bNode == null) {
			bNode = new GraphNode(b);
		}
		aNode.links.add(bNode);
		return false;
	}

	GraphNode search(GraphNode current, Integer value) {
		Integer counter;
		GraphNode match = null;
		if (current == null) {
			return null;
		}
		if (current.value == value) {
			return current;
		}
		for (counter = 0; counter < current.links.size(); counter++) {
			if (current.links.get(counter).value == value) {
				return current.links.get(counter);
			}
		}
		for (counter = 0; counter < current.links.size(); counter++) {
			match = search(current.links.get(counter), value);
			if (match != null) {
				return match;
			}
		}
		return match;
	}

	void printGraph(GraphNode current) {
		if (current != null) {
			System.out.println("Current : " + current.value);
			System.out.print("Children :");
			current.links.stream().forEach(node -> System.out.print(" " + node.value));
			System.out.println(" ");
			current.links.stream().forEach(node -> printGraph(node));
		}
	}

	Integer getShortestHops(GraphNode sourceNode, GraphNode destNode) {
		Integer minHops = null;
		Integer counter = 0;
		Boolean foundAsChild = false;
		for (counter = 0; !foundAsChild && counter < sourceNode.links.size(); counter++) {
			if (sourceNode.links.get(counter).equals(destNode)) {
				minHops = 1;
				foundAsChild = true;
			}
		}
		if (!foundAsChild) {
			minHops = null;
			for (counter = 0; counter < sourceNode.links.size(); counter++) {
				Integer nextLevelHops = getShortestHops(sourceNode.links.get(counter), destNode);
				if (nextLevelHops != null) {
					nextLevelHops++;
					if (minHops == null) {
						minHops = nextLevelHops;
					} else if (minHops > nextLevelHops) {
						minHops = nextLevelHops;
					}
				}
			}
		}
		return minHops;
	}

}
