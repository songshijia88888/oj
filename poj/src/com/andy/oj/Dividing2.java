package com.andy.oj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Dividing2 {
	public static void main(String[] args) throws IOException, InterruptedException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int sum = 0;
		String s = br.readLine();
		int count = 1;
		while (!"0 0 0 0 0 0".equals(s)) {
			String[] ss = s.split(" ");
			for (int i = 0; i < ss.length; i++) {
				int temp = Integer.parseInt(ss[i]);
				if (temp > 0) {
					map.put(i + 1, temp);
					sum = sum + (i + 1) * temp;
//					System.out.println(map);
				}
			}

			if (sum % 2 == 0) {
				int tempsum = sum / 2;
				int part = 6;
				while (tempsum > 0) {
					if (part == 0) {
						part = 6;
					}
					System.out.println("tempsum:" + tempsum + ", part:" + part);
					if (map.containsKey(part) &&  map.get(part) > 0) {
						Thread.sleep(500);
						tempsum = tempsum - part;
						map.put(part, map.get(part) - 1);
						System.out.println("\ttempsum:" + tempsum + ", part:" + part);
//						if(tempsum < 0){
//							tempsum = tempsum + part;
//							System.out.println("\t\ttempsum:" + tempsum + ", part:" + part);
//						}
//						else{
//							map.put(part, map.get(part) - 1);
//							System.out.println("\t\t\ttempsum:" + tempsum + ", part:" + part);
//						}
					}
					part--;
				}
				System.out.println("tempsum:" + tempsum + ", part:" + part);
				System.out.println(map);
				
				int tempsum2 = 0;
				for (int i = 1; i < 7; i++) {
					if (map.containsKey(i)) {
						tempsum2 = tempsum2 + map.get(i) * i;
					}

				}
				//tempsum2 = tempsum2 - tempsum;
//				System.out.println(map);
//				System.out.println("tempSum2:" + tempsum2 + ",    sum/2:" + sum / 2);

				if (tempsum2 == sum / 2) {
					sb.append("Collection #").append(count)
							.append(":\nCan be divided.").append("\n");
				} else {
					sb.append("Collection #").append(count)
							.append(":\nCan't be divided.").append("\n");
				}
			} else {
				sb.append("Collection #").append(count)
						.append(":\nCan't be divided.").append("\n");
			}
			map.clear();
			sum = 0;
			s = br.readLine();
			count++;
		}

		System.out.println(sb.toString());
	}

}
