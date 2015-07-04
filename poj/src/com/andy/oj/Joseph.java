package com.andy.oj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Joseph {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<Integer, Integer> mapresult = new HashMap<Integer, Integer>();
		StringBuffer sb = new StringBuffer();
		
		String s = br.readLine();
		int k = 0;
		int m = 0;
		int index = 0;
		while (!"0".equals(s)) {
			k = Integer.parseInt(s);
			if (mapresult.containsKey(k)) {
				sb.append(mapresult.get(k)).append("\n");
				s = br.readLine();
				continue;
			}
			
			m = k;
			index = 0;
			int size = 2 * k;
			int varysize = 2 * k;
			
			while (varysize > k) {
				index = (m + index) % varysize;
				if (index > k - 1) {
					varysize--;
				} else {
					m++;
					index = 0;
					varysize = size;
				}
			}
			sb.append(m + 1).append("\n");
			mapresult.put(k, m + 1);
			s = br.readLine();
		}
		System.out.println(sb.toString());
	}
}
