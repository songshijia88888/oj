package com.andy.oj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class JuryCompromise2 {
	private static int min = 0;
	private static int max = 0;
	private static int dj = 0;
	private static int pj = 0;
	private static List<Juror> jurors = new LinkedList<Juror>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int round = 0;
		
		while (!"0 0".equals(s)) {
			jurors.clear();
			String[] ss = s.split(" ");
			int totall = Integer.parseInt(ss[0]);
			int target = Integer.parseInt(ss[1]);
			int[] indexs = new int[target];
			int[] result = new int[target];
			int count2 = 0;
			while (count2 < totall) {
				s = br.readLine();
				ss = s.split(" ");
				count2++;
				jurors.add(new Juror(Integer.parseInt(ss[0]), Integer
						.parseInt(ss[1])));

			}

			int min2 = 20 * 20 + 17;
			int max2 = 0;

			int count = 0;
			for (int i = 0; i < totall - target + 1; i++) {
				int sp = i;
				for (int j = 0; j < target - 1; j++) {
					indexs[j] = sp;
					sp++;
				}
				for (int j = sp; j < totall; j++) {
					indexs[target - 1] = j;
					System.out.println("---------------Round:" + count + "---------------");
					sumDjPj(indexs);
					if (min < min2) {
						for (int k = 0; k < target; k++) {
							result[k] = indexs[k];
						}
						min2 = min;
					} else if (min == min2) {
						if (max > max2) {
							for (int k = 0; k < target; k++) {
								result[k] = indexs[k];
							}
							max2 = max;
						}
					}
				}
			}
			sumDjPj(result);
			System.out.println("Jury #" + (round + 1) + "\n" + "Best jury has value " + pj + " for prosecution and value " + dj + " for defence:");
			for(int i = 0; i < result.length; i++){
				System.out.print(" " + (result[i] + 1));
			}
			System.out.println("\n");
			s = br.readLine();
			round++;
		}
	}

	public static void sumDjPj(int[] indexs) {
		int dj = 0;
		int pj = 0;
		for (int i = 0; i < indexs.length; i++) {
			System.out.print(" juror:" + indexs[i] + jurors.get(indexs[i]));
			dj = dj + jurors.get(indexs[i]).d;
			pj = pj + jurors.get(indexs[i]).p;
		}
		System.out.println("");

		min = Math.abs(dj - pj);
		max = dj + pj;
		JuryCompromise2.dj = dj;
		JuryCompromise2.pj = pj;
		System.out.println("dj:" + dj + " pj:" + pj);
		System.out.println("max:" + max + " min:" + min);
	}
}

class Juror {
	int d;
	int p;

	public Juror(int p, int d) {
		this.d = d;
		this.p = p;
	}
	@Override
	public String toString() {
		return "(" + p + "," + d + ")";
	}
}
