package com.abn.javatest;

import java.util.*;

public class Town {
	private String name;
	private HashMap<String,Route> RouteInTown = new HashMap<>();
	private boolean vistited = false ;

	public Town(String name) {
		this.name = name;
	}
	
	public void addRouteInTown(Route route) {
		String routeName = route.getStartTown().getTownName() + "" + route.getEndTown().getTownName();
		if (RouteInTown.containsKey(routeName)) {
			System.out.println(getClass().getName() + ": A route will never appear more than once");
		}else {
			this.RouteInTown.put(routeName, route);
		}
	}
	
	public HashMap<String,Route> getRouteInTown(){
		return this.RouteInTown;
	}
	

	
	public String getTownName() {
		return this.name;
	}

	public boolean getVistited() {
		return vistited;
	}

	public void setVistited(boolean vistited) {
		this.vistited = vistited;
	}
	
}
