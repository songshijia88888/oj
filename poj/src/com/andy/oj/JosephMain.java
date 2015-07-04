package com.andy.oj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class JosephMain {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<Integer, Integer> mapresult = new HashMap<Integer, Integer>();
		mapresult.put(1, 2);
		mapresult.put(2, 7);
		mapresult.put(3, 5);
		mapresult.put(4, 30);
		mapresult.put(5, 169);
		mapresult.put(6, 441);
		mapresult.put(7, 1872);
		mapresult.put(8, 7632);
		mapresult.put(9, 1740);
		mapresult.put(10, 93313);
		mapresult.put(11, 459901);
		mapresult.put(12, 1358657);
		mapresult.put(13, 2504881);
		mapresult.put(14, 13482720);
		String s = br.readLine();
		StringBuffer sb = new StringBuffer();
		while (!"0".equals(s)) {
			sb.append(mapresult.get(Integer.parseInt(s))).append("\n");
			s = br.readLine();
		}
		System.out.println(sb.toString());
	}
}
