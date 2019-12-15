package com.abn.javatest;

import java.util.*;
import java.util.Map.Entry;

public class Graph {
	private HashMap<String, Town> towns = new HashMap<String, Town>();
	private ArrayList<Integer> distanceAL = new ArrayList<Integer>();

	public void addTown(Town town) {
		this.towns.put(town.getTownName(), town);
	}

	public void printGraph() {
		if (towns.isEmpty()) {
			System.out.println(getClass().getName() + ": No Town added");
		} else {
			for (Entry<String, Town> entry : towns.entrySet()) {
				System.out.println("========================");
				System.out.println("Town name :" + entry.getKey());
				System.out.println("Route :");
				for (Entry<String, Route> RouteEntry : entry.getValue().getRouteInTown().entrySet()) {
					System.out.println(RouteEntry.getValue().getStartTown().getTownName() + " -> "
							+ RouteEntry.getValue().getEndTown().getTownName() + " ,"
							+ RouteEntry.getValue().getDistance());
				}
				System.out.println("========================");

			}
		}
	}

	public HashMap<String, Town> getTowns() {
		return this.towns;
	}

	public Town getTown(String townname) {
		if (towns.containsKey(townname)) {
			return towns.get(townname);

		}
		return new Town(townname);
	}

	public int findTotalDistance(List<Town> townlist) throws Exception {
		int totalDistance = 0;
		int numberOfTown = townlist.size();
		for (int i = 0; i < numberOfTown - 1; i++) {
			String routename = townlist.get(i).getTownName() + "" + townlist.get(i + 1).getTownName();
			if (!townlist.get(i).getRouteInTown().containsKey(routename)) {
				System.out.println("NO SUCH ROUTE");
				throw new Exception("NO SUCH ROUTE");

			}
			totalDistance += townlist.get(i).getRouteInTown().get(routename).getDistance();
		}
		return totalDistance;
	}

	public int findNumOfRouteWithMaxStop(Town startTown, Town endTown, int maxStop) {
		return findNumOfRouteMethodWithMaxStop(startTown, endTown, maxStop, 0);

	}

	public int findNumOfRouteWithExactStop(Town startTown, Town endTown, int maxStop) {
		return findNumOfRouteMethodWithExactStop(startTown, endTown, maxStop, 0);

	}

	private int findNumOfRouteMethodWithExactStop(Town startTown, Town endTown, int maxStop, int currentStop) {
		int numOfRoute = 0;
		if (towns.containsKey(startTown.getTownName()) && towns.containsKey(endTown.getTownName())) {
			if (maxStop == 0) {
				return maxStop;
			}
			HashMap<String, Route> routes = this.towns.get(startTown.getTownName()).getRouteInTown();

			for (Entry<String, Route> route : routes.entrySet()) {
				// System.out.print(route.getValue().getStartTown().getTownName());
				// System.out.print("(Current Stop: " + currentStop + ")");
				// System.out.print(" --> ");
				if ((!route.getValue().getEndTown().getTownName().equals(endTown.getTownName())
						&& currentStop < maxStop) || currentStop < maxStop) {
					numOfRoute += findNumOfRouteMethodWithExactStop(route.getValue().getEndTown(), endTown, maxStop,
							currentStop + 1);

				}
				if ((route.getValue().getEndTown().getTownName().equals(endTown.getTownName())
						&& currentStop == maxStop)) {

					// System.out.println(route.getValue().getEndTown().getTownName());
					// System.out.println("Current Stop: " + currentStop);
					numOfRoute++;

				}
			}

		} else {
			System.out.println("NO SUCH ROUTE");
			System.exit(0);
		}
		// System.out.println();
		// System.out.println("numOfRoute: " + numOfRoute);
		return numOfRoute;
	}

	private int findNumOfRouteMethodWithMaxStop(Town startTown, Town endTown, int maxStop, int currentStop) {
		int numOfRoute = 0;

		if (towns.containsKey(startTown.getTownName()) && towns.containsKey(endTown.getTownName())) {

			if (maxStop == 0) {
				return maxStop;
			} else if (currentStop == maxStop) {
				return numOfRoute;
			}

			HashMap<String, Route> routes = this.towns.get(startTown.getTownName()).getRouteInTown();

			for (Entry<String, Route> route : routes.entrySet()) {
				// System.out.print(route.getValue().getStartTown().getTownName());
				// System.out.print(" --> ");
				if (!route.getValue().getEndTown().getTownName().equals(endTown.getTownName())) {

					numOfRoute += findNumOfRouteMethodWithMaxStop(route.getValue().getEndTown(), endTown, maxStop,
							currentStop + 1);

				} else {
					// System.out.print(route.getValue().getEndTown().getTownName());
					// System.out.println("numOfRoute: " + numOfRoute);
					// System.out.println("Current Stop: " + currentStop);
					numOfRoute++;
				}
			}

		} else {
			System.out.println("NO SUCH ROUTE");
			System.exit(0);
		}
		// System.out.println();
		// System.out.println("return: " + numOfRoute);
		return numOfRoute;
	}

	public int findShortestRoute(Town startTown, Town endTown) {
        return findShortestRouteMethod(startTown, endTown, 0, 0);
	}

	public int findShortestRouteMethod(Town startTown, Town endTown, int distance, int sdistance) {
		startTown.setVistited(true);
//		System.out.println("Loop: " + i);
//		System.out.println("Start Town: " + startTown.getTownName());
//		System.out.println("End Town: " + endTown.getTownName());
//		System.out.println("distance: " + distance);
//		System.out.println("sdistance: " + sdistance);
		if (towns.containsKey(startTown.getTownName()) && towns.containsKey(endTown.getTownName())) {

			HashMap<String, Route> routes = this.towns.get(startTown.getTownName()).getRouteInTown();
			// System.out.println(routes.size());
			for (Entry<String, Route> route : routes.entrySet()) {
//				System.out.print(route.getValue().getStartTown().getTownName());
//				System.out.print(" --> ");
				if (route.getValue().getEndTown().getTownName().equals(endTown.getTownName())
						|| !route.getValue().getEndTown().getVistited()) {
					distance += route.getValue().getDistance();
				}
//				System.out.print(route.getValue().getEndTown().getTownName());
//
//				System.out.println();
				if (route.getValue().getEndTown().getTownName().equals(endTown.getTownName())) {
					System.out.println();
					if (distance < sdistance || sdistance == 0) {
						sdistance = distance;
					}
					startTown.setVistited(false);
					return sdistance;
				} else if (!route.getValue().getEndTown().getVistited()) {
					sdistance = findShortestRouteMethod(route.getValue().getEndTown(), endTown, distance, sdistance);
					distance -= route.getValue().getDistance();
				}

			}
		}
		startTown.setVistited(false);
		return sdistance;

	}

	public int findAllRouteWithLimit (Town startTown, Town endTown, int maxDistance) {
		return findAllRouteWithLimitMethod( startTown,  endTown, maxDistance, 0) ;
	}
	
	private int findAllRouteWithLimitMethod(Town startTown, Town endTown, int maxDistance, int currentDistance) {
		int numOfRoute = 0;
		if (towns.containsKey(startTown.getTownName()) && towns.containsKey(endTown.getTownName())) {
			HashMap<String, Route> routes = this.towns.get(startTown.getTownName()).getRouteInTown();
			for (Entry<String, Route> route : routes.entrySet()) {
				currentDistance += route.getValue().getDistance();
				if (currentDistance < maxDistance){
//					System.out.print(route.getValue().getStartTown().getTownName());
//					System.out.print(" --> ");
					if (!route.getValue().getEndTown().getTownName().equals(endTown.getTownName())) {
						numOfRoute += findAllRouteWithLimitMethod(route.getValue().getEndTown(), endTown, maxDistance,
								currentDistance);
						currentDistance -= route.getValue().getDistance();
					} else {
//						System.out.println(route.getValue().getEndTown().getTownName());
//						System.out.println("TotalDistance: " + currentDistance);
						numOfRoute++;
						numOfRoute += findAllRouteWithLimitMethod(route.getValue().getEndTown(), endTown, maxDistance,
								currentDistance);
					}
				}else {
					currentDistance -= route.getValue().getDistance();
				}
			}
		}
		return numOfRoute;
	}
}