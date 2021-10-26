package com.learn.vgshan;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ShortestHop {

	public static void main(String[] args) {
		//Inputs Section Starts here...
		int sourceNode = 1;
		int destinationNode = 6;
		Integer routesArray[][] = { { 1, 2 }, { 1, 5 }, { 2, 3 }, { 3, 4 }, { 4, 6 } };
		//Inputs section Ends here...
		List<List<Integer>> routes = Arrays.stream(routesArray).map(Arrays::asList).collect(Collectors.toList());
		System.out.println("Shortest Hops : " + getShortestHop(sourceNode, destinationNode, routes));
	}

	public static int getShortestHop(int source, int destination, List<List<Integer>> routes) {
		Graph graph = new Graph();
		Boolean goodToGo = true;
		routes.stream().forEach(routeLink -> graph.add(routeLink.get(0), routeLink.get(1)));
		graph.printGraph(graph.root);
		int shortestHops = 0;
		GraphNode sourceNode = graph.search(graph.root, source);
		GraphNode destNode = graph.search(sourceNode, destination);

		if (sourceNode == null) {
			goodToGo = false;
			System.out.println("Source Not Found in Routes...");
		}
		if (destNode == null) {
			goodToGo = false;
			System.out.println("Destination Not Found in Routes from Source...");
		}
		if (source == destination) {
			goodToGo = false;
			System.out.println("Source and Destination cannot be same...");
		}

		if (goodToGo) {
			shortestHops = graph.getShortestHops(sourceNode, destNode);
		}

		return shortestHops;
	}
}
