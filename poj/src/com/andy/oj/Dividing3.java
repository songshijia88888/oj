package com.andy.oj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Dividing3 {
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
				}
			}

			if (sum % 2 == 0) {
				int tempsum = sum / 2;
				int part = 6;
				while (tempsum > 0) {
					if(part < 0){
						break;
					}
					if (map.containsKey(part)) {
						int nmax = tempsum / part;
						if (nmax >= map.get(part)) {
							nmax = map.remove(part);
						} else {
							map.put(part, map.get(part) - nmax);
						}
						tempsum = tempsum - part * nmax;
					}
					part--;
				}
				int tempsum2 = 0;
				for (int i = 1; i < 7; i++) {
					if (map.containsKey(i)) {
						tempsum2 = tempsum2 + map.get(i) * i;
					}

				}

				if (tempsum2 == sum / 2 && tempsum == 0) {
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
			sb.append("\n");
			count++;
		}

		System.out.println(sb.toString());
	}
}
