package edu.metrostate.ics340.p4.jw2684.test;

import static org.junit.jupiter.api.Assertions.*;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import com.google.common.graph.ValueGraph;
import com.google.common.graph.Graphs;

import edu.metrostate.ics340.p4.jw2684.RailPlanner;
import org.junit.jupiter.api.Test;

class RailPlannerTest {

	@Test
	void test() {
		MutableValueGraph<String, Integer> test = ValueGraphBuilder.undirected().allowsSelfLoops(false).build();
		test.putEdgeValue("a", "b", 2);
		test.putEdgeValue("a", "c", 3);
		String test1 = RailPlanner
				.createPlan("E:\\\\METRO_STATE\\\\ICS340\\\\assignments\\\\P4_RailPlanner\\\\p4_scenario3.csv").edges()
				.toString();
		String test2 = test.edges().toString();

		assertEquals(test1, test2);
	}

	@Test
	void test2() {

		ValueGraph<String, Integer> test1 = RailPlanner
				.createPlan("E:\\\\METRO_STATE\\\\ICS340\\\\assignments\\\\P4_RailPlanner\\\\p4_scenario3.csv");

		assertEquals(false, Graphs.hasCycle(test1.asGraph()));
	}

	@Test
	void test3() {

		ValueGraph<String, Integer> test1 = RailPlanner
				.createPlan("E:\\\\METRO_STATE\\\\ICS340\\\\assignments\\\\P4_RailPlanner\\\\p4_scenario0.csv");

		assertEquals(false, Graphs.hasCycle(test1.asGraph()));
	}

	@Test
	void test4() {

		ValueGraph<String, Integer> test1 = RailPlanner
				.createPlan("E:\\\\METRO_STATE\\\\ICS340\\\\assignments\\\\P4_RailPlanner\\\\p4_scenario1.csv");

		assertEquals(false, Graphs.hasCycle(test1.asGraph()));
	}

	@Test
	void test5() {

		ValueGraph<String, Integer> test1 = RailPlanner
				.createPlan("E:\\\\METRO_STATE\\\\ICS340\\\\assignments\\\\P4_RailPlanner\\\\p4_scenario4.csv");

		assertEquals(false, Graphs.hasCycle(test1.asGraph()));
	}

	@Test
	void test6() {
		MutableValueGraph<String, Integer> test = ValueGraphBuilder.undirected().allowsSelfLoops(false).build();
		test.putEdgeValue("a", "b", 4);
		test.putEdgeValue("b", "c", 8);
		test.putEdgeValue("i", "c", 2);
		test.putEdgeValue("c", "d", 7);
		test.putEdgeValue("h", "g", 1);
		test.putEdgeValue("g", "f", 2);
		test.putEdgeValue("c", "f", 4);
		test.putEdgeValue("d", "e", 9);
		int test1 = RailPlanner
				.createPlan("E:\\\\METRO_STATE\\\\ICS340\\\\assignments\\\\P4_RailPlanner\\\\p4_scenario4.csv").edges()
				.size();
		int test2 = test.edges().size();
		assertEquals(test1, test2);
	}

	@Test
	void test7() {

		ValueGraph<String, Integer> test1 = RailPlanner
				.createPlan("E:\\\\METRO_STATE\\\\ICS340\\\\assignments\\\\P4_RailPlanner\\\\p4_scenario5.csv");
		;

		// Test shows that the program, if sees the same two nodes with a different edge
		// value on the list, will take the largest edge value.

		assertEquals(false, Graphs.hasCycle(test1.asGraph()));
	}

	@Test
	void test8() {

		ValueGraph<String, Integer> test2 = RailPlanner
				.createPlan("E:\\\\METRO_STATE\\\\ICS340\\\\assignments\\\\P4_RailPlanner\\\\p4_scenario6.csv");
		

		// Duplicate values are removed, largest edge value is taken.

		assertEquals(false, Graphs.hasCycle(test2.asGraph()));
	}

}
