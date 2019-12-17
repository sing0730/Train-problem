package com.abn.javatest;

import java.util.*;
import java.util.Map.Entry;

public class Graph {
	private HashMap<String, Town> towns = new HashMap<String, Town>();

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

	/*
	 * This method is to find the number of route with max stop. will reture the number of route when current stop is equal to exact stop
	 */
	
	private int findNumOfRouteMethodWithExactStop(Town startTown, Town endTown, int maxStop, int currentStop) {
		int numOfRoute = 0;
		if (towns.containsKey(startTown.getTownName()) && towns.containsKey(endTown.getTownName())) {
			if (maxStop == 0) {
				return maxStop;
			}
			HashMap<String, Route> routes = this.towns.get(startTown.getTownName()).getRouteInTown();

			for (Entry<String, Route> route : routes.entrySet()) {
				if ((!route.getValue().getEndTown().getTownName().equals(endTown.getTownName())
						&& currentStop < maxStop) || currentStop < maxStop) {
					numOfRoute += findNumOfRouteMethodWithExactStop(route.getValue().getEndTown(), endTown, maxStop,
							currentStop + 1);

				}
				if ((route.getValue().getEndTown().getTownName().equals(endTown.getTownName())
						&& currentStop == maxStop)) {
					numOfRoute++;

				}
			}

		} else {
			System.out.println("NO SUCH ROUTE");
			System.exit(0);
		}
		return numOfRoute;
	}
	
	/*
	 * This method is to find the number of route with max stop. will reture the number of route when current stop is equal to max stop
	 */

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
				if (!route.getValue().getEndTown().getTownName().equals(endTown.getTownName())) {

					numOfRoute += findNumOfRouteMethodWithMaxStop(route.getValue().getEndTown(), endTown, maxStop,
							currentStop + 1);

				} else {
					numOfRoute++;
				}
			}

		} else {
			System.out.println("NO SUCH ROUTE");
			System.exit(0);
		}
		return numOfRoute;
	}

	public int findShortestRoute(Town startTown, Town endTown) {
		return findShortestRouteMethod(startTown, endTown, 0, 0);
	}

	/*
	 * This method is to find the shortest route. The method will set every current
	 * town to be visited to avoid the endless route. It will compare the current
	 * distance with lastest shortest distance to get the shortest distance.
	 */
	private int findShortestRouteMethod(Town startTown, Town endTown, int distance, int sdistance) {
		// set current down to visited
		startTown.setVistited(true);
		if (towns.containsKey(startTown.getTownName()) && towns.containsKey(endTown.getTownName())) {
			// Get all route in current town in hashmap
			HashMap<String, Route> routes = this.towns.get(startTown.getTownName()).getRouteInTown();
			for (Entry<String, Route> route : routes.entrySet()) {

				// add the distance if find the lastest town or non visited town
				if (route.getValue().getEndTown().getTownName().equals(endTown.getTownName())
						|| !route.getValue().getEndTown().getVistited()) {
					distance += route.getValue().getDistance();
				}

				// check if current town is the last town
				if (route.getValue().getEndTown().getTownName().equals(endTown.getTownName())) {
					// check the current distance is smaller than shortest distance found before
					if (distance < sdistance || sdistance == 0) {
						sdistance = distance;
					}
					// reset the town to non visit town
					startTown.setVistited(false);
					// return the shortest distance
					return sdistance;
					// if current town is non visited
				} else if (!route.getValue().getEndTown().getVistited()) {
					// find the distance in next town
					sdistance = findShortestRouteMethod(route.getValue().getEndTown(), endTown, distance, sdistance);
					// Reset the distance to pervious before go in next of the loop
					distance -= route.getValue().getDistance();
				}

			}
		}
		startTown.setVistited(false);
		return sdistance;

	}

	public int findAllRouteWithLimit(Town startTown, Town endTown, int maxDistance) {
		return findAllRouteWithLimitMethod(startTown, endTown, maxDistance, 0);
	}

	/*
	 * This method is to find the total number of route with a max distance. It will keep finding the route of next town if the current distance is smaller than max distance.
	 */
	private int findAllRouteWithLimitMethod(Town startTown, Town endTown, int maxDistance, int currentDistance) {
		int numOfRoute = 0;
		if (towns.containsKey(startTown.getTownName()) && towns.containsKey(endTown.getTownName())) {
			// Get all route in current town in hashmap
			HashMap<String, Route> routes = this.towns.get(startTown.getTownName()).getRouteInTown();
			for (Entry<String, Route> route : routes.entrySet()) {
				// add the distance of current route to current distance
				currentDistance += route.getValue().getDistance();
				// check if still smaller than max distance
				if (currentDistance < maxDistance) {
					// check if the last down
					if (!route.getValue().getEndTown().getTownName().equals(endTown.getTownName())) {
						// keep finding route of next town
						numOfRoute += findAllRouteWithLimitMethod(route.getValue().getEndTown(), endTown, maxDistance,
								currentDistance);
						// Reset the distance to pervious before go in next of the loop
						currentDistance -= route.getValue().getDistance();
					} else {
						// the total number of route + 1 if it is the last down and current distance is smaller than max distance
						numOfRoute++;
						numOfRoute += findAllRouteWithLimitMethod(route.getValue().getEndTown(), endTown, maxDistance,
								currentDistance);
					}
				} else {
					currentDistance -= route.getValue().getDistance();
				}
			}
		}
		return numOfRoute;
	}
}