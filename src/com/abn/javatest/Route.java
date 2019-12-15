package com.abn.javatest;

public class Route {
	private Town StartTown;
	private Town EndTown;
	private int Distance;
	
	public Route(Town startTown,Town endTown, int distance){
		this.StartTown = startTown;
		this.EndTown = endTown;
		this.Distance = distance;
	}
	
	public Town getStartTown() {
		return StartTown;
	}

	public Town getEndTown() {
		return EndTown;
	}

	public int getDistance() {
		return Distance;
	}

	
	
}
