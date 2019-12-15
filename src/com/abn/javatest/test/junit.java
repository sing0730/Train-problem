package com.abn.javatest.test;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.abn.javatest.Graph;
import com.abn.javatest.Route;
import com.abn.javatest.Town;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class junit {
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	Graph graph = new Graph();
	private Town A;
	private Town B;
	private Town C;
	private Town D;
	private Town E;

	@Before
	public void init() {
		String[] args = new String[9];
		args[0] = "AB5";
		args[1] = "BC4";
		args[2] = "CD8";
		args[3] = "DC8";
		args[4] = "DE6";
		args[5] = "AD5";
		args[6] = "CE2";
		args[7] = "EB3";
		args[8] = "AE7";

		for (int i = 0; i < args.length; i++) {
			char[] charArray = args[i].toCharArray();
			int distance = Integer.parseInt(Character.toString(charArray[2]));
			Town starttown = graph.getTown(Character.toString(charArray[0]));
			Town endtown = graph.getTown(Character.toString(charArray[1]));
			starttown.addRouteInTown(new Route(starttown, endtown, distance));
			graph.addTown(starttown);
		}
		A = graph.getTown("A");
		B = graph.getTown("B");
		C = graph.getTown("C");
		D = graph.getTown("D");
		E = graph.getTown("E");
	}

	@Test
	public void Output1() throws Exception {
		List<Town> towns = new ArrayList<>();
		towns.add(A);
		towns.add(B);
		towns.add(C);

		assertEquals(9, graph.findTotalDistance(towns));
	}
	
	@Test
	public void Output2() throws Exception {
		List<Town> towns = new ArrayList<>();
		towns.add(A);
		towns.add(D);


		assertEquals(5, graph.findTotalDistance(towns));
	}
	
	@Test
	public void Output3() throws Exception  {
		List<Town> towns = new ArrayList<>();
		towns.add(A);
		towns.add(D);
		towns.add(C);

		assertEquals(13, graph.findTotalDistance(towns));
	}
	
	@Test
	public void Output4() throws Exception {
		List<Town> towns = new ArrayList<>();
		towns.add(A);
		towns.add(E);
		towns.add(B);
		towns.add(C);
		towns.add(D);
		assertEquals(22, graph.findTotalDistance(towns));
	}
	
	@Test
	public void Output5() throws Exception {
		List<Town> towns = new ArrayList<>();
		towns.add(A);
		towns.add(E);
		towns.add(D);
	    exceptionRule.expectMessage("NO SUCH ROUTE");
	    graph.findTotalDistance(towns);
	}
	
	@Test
	public void Output6() {
		assertEquals(2, graph.findNumOfRouteWithMaxStop(C, C, 3));
	}
	
	@Test
	public void Output7() {
		assertEquals(3, graph.findNumOfRouteWithExactStop(A, C, 4));
	}
	
	@Test
	public void Output8() {
		assertEquals(9, graph.findShortestRoute(A, C));
	}
	
	@Test
	public void Output9() {
		assertEquals(9, graph.findShortestRoute(B, B));
	}
	
	@Test
	public void Output10() {
		assertEquals(7,graph.findAllRouteWithLimit(C,C,30));
	}

}