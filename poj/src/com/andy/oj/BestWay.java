package com.andy.oj;

public class BestWay {
	//private int[] target = {3, 5, 8, 9, 17, 26};
	private int[] target = new int[]{4, 5, 10};
	private int stopCount = 1;
	private int[] path = new int[stopCount];
	private int[] times = new int[target.length];
	private int pathCount = 0;
	
	public int[] getPath() {
		return path;
	}

	public void setPath(int[] path) {
		this.path = path;
	}

	public BestWay(){
		
	}
	
	public int[] getTarget() {
		return target;
	}

	public void setTarget(int[] target) {
		this.target = target;
	}

	public BestWay(int[] target, int stopCount){
		this.target = target;
		this.stopCount = stopCount;
		this.path = new int[stopCount];
	}
	
	public boolean isOK(int[] times){
		int max = times[times.length - 1];
		for(int i = 0; i < times.length - 1; i++){
			if(times[i] >= max){
				return false;
			}
		}
		
		return true;
	}
	
	public int timeCalculator(int floor, int elevator, int hasStoppedCount){
		if(floor == target[target.length-1]){
			return 10 * stopCount + (floor -1 ) * 4;
		}
		else{
			int step = floor - elevator;
			if(step < 0){
				step = -1 * step;
		}
			
		return 10 * hasStoppedCount + (elevator - 1) * 4 + step * 20;
		}
	}
	
	
	public void stopPath(int base, int startPoint, int length){
		//System.out.println("base:" + base + ", startPoint:" + startPoint + ", length:" + length);
		int endPoint = base + length - stopCount + pathCount + 1;
		if(endPoint > target.length){
			endPoint = target.length;
		}
		for (int i = startPoint; i < endPoint; i++) {

			path[pathCount] = i;
			pathCount++;

			// System.out.println("pathCount:" + pathCount);

			if (pathCount < stopCount) {
				stopPath(i, i + 1, target.length - i - 1);
			} else {
				System.out.print("order:");
				for (int j = 0; j < path.length; j++) {
					System.out.print(path[j] + " ");
				}
				System.out.print("floor:");
				
				int maxTime = timeCalculator(target[target.length-1], -1, -1);
				times[target.length -1] = maxTime;
				for(int m = 0; m < target.length; m++){
					times[m] = maxTime;
				}
//				System.out.println("maxTime:" + maxTime);
//				System.out.println("times[target.length -1]:" + times[target.length -1]);
				for (int j = 0; j < path.length; j++) {
					System.out.print(target[path[j]] + " ");
					//循环计算各楼层到达时间，昨到最优解
					for(int k = 0; k < target.length - 1 ; k++){
						int time = timeCalculator(target[k], target[path[j]], j);
						//times[k] = time;
						if(time < times[k]){
							times[k] = time;
						}
//						else{
//							System.out.println("");
//							System.out.print(path[j] + " breaked!");
//							System.out.println("");
//							break;
//						}
					}
				}
				System.out.print("Time:");
				for(int k = 0; k < target.length ; k++){
					System.out.print(times[k] + " ");
				}
				System.out.println("");
				pathCount--;
			}
		}
		pathCount--;
	}
	
	
	public void getSolution(){
		while(!isOK(times)){
			stopPath(0, 0, target.length - 1);
			stopCount++;
			pathCount = 0;
			path = new int[stopCount];
		}
	}
}
