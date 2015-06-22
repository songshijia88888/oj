package com.andy.oj;

public class BestSolution {
	private int[] floors;
	private int n;
	private int stopCount;
	
	private int[] path;
	private BestWay bw;
	
	public BestSolution(int[] floors){
		this.floors = floors;
		this.n = floors.length;
		this.stopCount = 1;
		this.bw = new BestWay(floors, stopCount);
	}
	
	public int hasStoppedCount(int elevator){
		path = bw.getPath();
		int whichone = 0;
		for(int j = 0; j < n; j++){
			if(floors[j] == elevator){
				whichone = j;
				break;
			}
		}
		int hasStoppedNumber = 0;
		for(int i = 0; i < stopCount; i++){
			if(path[i] == whichone){
				hasStoppedNumber = i;
			}
		}
		
		return hasStoppedNumber;
	}
	
	public int timeCalculator(int floor, int elevator){
		if(floor == floors[n-1]){
			return 10 * stopCount + (floor -1 ) * 4;
		}
		else{
			int step = floor - elevator;
			if(step < 0){
				step = -1 * step;
			}
			return 10 * hasStoppedCount(elevator) + (elevator - 1) * 4 + step * 20;
		}
	}
}
