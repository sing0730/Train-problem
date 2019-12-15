/**
 * 
 */
package com.abn.javatest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


/**
 * @author sing
 *
 */
public class RailroadServices {

	public static void main(String[] args) throws Exception {

		if (args.length == 0) {
			args = new String[9];
			args[0] = "AB5";
			args[1] = "BC4";
			args[2] = "CD8";
			args[3] = "DC8";
			args[4] = "DE6";
			args[5] = "AD5";
			args[6] = "CE2";
			args[7] = "EB3";
			args[8] = "AE7";	
		}
		Graph graph = new Graph();
		for (int i = 0 ; i < args.length ; i++) {
				char[] charArray = args[i].toCharArray();
				int distance = Integer.parseInt(Character.toString(charArray[2]));
				Town starttown = graph.getTown(Character.toString(charArray[0]));
				Town endtown = graph.getTown(Character.toString(charArray[1]));
				starttown.addRouteInTown(new Route(starttown,endtown,distance));
				graph.addTown(starttown);
		}
		
		graph.printGraph();
//		 Town C = graph.getTown("C");
//		 Town D = graph.getTown("D");
//		 Town A = graph.getTown("A");
//		 Town E = graph.getTown("E");
//		 Town B = graph.getTown("B");
//		List<Town> towns = new ArrayList<>();
//		towns.add(A);
//		towns.add(E);
//		towns.add(D);
//		
//		System.out.println(graph.findTotalDistance(towns));
//		
//		System.out.println(graph.findNumOfRouteWithMaxStop(A, E, 3));
//
//		
	}

}
