package edu.metrostate.ics340.p4.jw2684;

/**
 * @author joshua warner
 * This program uses Kruskal's algorithhm to develop an optimal
 * value graph.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.InputStreamReader;
import com.google.common.graph.ValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.Graphs;
import java.util.Comparator;
import java.util.PriorityQueue;

public class RailPlanner {
	


	/**
	 * 
	 * @param createPlan takes in the CSV file and creates a value graph
	 * @return a value graph that has been optimized by Kruskal's algorithm
	 */
	public static ValueGraph<String, Integer> createPlan(String estimateFilePath) {

		MutableValueGraph<String, Integer> optimal = ValueGraphBuilder.undirected().allowsSelfLoops(false).build();
		PriorityQueue<railEdge> minRail = new PriorityQueue<>(new railComparator());

		File file = new File(estimateFilePath);
		FileInputStream fstream = null;
		try {
			fstream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File Not Accessible");
		}
		Scanner input = new Scanner(new InputStreamReader(fstream));
		String firstNode = null;
		String secondNode = null;
		int value = 0;
		input.useDelimiter("[\\|\\n\\r\\f]+");

		while (input.hasNext()) {

			firstNode = input.next();

			secondNode = input.next();

			value = Integer.parseInt(input.next());

			minRail.offer(new railEdge(firstNode, secondNode, value));

		}
		input.close();

		while (!minRail.isEmpty()) {
			railEdge temp = minRail.poll();
			optimal.putEdgeValue(temp.first, temp.second, temp.valueInput);
			if (Graphs.hasCycle(optimal.asGraph())) {
				optimal.removeEdge(temp.first, temp.second);
			}
		}

		return optimal;
	}

	/**
	 * 
	 * a class that takes in the two nodes and the edge value is the scanner reads in the data and saved them in a priority queue
	 *
	 */
	private static class railEdge {
		private String first;
		private String second;
		int valueInput;

		public railEdge(String firstInput, String secondInput, int inputValue) {
			this.first = firstInput;
			this.second = secondInput;
			this.valueInput = inputValue;
		}
	}

	/**
	 * 
	 * this comparator is used for the railEdge priority queue to find the minimum railEdge edge value
	 *
	 */
	private static class railComparator implements Comparator<railEdge> {
		public int compare(railEdge node1, railEdge compareNode) {
			return node1.valueInput - compareNode.valueInput;
		}
	}

}
