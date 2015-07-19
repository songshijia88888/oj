package com.andy.oj;

import java.util.LinkedList;
import java.util.List;

public class JuryCompromise {
	private static int min = 0;
	private static int max = 0;
	private static List<Juror2> jurors = new LinkedList<Juror2>();
	
	public static void main(String[] args){
		int totall = 4;
		int target = 2;
		jurors.add(new Juror2(1, 2));
		jurors.add(new Juror2(2, 3));
		jurors.add(new Juror2(4, 1));
		jurors.add(new Juror2(6, 2));
		int[] indexs = new int[target];
		int[] result = new int[target];
		
		int min2 = 20 * 20 + 17;
		int max2 = 0;
		
		int count = 0;
		for(int i = 0; i < totall - target + 1; i++){
			int sp = i;
			for(int j = 0;  j < target - 1; j++){
				indexs[j] = sp;
				sp++;
			}
			for(int j = sp; j < totall; j++){
				System.out.println("---------Round:" + count + "---------");
				indexs[target - 1] = j;
				sumDjPj(indexs);
				if(min < min2){
					for(int k = 0; k < target; k++){
						result[k] = indexs[k];
					}
					min2 = min;
				}
				else if(min == min2){
					if(max > max2 ){
						for(int k = 0; k < target; k++){
							result[k] = indexs[k];
						}
						max2 = max;
					}
				}
				count++;
			}
		}
		System.out.println("\n\n\nFinally, the jurors:");
		sumDjPj(result);
	}
	
	
	public static void sumDjPj(int[] indexs){
		int dj = 0;
		int pj = 0;
		for(int i = 0; i < indexs.length; i++){
			System.out.print( "juror:" + (indexs[i] + 1) + jurors.get(indexs[i]) + " ");
			dj = dj + jurors.get(indexs[i]).d;
			pj = pj + jurors.get(indexs[i]).p;
		}
		
		min = Math.abs(dj - pj);
		max = dj + pj;
		System.out.print("\nmin:" + min + ", max:" + max + "\n");
		System.out.println("pj:" + pj + ", dj:" + dj);
	}
}

class Juror2{
	int d;
	int p;
	
	public Juror2(int p, int d){
		this.d = d;
		this.p = p;
	}
	@Override
	public String toString() {
		
		return "(" + d + ", " + p + ")";
	}
}